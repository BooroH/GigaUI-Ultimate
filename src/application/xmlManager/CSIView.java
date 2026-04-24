package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManager;
import application.util.ImageManagerV2;

public class CSIView {

	public static void readCSIView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/ComboSequenceIndicator/CSIView.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		if (content.contains("horizontal")) {
			settings.setIsCSIHorizontal(1);
		} else {
			settings.setIsCSIHorizontal(0);
		}

		// 다크모드 컬러
		Matcher m;
		Pattern p;
		p = Pattern.compile("bitmap_gfx=\"../../Customized/gfx/bottombar/[^\"]*_c_(-?\\d+)_(-?\\d+)_(-?\\d+)\\.png\"");
		m = p.matcher(content);

		if (m.find()) {
			int hue = Integer.parseInt(m.group(1));
			int chroma = Integer.parseInt(m.group(2));
			int lightness = Integer.parseInt(m.group(3));

			settings.setCsi_bg_lightness(lightness);
		} else {
			settings.setCsi_bg_lightness(0);
		}

		// 사이즈추출
		p = Pattern.compile("max_size_limit=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);

		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));

			settings.setCsi_size(width);
		}

		// 슬롯bg 알파추출
		p = Pattern.compile("bitmap_gfx\\s*=\\s*\"[^\"]*a(\\d+)");
		m = p.matcher(content);

		if (m.find()) {
			int value = Integer.parseInt(m.group(1));
			settings.setCsi_bg_alpha(value);
		}

		// 하이라이트 알파추출
		settings.setCsi_highlight_alpha(1.0);
		p = Pattern.compile("view_alpha=\"([0-9]*\\.?[0-9]+)\"");
		m = p.matcher(content);

		if (m.find()) {
			double alpha = Double.parseDouble(m.group(1));
			settings.setCsi_highlight_alpha(alpha);
		}

		// 갭
		p = Pattern.compile("layout_borders=\"Rect\\((-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			int first = Integer.parseInt(m.group(1));
			int second = Integer.parseInt(m.group(2));
			int third = Integer.parseInt(m.group(3));
			int fourth = Integer.parseInt(m.group(4));

			int gap = first +second + third + fourth;
			settings.setCsi_gap(gap);
		} else {
			settings.setCsi_gap(0);
		}

	}

	public static void writeCSIView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/ComboSequenceIndicator/CSIView.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		String csi_layout;
		String imgFile;

		// bg 다크모드 컬러
		String chroma_code = "";

		String slot_souceImgDir = customizedDir + "/gfx/bottombar/dir_a" + settings.getCsi_bg_alpha() + ".png";
		String slot_customImgDir = customizedDir + "/gfx/bottombar/dir_a" + settings.getCsi_bg_alpha() + ".png";

		if (settings.getCsi_bg_lightness() == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf(settings.getCsi_bg_lightness());

			slot_customImgDir = customizedDir + "/gfx/bottombar/dir_a" + settings.getCsi_bg_alpha() + chroma_code
					+ ".png";

			ImageManagerV2.process(slot_souceImgDir, slot_customImgDir, 0, 0, settings.getCsi_bg_lightness());
		}

		imgFile = "dir_a" + settings.getCsi_bg_alpha() + chroma_code + ".png";

		content = content.replaceAll("var_img_file", imgFile);

		int size = settings.getCsi_size();
		content = content.replaceAll("var_size", String.valueOf(size));

		// 슬롯마진
		String icon_size;
		if (size > 84) {
			icon_size = String.valueOf(size - 11 * 2);
		} else if (size > 76) {
			icon_size = String.valueOf(size - 10 * 2);
		} else if (size > 68) {
			icon_size = String.valueOf(size - 9 * 2);
		} else if (size > 60) {
			icon_size = String.valueOf(size - 8 * 2);
		} else if (size > 52) {
			icon_size = String.valueOf(size - 7 * 2);
		} else if (size > 42) {
			icon_size = String.valueOf(size - 6 * 2);
		} else if (size > 34) {
			icon_size = String.valueOf(size - 5 * 2);
		} else {
			icon_size = String.valueOf(size - 4 * 2);
		}

		content = content.replaceAll("var_icon_size", icon_size);

		// 하이라이트알파
		String highlightAlpha = "";

		if (settings.getCsi_highlight_alpha() == 1.0) {
			highlightAlpha = "";
		} else {
			highlightAlpha = " view_alpha=\"" + String.valueOf(settings.getCsi_highlight_alpha()) + "\"";
		}

		content = content.replaceAll("var_highlight_alpha", highlightAlpha);

		// 갭, 방향
		int gap = settings.getCsi_gap();
		int x1 = 0;
		int x2 = 0;
		String layout_borders = "";
		String icon_layout_borders = "";
		String end_img = "";
		//String start_img = "";

		if (gap % 2 == 0) {
			x1 = gap / 2;
			x2 = gap / 2;
		} else {
			x1 = gap / 2 - 1;
			x2 = gap / 2;
		}

		if (settings.getIsCSIHorizontal() == 0) {
			csi_layout = new String("vertical");
			if (gap != 0) {
				layout_borders = " layout_borders=\"Rect(0," + String.valueOf(x1) + ",0," + String.valueOf(x2) + ")\"";
				icon_layout_borders = " layout_borders=\"Rect(0,0,0," + String.valueOf(x2) + ")\"";
			}
			if (gap < -1) {
				end_img = "<View view_layout=\"" + csi_layout + "\">\r\n"
						+ "		<BitmapView bitmap_gfx=\"../../Customized/gfx/bottombar/" + imgFile
						+ "\" layout_borders=\"Rect(0," + String.valueOf(-x2-(size+1)) + ",0,0)\" max_size_limit=\"Point("
						+ String.valueOf(size) + "," + String.valueOf(size) + ")\"/>\r\n" + "	</View>";
				/*
				start_img = "<View view_layout=\"" + csi_layout + "\">\r\n"
						+ "		<BitmapView bitmap_gfx=\"../../Customized/gfx/bottombar/" + imgFile
						+ "\" layout_borders=\"Rect(0,0,0,"+String.valueOf(-x1-(size+1))+")\" max_size_limit=\"Point("
						+ String.valueOf(size) + "," + String.valueOf(size) + ")\"/>\r\n" + "	</View>";
				*/
			}
		} else {
			csi_layout = new String("horizontal");
			if (gap != 0) {
				layout_borders = " layout_borders=\"Rect(" + String.valueOf(x1) + ",0," + String.valueOf(x2) + ",0)\"";
				icon_layout_borders = " layout_borders=\"Rect(0,0," + String.valueOf(x2) + ",0)\"";
			}
			if (gap < -1) {
				end_img = "<View view_layout=\"" + csi_layout + "\">\r\n"
						+ "		<BitmapView bitmap_gfx=\"../../Customized/gfx/bottombar/" + imgFile
						+ "\" layout_borders=\"Rect(" + String.valueOf(-x2-(size+1)) + ",0,0,0)\" max_size_limit=\"Point("
						+ String.valueOf(size) + "," + String.valueOf(size) + ")\"/>\r\n" + "	</View>";
				/*
				start_img = "<View view_layout=\"" + csi_layout + "\">\r\n"
						+ "		<BitmapView bitmap_gfx=\"../../Customized/gfx/bottombar/" + imgFile
						+ "\" layout_borders=\"Rect(0,0,"+String.valueOf(-x1-(size+1))+",0)\" max_size_limit=\"Point("
						+ String.valueOf(size) + "," + String.valueOf(size) + ")\"/>\r\n" + "	</View>";
						*/
			}
		}

		content = content.replaceAll("var_view_layout", csi_layout);
		content = content.replaceAll("var_layout_borders", layout_borders);
		content = content.replaceAll("var_icon_layout_borders", icon_layout_borders);
		//content = content.replaceAll("var_start_img", start_img);
		content = content.replaceAll("var_end_img", end_img);

		FileManager.stringToFile(targetDir, content);
	}
}
