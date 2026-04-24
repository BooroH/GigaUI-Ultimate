package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManagerV2;

public class CharPortraitLeft {

	public static void readLeftPortrait(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/CharPortraitLeft.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		p = Pattern.compile("icon_size=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setLP_buff_size(Integer.parseInt(m.group(1)));
		}

		p = Pattern.compile("icon_spacing=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setLP_buff_spacing(Integer.parseInt(m.group(1)));
		}

		p = Pattern.compile("max_columns=\"(\\d+)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setLP_buff_column(Integer.parseInt(m.group(1)));
		}

		// leftportrait에서 체력바 폰트 볼드인지 확인
		if (content.contains("NORMAL_BOLD")) {
			settings.setHPLabelBold(true);
		} else {
			settings.setHPLabelBold(false);
		}
		
		// 사이즈
		p = Pattern.compile(
				"<ProgressBar[^>]*name=\"HealthBar\"[\\s\\S]*?max_size_limit=\"Point\\((\\d+),(\\d+)\\)\"[\\s\\S]*?>",
				Pattern.CASE_INSENSITIVE);
		m = p.matcher(content);

		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));

			settings.setLPortrait_hp_height(height);
		} else {
			settings.setLPortrait_hp_height(59);
		}

		// 알파추출
		p = Pattern.compile("bg_gfx\\s*=\\s*\"[^\"]*a(\\d+)");
		m = p.matcher(content);

		if (m.find()) {
			int value = Integer.parseInt(m.group(1));
			settings.setLPortrait_bg_alpha(value);
		}

		// 다크모드 컬러
		p = Pattern.compile("bitmap_gfx=\"../../Customized/gfx/portrait/[^\"]*_c_(-?\\d+)_(-?\\d+)_(-?\\d+)\\.png\"");
		m = p.matcher(content);

		if (m.find()) {
			int hue = Integer.parseInt(m.group(1));
			int chroma = Integer.parseInt(m.group(2));
			int lightness = Integer.parseInt(m.group(3));

			settings.setMainPortrait_bg_lightness(lightness);
		} else {
			settings.setMainPortrait_bg_lightness(0);
		}
	}

	public static void writeCharPortraitLeft(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/CharPortraitLeft.xml";

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
		String chroma_code = "";

		String hp_bg_img_dir = customizedDir + "/gfx/portrait/" + hp_bg_img + ".png";
		String hp_bg_img_custom_dir = hp_bg_img_dir;

		String stam_bg_img_dir = customizedDir + "/gfx/portrait/" + stam_bg_img + ".png";
		String stam_bg_img_custom_dir = stam_bg_img_dir;

		String stam_s_bg_img_dir = customizedDir + "/gfx/portrait/" + stam_s_bg_img + ".png";
		String stam_s_bg_img_custom_dir = stam_s_bg_img_dir;

		String mana_s_bg_img_dir = customizedDir + "/gfx/portrait/" + mana_s_bg_img + ".png";
		String mana_s_bg_img_custom_dir = mana_s_bg_img_dir;
		
		double bg_lightness = settings.getMainPortrait_bg_lightness();

		if (bg_lightness == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf((int) bg_lightness);

			hp_bg_img_custom_dir = customizedDir + "/gfx/portrait/" + hp_bg_img + chroma_code + ".png";
			ImageManagerV2.process(hp_bg_img_dir, hp_bg_img_custom_dir, 0, 0,
					bg_lightness);

			stam_bg_img_custom_dir = customizedDir + "/gfx/portrait/" + stam_bg_img + chroma_code + ".png";
			ImageManagerV2.process(stam_bg_img_dir, stam_bg_img_custom_dir, 0, 0,
					bg_lightness);

			stam_s_bg_img_custom_dir = customizedDir + "/gfx/portrait/" + stam_s_bg_img + chroma_code + ".png";
			ImageManagerV2.process(stam_s_bg_img_dir, stam_s_bg_img_custom_dir, 0, 0,
					bg_lightness);

			mana_s_bg_img_custom_dir = customizedDir + "/gfx/portrait/" + mana_s_bg_img + chroma_code + ".png";
			ImageManagerV2.process(mana_s_bg_img_dir, mana_s_bg_img_custom_dir, 0, 0,
					bg_lightness);
		}

		content = content.replaceAll("var_hp_bg_img", hp_bg_img + chroma_code + ".png");
		content = content.replaceAll("var_stam_bg_img", stam_bg_img + chroma_code + ".png");
		content = content.replaceAll("var_stam_s_bg_img", stam_s_bg_img + chroma_code + ".png");
		content = content.replaceAll("var_mana_s_bg_img", mana_s_bg_img + chroma_code + ".png");

		// 디버프아이콘
		content = content.replaceAll("var_icon_size", String.valueOf(settings.getLP_buff_size()));
		content = content.replaceAll("var_icon_spacing", String.valueOf(settings.getLP_buff_spacing()));
		content = content.replaceAll("var_max_columns", String.valueOf(settings.getLP_buff_column()));

		// 체력바 폰트 볼드
		if (settings.isHPLabelBold()) {
			content = content.replaceAll("var_label_font", "label_font=\"NORMAL_BOLD\"");
		} else {
			content = content.replaceAll("var_label_font", "");
		}

		// hp사이즈
		int hp_height = settings.getLPortrait_hp_height();
		int hp_width = 235;
		int stam_height = 27;
		int stam_layout_top = 26;
		String hp_size = "";
		String stam_size = "";
		String container_size = "";
		String margin = "8";

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

		int container_height = stam_height + stam_layout_top;

		if (hp_height < 59) {
			hp_size = "max_size_limit=\"Point(" + String.valueOf(hp_width) + "," + String.valueOf(hp_height) + ")\"";
			stam_size = " max_size_limit=\"Point(" + String.valueOf(hp_width) + "," + String.valueOf(stam_height)
					+ ")\"";
			container_size = "max_size_limit=\"Point(" + String.valueOf(hp_width) + ","
					+ String.valueOf(container_height) + ")\"";
		} else {
			hp_size = "";
			stam_size = "";
			container_size = "";
		}

		content = content.replaceAll("var_hp_size", hp_size);
		content = content.replaceAll("var_stam_size", stam_size);
		content = content.replaceAll("var_container_size", container_size);
		content = content.replaceAll("var_stam_layout_top", String.valueOf(stam_layout_top));
		content = content.replaceAll("var_margin", margin);

		FileManager.stringToFile(targetDir, content);
	}
}
