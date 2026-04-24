package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManager;
import application.util.ImageManagerV2;
import application.util.MarginCalculator;

public class CommandTimerBar {

	public static void readCommandTimerBar(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/CommandTimerBar.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		// 텍스트위치
		if (content.contains("top_")) {
			settings.setCastBar_text_location(1);
		} else if (content.contains("mid_")) {
			settings.setCastBar_text_location(2);
		} else if (content.contains("bot_")) {
			settings.setCastBar_text_location(3);
		}

		// 텍스트 y
		p = Pattern.compile("layout_borders=\"Rect\\((-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			int first = Integer.parseInt(m.group(1));
			int second = Integer.parseInt(m.group(2));
			int third = Integer.parseInt(m.group(3));
			int fourth = Integer.parseInt(m.group(4));

			int text_y = fourth - second;
			settings.setCastBar_text_y(text_y);
		} else {
			settings.setCastBar_text_y(0);
		}

		// 사이즈
		p = Pattern.compile("max_size_limit=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			settings.setCastBar_width(width);
			int height = Integer.parseInt(m.group(2));
			settings.setCastBar_height(height);
		} else {
			settings.setCastBar_width(345);
			settings.setCastBar_height(55);
		}

		// 컬러
		p = Pattern.compile("fg_gfx=\"[^\"]*c_(-?\\d+)_(-?\\d+)_(-?\\d+)\\.png\"");
		m = p.matcher(content);

		if (m.find()) {
			double hue = Double.parseDouble(m.group(1));
			double chroma = Double.parseDouble(m.group(2));
			double lightness = Double.parseDouble(m.group(3));

			settings.setCastBar_hue(hue);
			settings.setCastBar_chroma(chroma);
			settings.setCastBar_lightness(lightness);
		} else {
			if (content.contains("_white")) {
				settings.setCastBar_hue(181);
				settings.setCastBar_chroma(0);
				settings.setCastBar_lightness(0);
			} else {
				settings.setCastBar_hue(0);
				settings.setCastBar_chroma(0);
				settings.setCastBar_lightness(0);
			}
		}

		// bg 컬러
		p = Pattern.compile("bg_gfx=\"[^\"]*c_(-?\\d+)_(-?\\d+)_(-?\\d+)\\.png\"");
		m = p.matcher(content);

		if (m.find()) {
			int hue = Integer.parseInt(m.group(1));
			int chroma = Integer.parseInt(m.group(2));
			int lightness = Integer.parseInt(m.group(3));

			settings.setCastbar_bg_lightness(lightness);
		} else {
			settings.setCastbar_bg_lightness(0);
		}

		// 알파추출
		p = Pattern.compile("bg_gfx\\s*=\\s*\"[^\"]*a(\\d+)");
		m = p.matcher(content);

		if (m.find()) {
			int value = Integer.parseInt(m.group(1));
			settings.setCastBar_alpha(value);
		}

		// 스파클이펙트유무
		if (content.contains("sparkle_gfx")) {
			settings.setCastbarSparkleOn(true);
		} else {
			settings.setCastbarSparkleOn(false);
		}

	}

