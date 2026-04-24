package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.ImageManager;
import application.util.ImageManagerV2;

public class BottomBar {

	public static void readBottomBar(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/BottomBar.xml";
		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		// 알파추출
		p = Pattern.compile("slot_bg_gfx\\s*=\\s*\"[^\"]*a(\\d+)");
		m = p.matcher(content);

		if (m.find()) {
			int value = Integer.parseInt(m.group(1));
			settings.setSlot_bg_alpha(value);
		}

		// icon_spacing 추출
		m = Pattern.compile("icon_spacing=\"(-?\\d+)\"").matcher(content);
		if (m.find()) {
			settings.setIcon_spacing(Integer.parseInt(m.group(1)));
		}

		p = Pattern.compile("slot_size=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);

		int index = 0;
		while (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));

			if (index == 0) {
				// 첫 번째 slot_size 값
				settings.setSlot_size(width);
			} else if (index == 1) {
				// 두 번째 slot_size 값
				settings.setPerk_size(width);
			} else if (index == 2) {
				// 세 번째 slot_size 값
				settings.setDirSlot_size(width);
			}
			index++;
		}

		p = Pattern.compile("<!--\\s*(\\d+)x(\\d+)\\s*-->");
		m = p.matcher(content);

		if (m.find()) {
			settings.setBottomBar_row(Integer.parseInt(m.group(1)));
			settings.setBottomBar_column(Integer.parseInt(m.group(2)));
		}

		int perkIndex = content.indexOf("<template:PerkView");
		if (perkIndex != -1) {
			// PerkView 이전의 ShortcutView 블록 찾기
			Pattern shortcutPattern = Pattern.compile("<template:ShortcutView[^>]*>(.*?)</template:ShortcutView>",
					Pattern.DOTALL);
			Matcher shortcutMatcher = shortcutPattern.matcher(content.substring(0, perkIndex));

			String lastShortcutBlock = null;
			while (shortcutMatcher.find()) {
				lastShortcutBlock = shortcutMatcher.group(0); // 마지막 ShortcutView 블록 저장
			}

			if (lastShortcutBlock != null) {
				// view_flags="SBF_HIDE_EMPTY_SLOTS" 존재 여부
				if (lastShortcutBlock.contains("view_flags=\"SBF_HIDE_EMPTY_SLOTS\"")) {
					settings.setHideEmptySlots(true);
				} else
					settings.setHideEmptySlots(false);
			}
		}

		// bottombar 높이 추출
		p = Pattern.compile(
				"<View\\s+view_layout=\"horizontal\"\\s+v_alignment=\"BOTTOM\"\\s+layout_borders=\"Rect\\((-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)\\)\"[\\s\\S]*?<!--\\s*Left Section\\s*-->",
				Pattern.MULTILINE);
		m = p.matcher(content);
		if (m.find())
			settings.setBottomBar_Pos_Y(Integer.parseInt(m.group(4)));

		// left portrait 레이아웃 추출
		p = Pattern.compile(
				"<View\\s+name=\"LPortraitViewDock\"[^>]*layout_borders=\"Rect\\((-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			int first = Integer.parseInt(m.group(1));
			int second = Integer.parseInt(m.group(2));
			int third = Integer.parseInt(m.group(3));
			int fourth = Integer.parseInt(m.group(4));
			settings.setLPortrait_x(-(third / 2));
			settings.setLPortrait_y(fourth);
		}

		// rp 레이아웃
		p = Pattern.compile(
				"<View\\s+name=\"RPortraitViewDock\"[^>]*layout_borders=\"Rect\\((-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			int first = Integer.parseInt(m.group(1));
			int second = Integer.parseInt(m.group(2));
			int third = Integer.parseInt(m.group(3));
			int fourth = Integer.parseInt(m.group(4));
			settings.setRPortrait_x(first / 2);
			settings.setRPortrait_y(fourth);
		}

		p = Pattern.compile(
				"<SoulFragmentBar\\s+name=\"MovableSoulFragmentBar\"[^>]*layout_borders=\"Rect\\((-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			int first = Integer.parseInt(m.group(1));
			int second = Integer.parseInt(m.group(2));
			int third = Integer.parseInt(m.group(3));
			int fourth = Integer.parseInt(m.group(4));
			settings.setSoulshard_x((first - third) / 2);
			settings.setSoulshard_y(fourth);
		}

