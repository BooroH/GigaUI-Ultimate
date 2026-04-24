package application.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import org.w3c.dom.NodeList;

public class ImageManagerV2 {

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
                    int g = (argb >>  8) & 0xFF;
                    int b =  argb        & 0xFF;
                    int rgb = transform(r, g, b, hueShift, chromaAdj, lightAdj);
                    outImg.setRGB(x, y, (a << 24) | (rgb & 0xFFFFFF));
                }
            }

            writePng(outImg, new File(out));
        } catch (IOException e) {
        }
    }

    static int transform(int ri, int gi, int bi, double hueShift, double chromaAdj, double lightAdj) {

        // 파라미터 정규화
        double hue = hueShift / 180.0;  // -1.0 ~ 1.0
        double sat = chromaAdj / 100.0; // -1.0 ~ 1.0
        double lit = lightAdj  / 100.0; // -1.0 ~ 1.0

        // 1. RGB 8bit → 0~1
        double r = ri / 255.0;
        double g = gi / 255.0;
        double b = bi / 255.0;

        // 2. RGB → HSL
        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        double delta = max - min;

        double L = (max + min) / 2.0;
        double H = 0.0;
        double S = 0.0;

        if (delta > 1e-10) {
            S = delta / (1.0 - Math.abs(2.0 * L - 1.0));

            if      (max == r) H = ((g - b) / delta + 6.0) % 6.0 / 6.0;
            else if (max == g) H = ((b - r) / delta + 2.0) / 6.0;
            else               H = ((r - g) / delta + 4.0) / 6.0;
        }

        // 3. GIMP Hue-Saturation 적용 (gimpoperationhuesaturation.c 기준)

        // Hue
        H += hue;
        if (H < 0.0) H += 1.0;
        if (H > 1.0) H -= 1.0;

        // Saturation
        S = Math.max(0.0, Math.min(1.0, S + sat));

        // Lightness: GIMP 소스 공식
        // v < 0  →  L * (v + 1)
        // v >= 0 →  L + v * (1 - L)
        if (lit < 0.0) {
            L = L * (lit + 1.0);
        } else {
            L = L + lit * (1.0 - L);
        }

        // 4. HSL → RGB
        double C = (1.0 - Math.abs(2.0 * L - 1.0)) * S;
        double X = C * (1.0 - Math.abs((H * 6.0) % 2.0 - 1.0));
        double m = L - C / 2.0;

        double ro, go, bo;
        int sector = (int)(H * 6.0);
        switch (sector % 6) {
            case 0:  ro=C; go=X; bo=0; break;
            case 1:  ro=X; go=C; bo=0; break;
            case 2:  ro=0; go=C; bo=X; break;
            case 3:  ro=0; go=X; bo=C; break;
            case 4:  ro=X; go=0; bo=C; break;
            default: ro=C; go=0; bo=X; break;
        }

        // 5. 8bit 변환
        int rOut = clamp255((int) Math.round((ro + m) * 255.0));
        int gOut = clamp255((int) Math.round((go + m) * 255.0));
        int bOut = clamp255((int) Math.round((bo + m) * 255.0));

        return (rOut << 16) | (gOut << 8) | bOut;
    }

    static int clamp255(int v) {
        return v < 0 ? 0 : v > 255 ? 255 : v;
    }
}
