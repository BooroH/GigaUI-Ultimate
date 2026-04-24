package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManagerV2;

public class CharPortraitRight {

	public static void readRightPortrait(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/CharPortraitRight.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		p = Pattern.compile("icon_size=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setRP_buff_size(Integer.parseInt(m.group(1)));
		}

		p = Pattern.compile("icon_spacing=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setRP_buff_spacing(Integer.parseInt(m.group(1)));
		}

		p = Pattern.compile("max_columns=\"(\\d+)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setRP_buff_column(Integer.parseInt(m.group(1)));
		}
		p = Pattern.compile("filter=\"([^\"]+)\"");
		m = p.matcher(content);

		if (content.contains("name=\"BuffListView\"")) {
			if (m.find()) {
				String filterValue = m.group(1); // 예: "friendly|hostile"

				boolean hasFriendly = filterValue.contains("friendly");
				boolean hasHostile = filterValue.contains("hostile");

				if (hasHostile && hasFriendly) {
					settings.setRP_buff_filter(3); // 둘다있음
				} else if (hasHostile) {
					settings.setRP_buff_filter(1); // hostile만 있음
				} else if (hasFriendly) {
					settings.setRP_buff_filter(2); // friendly만 있음
				}
			} else {
				settings.setRP_buff_filter(3); // 둘다있음
			}
		} else {
			settings.setRP_buff_filter(0);
		}

		// 타겟의타겟
		p = Pattern.compile("<View[^>]*name=\"TargetContainerView\"[^>]*>", Pattern.DOTALL);
		m = p.matcher(content);

		while (m.find()) {
			String targetBlock = m.group();

			Pattern borderPattern = Pattern.compile("layout_borders=\"Rect\\((-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)\\)\"");
			Matcher borderMatcher = borderPattern.matcher(targetBlock);

			while (borderMatcher.find()) {
				int left = Integer.parseInt(borderMatcher.group(1));
				int top = Integer.parseInt(borderMatcher.group(2));
				int right = Integer.parseInt(borderMatcher.group(3));
				int bottom = Integer.parseInt(borderMatcher.group(4));

				settings.setTargetOfTargetX(left - right);
				settings.setToT_layout_top(top);
			}
		}

	}

