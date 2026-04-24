package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;

public class PetListView {

	public static void readPetListView(String customizedDir) {

		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/PetAndFollowerGUI/PetListView.xml";
		
		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		p = Pattern.compile("view_alpha\\s*=\\s*\"([0-9.]+)\"");
		m = p.matcher(content);
		if (m.find()) {
			String alphaValue = m.group(1);
			double alpha = Double.parseDouble(alphaValue);
			settings.setPetListAlpha(alpha);
		}
		else {
			settings.setPetListAlpha(1.0);
		}

		// 사이즈제한 추출
		p = Pattern.compile("<MiniCharListView[^>]*?max_size_limit\\s*=\\s*\"Point\\((\\d+),(\\d+)\\)\"",
				Pattern.DOTALL);
		m = p.matcher(content);

		settings.setPetListSizeLimit(false);
		if (m.find()) {
			String x = m.group(1);
			String y = m.group(2);
			settings.setPetListSize(Integer.parseInt(y));
			settings.setPetListSizeLimit(true);
		}

	}

	public static void writePetListView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/PetAndFollowerGUI/PetListView.xml";
		
		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 펫리스트
		if (settings.getPetListAlpha() == 1.0) {
			content = content.replaceAll("var_alpha", "");
		} else {
			content = content.replaceAll("var_alpha",
					" view_alpha=\"" + String.valueOf(settings.getPetListAlpha()) + "\"");
		}
		
		// 사이즈리밋
		if (settings.isPetListSizeLimit()) {
			content = content.replaceAll("var_size_limit",
					"max_size_limit=\"Point(227," + String.valueOf(settings.getPetListSize()) + ")\"");
		} else {
			content = content.replaceAll("var_size_limit", "");
		}

		FileManager.stringToFile(targetDir, content);

	}
}
