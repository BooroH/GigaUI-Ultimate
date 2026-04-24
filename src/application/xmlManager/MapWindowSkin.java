package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManager;
import application.util.ImageManagerV2;

public class MapWindowSkin {

	public static void readMapWindowSkin(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/MapGUI/MapWindowSkin.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		// 심플맵
		if (content.contains("name=\"CoordinatesView\"")) {
			settings.setIsSimpleMap(0);
		} else {
			settings.setIsSimpleMap(1);
		}

		// 다크모드 컬러
		Matcher m;
		Pattern p;
		p = Pattern.compile("gfx_tl=\"../../Customized/gfx/borders/map_tl[^\"]*_c_(-?\\d+)_(-?\\d+)_(-?\\d+)\\.png\"");
		m = p.matcher(content);

		if (m.find()) {
			int hue = Integer.parseInt(m.group(1));
			int chroma = Integer.parseInt(m.group(2));
			int lightness = Integer.parseInt(m.group(3));

			settings.setWindows_bg_lightness(lightness);
		} else {
			settings.setWindows_bg_lightness(0);
		}

	}

	public static void writeMapWindowSkin(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/MapGUI/MapWindowSkin.xml";

		String sourceDir;
		String targetDir = customizedDir + defaultDir;

		if (settings.getIsSimpleMap() == 0) {
			sourceDir = "Data/format/Views/MapGUI/MapWindowSkins/DefaultMap/MapWindowSkin.xml";
		} else {
			sourceDir = "Data/format/Views/MapGUI/MapWindowSkins/MinimalMap/MapWindowSkin.xml";
		}

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 타이틀마진
		String mapTitleMargin;
		int fontSize = settings.getFontSize();
		if(fontSize==0) {
			mapTitleMargin = "11";
		}
		else if(fontSize==1) {
			mapTitleMargin = "10";
		}
		else if(fontSize==2) {
			mapTitleMargin = "9";
		}
		else if(fontSize==3) {
			mapTitleMargin = "9";
		}
		else if(fontSize==4) {
			mapTitleMargin = "8";
		}
		else if(fontSize==5) {
			mapTitleMargin = "8";
		}
		else {
			mapTitleMargin = "7";
		}
		content = content.replaceAll("var_title_margin", mapTitleMargin);

		// 컬러
		String chroma_code = "";
		String map_tl_SouceImgDir = customizedDir + "/gfx/borders/map_tl.png";
		String map_tl_CustomImgDir = customizedDir + "/gfx/borders/map_tl.png";
		String map_t_SouceImgDir = customizedDir + "/gfx/borders/map_t.png";
		String map_t_CustomImgDir = customizedDir + "/gfx/borders/map_t.png";
		String map_tr_SouceImgDir = customizedDir + "/gfx/borders/map_tr.png";
		String map_tr_CustomImgDir = customizedDir + "/gfx/borders/map_tr.png";
		String map_lr_SouceImgDir = customizedDir + "/gfx/borders/map_lr.png";
		String map_lr_CustomImgDir = customizedDir + "/gfx/borders/map_lr.png";
		String map_bl_minimal_SouceImgDir = customizedDir + "/gfx/borders/map_bl_minimal.png";
		String map_bl_minimal_CustomImgDir = customizedDir + "/gfx/borders/map_bl_minimal.png";
		String map_b_minimal_SouceImgDir = customizedDir + "/gfx/borders/map_b_minimal.png";
		String map_b_minimal_CustomImgDir = customizedDir + "/gfx/borders/map_b_minimal.png";
		String map_br_minimal_SouceImgDir = customizedDir + "/gfx/borders/map_br_minimal.png";
		String map_br_minimal_CustomImgDir = customizedDir + "/gfx/borders/map_br_minimal.png";
		String map_bl_default_SouceImgDir = customizedDir + "/gfx/borders/map_bl_default.png";
		String map_bl_default_CustomImgDir = customizedDir + "/gfx/borders/map_bl_default.png";
		String map_b_default_SouceImgDir = customizedDir + "/gfx/borders/map_b_default.png";
		String map_b_default_CustomImgDir = customizedDir + "/gfx/borders/map_b_default.png";
		String map_br_default_SouceImgDir = customizedDir + "/gfx/borders/map_br_default.png";
		String map_br_default_CustomImgDir = customizedDir + "/gfx/borders/map_br_default.png";
		if (settings.getWindows_bg_lightness() == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf((int) settings.getWindows_bg_lightness());

			map_tl_CustomImgDir = customizedDir + "/gfx/borders/map_tl" + chroma_code + ".png";
			ImageManagerV2.process(map_tl_SouceImgDir, map_tl_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			map_t_CustomImgDir = customizedDir + "/gfx/borders/map_t" + chroma_code + ".png";
			ImageManagerV2.process(map_t_SouceImgDir, map_t_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			map_tr_CustomImgDir = customizedDir + "/gfx/borders/map_tr" + chroma_code + ".png";
			ImageManagerV2.process(map_tr_SouceImgDir, map_tr_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			map_lr_CustomImgDir = customizedDir + "/gfx/borders/map_lr" + chroma_code + ".png";
			ImageManagerV2.process(map_lr_SouceImgDir, map_lr_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			map_bl_minimal_CustomImgDir = customizedDir + "/gfx/borders/map_bl_minimal" + chroma_code + ".png";
			ImageManagerV2.process(map_bl_minimal_SouceImgDir, map_bl_minimal_CustomImgDir, 0, 0,
					settings.getWindows_bg_lightness());
			map_b_minimal_CustomImgDir = customizedDir + "/gfx/borders/map_b_minimal" + chroma_code + ".png";
			ImageManagerV2.process(map_b_minimal_SouceImgDir, map_b_minimal_CustomImgDir, 0, 0,
					settings.getWindows_bg_lightness());
			map_br_minimal_CustomImgDir = customizedDir + "/gfx/borders/map_br_minimal" + chroma_code + ".png";
			ImageManagerV2.process(map_br_minimal_SouceImgDir, map_br_minimal_CustomImgDir, 0, 0,
					settings.getWindows_bg_lightness());
			map_bl_default_CustomImgDir = customizedDir + "/gfx/borders/map_bl_default" + chroma_code + ".png";
			ImageManagerV2.process(map_bl_default_SouceImgDir, map_bl_default_CustomImgDir, 0, 0,
					settings.getWindows_bg_lightness());
			map_b_default_CustomImgDir = customizedDir + "/gfx/borders/map_b_default" + chroma_code + ".png";
			ImageManagerV2.process(map_b_default_SouceImgDir, map_b_default_CustomImgDir, 0, 0,
					settings.getWindows_bg_lightness());
			map_br_default_CustomImgDir = customizedDir + "/gfx/borders/map_br_default" + chroma_code + ".png";
			ImageManagerV2.process(map_br_default_SouceImgDir, map_br_default_CustomImgDir, 0, 0,
					settings.getWindows_bg_lightness());
		}

		content = content.replaceAll("var_bg_chroma", chroma_code);

		FileManager.stringToFile(targetDir, content);

	}
}
