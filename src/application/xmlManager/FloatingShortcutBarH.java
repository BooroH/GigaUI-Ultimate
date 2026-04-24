package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;

public class FloatingShortcutBarH {
	
	public static void readFloatingShortcutBarH(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/FloatingShortcutBarH.xml";
		
		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		p = Pattern.compile("slot_size=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		
		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));
			settings.setSidebar_slot_size(width);
		}

	}
	
	public static void writeFloatingShortcutBarH(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/FloatingShortcutBarH.xml";

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

		content = content.replaceAll("var_img_name", "a"+settings.getSlot_bg_alpha()+chroma_code);
		
		//사이즈
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
