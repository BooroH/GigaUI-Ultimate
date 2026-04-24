package application.xmlManager;

import application.setting.Settings;
import application.util.FileManager;

public class WindowSkins {
	public static void writeWindowSkins(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/WindowSkins/Normal.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);
		

		// 컬러
		String chroma_code = "";

		if (settings.getWindows_bg_lightness() == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf((int) settings.getWindows_bg_lightness());
		}

		content = content.replaceAll("var_bg_chroma", chroma_code);

		FileManager.stringToFile(targetDir, content);

	}
}