	public static void writeCharPortraitRight(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/CharPortraitRight.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 알파+다크모드
		int bg_alpha = settings.getLPortrait_bg_alpha();
		String hp_bg_img = "hp_bg_a" + String.valueOf(bg_alpha);
		String stam_bg_img = "stam_bg_a" + String.valueOf(bg_alpha);
		String stam_s_bg_img = "stam_s_bg_a" + String.valueOf(bg_alpha);
		String mana_s_bg_img = "mana_s_bg_a" + String.valueOf(bg_alpha);
		String t_bg_img = "t_bg_a" + String.valueOf(bg_alpha);
		String chroma_code = "";

		String t_bg_img_dir = customizedDir + "/gfx/portrait/" + t_bg_img + ".png";
		String t_bg_img_custom_dir = t_bg_img_dir;

		double bg_lightness = settings.getMainPortrait_bg_lightness();

		if (bg_lightness == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf((int) bg_lightness);

			t_bg_img_custom_dir = customizedDir + "/gfx/portrait/" + t_bg_img + chroma_code + ".png";
			ImageManagerV2.process(t_bg_img_dir, t_bg_img_custom_dir, 0, 0, bg_lightness);

		}

		content = content.replaceAll("var_hp_bg_img", hp_bg_img + chroma_code + ".png");
		content = content.replaceAll("var_stam_bg_img", stam_bg_img + chroma_code + ".png");
		content = content.replaceAll("var_stam_s_bg_img", stam_s_bg_img + chroma_code + ".png");
		content = content.replaceAll("var_mana_s_bg_img", mana_s_bg_img + chroma_code + ".png");
		content = content.replaceAll("var_t_bg_img", t_bg_img + chroma_code + ".png");

		// right portrait
		if (settings.getRP_buff_filter() == 0) {
			content = content.replaceAll("<BuffListView[^>]*/>", "");
		} else {
			content = content.replaceAll("var_icon_size", String.valueOf(settings.getRP_buff_size()));
			content = content.replaceAll("var_icon_spacing", String.valueOf(settings.getRP_buff_spacing()));
			content = content.replaceAll("var_max_columns", String.valueOf(settings.getRP_buff_column()));
			if (settings.getRP_buff_filter() == 1) {
				content = content.replaceAll("var_buff_filter", "filter=\"hostile\"");
			} else if (settings.getRP_buff_filter() == 2) {
				content = content.replaceAll("var_buff_filter", "filter=\"friendly\"");
			} else if (settings.getRP_buff_filter() == 3) {
				content = content.replaceAll("var_buff_filter", "");
			}
		}

		// 체력바 폰트 볼드
		if (settings.isHPLabelBold()) {
			content = content.replaceAll("var_label_font", "label_font=\"NORMAL_BOLD\"");
		} else {
			content = content.replaceAll("var_label_font", "");
		}

		// hp사이즈
		int hp_height = settings.getLPortrait_hp_height();
		int hp_width = 353;
		int stam_height = 41;
		int stam_layout_top = 39;
		int target_category_tag_size = 25;
		int tot_width = 173;
		int energy_layout_bottom = 11;

		String hp_size = "";
		String stam_size = "";
		String container_size = "";
		String tot_size = "";
		String margin = "12";

		String tot_left_margin = "";
		String tot_right_margin = "";

		if (hp_height == 55) {
			hp_width = 333;
			stam_height = 38;
			stam_layout_top = 36;
			target_category_tag_size = 23;
			tot_width = 161;
			margin = "10.8";
			energy_layout_bottom = 10;
		} else if (hp_height == 51) {
			hp_width = 313;
			stam_height = 35;
			stam_layout_top = 34;
			target_category_tag_size = 22;
			tot_width = 149;
			margin = "10";
			energy_layout_bottom = 9;
		} else if (hp_height == 49) {
			hp_width = 295;
			stam_height = 34;
			stam_layout_top = 32;
			target_category_tag_size = 21;
			tot_width = 145;
			margin = "9.8";
			energy_layout_bottom = 8;
		} else if (hp_height == 45) {
			hp_width = 273;
			stam_height = 31;
			stam_layout_top = 30;
			target_category_tag_size = 19;
			tot_width = 131;
			margin = "9";
			energy_layout_bottom = 7;
		} else if (hp_height == 43) {
			hp_width = 255;
			stam_height = 30;
			stam_layout_top = 28;
			target_category_tag_size = 18;
			tot_width = 127;
			margin = "8.6";
			energy_layout_bottom = 6;
		} else if (hp_height == 39) {
			hp_width = 235;
			stam_height = 27;
			stam_layout_top = 26;
			target_category_tag_size = 17;
			tot_width = 115;
			margin = "8";
			energy_layout_bottom = 5;
		} else if (hp_height == 35) {
			hp_width = 217;
			stam_height = 24;
			stam_layout_top = 23;
			target_category_tag_size = 15;
			tot_width = 103;
			margin = "7.2";
			energy_layout_bottom = 4;
		} else if (hp_height == 33) {
			hp_width = 199;
			stam_height = 23;
			stam_layout_top = 22;
			target_category_tag_size = 14;
			tot_width = 99;
			margin = "7";
			energy_layout_bottom = 3;
		} else if (hp_height == 29) {
			hp_width = 177;
			stam_height = 20;
			stam_layout_top = 19;
			target_category_tag_size = 11;
			tot_width = 87;
			margin = "6";
			energy_layout_bottom = 2;
		} else {
			hp_width = 353;
			stam_height = 41;
			stam_layout_top = 39;
			target_category_tag_size = 25;
			tot_width = 173;
			margin = "12";
			energy_layout_bottom = 11;
		}

		int container_height = stam_height + stam_layout_top + target_category_tag_size;

		if (hp_height < 59) {
			hp_size = "max_size_limit=\"Point(" + String.valueOf(hp_width) + "," + String.valueOf(hp_height) + ")\"";
			stam_size = " max_size_limit=\"Point(" + String.valueOf(hp_width) + "," + String.valueOf(stam_height)
					+ ")\"";
			container_size = "max_size_limit=\"Point(" + String.valueOf(hp_width) + ","
					+ String.valueOf(container_height) + ")\"";
			tot_size = " max_size_limit=\"Point(" + String.valueOf(tot_width) + "," + String.valueOf(stam_height)
					+ ")\"";

			if (hp_height == 55) {
				tot_left_margin = "left_margin=\"5.4\"";
				tot_right_margin = "right_margin=\"5.4\"";
			} else {
				tot_left_margin = "left_margin=\"" + margin + "\"";
				tot_right_margin = "right_margin=\"" + margin + "\"";
			}
		} else {
			hp_size = "";
			stam_size = "";
			container_size = "";
			tot_size = "";
			tot_left_margin = "";
			tot_right_margin = "";
		}

		content = content.replaceAll("var_hp_size", hp_size);
		content = content.replaceAll("var_stam_size", stam_size);
		content = content.replaceAll("var_container_size", container_size);
		content = content.replaceAll("var_stam_layout_top", String.valueOf(stam_layout_top));
		content = content.replaceAll("var_tot_size", tot_size);
		content = content.replaceAll("var_tot_name_width", String.valueOf(tot_width));
		content = content.replaceAll("var_margin", margin);
		content = content.replaceAll("var_tot_left_margin", tot_left_margin);
		content = content.replaceAll("var_tot_right_margin", tot_right_margin);
		content = content.replaceAll("var_energy_layout_bottom", String.valueOf(energy_layout_bottom));

		// tot 레이아웃
		int tot_layout_left = 0;
		int tot_layout_right = 0;
		int tot_pos_x = settings.getTargetOfTargetX();
		int tot_layout_top = settings.getToT_layout_top();
		if (tot_pos_x < 0) {
			tot_layout_right = tot_pos_x;
			tot_layout_left = 0;
		} else {
			tot_layout_right = 0;
			tot_layout_left = tot_pos_x;
		}

		content = content.replaceAll("var_tot_layout_left", String.valueOf(tot_layout_left));
		content = content.replaceAll("var_tot_layout_right", String.valueOf(tot_layout_right));
		content = content.replaceAll("var_tot_layout_top", String.valueOf(tot_layout_top));

		// 폰트사이즈별 최적화
		int font_bottom_margin_reducer;
		
		if (settings.getFontSize() == 0) {
			font_bottom_margin_reducer = 0;
		} else if (settings.getFontSize() == 1) {
			font_bottom_margin_reducer = -1;
		} else if (settings.getFontSize() == 2) {
			font_bottom_margin_reducer = -1;
		} else if (settings.getFontSize() == 3) {
			font_bottom_margin_reducer = -1;
		} else if (settings.getFontSize() == 4) {
			font_bottom_margin_reducer = -1;
		} else if (settings.getFontSize() == 5) {
			font_bottom_margin_reducer = -2;
		} else {
			font_bottom_margin_reducer = -2;
		}
		
		int tot_name_layout_bottom = 18 + stam_height - 27 + font_bottom_margin_reducer;

		content = content.replaceAll("var_tot_name_layout_bottom", String.valueOf(tot_name_layout_bottom));

		FileManager.stringToFile(targetDir, content);
	}
}
