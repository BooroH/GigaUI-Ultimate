package application.xmlManager;

import application.setting.Settings;
import application.util.FileManager;

public class FloatingShortcutBarV {
	public static void writeFloatingShortcutBarV(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/FloatingShortcutBarV.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		String chroma_code = "";

		// 컬러
		if (settings.getSlot_bg_lightness() == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf((int) settings.getSlot_bg_lightness());
		}

		content = content.replaceAll("var_img_name", "a" + settings.getSlot_bg_alpha() + chroma_code);

		// 사이즈
		int size = settings.getSidebar_slot_size();
		String slot_margin;

		if (size > 84) {
			slot_margin = "11";
		} else if (size > 76) {
			slot_margin = "10";
		} else if (size > 68) {
			slot_margin = "9";
		} else if (size > 60) {
			slot_margin = "8";
		} else if (size > 52) {
			slot_margin = "7";
		} else if (size > 42) {
			slot_margin = "6";
		} else if (size > 34) {
			slot_margin = "5";
		} else {
			slot_margin = "4";
		}

		content = content.replaceAll("var_size", String.valueOf(size));
		content = content.replaceAll("var_margin", slot_margin);

		FileManager.stringToFile(targetDir, content);

	}
}
