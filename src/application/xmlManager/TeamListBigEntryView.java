package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;

public class TeamListBigEntryView {

	public static void readTeamListBigEntryView(String customizedDir) {

		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/TeamGUI/TeamListBigEntryView.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		// 사이즈 추출
		p = Pattern
				.compile("<View[^>]*name=\"Health\"[^>]*max_size_limit=\"Point\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\"");
		m = p.matcher(content);

		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));

			settings.setGroup_hp_width(width);
			settings.setGroup_hp_height(height);
		}

		// 알파값추출
		settings.setGroupHPAlpha(1.0);
		p = Pattern.compile("<CharStatBar\\s+name=\"HealthBar\"[\\s\\S]*?view_local_alpha=\"([0-9]*\\.?[0-9]+)\"");
		m = p.matcher(content);

		if (m.find()) {
			double alpha = Double.parseDouble(m.group(1));
			settings.setGroupHPAlpha(alpha);
		}

		// 네임 bg 알파값추출
		settings.setGroup_nameBg_alpha(1);
		p = Pattern.compile(
				"bitmap_gfx=\"../../Customized/gfx/teamlist/name_bg.png\"[\\s\\S]*?view_local_alpha=\"([0-9]*\\.?[0-9]+)\"");
		m = p.matcher(content);

		if (m.find()) {
			double alpha = Double.parseDouble(m.group(1));
			settings.setGroup_nameBg_alpha(alpha);
		}

	}

	public static void writeTeamListBigEntryView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/TeamGUI/TeamListBigEntryView.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 사이즈
		int width = settings.getGroup_hp_width();
		int height = settings.getGroup_hp_height();
		int text_width;
		String left_margin;
		String right_margin;

		if (width < 129) {
			text_width = width - 24;
			left_margin = "1.4";
			right_margin = "1.4";
		} else if (width < 169) {
			text_width = width - 26;
			left_margin = "1.8";
			right_margin = "1.8";
		} else if (width < 193) {
			text_width = width - 28;
			left_margin = "2.2";
			right_margin = "2.2";
		} else if (width < 227) {
			text_width = width - 30;
			left_margin = "2.6";
			right_margin = "2.6";
		} else {
			text_width = width - 30;
			left_margin = "3";
			right_margin = "3";
		}

		content = content.replaceAll("var_width", String.valueOf(width));
		content = content.replaceAll("var_height", String.valueOf(height));
		content = content.replaceAll("var_text_width", String.valueOf(text_width));
		content = content.replaceAll("var_left_margin", left_margin);
		content = content.replaceAll("var_right_margin", right_margin);

		// hp 알파
		if (settings.getGroupHPAlpha() == 1.0) {
			content = content.replaceAll("var_hp_alpha", "");
		} else {
			content = content.replaceAll("var_hp_alpha",
					"view_local_alpha=\"" + String.valueOf(settings.getGroupHPAlpha()) + "\"");
		}

		// 네임 bg 알파
		if (settings.getGroup_nameBg_alpha() == 1.0) {
			content = content.replaceAll("var_nameBg_alpha", "");
		} else {
			content = content.replaceAll("var_nameBg_alpha",
					" view_local_alpha=\"" + String.valueOf(settings.getGroup_nameBg_alpha()) + "\"");
		}

		// 체력바 폰트 볼드
		if (settings.isHPLabelBold()) {
			content = content.replaceAll("var_label_font", "label_font=\"NORMAL_BOLD\"");
		} else {
			content = content.replaceAll("var_label_font", "");
		}
		
		//폰트사이즈별 네임bg 조절
		int font_size = settings.getFontSize();
		String nameBg_height;
		String name_layout_borders;
		if(font_size==0) {
			nameBg_height="19";
			name_layout_borders="layout_borders=\"Rect(3,-1,0,0)\"";
		}
		else if(font_size==1) {
			nameBg_height="20";
			name_layout_borders="layout_borders=\"Rect(3,0,0,0)\"";
		}
		else if(font_size==2) {
			nameBg_height="21";
			name_layout_borders="layout_borders=\"Rect(3,0,0,0)\"";
		}
		else if(font_size==3) {
			nameBg_height="21";
			name_layout_borders="layout_borders=\"Rect(3,0,0,0)\"";
		}
		else if(font_size==4) {
			nameBg_height="22";
			name_layout_borders="layout_borders=\"Rect(3,0,0,0)\"";
		}
		else {
			nameBg_height="22";
			name_layout_borders="layout_borders=\"Rect(3,-1,0,0)\"";
		}
		
		content = content.replaceAll("var_nameBg_height", nameBg_height);
		content = content.replaceAll("var_name_layout_borders", name_layout_borders);

		FileManager.stringToFile(targetDir, content);

	}
}