	public static void writeCommandTimerBar(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/CommandTimerBar.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 캐스팅바
		String castBar_colorname = "";
		String castBarTextLocation = ""; // 탑인지 바텀인지
		String castBarTextLayout = "";
		String castBarSize = "";
		String castBar_left_margin = "";
		String castBar_right_margin = "";
		String alignment = "";

		// 타입
		if (settings.getCastBar_text_location() == 1) {
			castBarTextLocation = "top_";
			alignment = " v_alignment=\"TOP\"";
		} else if (settings.getCastBar_text_location() == 2) {
			castBarTextLocation = "mid_";
		} else if (settings.getCastBar_text_location() == 3) {
			castBarTextLocation = "bot_";
			alignment = " v_alignment=\"BOTTOM\"";
		}

		// 캐스팅바 알파 + 이미지파일 이름지정 (확장자 제외)
		String bg_img_name = castBarTextLocation + "bg_a" + String.valueOf(settings.getCastBar_alpha());

		// 사이즈
		int width = settings.getCastBar_width();
		int height = settings.getCastBar_height();
		if (width < 345 || height < 55) {
			castBarSize = "max_size_limit=\"Point(" + String.valueOf(width) + "," + String.valueOf(height) + ")\"";
		}

		// 마진
		String margin;
		double margin_double = MarginCalculator.calc(8, 346, 230, 2.6, width + 1);

		if (margin_double == 1.0) {
			margin = "1";
		} else if (margin_double == 2.0) {
			margin = "2";
		} else if (margin_double == 3.0) {
			margin = "3";
		} else if (margin_double == 4.0) {
			margin = "4";
		} else if (margin_double == 5.0) {
			margin = "5";
		} else if (margin_double == 6.0) {
			margin = "6";
		} else if (margin_double == 7.0) {
			margin = "7";
		} else if (margin_double == 8.0) {
			margin = "8";
		} else {
			margin = String.valueOf(margin_double);
		}

		castBar_left_margin = "left_margin=\"" + margin + "\"";
		castBar_right_margin = "right_margin=\"" + margin + "\"";

		content = content.replaceAll("var_left_margin", castBar_left_margin);
		content = content.replaceAll("var_right_margin", castBar_right_margin);

		// 컬러
		String castbarSouceImgDir = customizedDir + "/gfx/castbar/" + castBarTextLocation + "fg.png";
		String castbarCustomImgDir = customizedDir + "/gfx/castbar/" + castBarTextLocation + "fg.png";

		String fxSouceImgDir = customizedDir + "/gfx/castbar/fx_" + castBarTextLocation + String.valueOf(height)
				+ ".png";
		String fxCustomImgDir = customizedDir + "/gfx/castbar/fx_" + castBarTextLocation + String.valueOf(height)
				+ ".png";

		if (settings.getCastBar_hue() == 0 && settings.getCastBar_chroma() == 0
				&& settings.getCastBar_lightness() == 0) {
			castBar_colorname = "";
		} else if (settings.getCastBar_hue() == 181) {
			castBar_colorname = "_white";
		} else {
			castBar_colorname = "_c_" + String.valueOf((int) settings.getCastBar_hue()) + "_"
					+ String.valueOf((int) settings.getCastBar_chroma()) + "_"
					+ String.valueOf((int) settings.getCastBar_lightness());

			castbarCustomImgDir = customizedDir + "/gfx/castbar/" + castBarTextLocation + "fg" + castBar_colorname
					+ ".png";
			ImageManager.process(castbarSouceImgDir, castbarCustomImgDir, settings.getCastBar_hue(),
					settings.getCastBar_chroma(), settings.getCastBar_lightness());

			fxCustomImgDir = customizedDir + "/gfx/castbar/fx_" + castBarTextLocation + String.valueOf(height)
					+ castBar_colorname + ".png";
			ImageManager.process(fxSouceImgDir, fxCustomImgDir, settings.getCastBar_hue(), settings.getCastBar_chroma(),
					settings.getCastBar_lightness());
		}

		// bg 다크모드 컬러
		String castbar_bg_chroma_code = "";
		String castbar_bg_souceImgDir = customizedDir + "/gfx/castbar/" + bg_img_name + ".png";
		String castbar_bg_customImgDir = customizedDir + "/gfx/castbar/" + bg_img_name + ".png";
		if (settings.getCastbar_bg_lightness() == 0) {
			castbar_bg_chroma_code = "";
		} else {
			castbar_bg_chroma_code = "_c_0_0_" + String.valueOf(settings.getCastbar_bg_lightness());

			castbar_bg_customImgDir = customizedDir + "/gfx/castbar/" + bg_img_name + castbar_bg_chroma_code + ".png";
			ImageManagerV2.process(castbar_bg_souceImgDir, castbar_bg_customImgDir, 0, 0,
					settings.getCastbar_bg_lightness());
		}

		bg_img_name = bg_img_name + castbar_bg_chroma_code;
		content = content.replaceAll("var_bg_imgName", bg_img_name);

		// 텍스트레이아웃
		if (settings.getCastBar_text_y() == 0) {
			castBarTextLayout = "";
		} else {
			if (settings.getCastBar_text_location() == 1) {
				castBarTextLayout = "layout_borders=\"Rect(0," + String.valueOf(-settings.getCastBar_text_y())
						+ ",0,0)\"";
			} else if (settings.getCastBar_text_location() == 2) {
				if (settings.getCastBar_text_y() < 0) {
					castBarTextLayout = "layout_borders=\"Rect(0," + String.valueOf(-settings.getCastBar_text_y())
							+ ",0,0)\"";
				} else {
					castBarTextLayout = "layout_borders=\"Rect(0,0,0," + String.valueOf(settings.getCastBar_text_y())
							+ ")\"";
				}
			} else if (settings.getCastBar_text_location() == 3) {
				castBarTextLayout = "layout_borders=\"Rect(0,0,0," + String.valueOf(settings.getCastBar_text_y())
						+ ")\"";
			}
		}

		content = content.replaceAll("var_color", castBar_colorname);
		content = content.replaceAll("var_text_location", castBarTextLocation);
		content = content.replaceAll("var_text_layout", castBarTextLayout);
		content = content.replaceAll("var_size", castBarSize);
		content = content.replaceAll("var_alignment", alignment);

		// 스파클링 fx
		String sparkle_img_code = "";
		int sparkle_offset;
		String sparkle_offset_code;

		if (height <= 31) {
			sparkle_offset = -6;
		} else if (height <= 33) {
			sparkle_offset = -7;
		} else if (height <= 35) {
			sparkle_offset = -7;
		} else if (height <= 37) {
			sparkle_offset = -8;
		} else if (height <= 39) {
			sparkle_offset = -8;
		} else if (height <= 41) {
			sparkle_offset = -8;
		} else if (height <= 43) {
			sparkle_offset = -9;
		} else if (height <= 45) {
			sparkle_offset = -9;
		} else if (height <= 47) {
			sparkle_offset = -10;
		} else if (height <= 49) {
			sparkle_offset = -10;
		} else if (height <= 51) {
			sparkle_offset = -10;
		} else if (height < 55) {
			sparkle_offset = -11;
		} else {
			sparkle_offset = -11;
		}

		if (settings.isCastbarSparkleOn()) {
			sparkle_img_code = "sparkle_gfx=\"../../Customized/gfx/castbar/fx_" + castBarTextLocation
					+ String.valueOf(height) + castBar_colorname + ".png\"";

			sparkle_offset_code = "sparkle_offset=\"" + String.valueOf(sparkle_offset) + "\"";

		} else {
			sparkle_img_code = "";
			sparkle_offset_code = "";
		}

		content = content.replaceAll("var_sparkle_img", sparkle_img_code);
		content = content.replaceAll("var_sparkle_offset", sparkle_offset_code);

		FileManager.stringToFile(targetDir, content);

	}
}
