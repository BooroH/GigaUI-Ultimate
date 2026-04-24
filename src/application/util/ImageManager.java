package application.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;

import org.w3c.dom.NodeList;

public class ImageManager {

	private static final double EPSILON = 1e-4;

	/*
	static void writePng(BufferedImage img, File out) throws IOException {
		ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
		ImageWriteParam param = writer.getDefaultWriteParam();

		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(0.0f); // 0.0 = 최대 압축 (GIMP level 9과 동일)

		try (ImageOutputStream ios = ImageIO.createImageOutputStream(out)) {
			writer.setOutput(ios);
			writer.write(null, new javax.imageio.IIOImage(img, null, null), param);
		}
		writer.dispose();
	}*/
	
	static void writePng(BufferedImage img, File out) throws IOException {
	    ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
	    ImageWriteParam param = writer.getDefaultWriteParam();
	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	    param.setCompressionQuality(0.0f);

	    // 메타데이터 완전 제거
	    ImageTypeSpecifier typeSpec = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
	    IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpec, param);
	    String format = metadata.getNativeMetadataFormatName();
	    IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(format);
	    for (String tag : new String[]{"tEXt", "iTXt", "zTXt", "tIME", "pHYs"}) {
	        NodeList nodes = root.getElementsByTagName(tag);
	        for (int i = nodes.getLength() - 1; i >= 0; i--)
	            nodes.item(i).getParentNode().removeChild(nodes.item(i));
	    }
	    metadata.setFromTree(format, root);

	    try (ImageOutputStream ios = ImageIO.createImageOutputStream(out)) {
	        writer.setOutput(ios);
	        writer.write(null, new IIOImage(img, null, metadata), param);
	    }
	    writer.dispose();
	}

	public static void process(String in, String out, double hueShift, double chromaAdj, double lightAdj) {
		try {
			BufferedImage img = ImageIO.read(new File(in));
			int w = img.getWidth(), h = img.getHeight();
			BufferedImage outImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					int argb = img.getRGB(x, y);
					int a = (argb >> 24) & 0xFF;
					int r = (argb >> 16) & 0xFF;
					int g = (argb >> 8) & 0xFF;
					int b = argb & 0xFF;
					int rgb = transform(r, g, b, hueShift, chromaAdj, lightAdj);
					outImg.setRGB(x, y, (a << 24) | (rgb & 0xFFFFFF));
				}
			}

			writePng(outImg, new File(out));
		} catch (IOException e) {
		}
	}

	static int transform(int ri, int gi, int bi, double hueShift, double chromaAdj, double lightAdj) {

		// 1. sRGB 8bit → linear
		double R = srgbToLinear(ri / 255.0);
		double G = srgbToLinear(gi / 255.0);
		double B = srgbToLinear(bi / 255.0);

		// 2. linear sRGB → XYZ D65
		double X = R * 0.4124564 + G * 0.3575761 + B * 0.1804375;
		double Y = R * 0.2126729 + G * 0.7151522 + B * 0.0721750;
		double Z = R * 0.0193339 + G * 0.1191920 + B * 0.9503041;

		// 3. XYZ D65 → XYZ D50 (Bradford chromatic adaptation)
		// babl의 CIE Lab은 D50 white point 사용 (ICC 표준)
		double Xd = 1.0478112 * X + 0.0228866 * Y - 0.0501270 * Z;
		double Yd = 0.0295424 * X + 0.9904844 * Y - 0.0170491 * Z;
		double Zd = -0.0092345 * X + 0.0150436 * Y + 0.7521316 * Z;

		// 4. XYZ D50 → CIE L*a*b* (D50 white point)
		final double Xn = 0.96429, Yn = 1.00000, Zn = 0.82510;
		double fy0 = labF(Yd / Yn);
		double L = 116.0 * fy0 - 16.0;
		double Alab = 500.0 * (labF(Xd / Xn) - fy0);
		double Blab = 200.0 * (fy0 - labF(Zd / Zn));

		// 5. Lab → LCH
		double C = Math.sqrt(Alab * Alab + Blab * Blab);
		double H = Math.toDegrees(Math.atan2(Blab, Alab));

		// 6. GEGL hue-chroma 적용
		L += lightAdj; // clamp 없음 — babl이 변환 후 처리

		if (C > EPSILON) {
			H += hueShift;
			C *= (1.0 + chromaAdj / 100.0);
			if (C < 0.0)
				C = 0.0;
		}

		// 7. H 정규화
		H = H % 360.0;
		if (H < 0.0)
			H += 360.0;

		// 8. LCH → Lab
		double Hrad = Math.toRadians(H);
		Alab = C * Math.cos(Hrad);
		Blab = C * Math.sin(Hrad);

		// 9. Lab → XYZ D50
		double fy2 = (L + 16.0) / 116.0;
		double Xo = Xn * labFInv(fy2 + Alab / 500.0);
		double Yo = Yn * labFInv(fy2);
		double Zo = Zn * labFInv(fy2 - Blab / 200.0);

		// 10. XYZ D50 → linear sRGB (D50용 역행렬)
		double Rl = 3.1338561 * Xo - 1.6168667 * Yo - 0.4906146 * Zo;
		double Gl = -0.9787684 * Xo + 1.9161415 * Yo + 0.0334540 * Zo;
		double Bl = 0.0719453 * Xo - 0.2289914 * Yo + 1.4052427 * Zo;

		// 11. gamut clip
		Rl = clamp01(Rl);
		Gl = clamp01(Gl);
		Bl = clamp01(Bl);

		// 12. linear → sRGB 8bit
		int ro = clamp255((int) Math.round(linearToSrgb(Rl) * 255.0));
		int go = clamp255((int) Math.round(linearToSrgb(Gl) * 255.0));
		int bo = clamp255((int) Math.round(linearToSrgb(Bl) * 255.0));

		return (ro << 16) | (go << 8) | bo;
	}

	static double labF(double t) {
		final double d = 6.0 / 29.0;
		return t > d * d * d ? Math.cbrt(t) : t / (3.0 * d * d) + 4.0 / 29.0;
	}

	static double labFInv(double t) {
		final double d = 6.0 / 29.0;
		return t > d ? t * t * t : 3.0 * d * d * (t - 4.0 / 29.0);
	}

	static double srgbToLinear(double v) {
		return v <= 0.04045 ? v / 12.92 : Math.pow((v + 0.055) / 1.055, 2.4);
	}

	static double linearToSrgb(double v) {
		return v <= 0.0031308 ? v * 12.92 : 1.055 * Math.pow(v, 1.0 / 2.4) - 0.055;
	}

	static double clamp01(double v) {
		return v < 0.0 ? 0.0 : v > 1.0 ? 1.0 : v;
	}

	static int clamp255(int v) {
		return v < 0 ? 0 : v > 255 ? 255 : v;
	}

}
