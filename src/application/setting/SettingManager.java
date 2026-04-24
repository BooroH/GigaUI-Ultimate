package application.setting;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import application.xmlManager.*;

public class SettingManager {

	public static void saveProfile(File file) {
		Settings settings = Settings.getInstance();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("icon_spacing=").append(settings.getIcon_spacing()).append("\n");
			sb.append("slot_size=").append(settings.getSlot_size()).append("\n");
			sb.append("perk_size=").append(settings.getPerk_size()).append("\n");
			sb.append("dirSlot_size=").append(settings.getDirSlot_size()).append("\n");
			sb.append("bottomBar_row=").append(settings.getBottomBar_row()).append("\n");
			sb.append("bottomBar_column=").append(settings.getBottomBar_column()).append("\n");
			sb.append("isHideEmptySlots=").append(settings.isHideEmptySlots()).append("\n");
			sb.append("bottomBar_Pos_Y=").append(settings.getBottomBar_Pos_Y()).append("\n");
			sb.append("lPortrait_x=").append(settings.getLPortrait_x()).append("\n");
			sb.append("lPortrait_y=").append(settings.getLPortrait_y()).append("\n");
			sb.append("rPortrait_x=").append(settings.getRPortrait_x()).append("\n");
			sb.append("rPortrait_y=").append(settings.getRPortrait_y()).append("\n");
			sb.append("tot_name_layout_bottom=").append(settings.getTot_name_layout_bottom()).append("\n");
			sb.append("lp_buff_size=").append(settings.getLP_buff_size()).append("\n");
			sb.append("lp_buff_spacing=").append(settings.getLP_buff_spacing()).append("\n");
			sb.append("lp_buff_column=").append(settings.getLP_buff_column()).append("\n");
			sb.append("rp_buff_size=").append(settings.getRP_buff_size()).append("\n");
			sb.append("rp_buff_spacing=").append(settings.getRP_buff_spacing()).append("\n");
			sb.append("rp_buff_column=").append(settings.getRP_buff_column()).append("\n");
			sb.append("rp_buff_filter=").append(settings.getRP_buff_filter()).append("\n");
			sb.append("soulshard_x=").append(settings.getSoulshard_x()).append("\n");
			sb.append("soulshard_y=").append(settings.getSoulshard_y()).append("\n");
			sb.append("nameplate_alpha=").append(settings.getNameplate_alpha()).append("\n");
			sb.append("floatingPortrait_bg_alpha=").append(settings.getFloatingPortrait_bg_alpha()).append("\n");
			sb.append("floatingPortrait_show_mana=").append(settings.isFloatingPortrait_show_mana()).append("\n");
			sb.append("floatingPortrait_buff_filter=").append(settings.getFloatingPortrait_buff_filter()).append("\n");
			sb.append("floatingPortrait_buff_size=").append(settings.getFloatingPortrait_buff_size()).append("\n");
			sb.append("floatingPortrait_buff_spacing=").append(settings.getFloatingPortrait_buff_spacing())
					.append("\n");
			sb.append("floatingPortrait_buff_column=").append(settings.getFloatingPortrait_buff_column()).append("\n");
			sb.append("floatingPortrait_hp_hue=").append(settings.getFloatingPortrait_hp_hue()).append("\n");
			sb.append("floatingPortrait_hp_chroma=").append(settings.getFloatingPortrait_hp_chroma()).append("\n");
			sb.append("floatingPortrait_hp_lightness=").append(settings.getFloatingPortrait_hp_lightness())
					.append("\n");
			sb.append("floatingPortrait_hp_height=").append(settings.getFloatingPortrait_hp_height()).append("\n");
			sb.append("miniSlot_count=").append(settings.getMiniSlot_count()).append("\n");
			sb.append("miniSlot_spacing=").append(settings.getMiniSlot_spacing()).append("\n");
			sb.append("miniSlot_margin=").append(settings.getMiniSlot_margin()).append("\n");
			sb.append("miniSlot_size=").append(settings.getMiniSlot_size()).append("\n");
			sb.append("miniSlot_pos_x=").append(settings.getMiniSlot_pos_x()).append("\n");
			sb.append("miniSlot_pos_y=").append(settings.getMiniSlot_pos_y()).append("\n");
			sb.append("isMiniSlotHide=").append(settings.isMiniSlotHide()).append("\n");
			sb.append("isSlotHideForOptionOnly=").append(settings.isSlotHideForOptionOnly()).append("\n");
			sb.append("showMiniMap=").append(settings.getShowMiniMap()).append("\n");
			sb.append("isSimpleMap=").append(settings.getIsSimpleMap()).append("\n");
			sb.append("isCSIHorizontal=").append(settings.getIsCSIHorizontal()).append("\n");
			sb.append("castBar_text_location=").append(settings.getCastBar_text_location()).append("\n");
			sb.append("castBar_alpha=").append(settings.getCastBar_alpha()).append("\n");
			sb.append("castBar_hue=").append(settings.getCastBar_hue()).append("\n");
			sb.append("castBar_chroma=").append(settings.getCastBar_chroma()).append("\n");
			sb.append("castBar_lightness=").append(settings.getCastBar_lightness()).append("\n");
			sb.append("castBar_width=").append(settings.getCastBar_width()).append("\n");
			sb.append("castBar_height=").append(settings.getCastBar_height()).append("\n");
			sb.append("castBar_text_y=").append(settings.getCastBar_text_y()).append("\n");
			sb.append("inventory_layout=").append(settings.getInventory_layout()).append("\n");
			sb.append("inventory_row=").append(settings.getInventory_row()).append("\n");
			sb.append("inventory_column=").append(settings.getInventory_column()).append("\n");
			sb.append("trader_layout=").append(settings.getTrader_layout()).append("\n");
			sb.append("trader_row=").append(settings.getTrader_row()).append("\n");
			sb.append("trader_column=").append(settings.getTrader_column()).append("\n");
			sb.append("fontSize=").append(settings.getFontSize()).append("\n");
			sb.append("cdFontSize=").append(settings.getCDFontSize()).append("\n");
			sb.append("cdFontWeight=").append(settings.getCDFontWeight()).append("\n");
			sb.append("isHPLabelBold=").append(settings.isHPLabelBold()).append("\n");
			sb.append("top_buff_size=").append(settings.getTop_buff_size()).append("\n");
			sb.append("top_buff_spacing=").append(settings.getTop_buff_spacing()).append("\n");
			sb.append("top_buff_column=").append(settings.getTop_buff_column()).append("\n");
			sb.append("petListSize=").append(settings.getPetListSize()).append("\n");
			sb.append("isPetListSizeLimit=").append(settings.isPetListSizeLimit()).append("\n");
			sb.append("petListAlpha=").append(settings.getPetListAlpha()).append("\n");
			sb.append("groupHPColor=").append(settings.getGroupHPColor()).append("\n");
			sb.append("raidHPColor=").append(settings.getRaidHPColor()).append("\n");
			sb.append("overheadHPWidth=").append(settings.getOverheadHPWidth()).append("\n");
			sb.append("overheadHPHeight=").append(settings.getOverheadHPHeight()).append("\n");
			sb.append("overheadHPColor=").append(settings.getOverheadHPColor()).append("\n");
			sb.append("overheadHPAlpha=").append(settings.getOverheadHPAlpha()).append("\n");
			sb.append("overheadFontWeight=").append(settings.getOverheadFontWeight()).append("\n");
			sb.append("overheadFontSize=").append(settings.getOverheadFontSize()).append("\n");
			sb.append("isMenuIcon_right=").append(settings.isMenuIcon_right()).append("\n");
			sb.append("isMenuIcon_horizontal=").append(settings.isMenuIcon_horizontal()).append("\n");
			sb.append("slot_bg_lightness=").append(settings.getSlot_bg_lightness()).append("\n");
			sb.append("castbar_bg_lightness=").append(settings.getCastbar_bg_lightness()).append("\n");
			sb.append("mainPortrait_bg_lightness=").append(settings.getMainPortrait_bg_lightness()).append("\n");
			sb.append("floatingPortrait_bg_lightness=").append(settings.getFloatingPortrait_bg_lightness())
					.append("\n");
			sb.append("minimap_bg_lightness=").append(settings.getMinimap_bg_lightness()).append("\n");
			sb.append("soulshard_bg_lightness=").append(settings.getSoulshard_bg_lightness()).append("\n");
			sb.append("csi_bg_lightness=").append(settings.getCsi_bg_lightness()).append("\n");
			sb.append("windows_bg_lightness=").append(settings.getWindows_bg_lightness()).append("\n");
			sb.append("soulshard_height=").append(settings.getSoulshard_height()).append("\n");
			sb.append("groupHPAlpha=").append(settings.getGroupHPAlpha()).append("\n");
			sb.append("raidHPAlpha=").append(settings.getRaidHPAlpha()).append("\n");
			sb.append("slot_bg_alpha=").append(settings.getSlot_bg_alpha()).append("\n");
			sb.append("minimap_size=").append(settings.getMinimap_size()).append("\n");
			sb.append("sidePanelWidth=").append(settings.getSidePanelWidth()).append("\n");
			sb.append("csi_size=").append(settings.getCsi_size()).append("\n");
			sb.append("csi_bg_alpha=").append(settings.getCsi_bg_alpha()).append("\n");
			sb.append("csi_highlight_alpha=").append(settings.getCsi_highlight_alpha()).append("\n");
			sb.append("csi_gap=").append(settings.getCsi_gap()).append("\n");
			sb.append("group_nameBg_alpha=").append(settings.getGroup_nameBg_alpha()).append("\n");
			sb.append("raid_nameBg_alpha=").append(settings.getRaid_nameBg_alpha()).append("\n");
			sb.append("group_hp_width=").append(settings.getGroup_hp_width()).append("\n");
			sb.append("group_hp_height=").append(settings.getGroup_hp_height()).append("\n");
			sb.append("raid_hp_height=").append(settings.getRaid_hp_height()).append("\n");
			sb.append("raid_hp_width=").append(settings.getRaid_hp_width()).append("\n");
			sb.append("sidebar_slot_size=").append(settings.getSidebar_slot_size()).append("\n");
			sb.append("targetOfTargetX=").append(settings.getTargetOfTargetX()).append("\n");
			sb.append("toT_layout_top=").append(settings.getToT_layout_top()).append("\n");
			sb.append("lPortrait_bg_alpha=").append(settings.getLPortrait_bg_alpha()).append("\n");
			sb.append("lPortrait_hp_height=").append(settings.getLPortrait_hp_height()).append("\n");
			sb.append("isBuffTimerBold=").append(settings.isBuffTimerBold()).append("\n");
			sb.append("isRemoveHelpButton=").append(settings.isRemoveHelpButton()).append("\n");
			sb.append("isRemoveRankButton=").append(settings.isRemoveRankButton()).append("\n");
			sb.append("isRemoveXpBar=").append(settings.isRemoveXpBar()).append("\n");
			sb.append("isCastbarSparkleOn=").append(settings.isCastbarSparkleOn()).append("\n");
			sb.append("buff_stack_loc=").append(settings.getBuff_stack_loc()).append("\n");

			Files.write(file.toPath(), sb.toString().getBytes());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static boolean loadProfile(File file) {
		Settings settings = Settings.getInstance();
		try {
			List<String> lines = Files.readAllLines(file.toPath());
			Set<String> loadedKeys = new HashSet<>();

			for (String line : lines) {
				String[] parts = line.split("=");
				if (parts.length == 2) {
					String key = parts[0].trim();
					String value = parts[1].trim();

					switch (key) {
					case "icon_spacing":
						settings.setIcon_spacing(Integer.parseInt(value));
						break;
					case "slot_size":
						settings.setSlot_size(Integer.parseInt(value));
						loadedKeys.add(key);
						break;
					case "perk_size":
						settings.setPerk_size(Integer.parseInt(value));
						loadedKeys.add(key);
						break;
					case "dirSlot_size":
						settings.setDirSlot_size(Integer.parseInt(value));
						loadedKeys.add(key);
						break;
					case "bottomBar_row":
						settings.setBottomBar_row(Integer.parseInt(value));
						loadedKeys.add(key);
						break;
					case "bottomBar_column":
						settings.setBottomBar_column(Integer.parseInt(value));
						loadedKeys.add(key);
						break;
					case "bottomBar_Pos_Y":
						settings.setBottomBar_Pos_Y(Integer.parseInt(value));
						break;
					case "lPortrait_x":
						settings.setLPortrait_x(Integer.parseInt(value));
						break;
					case "lPortrait_y":
						settings.setLPortrait_y(Integer.parseInt(value));
						break;
					case "rPortrait_x":
						settings.setRPortrait_x(Integer.parseInt(value));
						break;
					case "rPortrait_y":
						settings.setRPortrait_y(Integer.parseInt(value));
						break;
					case "tot_name_layout_bottom":
						settings.setTot_name_layout_bottom(Integer.parseInt(value));
						break;
					case "lp_buff_size":
						settings.setLP_buff_size(Integer.parseInt(value));
						break;
					case "lp_buff_spacing":
						settings.setLP_buff_spacing(Integer.parseInt(value));
						break;
					case "lp_buff_column":
						settings.setLP_buff_column(Integer.parseInt(value));
						break;
					case "rp_buff_size":
						settings.setRP_buff_size(Integer.parseInt(value));
						break;
					case "rp_buff_spacing":
						settings.setRP_buff_spacing(Integer.parseInt(value));
						break;
					case "rp_buff_column":
						settings.setRP_buff_column(Integer.parseInt(value));
						break;
					case "rp_buff_filter":
						settings.setRP_buff_filter(Integer.parseInt(value));
						break;
					case "soulshard_x":
						settings.setSoulshard_x(Integer.parseInt(value));
						break;
					case "soulshard_y":
						settings.setSoulshard_y(Integer.parseInt(value));
						break;
					case "floatingPortrait_buff_filter":
						settings.setFloatingPortrait_buff_filter(Integer.parseInt(value));
						break;
					case "floatingPortrait_buff_size":
						settings.setFloatingPortrait_buff_size(Integer.parseInt(value));
						break;
					case "floatingPortrait_buff_spacing":
						settings.setFloatingPortrait_buff_spacing(Integer.parseInt(value));
						break;
					case "floatingPortrait_buff_column":
						settings.setFloatingPortrait_buff_column(Integer.parseInt(value));
						break;
					case "floatingPortrait_hp_height":
						settings.setFloatingPortrait_hp_height(Integer.parseInt(value));
						break;
					case "miniSlot_count":
						settings.setMiniSlot_count(Integer.parseInt(value));
						break;
					case "miniSlot_spacing":
						settings.setMiniSlot_spacing(Integer.parseInt(value));
						break;
					case "miniSlot_margin":
						settings.setMiniSlot_margin(Integer.parseInt(value));
						break;
					case "miniSlot_size":
						settings.setMiniSlot_size(Integer.parseInt(value));
						break;
					case "miniSlot_pos_x":
						settings.setMiniSlot_pos_x(Integer.parseInt(value));
						break;
					case "miniSlot_pos_y":
						settings.setMiniSlot_pos_y(Integer.parseInt(value));
						break;
					case "showMiniMap":
						settings.setShowMiniMap(Integer.parseInt(value));
						break;
					case "isSimpleMap":
						settings.setIsSimpleMap(Integer.parseInt(value));
						break;
					case "isCSIHorizontal":
						settings.setIsCSIHorizontal(Integer.parseInt(value));
						break;
					case "castBar_text_location":
						settings.setCastBar_text_location(Integer.parseInt(value));
						break;
					case "castBar_width":
						settings.setCastBar_width(Integer.parseInt(value));
						break;
					case "castBar_height":
						settings.setCastBar_height(Integer.parseInt(value));
						break;
					case "castBar_text_y":
						settings.setCastBar_text_y(Integer.parseInt(value));
						break;
					case "inventory_layout":
						settings.setInventory_layout(Integer.parseInt(value));
						break;
					case "inventory_row":
						settings.setInventory_row(Integer.parseInt(value));
						break;
					case "inventory_column":
						settings.setInventory_column(Integer.parseInt(value));
						break;
					case "trader_layout":
						settings.setTrader_layout(Integer.parseInt(value));
						break;
					case "trader_row":
						settings.setTrader_row(Integer.parseInt(value));
						break;
					case "trader_column":
						settings.setTrader_column(Integer.parseInt(value));
						break;
					case "fontSize":
						settings.setFontSize(Integer.parseInt(value));
						break;
					case "cdFontSize":
						settings.setCDFontSize(Integer.parseInt(value));
						break;
					case "top_buff_size":
						settings.setTop_buff_size(Integer.parseInt(value));
						break;
					case "top_buff_spacing":
						settings.setTop_buff_spacing(Integer.parseInt(value));
						break;
					case "top_buff_column":
						settings.setTop_buff_column(Integer.parseInt(value));
						break;
					case "petListSize":
						settings.setPetListSize(Integer.parseInt(value));
						break;
					case "groupHPColor":
						settings.setGroupHPColor(Integer.parseInt(value));
						break;
					case "raidHPColor":
						settings.setRaidHPColor(Integer.parseInt(value));
						break;
					case "overheadHPWidth":
						settings.setOverheadHPWidth(Integer.parseInt(value));
						break;
					case "overheadHPHeight":
						settings.setOverheadHPHeight(Integer.parseInt(value));
						break;
					case "overheadHPColor":
						settings.setOverheadHPColor(Integer.parseInt(value));
						break;
					case "overheadFontSize":
						settings.setOverheadFontSize(Integer.parseInt(value));
						break;
					case "slot_bg_lightness":
						settings.setSlot_bg_lightness(Integer.parseInt(value));
						break;
					case "castbar_bg_lightness":
						settings.setCastbar_bg_lightness(Integer.parseInt(value));
						break;
					case "mainPortrait_bg_lightness":
						settings.setMainPortrait_bg_lightness(Integer.parseInt(value));
						break;
					case "floatingPortrait_bg_lightness":
						settings.setFloatingPortrait_bg_lightness(Integer.parseInt(value));
						break;
					case "minimap_bg_lightness":
						settings.setMinimap_bg_lightness(Integer.parseInt(value));
						break;
					case "soulshard_bg_lightness":
						settings.setSoulshard_bg_lightness(Integer.parseInt(value));
						break;
					case "csi_bg_lightness":
						settings.setCsi_bg_lightness(Integer.parseInt(value));
						break;
					case "windows_bg_lightness":
						settings.setWindows_bg_lightness(Integer.parseInt(value));
						break;
					case "soulshard_height":
						settings.setSoulshard_height(Integer.parseInt(value));
						break;
					case "slot_bg_alpha":
						settings.setSlot_bg_alpha(Integer.parseInt(value));
						break;
					case "minimap_size":
						settings.setMinimap_size(Integer.parseInt(value));
						break;
					case "sidePanelWidth":
						settings.setSidePanelWidth(Integer.parseInt(value));
						break;
					case "csi_size":
						settings.setCsi_size(Integer.parseInt(value));
						break;
					case "csi_bg_alpha":
						settings.setCsi_bg_alpha(Integer.parseInt(value));
						break;
					case "csi_gap":
						settings.setCsi_gap(Integer.parseInt(value));
						break;
					case "group_hp_width":
						settings.setGroup_hp_width(Integer.parseInt(value));
						break;
					case "group_hp_height":
						settings.setGroup_hp_height(Integer.parseInt(value));
						break;
					case "raid_hp_height":
						settings.setRaid_hp_height(Integer.parseInt(value));
						break;
					case "raid_hp_width":
						settings.setRaid_hp_width(Integer.parseInt(value));
						break;
					case "sidebar_slot_size":
						settings.setSidebar_slot_size(Integer.parseInt(value));
						break;
					case "targetOfTargetX":
						settings.setTargetOfTargetX(Integer.parseInt(value));
						break;
					case "toT_layout_top":
						settings.setToT_layout_top(Integer.parseInt(value));
						break;
					case "castBar_alpha":
						settings.setCastBar_alpha(Integer.parseInt(value));
						break;
					case "lPortrait_bg_alpha":
						settings.setLPortrait_bg_alpha(Integer.parseInt(value));
						break;
					case "lPortrait_hp_height":
						settings.setLPortrait_hp_height(Integer.parseInt(value));
						break;
					case "floatingPortrait_bg_alpha":
						settings.setFloatingPortrait_bg_alpha(Integer.parseInt(value));
						break;
					case "buff_stack_loc":
						settings.setBuff_stack_loc(Integer.parseInt(value));
						break;

					case "floatingPortrait_hp_hue":
						settings.setFloatingPortrait_hp_hue(Double.parseDouble(value));
						break;
					case "floatingPortrait_hp_chroma":
						settings.setFloatingPortrait_hp_chroma(Double.parseDouble(value));
						break;
					case "floatingPortrait_hp_lightness":
						settings.setFloatingPortrait_hp_lightness(Double.parseDouble(value));
						break;
					case "castBar_hue":
						settings.setCastBar_hue(Double.parseDouble(value));
						break;
					case "castBar_chroma":
						settings.setCastBar_chroma(Double.parseDouble(value));
						break;
					case "castBar_lightness":
						settings.setCastBar_lightness(Double.parseDouble(value));
						break;
					case "nameplate_alpha":
						settings.setNameplate_alpha(Double.parseDouble(value));
						break;
					case "petListAlpha":
						settings.setPetListAlpha(Double.parseDouble(value));
						break;
					case "overheadHPAlpha":
						settings.setOverheadHPAlpha(Double.parseDouble(value));
						break;
					case "groupHPAlpha":
						settings.setGroupHPAlpha(Double.parseDouble(value));
						break;
					case "raidHPAlpha":
						settings.setRaidHPAlpha(Double.parseDouble(value));
						break;
					case "csi_highlight_alpha":
						settings.setCsi_highlight_alpha(Double.parseDouble(value));
						break;
					case "group_nameBg_alpha":
						settings.setGroup_nameBg_alpha(Double.parseDouble(value));
						break;
					case "raid_nameBg_alpha":
						settings.setRaid_nameBg_alpha(Double.parseDouble(value));
						break;

					case "isHideEmptySlots":
						settings.setHideEmptySlots(Boolean.parseBoolean(value));
						break;
					case "floatingPortrait_show_mana":
						settings.setFloatingPortrait_show_mana(Boolean.parseBoolean(value));
						break;
					case "isPetListSizeLimit":
						settings.setPetListSizeLimit(Boolean.parseBoolean(value));
						break;
					case "isMiniSlotHide":
						settings.setMiniSlotHide(Boolean.parseBoolean(value));
						break;
					case "isSlotHideForOptionOnly":
						settings.setSlotHideForOptionOnly(Boolean.parseBoolean(value));
						break;
					case "isHPLabelBold":
						settings.setHPLabelBold(Boolean.parseBoolean(value));
						break;
					case "isMenuIcon_right":
						settings.setMenuIcon_right(Boolean.parseBoolean(value));
						break;
					case "isMenuIcon_horizontal":
						settings.setMenuIcon_horizontal(Boolean.parseBoolean(value));
						break;
					case "isBuffTimerBold":
						settings.setBuffTimerBold(Boolean.parseBoolean(value));
						break;
					case "isRemoveHelpButton":
						settings.setRemoveHelpButton(Boolean.parseBoolean(value));
						break;
					case "isRemoveRankButton":
						settings.setRemoveRankButton(Boolean.parseBoolean(value));
						break;
					case "isRemoveXpBar":
						settings.setRemoveXpBar(Boolean.parseBoolean(value));
						break;
					case "isCastbarSparkleOn":
						settings.setCastbarSparkleOn(Boolean.parseBoolean(value));
						break;

					case "cdFontWeight":
						settings.setCDFontWeight(value);
						break;
					case "overheadFontWeight":
						settings.setOverheadFontWeight(value);
						break;
					}
				}
			}
			
			List<String> requiredKeys = Arrays.asList("slot_size", "bottomBar_row", "bottomBar_column");
			for (String key : requiredKeys) {
				if (!loadedKeys.contains(key)) {
					return false;
				}
			}
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static void loadSettings(String aocDir) {
		String customizedDir = aocDir + "/Data/GUI/Customized";

		BottomBar.readBottomBar(customizedDir);
		BuffListViewIcon.readBuffListViewIcon(customizedDir);
		CharPortraitLeft.readLeftPortrait(customizedDir);
		CharPortraitRight.readRightPortrait(customizedDir);
		CommandTimerBar.readCommandTimerBar(customizedDir);
		CSIView.readCSIView(customizedDir);
		FloatingPortraitView.readFloatingPortraitView(customizedDir);
		Fonts.readFonts(customizedDir);
		HUDView.readdHUDView(customizedDir);
		InventoryView.readInventoryView(customizedDir);
		MapWindowSkin.readMapWindowSkin(customizedDir);
		MinimapView.readMinimapView(customizedDir);
		OverheadText.readOverheadText(customizedDir);
		PetListView.readPetListView(customizedDir);
		TeamListBigEntryView.readTeamListBigEntryView(customizedDir);
		TeamListEntryView.readTeamListEntryView(customizedDir);
		TradePostMainView.readTradePostMainView(customizedDir);
		MainSplitView.readMainSplitView(customizedDir);
		SoulFragmentBar.readSoulFragmentBar(customizedDir);
		FloatingShortcutBarH.readFloatingShortcutBarH(customizedDir);

		correctPortraitLayoutDiff();
	}

	// 왼쪽 오른쪽 포트리트 높이차이 보간
	public static void correctPortraitLayoutDiff() {
		Settings settings = Settings.getInstance();
		
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
					font_spacing_adjust = -2;
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

		int lp_layout_bottom = settings.getLPortrait_y();
		settings.setLPortrait_y(lp_layout_bottom - portraits_layout_bottom_diff);
	}
}