		// 미니슬롯
		if (content.contains("SideBar1_4")) {

			p = Pattern.compile("<ShortcutView\\s+name=\"SideBar1_4\"([^>]*)>", Pattern.DOTALL);
			m = p.matcher(content);

			if (m.find()) {
				String blockContent = m.group(1);

				// 블록 안에서 slot_count 추출
				Pattern countPattern = Pattern.compile("slot_count=\"(\\d+)\"");
				Matcher countMatcher = countPattern.matcher(blockContent);

				if (countMatcher.find()) {
					settings.setMiniSlot_count(Integer.parseInt(countMatcher.group(1)));
				}

				Pattern posPattern = Pattern
						.compile("layout_borders=\"Rect\\((-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)\\)\"");
				Matcher posMatcher = posPattern.matcher(blockContent);
				if (posMatcher.find()) {
					int first = Integer.parseInt(posMatcher.group(1));
					int second = Integer.parseInt(posMatcher.group(2));
					int third = Integer.parseInt(posMatcher.group(3));
					int fourth = Integer.parseInt(posMatcher.group(4));
					settings.setMiniSlot_pos_x((first - third) / 2);
					settings.setMiniSlot_pos_y(fourth);
				}

			}

			// "RPortraitViewDock" 시작 위치
			int startIndex = content.indexOf("name=\"RPortraitViewDock\"");
			// "SideBar1_4" 시작 위치
			int endIndex = content.indexOf("name=\"SideBar1_4\"");

			if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
				// 해당 구간 추출
				String block = content.substring(startIndex, endIndex);

				// slot_size="Point(x,y)" 추출
				Pattern sizePattern = Pattern.compile("slot_size=\"Point\\((\\d+),(\\d+)\\)\"");
				Matcher sizeMatcher = sizePattern.matcher(block);
				if (sizeMatcher.find()) {
					settings.setMiniSlot_size(Integer.parseInt(sizeMatcher.group(1)));
				}

				// view_flags="SBF_HIDE_EMPTY_SLOTS" 포함 여부 확인
				if (block.contains("view_flags=\"SBF_HIDE_EMPTY_SLOTS\"")) {
					settings.setMiniSlotHide(true);
				} else {
					settings.setMiniSlotHide(false);
				}
			}

		}

		// 컬러
		p = Pattern.compile("slot_bg_gfx=\"[^\"]*_c_(-?\\d+)_(-?\\d+)_(-?\\d+)\\.png\"");
		m = p.matcher(content);

		if (m.find()) {
			int hue = Integer.parseInt(m.group(1));
			int chroma = Integer.parseInt(m.group(2));
			int lightness = Integer.parseInt(m.group(3));

			settings.setSlot_bg_lightness(lightness);
		} else {

			settings.setSlot_bg_lightness(0);
		}

	}

	public static void writeBottomBar(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/BottomBar.xml";

		String row = String.valueOf(settings.getBottomBar_row());
		String column = String.valueOf(settings.getBottomBar_column());

		String sourceDir = "Data/format/Views/HUD/Layouts/" + row + "x" + column + "/BottomBar.xml";
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		int slot_size = settings.getSlot_size();
		int dir_size = slot_size + 2;
		String slot_img_name = "a" + String.valueOf(settings.getSlot_bg_alpha());
		String slot_dir_img_name = "dir_a" + String.valueOf(settings.getSlot_bg_alpha());
		String chroma_code = "";
		String slot_margin = "";
		String dir_margin = "";

		// 슬롯마진
		if (slot_size > 84) {
			slot_margin = "11";
		} else if (slot_size > 76) {
			slot_margin = "10";
		} else if (slot_size > 68) {
			slot_margin = "9";
		} else if (slot_size > 60) {
			slot_margin = "8";
		} else if (slot_size > 52) {
			slot_margin = "7";
		} else if (slot_size > 42) {
			slot_margin = "6";
		} else if (slot_size > 34) {
			slot_margin = "5";
		} else {
			slot_margin = "4";
		}

		// dir마진
		if (dir_size > 84) {
			dir_margin = "11";
		} else if (dir_size > 76) {
			dir_margin = "10";
		} else if (dir_size > 68) {
			dir_margin = "9";
		} else if (dir_size > 60) {
			dir_margin = "8";
		} else if (dir_size > 52) {
			dir_margin = "7";
		} else if (dir_size > 42) {
			dir_margin = "6";
		} else if (dir_size > 34) {
			dir_margin = "5";
		} else {
			dir_margin = "4";
		}

		// 미니슬롯마진
		if (settings.getMiniSlot_size() > 84) {
			settings.setMiniSlot_margin(11);
		} else if (settings.getMiniSlot_size() > 76) {
			settings.setMiniSlot_margin(10);
		} else if (settings.getMiniSlot_size() > 68) {
			settings.setMiniSlot_margin(9);
		} else if (settings.getMiniSlot_size() > 60) {
			settings.setMiniSlot_margin(8);
		} else if (settings.getMiniSlot_size() > 52) {
			settings.setMiniSlot_margin(7);
		} else if (settings.getMiniSlot_size() > 42) {
			settings.setMiniSlot_margin(6);
		} else if (settings.getMiniSlot_size() > 34) {
			settings.setMiniSlot_margin(5);
		} else {
			settings.setMiniSlot_margin(4);
		}

		content = content.replaceAll("var_icon_spacing", String.valueOf(settings.getIcon_spacing()));
		content = content.replaceAll("var_slot_size", String.valueOf(slot_size));
		content = content.replaceAll("var_perk_size",
				String.valueOf(slot_size - (Integer.parseInt(slot_margin) - 2) * 2));
		content = content.replaceAll("var_dirSlot_size", String.valueOf(slot_size + 2));

		content = content.replaceAll("var_slot_margin", slot_margin);
		content = content.replaceAll("var_dir_margin", dir_margin);

		// 컬러
		String slotSouceImgDir = customizedDir + "/gfx/bottombar/" + slot_img_name + ".png";
		String slotCustomImgDir = customizedDir + "/gfx/bottombar/" + slot_img_name + ".png";
		String slotDirSouceImgDir = customizedDir + "/gfx/bottombar/" + slot_dir_img_name + ".png";
		String slotDirCustomImgDir = customizedDir + "/gfx/bottombar/" + slot_dir_img_name + ".png";
		if (settings.getSlot_bg_lightness() == 0) {
			chroma_code = "";
		} else {
			chroma_code = "_c_0_0_" + String.valueOf((int) settings.getSlot_bg_lightness());

			slotCustomImgDir = customizedDir + "/gfx/bottombar/" + slot_img_name + chroma_code + ".png";
			slotDirCustomImgDir = customizedDir + "/gfx/bottombar/" + slot_dir_img_name + chroma_code + ".png";
			ImageManagerV2.process(slotSouceImgDir, slotCustomImgDir, 0, 0, settings.getSlot_bg_lightness());
			ImageManagerV2.process(slotDirSouceImgDir, slotDirCustomImgDir, 0, 0, settings.getSlot_bg_lightness());
		}

		content = content.replaceAll("var_slot_img", slot_img_name + chroma_code + ".png");
		content = content.replaceAll("var_dirSlot_img", slot_dir_img_name + chroma_code + ".png");

		int perkSwitchButtonPosX = -18 + 2
				* ((settings.getSlot_size() + 1 + settings.getIcon_spacing()) * (settings.getBottomBar_column() - 1) / 2
						+ 3)
				+ slot_size - 47 + (6 - Integer.valueOf(slot_margin)) * 2;
		int pageButtonPosY;

		if (settings.getBottomBar_row() == 2) {
			pageButtonPosY = -21
					+ (settings.getSlot_size() + 1 + settings.getIcon_spacing()) * (settings.getBottomBar_row() - 1)
					+ (settings.getSlot_size() + 1) / 2;
		} else {
			pageButtonPosY = -20
					+ (settings.getSlot_size() + 1 + settings.getIcon_spacing()) * (settings.getBottomBar_row() - 1)
					+ (settings.getSlot_size() + 1) / 2;
		}

		content = content.replaceAll("var_perk_switch_button_pos_x", String.valueOf(perkSwitchButtonPosX));
		content = content.replaceAll("var_page_button_pos_y", String.valueOf(pageButtonPosY));

		// 바텀바높이
		content = content.replaceAll("var_bottombar_pos_y", String.valueOf(settings.getBottomBar_Pos_Y()));

		if (!settings.isHideEmptySlots()) {
			content = content.replaceAll("var_hide_empty_slots", "");
		} else {
			content = content.replaceAll("var_hide_empty_slots", " view_flags=\"SBF_HIDE_EMPTY_SLOTS\"");
		}

		// 포트레잇 레이아웃
		int portraits_layout_bottom_diff;
		int hp_height = settings.getLPortrait_hp_height();
		int target_category_tag_size;
		int tot_height;
		if (hp_height == 55) {
			tot_height = 38;
			target_category_tag_size = 23;
		} else if (hp_height == 51) {
			tot_height = 35;
			target_category_tag_size = 22;
		} else if (hp_height == 49) {
			tot_height = 34;
			target_category_tag_size = 21;
		} else if (hp_height == 45) {
			tot_height = 31;
			target_category_tag_size = 19;
		} else if (hp_height == 43) {
			tot_height = 30;
			target_category_tag_size = 18;
		} else if (hp_height == 39) {
			tot_height = 27;
			target_category_tag_size = 17;
		} else if (hp_height == 35) {
			tot_height = 24;
			target_category_tag_size = 15;
		} else if (hp_height == 33) {
			tot_height = 23;
			target_category_tag_size = 14;
		} else if (hp_height == 29) {
			tot_height = 20;
			target_category_tag_size = 11;
		} else {
			tot_height = 41;
			target_category_tag_size = 25;
		}

		int font_size = settings.getFontSize();
		int font_spacing_adjust;
		if(font_size==0) {
			font_spacing_adjust = 0;
		}
		else if(font_size==1) {
			font_spacing_adjust = -1;
		}
		else if(font_size==2) {
			font_spacing_adjust = -1;
		}

		else if(font_size==3) {
			font_spacing_adjust = -1;
		}

		else if(font_size==4) {
			font_spacing_adjust = -1;
		}

		else if(font_size==5) {
			font_spacing_adjust = -1;
		}

		else {
			font_spacing_adjust = -1;
		}


		// 폰트사이즈+15(디폴트 라지볼드)+5(마진) - 9(레이아웃)
		portraits_layout_bottom_diff = (tot_height + 1) + target_category_tag_size + (font_size + 10 + font_spacing_adjust)
				+ settings.getToT_layout_top();

		content = content.replaceAll("var_lp_pos_x", String.valueOf(-2 * settings.getLPortrait_x()));
		content = content.replaceAll("var_rp_pos_x", String.valueOf(2 * settings.getRPortrait_x()));
		content = content.replaceAll("var_lp_pos_y",
				String.valueOf(settings.getLPortrait_y() + portraits_layout_bottom_diff));
		content = content.replaceAll("var_rp_pos_y", String.valueOf(settings.getRPortrait_y()));

		// 어새신 소울샤드
		if (settings.getSoulshard_x() < 0) {
			content = content.replaceAll("var_soulshard_pos_x1", String.valueOf(0));
			content = content.replaceAll("var_soulshard_pos_x2", String.valueOf(-2 * settings.getSoulshard_x()));
		} else if (settings.getSoulshard_x() > 0) {
			content = content.replaceAll("var_soulshard_pos_x1", String.valueOf(2 * settings.getSoulshard_x()));
			content = content.replaceAll("var_soulshard_pos_x2", String.valueOf(0));
		} else {
			content = content.replaceAll("var_soulshard_pos_x1", String.valueOf(0));
			content = content.replaceAll("var_soulshard_pos_x2", String.valueOf(0));
		}

		content = content.replaceAll("var_soulshard_pos_y", String.valueOf(settings.getSoulshard_y()));

		int miniSlot_x1 = 0;
		int miniSlot_x2 = 0;
		if (settings.getMiniSlot_pos_x() < 0) {
			miniSlot_x1 = 0;
			miniSlot_x2 = -2 * settings.getMiniSlot_pos_x();
		} else if (settings.getMiniSlot_pos_x() > 0) {
			miniSlot_x1 = 2 * settings.getMiniSlot_pos_x();
			miniSlot_x2 = 0;
		}

		// 빈슬롯 숨김여부
		String miniSlot_hide = "";

		if (!settings.isHideEmptySlots()) {
			if (settings.isMiniSlotHide()) {
				miniSlot_hide = " view_flags=\"SBF_HIDE_EMPTY_SLOTS\"";
			}
		}

		if (settings.getMiniSlot_count() == 0) {
			content = content.replaceAll("var_mini_slot", "");
		} else {
			if (settings.getBottomBar_column() < 19) {
				content = content.replaceAll("var_mini_slot",
						"<template:ShortcutView icon_spacing=\"" + String.valueOf(settings.getMiniSlot_spacing()) + "\""
								+ miniSlot_hide + ">\r\n" + "			<template:ItemSlotView\r\n"
								+ "				slot_size=\"Point(" + String.valueOf(settings.getMiniSlot_size()) + ","
								+ String.valueOf(settings.getMiniSlot_size()) + ")\"\r\n"
								+ "				icon_borders=\"Point(" + String.valueOf(settings.getMiniSlot_margin())
								+ "," + String.valueOf(settings.getMiniSlot_margin()) + ")\"\r\n"
								+ "				template:name=\"SlotTemplate\"\r\n" + "			/>\r\n"
								+ "		</template:ShortcutView>\r\n" + "		<ShortcutView name=\"SideBar1_4\"\r\n"
								+ "			first_slot=\"20015\"\r\n" + "			slot_count=\""
								+ String.valueOf(settings.getMiniSlot_count()) + "\"\r\n"
								+ "			hotkeyrange_start=\"Shortcutbar_Extra1_16\"\r\n"
								+ "			hotkeyrange_length=\"" + String.valueOf(settings.getMiniSlot_count())
								+ "\"\r\n" + "			layout_borders=\"Rect(" + String.valueOf(miniSlot_x1) + ",0,"
								+ String.valueOf(miniSlot_x2) + "," + String.valueOf(settings.getMiniSlot_pos_y())
								+ ")\"\r\n" + "			\r\n" + "		/>");
			} else {
				String sideBar2 = "";
				if (settings.getBottomBar_column() == 19) {
					sideBar2 = "1";
				} else if (settings.getBottomBar_column() > 19 && settings.getBottomBar_column() < 27) {
					sideBar2 = "2";
				} else if (settings.getBottomBar_column() == 27) {
					sideBar2 = "3";
				}
				content = content.replaceAll("var_mini_slot",
						"<template:ShortcutView icon_spacing=\"" + String.valueOf(settings.getMiniSlot_spacing()) + "\""
								+ miniSlot_hide + ">\r\n" + "			<template:ItemSlotView\r\n"
								+ "				slot_size=\"Point(" + String.valueOf(settings.getMiniSlot_size()) + ","
								+ String.valueOf(settings.getMiniSlot_size()) + ")\"\r\n"
								+ "				icon_borders=\"Point(" + String.valueOf(settings.getMiniSlot_margin())
								+ "," + String.valueOf(settings.getMiniSlot_margin()) + ")\"\r\n"
								+ "				template:name=\"SlotTemplate\"\r\n" + "			/>\r\n"
								+ "		</template:ShortcutView>\r\n" + "		<ShortcutView name=\"SideBar2_"
								+ sideBar2 + "\"\r\n" + "			first_slot=\"20115\"\r\n"
								+ "			slot_count=\"" + String.valueOf(settings.getMiniSlot_count()) + "\"\r\n"
								+ "			hotkeyrange_start=\"Shortcutbar_Extra2_16\"\r\n"
								+ "			hotkeyrange_length=\"" + String.valueOf(settings.getMiniSlot_count())
								+ "\"\r\n" + "			layout_borders=\"Rect(" + String.valueOf(miniSlot_x1) + ",0,"
								+ String.valueOf(miniSlot_x2) + "," + String.valueOf(settings.getMiniSlot_pos_y())
								+ ")\"\r\n" + "			\r\n" + "		/>");
			}
		}

		// 폰트사이즈별 최적화
		if (settings.getFontSize() == 0) {
			// 페이지 버튼 마진
			content = content.replaceAll("var_page_margin", " layout_borders=\"Rect(0,-1,0,-1)\"");
		} else if (settings.getFontSize() == 1) {
			content = content.replaceAll("var_page_margin", " layout_borders=\"Rect(0,-2,0,-2)\"");
		} else if (settings.getFontSize() == 2) {
			content = content.replaceAll("var_page_margin", " layout_borders=\"Rect(0,-2,0,-2)\"");
		} else if (settings.getFontSize() == 3) {
			content = content.replaceAll("var_page_margin", " layout_borders=\"Rect(0,-3,0,-2)\"");
		} else {
			content = content.replaceAll("var_page_margin", " layout_borders=\"Rect(0,-3,0,-3)\"");
		}

		FileManager.stringToFile(targetDir, content);
	}
}
