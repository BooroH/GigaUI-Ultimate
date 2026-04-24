package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;

public class OverheadText {

	public static void readOverheadText(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/OverheadText.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		// 오버헤드알파값추출
		settings.setOverheadHPAlpha(1.0);
		p = Pattern.compile("view_alpha\\s*=\\s*\"([0-9.]+)\"");
		m = p.matcher(content);
		if (m.find()) {
			String alphaValue = m.group(1);
			double alpha = Double.parseDouble(alphaValue);
			settings.setOverheadHPAlpha(alpha);
		}

		// 사이즈
		p = Pattern.compile("max_size_limit=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			settings.setOverheadHPWidth(width);
			int height = Integer.parseInt(m.group(2));
			settings.setOverheadHPHeight(height);
		} else {
			if (content.contains("bg_3.png")) {
				settings.setOverheadHPWidth(227);
				settings.setOverheadHPHeight(24);
			} else {
				settings.setOverheadHPWidth(99);
				settings.setOverheadHPHeight(12);
			}
		}
	}

	public static void writeOverheadText(String customizedDir) {

		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/OverheadText.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		if (settings.getOverheadHPAlpha() == 1.0) {
			content = content.replaceAll("var_hp_alpha", "");
		} else {
			content = content.replaceAll("var_hp_alpha",
					"view_alpha=\"" + String.valueOf(settings.getOverheadHPAlpha()) + "\"");
		}

		// 사이즈
		int width = settings.getOverheadHPWidth();
		int height = settings.getOverheadHPHeight();
		String imgSize = "";
		String hpSize = "";
		if (width == 99 && height == 12) {
			imgSize = "";
		} else if (width < 164 && height < 19) {
			imgSize = "_2";
		} else {
			imgSize = "_3";
		}

		if ((width == 99 && height == 12) || (width == 227 && height == 24)) {
			hpSize = "";
		} else {
			hpSize = "max_size_limit=\"Point(" + String.valueOf(width) + "," + String.valueOf(height) + ")\"";
		}

		content = content.replaceAll("var_img_size", imgSize);
		content = content.replaceAll("var_hp_size", hpSize);

		FileManager.stringToFile(targetDir, content);

	}
}
