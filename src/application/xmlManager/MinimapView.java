package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManager;
import application.util.ImageManagerV2;

public class MinimapView {

	public static void readMinimapView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/MapGUI/MinimapView.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		if (content.contains("name=\"MapView\"")) {
			settings.setShowMiniMap(1);
		} else {
			settings.setShowMiniMap(0);
		}

		// 사이즈
		p = Pattern.compile(
				"<BitmapView bitmap_gfx=\"../../Customized/gfx/minimap/[\\s\\S]*?max_size_limit=\"Point\\((\\d+),(\\d+)\\)\"[\\s\\S]*?>",
				Pattern.CASE_INSENSITIVE);
		m = p.matcher(content);

		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));

			settings.setMinimap_size(width);
		} else {
			if (settings.getShowMiniMap() == 1)
				settings.setMinimap_size(404);
		}

		// 컬러
		p = Pattern.compile(
				"bitmap_gfx=\"../../Customized/gfx/minimap/minimap[^\"]*_c_(-?\\d+)_(-?\\d+)_(-?\\d+)\\.png\"");
		m = p.matcher(content);

		if (m.find()) {
			int hue = Integer.parseInt(m.group(1));
			int chroma = Integer.parseInt(m.group(2));
			int lightness = Integer.parseInt(m.group(3));

			settings.setMinimap_bg_lightness(lightness);
		} else {
			settings.setMinimap_bg_lightness(0);
		}

	}

	public static void writeMinimapView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/MapGUI/MinimapView.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		String chroma_code = "";
		// 컬러
		String minimapSouceImgDir = customizedDir + "/gfx/minimap/minimap.png";
		String minimapCustomImgDir = customizedDir + "/gfx/minimap/minimap.png";
		if (settings.getMinimap_bg_lightness() == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf((int) settings.getMinimap_bg_lightness());

			minimapCustomImgDir = customizedDir + "/gfx/minimap/minimap" + chroma_code + ".png";
			ImageManagerV2.process(minimapSouceImgDir, minimapCustomImgDir, 0, 0, settings.getMinimap_bg_lightness());
		}

		int frameSize = settings.getMinimap_size();
		int mapSize;
		String frameSizeLimit = " max_size_limit=\"Point(" + String.valueOf(frameSize) + "," + String.valueOf(frameSize)
				+ ")\"";
		String menuButtonLayoutY = "";
		String regionalMapLayoutX = "";
		
		if (frameSize == 404) {
			frameSizeLimit = "";
		}

		if (frameSize < 230) {
			mapSize = frameSize - 14;
			menuButtonLayoutY = "-9";
			regionalMapLayoutX = "-26";
		} else if (frameSize < 254) {
			mapSize = frameSize - 16;
			menuButtonLayoutY = "-8";
			regionalMapLayoutX = "-25";
		} else if (frameSize < 282) {
			mapSize = frameSize - 18;
			menuButtonLayoutY = "-8";
			regionalMapLayoutX = "-25";
		} else if (frameSize < 310) {
			mapSize = frameSize - 20;
			menuButtonLayoutY = "-7";
			regionalMapLayoutX = "-24";
		} else if (frameSize < 334) {
			mapSize = frameSize - 22;
			menuButtonLayoutY = "-7";
			regionalMapLayoutX = "-24";
		} else if (frameSize < 362) {
			mapSize = frameSize - 24;
			menuButtonLayoutY = "-6";
			regionalMapLayoutX = "-23";
		} else if (frameSize < 390) {
			mapSize = frameSize - 26;
			menuButtonLayoutY = "-6";
			regionalMapLayoutX = "-23";
		} else {
			mapSize = frameSize - 28;
			menuButtonLayoutY = "-5";
			regionalMapLayoutX = "-22";
		}

		// 미니맵
		String minimapView_code;
		if (settings.getShowMiniMap() == 1) {
			minimapView_code = "<View view_layout=\"stacked\" waypoint_cap_radius=\"65\" layout_inner_borders=\"Rect(10,34,12,0)\">\r\n"
					+ "	<RegionMapRenderer name=\"MapView\"\r\n"
					+ "		view_flags=\"RMRF_DISABLE_WAYPOINT_LABELS\"\r\n" + "		min_size_extend=\"Point("
					+ String.valueOf(mapSize) + "," + String.valueOf(mapSize) + ")\"\r\n"
					+ "		max_size_limit=\"Point(-1,-1)\"\r\n" + "		zoom_level_count=\"5\"\r\n"
					+ "		min_meter_per_pixel=\"4\"\r\n" + "		max_meter_per_pixel=\"100\"\r\n" + "	/>\r\n"
					+ "	<BitmapView bitmap_gfx=\"../../Customized/gfx/minimap/minimap" + chroma_code + ".png\""
					+ frameSizeLimit + "/>\r\n"
					+ "	<View view_layout=\"vertical\" h_local_alignment=\"RIGHT\" layout_borders=\"Rect(0,0,-29,0)\">\r\n"
					+ "		<Button name=\"ZoomInButton\" template:source=\"ClearButton\" label=\"+\" label_font=\"SC_LARGE_BOLD\" layout_borders=\"Rect(-6,0,0,-5)\" interaction_borders=\"Rect(6,0,0,5)\"/>\r\n"
					+ "		<Button name=\"ZoomOutButton\" template:source=\"ClearButton\" label=\"-\" label_font=\"SC_LARGE_BOLD\" layout_borders=\"Rect(-6,-5,0,0)\" interaction_borders=\"Rect(6,5,0,0)\"/>\r\n"
					+ "	</View>\r\n"
					+ "	<Button name=\"MenuButton\" template:source=\"ClearButton\" label=\"⌃\" label_font=\"SC_LARGE_BOLD\" v_local_alignment=\"TOP\" layout_borders=\"Rect(0,"+menuButtonLayoutY+",0,0)\"/>\r\n"
					+ "	<Button name=\"RegionMapButton\" template:source=\"ClearButton\" label=\"<\" label_font=\"SC_LARGE_BOLD\" h_local_alignment=\"LEFT\" layout_borders=\"Rect("+regionalMapLayoutX+",0,0,0)\" view_tooltip_text=\"&lt;localized token=MinimapToggleRegionButtonTooltip&gt;\"/>\r\n"
					+ "	<View view_layout=\"vertical\" v_local_alignment=\"TOP\" h_local_alignment=\"RIGHT\" h_alignment=\"RIGHT\" layout_borders=\"Rect(0,-4,-6,0)\">\r\n"
					+ "		<BitmapView name=\"NewMailButton\"\r\n"
					+ "			bitmap_gfx=\"../../Customized/gfx/icons/mail.png\"\r\n"
					+ "			view_tooltip_text=\"&lt;localized token=YouVeGotNewMail category=Tradepost /&gt;\"\r\n"
					+ "			view_flags=\"WID_IGNORE_WHEN_HIDDEN\"\r\n"
					+ "			layout_borders=\"Rect(0,0,0,4)\"\r\n" + "		/>\r\n"
					+ "		<Button name=\"PendingQuestReward\"\r\n"
					+ "			gfxid_raised=\"../../Customized/gfx/icons/pending.png\"\r\n"
					+ "			gfxid_highlight=\"../../Customized/gfx/icons/pending_hover.png\"\r\n"
					+ "			view_tooltip_text=\"Inventory is full\"\r\n" + "		/>\r\n" + "	</View>\r\n"
					+ "</View>";
		} else {
			minimapView_code = "<View view_layout=\"vertical\" h_alignment=\"RIGHT\">\r\n"
					+ "	<Button name=\"MenuButton\" template:source=\"ClearButton\" label=\"Instance\" label_font=\"SC_BOLD\" layout_borders=\"Rect(-17,0,-16,0)\" interaction_borders=\"Rect(17,0,16,0)\"/>\r\n"
					+ "	<BitmapView name=\"NewMailButton\"\r\n"
					+ "		bitmap_gfx=\"../../Customized/gfx/icons/mail.png\"\r\n"
					+ "		view_tooltip_text=\"&lt;localized token=YouVeGotNewMail category=Tradepost /&gt;\"\r\n"
					+ "		view_flags=\"WID_IGNORE_WHEN_HIDDEN\"\r\n" + "		layout_borders=\"Rect(0,0,6,3)\"\r\n"
					+ "	/>\r\n" + "	<Button name=\"PendingQuestReward\"\r\n"
					+ "		gfxid_raised=\"../../Customized/gfx/icons/pending.png\"\r\n"
					+ "		gfxid_highlight=\"../../Customized/gfx/icons/pending_hover.png\"\r\n"
					+ "		view_tooltip_text=\"Inventory is full\"\r\n"
					+ "		layout_borders=\"Rect(0,0,6,0)\"\r\n" + "	/>\r\n" + "</View>";
		}

		content = content.replaceAll("var_minimapView_code", minimapView_code);

		FileManager.stringToFile(targetDir, content);

	}
}
