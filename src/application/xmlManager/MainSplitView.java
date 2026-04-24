package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManager;
import application.util.ImageManagerV2;

public class MainSplitView {

	public static void readMainSplitView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/MainGUI/MainSplitView.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Pattern p;
		Matcher m;

		// <!-- Menu --> 위치 찾기
		int menuIndex = content.indexOf("<!-- Menu -->");

		if (menuIndex != -1) {

			// Menu 이후 부분만 잘라냄
			String menuSection = content.substring(menuIndex);

			p = Pattern.compile("view_layout=\"([^\"]+)\".*?h_local_alignment=\"([^\"]+)\"", Pattern.DOTALL);

			m = p.matcher(menuSection);

			if (m.find()) {
				String viewLayout = m.group(1);
				String hAlign = m.group(2);

				if (viewLayout.equals("horizontal"))
					settings.setMenuIcon_horizontal(true);
				else
					settings.setMenuIcon_horizontal(false);

				if (hAlign.equals("RIGHT"))
					settings.setMenuIcon_right(true);
				else
					settings.setMenuIcon_right(false);
			}
		}

		// 사이드패널 사이즈 추출
		p = Pattern.compile("panel_size_normal=\"Point\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\"");
		m = p.matcher(content);

		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));

			settings.setSidePanelWidth(width);
		}

	}

	public static void writeMainSplitView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/MainGUI/MainSplitView.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 인벤토리 레이아웃별 사이드패널 사이즈
		int sidePanelWidth = settings.getSidePanelWidth();
		int mainSplit_panel_size_x = 389;
		int mainSplit_panel_size_y = 644;
		if (sidePanelWidth < 469) {
			if (settings.getInventory_layout() == 1) {
				mainSplit_panel_size_x = 389;
				mainSplit_panel_size_y = 644;
			} else if (settings.getInventory_layout() == 2) {
				mainSplit_panel_size_x = 405;
				mainSplit_panel_size_y = 684;
			} else if (settings.getInventory_layout() == 3) {
				mainSplit_panel_size_x = 413;
				mainSplit_panel_size_y = 674;
			}
		} else if (sidePanelWidth < 525) {
			mainSplit_panel_size_x = 469;
			mainSplit_panel_size_y = 761;
		} else if (sidePanelWidth < 605) {
			mainSplit_panel_size_x = 525;
			mainSplit_panel_size_y = 859;
		} else {
			mainSplit_panel_size_x = 605;
			mainSplit_panel_size_y = 961;
		}

		content = content.replaceAll("var_panel_size_x", String.valueOf(mainSplit_panel_size_x));
		content = content.replaceAll("var_panel_size_y", String.valueOf(mainSplit_panel_size_y));

		String menu_layout;
		String menu_location_h;
		String shop_layout_borders;
		String inventory_layout_borders;

		if (settings.isMenuIcon_horizontal()) {
			menu_layout = "horizontal";
			shop_layout_borders = "layout_borders=\"Rect(-2,0,0,0)\"";
		} else {
			menu_layout = "vertical";
			shop_layout_borders = "layout_borders=\"Rect(1,0,-1,0)\"";
		}

		if (!settings.isMenuIcon_right()) {
			menu_location_h = "LEFT";
			if (settings.isMenuIcon_horizontal())
				inventory_layout_borders = "layout_borders=\"Rect(2,0,-1,0)\"";
			else
				inventory_layout_borders = "layout_borders=\"Rect(0,0,-1,1)\"";
		} else {
			menu_location_h = "RIGHT";
			if (settings.isMenuIcon_horizontal())
				inventory_layout_borders = "layout_borders=\"Rect(0,0,-1,0)\"";
			else
				inventory_layout_borders = "layout_borders=\"Rect(0,0,-1,1)\"";
		}

		content = content.replaceAll("var_menu_layout", menu_layout);
		content = content.replaceAll("var_menu_location_h", menu_location_h);
		content = content.replaceAll("var_shop_layout_borders", shop_layout_borders);
		content = content.replaceAll("var_inventory_layout_borders", inventory_layout_borders);

		// 컬러
		String chroma_code = "";
		String tl_SouceImgDir = customizedDir + "/gfx/borders/tl.png";
		String tl_CustomImgDir = customizedDir + "/gfx/borders/tl.png";
		String t_SouceImgDir = customizedDir + "/gfx/borders/t.png";
		String t_CustomImgDir = customizedDir + "/gfx/borders/t.png";
		String tr_SouceImgDir = customizedDir + "/gfx/borders/tr.png";
		String tr_CustomImgDir = customizedDir + "/gfx/borders/tr.png";
		String l_SouceImgDir = customizedDir + "/gfx/borders/l.png";
		String l_CustomImgDir = customizedDir + "/gfx/borders/l.png";
		String r_SouceImgDir = customizedDir + "/gfx/borders/r.png";
		String r_CustomImgDir = customizedDir + "/gfx/borders/r.png";
		String bl_SouceImgDir = customizedDir + "/gfx/borders/bl.png";
		String bl_CustomImgDir = customizedDir + "/gfx/borders/bl.png";
		String b_SouceImgDir = customizedDir + "/gfx/borders/b.png";
		String b_CustomImgDir = customizedDir + "/gfx/borders/b.png";
		String br_SouceImgDir = customizedDir + "/gfx/borders/br.png";
		String br_CustomImgDir = customizedDir + "/gfx/borders/br.png";
		String bg_SouceImgDir = customizedDir + "/gfx/borders/bg.png";
		String bg_CustomImgDir = customizedDir + "/gfx/borders/bg.png";

		if (settings.getWindows_bg_lightness() == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf((int) settings.getWindows_bg_lightness());

			tl_CustomImgDir = customizedDir + "/gfx/borders/tl" + chroma_code + ".png";
			ImageManagerV2.process(tl_SouceImgDir, tl_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			t_CustomImgDir = customizedDir + "/gfx/borders/t" + chroma_code + ".png";
			ImageManagerV2.process(t_SouceImgDir, t_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			tr_CustomImgDir = customizedDir + "/gfx/borders/tr" + chroma_code + ".png";
			ImageManagerV2.process(tr_SouceImgDir, tr_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			l_CustomImgDir = customizedDir + "/gfx/borders/l" + chroma_code + ".png";
			ImageManagerV2.process(l_SouceImgDir, l_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			r_CustomImgDir = customizedDir + "/gfx/borders/r" + chroma_code + ".png";
			ImageManagerV2.process(r_SouceImgDir, r_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			bl_CustomImgDir = customizedDir + "/gfx/borders/bl" + chroma_code + ".png";
			ImageManagerV2.process(bl_SouceImgDir, bl_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			b_CustomImgDir = customizedDir + "/gfx/borders/b" + chroma_code + ".png";
			ImageManagerV2.process(b_SouceImgDir, b_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			br_CustomImgDir = customizedDir + "/gfx/borders/br" + chroma_code + ".png";
			ImageManagerV2.process(br_SouceImgDir, br_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());
			bg_CustomImgDir = customizedDir + "/gfx/borders/bg" + chroma_code + ".png";
			ImageManagerV2.process(bg_SouceImgDir, bg_CustomImgDir, 0, 0, settings.getWindows_bg_lightness());

		}

		content = content.replaceAll("var_bg_chroma", chroma_code);

		FileManager.stringToFile(targetDir, content);

	}

}
