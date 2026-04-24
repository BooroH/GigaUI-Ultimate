package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManager;
import application.util.ImageManagerV2;

public class SoulFragmentBar {
	public static void readSoulFragmentBar(String customizedDir) {

		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/SoulFragmentBar.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		// 사이즈
		p = Pattern.compile("max_size_limit=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			int height = Integer.parseInt(m.group(2));
			settings.setSoulshard_height(height);
		} else {
			settings.setSoulshard_height(44);
		}

		// bg 컬러
		p = Pattern.compile("bitmap_gfx=\"[^\"]*c_(-?\\d+)_(-?\\d+)_(-?\\d+)\\.png\"");
		m = p.matcher(content);

		if (m.find()) {
			int hue = Integer.parseInt(m.group(1));
			int chroma = Integer.parseInt(m.group(2));
			int lightness = Integer.parseInt(m.group(3));

			settings.setSoulshard_bg_lightness(lightness);
		} else {
			settings.setSoulshard_bg_lightness(0);
		}

	}

	public static void writeSoulFragmentBar(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/SoulFragmentBar.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 사이즈
		String soulshardSize = "";
		int height = settings.getSoulshard_height();
		int width;

		if (height == 42) {
			width = 156;
		} else if (height == 40) {
			width = 150;
		} else if (height == 38) {
			width = 142;
		} else if (height == 36) {
			width = 134;
		} else if (height == 34) {
			width = 128;
		} else if (height == 32) {
			width = 120;
		} else if (height == 30) {
			width = 112;
		} else if (height == 28) {
			width = 106;
		} else if (height == 26) {
			width = 99;
		} else {
			width = 91;
		}

		if (height > 43) {
			soulshardSize = "";
		} else {
			soulshardSize = " max_size_limit=\"Point(" + String.valueOf(width) + "," + String.valueOf(height) + ")\"";
		}

		content = content.replaceAll("var_size", soulshardSize);

		// bg 다크모드 컬러
		String soulshard_bg_chroma_code = "";
		String soulshard_bg_souceImgDir = customizedDir + "/gfx/soulshard/bg.png";
		String soulshard_bg_customImgDir = customizedDir + "/gfx/soulshard/bg.png";
		if (settings.getSoulshard_bg_lightness() == 0) {
			soulshard_bg_chroma_code = "";
		} else {
			soulshard_bg_chroma_code = "_c_0_0_" + String.valueOf(settings.getSoulshard_bg_lightness());

			soulshard_bg_customImgDir = customizedDir + "/gfx/soulshard/bg" + soulshard_bg_chroma_code + ".png";
			ImageManagerV2.process(soulshard_bg_souceImgDir, soulshard_bg_customImgDir, 0, 0,
					settings.getSoulshard_bg_lightness());
		}
		content = content.replaceAll("var_bg_chroma", soulshard_bg_chroma_code);

		FileManager.stringToFile(targetDir, content);
	}
}
