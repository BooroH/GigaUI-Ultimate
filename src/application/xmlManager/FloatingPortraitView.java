package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManagerV2;

public class FloatingPortraitView {

	public static void readFloatingPortraitView(String customizedDir) {

		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/FloatingPortraitView.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		// 사이즈
		p = Pattern.compile(
				"<ProgressBar[^>]*name=\"HealthBar\"[\\s\\S]*?max_size_limit=\"Point\\((\\d+),(\\d+)\\)\"[\\s\\S]*?>",
				Pattern.CASE_INSENSITIVE);
		m = p.matcher(content);

		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));

			settings.setFloatingPortrait_hp_height(height);
		} else {
			settings.setFloatingPortrait_hp_height(59);
		}

		// floating portrait nameplate alpha값 추출
		settings.setNameplate_alpha(1.0);
		p = Pattern.compile("<BitmapView[^>]*nameplate[^>]*view_local_alpha=\"(\\d+(?:\\.\\d+)?)\"");
		m = p.matcher(content);

		if (m.find()) {
			String alphaValueStr = m.group(1);
			settings.setNameplate_alpha(Double.parseDouble(alphaValueStr));
		}

		// 마나바
		if (content.contains("<ProgressBar name=\"ManaBar\""))
			settings.setFloatingPortrait_show_mana(true);
		else
			settings.setFloatingPortrait_show_mana(false);

		// 버프필터
		p = Pattern.compile("filter=\"([^\"]+)\"");
		m = p.matcher(content);

		if (content.contains("name=\"BuffListView\"")) {
			if (m.find()) {
				String filterValue = m.group(1); // 예: "friendly|hostile"

				boolean hasFriendly = filterValue.contains("friendly");
				boolean hasHostile = filterValue.contains("hostile");

				if (hasHostile && hasFriendly) {
					settings.setFloatingPortrait_buff_filter(3); // 둘다있음
				} else if (hasHostile) {
					settings.setFloatingPortrait_buff_filter(1); // hostile만 있음
				} else if (hasFriendly) {
					settings.setFloatingPortrait_buff_filter(2); // friendly만 있음
				}
			} else {
				settings.setFloatingPortrait_buff_filter(3); // 둘다있음
			}
		} else {
			settings.setFloatingPortrait_buff_filter(0);
		}

		// 버프사이즈
		p = Pattern.compile("icon_size=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setFloatingPortrait_buff_size(Integer.parseInt(m.group(1)));
		}

		// 버프갭
		p = Pattern.compile("icon_spacing=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setFloatingPortrait_buff_spacing(Integer.parseInt(m.group(1)));
		}

		// 버프컬럼
		p = Pattern.compile("max_columns=\"(\\d+)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setFloatingPortrait_buff_column(Integer.parseInt(m.group(1)));
		}

		// 알파추출
		p = Pattern.compile("bg_gfx\\s*=\\s*\"[^\"]*a(\\d+)");
		m = p.matcher(content);

		if (m.find()) {
			int value = Integer.parseInt(m.group(1));
			settings.setFloatingPortrait_bg_alpha(value);
		}

		// 다크모드 컬러
		p = Pattern.compile("bg_gfx=\"../../Customized/gfx/portrait/[^\"]*_c_(-?\\d+)_(-?\\d+)_(-?\\d+)\\.png\"");
		m = p.matcher(content);

		if (m.find()) {
			int hue = Integer.parseInt(m.group(1));
			int chroma = Integer.parseInt(m.group(2));
			int lightness = Integer.parseInt(m.group(3));

			settings.setFloatingPortrait_bg_lightness(lightness);
		} else {
			settings.setFloatingPortrait_bg_lightness(0);
		}

	}

	public static void writeFloatingPortraitView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/FloatingPortraitView.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);
		
		//알파+다크모드
		int bg_alpha = settings.getFloatingPortrait_bg_alpha();
		String hp_bg_img = "hp_bg_a" + String.valueOf(bg_alpha);
		String stam_bg_img = "stam_bg_a" + String.valueOf(bg_alpha);
		String stam_s_bg_img = "stam_s_bg_a" + String.valueOf(bg_alpha);
		String mana_s_bg_img = "mana_s_bg_a" + String.valueOf(bg_alpha);
		String chroma_code="";
		
		String hp_bg_img_dir = customizedDir + "/gfx/portrait/" + hp_bg_img + ".png";
		String hp_bg_img_custom_dir = hp_bg_img_dir;
		
		String stam_bg_img_dir = customizedDir + "/gfx/portrait/" + stam_bg_img + ".png";
		String stam_bg_img_custom_dir = stam_bg_img_dir;
		
		String stam_s_bg_img_dir = customizedDir + "/gfx/portrait/" + stam_s_bg_img + ".png";
		String stam_s_bg_img_custom_dir = stam_s_bg_img_dir;
		
		String mana_s_bg_img_dir = customizedDir + "/gfx/portrait/" + mana_s_bg_img + ".png";
		String mana_s_bg_img_custom_dir = mana_s_bg_img_dir;
		
		double bg_lightness = settings.getFloatingPortrait_bg_lightness();
		
		if (bg_lightness == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf((int) bg_lightness);

			hp_bg_img_custom_dir = customizedDir + "/gfx/portrait/" + hp_bg_img + chroma_code + ".png";
			ImageManagerV2.process(hp_bg_img_dir, hp_bg_img_custom_dir, 0, 0, bg_lightness);
			
			stam_bg_img_custom_dir = customizedDir + "/gfx/portrait/" + stam_bg_img + chroma_code + ".png";
			ImageManagerV2.process(stam_bg_img_dir, stam_bg_img_custom_dir, 0, 0, bg_lightness);
			
			stam_s_bg_img_custom_dir = customizedDir + "/gfx/portrait/" + stam_s_bg_img + chroma_code + ".png";
			ImageManagerV2.process(stam_s_bg_img_dir, stam_s_bg_img_custom_dir, 0, 0, bg_lightness);
			
			mana_s_bg_img_custom_dir = customizedDir + "/gfx/portrait/" + mana_s_bg_img + chroma_code + ".png";
			ImageManagerV2.process(mana_s_bg_img_dir, mana_s_bg_img_custom_dir, 0, 0, bg_lightness);
		}

		content = content.replaceAll("var_hp_bg_img", hp_bg_img + chroma_code + ".png");
		String stam_bg_img_file = stam_bg_img + chroma_code + ".png";
		String stam_s_bg_img_file = stam_s_bg_img + chroma_code + ".png";
		String mana_s_bg_img_file = mana_s_bg_img + chroma_code + ".png";

		// hp사이즈
		int hp_height = settings.getFloatingPortrait_hp_height();
		int hp_width = 217;
		int stam_height = 24;
		int stam_layout_top = 23;
		String hp_size = "";
		String stam_size = "";
		String nameplate_size = "";
		String margin = "7.2";

		if (hp_height == 55) {
			hp_width = 333;
			stam_height = 38;
			stam_layout_top = 36;
			margin = "10.8";
		} else if (hp_height == 51) {
			hp_width = 313;
			stam_height = 35;
			stam_layout_top = 34;
			margin = "10";
		} else if (hp_height == 49) {
			hp_width = 295;
			stam_height = 34;
			stam_layout_top = 32;
			margin = "9.8";
		} else if (hp_height == 45) {
			hp_width = 273;
			stam_height = 31;
			stam_layout_top = 30;
			margin = "9";
		} else if (hp_height == 43) {
			hp_width = 255;
			stam_height = 30;
			stam_layout_top = 28;
			margin = "8.6";
		} else if (hp_height == 39) {
			hp_width = 235;
			stam_height = 27;
			stam_layout_top = 26;
			margin = "8";
		} else if (hp_height == 35) {
			hp_width = 217;
			stam_height = 24;
			stam_layout_top = 23;
			margin = "7.2";
		} else if (hp_height == 33) {
			hp_width = 199;
			stam_height = 23;
			stam_layout_top = 22;
			margin = "7";
		} else if (hp_height == 29) {
			hp_width = 177;
			stam_height = 20;
			stam_layout_top = 19;
			margin = "6";
		} else {
			hp_width = 353;
			stam_height = 41;
			stam_layout_top = 39;
			margin = "12";
		}

		int font_size = settings.getFontSize();
		int nameplate_height = 24 + font_size;

		if (hp_height < 59) {
			hp_size = "max_size_limit=\"Point(" + String.valueOf(hp_width) + "," + String.valueOf(hp_height) + ")\"";
			stam_size = " max_size_limit=\"Point(" + String.valueOf(hp_width) + "," + String.valueOf(stam_height)
					+ ")\"";
			nameplate_size = "max_size_limit=\"Point(" + String.valueOf(hp_width) + ","
					+ String.valueOf(nameplate_height) + ")\"";
		} else {
			hp_size = "";
			stam_size = "";
			if (nameplate_height == 30) {
				nameplate_size = "";
			} else {
				nameplate_size = "max_size_limit=\"Point(" + String.valueOf(hp_width) + ","
						+ String.valueOf(nameplate_height) + ")\"";
			}
		}

		int name_layout_top;
		if (font_size == 0) {
			name_layout_top = -2;
		} else {
			name_layout_top = -3;
		}
		int nameplate_layout_bottom;
		if (hp_height < 35) {
			nameplate_layout_bottom = -8;
		} else if (hp_height < 43) {
			nameplate_layout_bottom = -9;
		} else if (hp_height < 49) {
			nameplate_layout_bottom = -10;
		} else if (hp_height < 55) {
			nameplate_layout_bottom = -11;
		} else {
			nameplate_layout_bottom = -12;
		}

		content = content.replaceAll("var_hp_size", hp_size);
		content = content.replaceAll("var_nameplate_size", nameplate_size);
		content = content.replaceAll("var_name_layout_top", String.valueOf(name_layout_top));
		content = content.replaceAll("var_nameplate_layout_bottom", String.valueOf(nameplate_layout_bottom));
		content = content.replaceAll("var_margin", margin);

		// 리소스바
		String mana_template = "";
		String mana_bar = "";
		if (settings.isFloatingPortrait_show_mana()) {
			mana_template = "<template:ProgressBar template:name = \"ManaBarShort\"\r\n"
					+ "		bg_gfx=\"../../Customized/gfx/portrait/"+mana_s_bg_img_file+"\"\r\n"
					+ "		fg_gfx=\"../../Customized/gfx/portrait/mana_s.png\"\r\n" + "		left_margin=\"0\"\r\n"
					+ "		right_margin=\"" + margin + "\"\r\n" + "	/>\r\n"
					+ "	<template:ProgressBar template:name = \"ManaBarLong\"\r\n"
					+ "		bg_gfx=\"../../Customized/gfx/portrait/"+stam_bg_img_file+"\"\r\n"
					+ "		fg_gfx=\"../../Customized/gfx/portrait/mana.png\"\r\n" + "		left_margin=\"" + margin
					+ "\"\r\n" + "		right_margin=\"" + margin + "\"\r\n" + "	/>\r\n"
					+ "	<template:ProgressBar template:name = \"StaminaBarShort\"\r\n"
					+ "		bg_gfx=\"../../Customized/gfx/portrait/"+stam_s_bg_img_file+"\"\r\n"
					+ "		fg_gfx=\"../../Customized/gfx/portrait/stam_s.png\"\r\n" + "		left_margin=\"" + margin
					+ "\"\r\n" + "		right_margin=\"0\"\r\n" + "	/>\r\n"
					+ "	<template:ProgressBar template:name = \"StaminaBarLong\"\r\n"
					+ "		bg_gfx=\"../../Customized/gfx/portrait/"+stam_bg_img_file+"\"\r\n"
					+ "		fg_gfx=\"../../Customized/gfx/portrait/stam.png\"\r\n" + "		left_margin=\"" + margin
					+ "\"\r\n" + "		right_margin=\"" + margin + "\"\r\n" + "	/>";
			mana_bar = "<View view_layout=\"horizontal\" layout_borders=\"Rect(0," + String.valueOf(stam_layout_top)
					+ ",0,0)\"" + stam_size + " view_alpha=\"0.94\">\r\n" + "			<ProgressBar name=\"StaminaBar\"\r\n"
					+ "				flash_on_reduce=\"false\"\r\n" + "				slide_down_time=\"0.01\"\r\n"
					+ "				view_flags=\"WID_IGNORE_WHEN_HIDDEN\"\r\n"
					+ "				label_color=\"#000000\"\r\n" + "				label_offset=\"Point(0,40)\"\r\n"
					+ "			/>\r\n" + "			<ProgressBar name=\"ManaBar\"\r\n"
					+ "				flash_on_reduce=\"false\"\r\n" + "				slide_down_time=\"0.01\"\r\n"
					+ "				view_flags=\"WID_IGNORE_WHEN_HIDDEN\"\r\n"
					+ "				label_color=\"#000000\"\r\n" + "				label_offset=\"Point(0,40)\"\r\n"
					+ "			/>\r\n" + "		</View>";
		}

		content = content.replaceAll("var_mana_template", mana_template);
		content = content.replaceAll("var_mana_bar", mana_bar);

		// 디법창
		String buffListView = "";

		if (settings.getFloatingPortrait_buff_filter() == 1) {
			buffListView = "<BuffListView name=\"BuffListView\"\r\n" + "		layout_borders=\"Rect(5,0,0,0)\"\r\n"
					+ "		h_local_alignment=\"LEFT\"\r\n" + "		filter=\"hostile\"\r\n" + "		icon_size=\"Point("
					+ String.valueOf(settings.getFloatingPortrait_buff_size()) + ","
					+ String.valueOf(settings.getFloatingPortrait_buff_size()) + ")\"\r\n"
					+ "		full_size_limit=\"0\"\r\n" + "		icon_spacing=\"Point("
					+ String.valueOf(settings.getFloatingPortrait_buff_spacing()) + ","
					+ String.valueOf(settings.getFloatingPortrait_buff_spacing()) + ")\"\r\n" + "		max_columns=\""
					+ settings.getFloatingPortrait_buff_column() + "\"\r\n" + "	/>";
		}

		content = content.replaceAll("var_buff_view", buffListView);

		// 체력바 폰트 볼드
		String label_font = "";
		if (settings.isHPLabelBold()) {
			label_font = "label_font=\"NORMAL_BOLD\"";
		}

		content = content.replaceAll("var_label_font", label_font);

		// 네임플레이트알파
		String nameplate_alpha = "";
		if (settings.getNameplate_alpha() == 1.0) {
			nameplate_alpha = "";
		} else {
			nameplate_alpha = " view_local_alpha=\"" + String.valueOf(settings.getNameplate_alpha()) + "\"";
		}

		content = content.replaceAll("var_nameplate_alpha", nameplate_alpha);

		FileManager.stringToFile(targetDir, content);

	}
}
