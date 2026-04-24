package application.setting;

public class Settings {
	private Settings() {
	}

	private static class Holder {
		private static final Settings INSTANCE = new Settings();
	}

	public static Settings getInstance() {
		return Holder.INSTANCE;
	}

	private int icon_spacing = -7;
	private int slot_size = 59;
	private int perk_size = 49;
	private int dirSlot_size = 61;
	private int bottomBar_row = 2;
	private int bottomBar_column = 17;
	private int bottomBar_Pos_Y = 4;
	private int lPortrait_x = -268;
	private int lPortrait_y = 8;
	private int rPortrait_x = 268;
	private int rPortrait_y = 8;
	private int tot_name_layout_bottom = -10;
	private int portraits_layout_bottom_diff;
	private int lp_buff_size = 31;
	private int lp_buff_spacing = 1;
	private int lp_buff_column = 8;
	private int rp_buff_size = 31;
	private int rp_buff_spacing = 1;
	private int rp_buff_column = 8;
	private int rp_buff_filter = 3; // 0=없음 1=디버프만 2=버프만 3=둘다
	private int soulshard_x = 0;
	private int soulshard_y = 5;
	private int floatingPortrait_buff_filter = 1;
	private int floatingPortrait_buff_size = 19;
	private int floatingPortrait_buff_spacing = 0;
	private int floatingPortrait_buff_column = 10;
	private int floatingPortrait_hp_height;
	private int miniSlot_count = 0;
	private int miniSlot_spacing = -4;
	private int miniSlot_margin = 4;
	private int miniSlot_size = 37;
	private int miniSlot_pos_x = 0;
	private int miniSlot_pos_y = 49;
	private int showMiniMap = 1;
	private int isSimpleMap = 0;
	private int isCSIHorizontal = 0;
	private int castBar_text_location = 1;
	private int castBar_width = 243;
	private int castBar_height = 37;
	private int castBar_text_y = -1;
	private int inventory_layout = 1;
	private int inventory_row = 10;
	private int inventory_column = 8;
	private int trader_layout = 1;
	private int trader_row = 5;
	private int trader_column = 10;
	private int fontSize = 0;
	private int cdFontSize = 15;
	private int top_buff_size = 31;
	private int top_buff_spacing = 1;
	private int top_buff_column = 20;
	private int petListSize = 240;
	private int groupHPColor = 0;
	private int raidHPColor = 0;
	private int overheadHPWidth = 121;
	private int overheadHPHeight = 14;
	private int overheadHPColor = 1;
	private int overheadFontSize = 32;
	private int targetOfTargetX = 220;
	private int toT_layout_top = 15;
	private int slot_bg_lightness = 0;
	private int castbar_bg_lightness = 0;
	private int mainPortrait_bg_lightness = 0;
	private int floatingPortrait_bg_lightness = 0;
	private int minimap_bg_lightness = 0;
	private int soulshard_bg_lightness = 0;
	private int csi_bg_lightness = 0;
	private int windows_bg_lightness = 0;
	private int soulshard_height = 30;
	private int slot_bg_alpha = 45;
	private int minimap_size = 240;
	private int sidePanelWidth = 389;
	private int csi_size = 61;
	private int csi_bg_alpha = 70;
	private int csi_gap = -7;
	private int group_hp_width;
	private int group_hp_height;
	private int raid_hp_height;
	private int raid_hp_width;
	private int sidebar_slot_size=55;
	private int castBar_alpha = 70;
	private int lPortrait_bg_alpha=70;
	private int lPortrait_hp_height;
	private int floatingPortrait_bg_alpha = 70;
	private int buff_stack_loc = 0; // 0 인사이드 1 어보브

	private double nameplate_alpha = 0.5;
	private double petListAlpha = 0.95;
	private double overheadHPAlpha = 0.94;
	private double groupHPAlpha = 0.95;
	private double raidHPAlpha = 0.95;
	private double csi_highlight_alpha = 70;
	private double group_nameBg_alpha = 0.7;
	private double raid_nameBg_alpha = 0.7;
	
	private double castBar_hue = 0;
	private double castBar_chroma = 0;
	private double castBar_lightness = 0;
	private double floatingPortrait_hp_hue = 0;
	private double floatingPortrait_hp_chroma = 0;
	private double floatingPortrait_hp_lightness = 0;

	private boolean isHideEmptySlots = false;
	private boolean floatingPortrait_show_mana = false;
	private boolean isMiniSlotHide = false;
	private boolean isSlotHideForOptionOnly = false;
	private boolean isHPLabelBold = false;
	private boolean isPetListSizeLimit = true;
	private boolean isMenuIcon_right = true;
	private boolean isMenuIcon_horizontal = true;
	private boolean isBuffTimerBold = false;
	private boolean isRemoveHelpButton = false;
	private boolean isRemoveRankButton = false;
	private boolean isRemoveXpBar = false;
	private boolean isCastbarSparkleOn = true;

	private String cdFontWeight = "GG Bold";
	private String overheadFontWeight = "GG Regular";
	
	public int getBuff_stack_loc() {
		return buff_stack_loc;
	}

	public void setBuff_stack_loc(int buff_stack_loc) {
		this.buff_stack_loc = buff_stack_loc;
	}
	
	public boolean isCastbarSparkleOn() {
		return isCastbarSparkleOn;
	}

	public void setCastbarSparkleOn(boolean isCastbarSparkleOn) {
		this.isCastbarSparkleOn = isCastbarSparkleOn;
	}
	
	public boolean isRemoveXpBar() {
		return isRemoveXpBar;
	}

	public void setRemoveXpBar(boolean isRemoveXpBar) {
		this.isRemoveXpBar = isRemoveXpBar;
	}
	
	public boolean isRemoveRankButton() {
		return isRemoveRankButton;
	}

	public void setRemoveRankButton(boolean isRemoveRankButton) {
		this.isRemoveRankButton = isRemoveRankButton;
	}
	
	public boolean isRemoveHelpButton() {
		return isRemoveHelpButton;
	}

	public void setRemoveHelpButton(boolean isRemoveHelpButton) {
		this.isRemoveHelpButton = isRemoveHelpButton;
	}
	
	public boolean isBuffTimerBold() {
		return isBuffTimerBold;
	}

	public void setBuffTimerBold(boolean isBuffTimerBold) {
		this.isBuffTimerBold = isBuffTimerBold;
	}
	
	public int getPortraits_layout_bottom_diff() {
		return portraits_layout_bottom_diff;
	}

	public void setPortraits_layout_bottom_diff(int portraits_layout_bottom_diff) {
		this.portraits_layout_bottom_diff = portraits_layout_bottom_diff;
	}
	
	public int getLPortrait_hp_height() {
		return lPortrait_hp_height;
	}

	public void setLPortrait_hp_height(int lPortrait_hp_height) {
		this.lPortrait_hp_height = lPortrait_hp_height;
	}
	
	public int getLPortrait_bg_alpha() {
		return lPortrait_bg_alpha;
	}

	public void setLPortrait_bg_alpha(int lPortrait_bg_alpha) {
		this.lPortrait_bg_alpha = lPortrait_bg_alpha;
	}
	
	public int getSidebar_slot_size() {
		return sidebar_slot_size;
	}

	public void setSidebar_slot_size(int sidebar_slot_size) {
		this.sidebar_slot_size = sidebar_slot_size;
	}
	
	public int getRaid_hp_width() {
		return raid_hp_width;
	}

	public void setRaid_hp_width(int raid_hp_width) {
		this.raid_hp_width = raid_hp_width;
	}
	
	public int getRaid_hp_height() {
		return raid_hp_height;
	}

	public void setRaid_hp_height(int raid_hp_height) {
		this.raid_hp_height = raid_hp_height;
	}
	
	public int getGroup_hp_height() {
		return group_hp_height;
	}

	public void setGroup_hp_height(int group_hp_height) {
		this.group_hp_height = group_hp_height;
	}
	
	public int getGroup_hp_width() {
		return group_hp_width;
	}

	public void setGroup_hp_width(int group_hp_width) {
		this.group_hp_width = group_hp_width;
	}
	
	public double getRaid_nameBg_alpha() {
		return raid_nameBg_alpha;
	}

	public void setRaid_nameBg_alpha(double raid_nameBg_alpha) {
		this.raid_nameBg_alpha = raid_nameBg_alpha;
	}
	
	public double getGroup_nameBg_alpha() {
		return group_nameBg_alpha;
	}

	public void setGroup_nameBg_alpha(double group_nameBg_alpha) {
		this.group_nameBg_alpha = group_nameBg_alpha;
	}
	
	
	public int getCsi_gap() {
		return csi_gap;
	}

	public void setCsi_gap(int csi_gap) {
		this.csi_gap = csi_gap;
	}
	
	public double getCsi_highlight_alpha() {
		return csi_highlight_alpha;
	}

	public void setCsi_highlight_alpha(double csi_highlight_alpha) {
		this.csi_highlight_alpha = csi_highlight_alpha;
	}
	
	public int getCsi_bg_alpha() {
		return csi_bg_alpha;
	}

	public void setCsi_bg_alpha(int csi_bg_alpha) {
		this.csi_bg_alpha = csi_bg_alpha;
	}
	
	public int getCsi_size() {
		return csi_size;
	}

	public void setCsi_size(int csi_size) {
		this.csi_size = csi_size;
	}
	
	public int getSidePanelWidth() {
		return sidePanelWidth;
	}

	public void setSidePanelWidth(int sidePanelWidth) {
		this.sidePanelWidth = sidePanelWidth;
	}
	
	public int getMinimap_size() {
		return minimap_size;
	}

	public void setMinimap_size(int minimap_size) {
		this.minimap_size = minimap_size;
	}
	
	public int getSlot_bg_alpha() {
		return slot_bg_alpha;
	}

	public void setSlot_bg_alpha(int slot_bg_alpha) {
		this.slot_bg_alpha = slot_bg_alpha;
	}
	
	public int getWindows_bg_lightness() {
		return windows_bg_lightness;
	}

	public void setWindows_bg_lightness(int windows_bg_lightness) {
		this.windows_bg_lightness = windows_bg_lightness;
	}
	
	public double getRaidHPAlpha() {
		return raidHPAlpha;
	}

	public void setRaidHPAlpha(double raidHPAlpha) {
		this.raidHPAlpha = raidHPAlpha;
	}
	
	public double getGroupHPAlpha() {
		return groupHPAlpha;
	}

	public void setGroupHPAlpha(double groupHPAlpha) {
		this.groupHPAlpha = groupHPAlpha;
	}
	
	public int getSoulshard_height() {
		return soulshard_height;
	}

	public void setSoulshard_height(int soulshard_height) {
		this.soulshard_height = soulshard_height;
	}
	
	public int getCsi_bg_lightness() {
		return csi_bg_lightness;
	}

	public void setCsi_bg_lightness(int csi_bg_lightness) {
		this.csi_bg_lightness = csi_bg_lightness;
	}
	
	public int getSoulshard_bg_lightness() {
		return soulshard_bg_lightness;
	}

	public void setSoulshard_bg_lightness(int soulshard_bg_lightness) {
		this.soulshard_bg_lightness = soulshard_bg_lightness;
	}
	
	public int getMinimap_bg_lightness() {
		return minimap_bg_lightness;
	}

	public void setMinimap_bg_lightness(int minimap_bg_lightness) {
		this.minimap_bg_lightness = minimap_bg_lightness;
	}
	
	public int getFloatingPortrait_bg_lightness() {
		return floatingPortrait_bg_lightness;
	}

	public void setFloatingPortrait_bg_lightness(int floatingPortrait_bg_lightness) {
		this.floatingPortrait_bg_lightness = floatingPortrait_bg_lightness;
	}
	
	public int getMainPortrait_bg_lightness() {
		return mainPortrait_bg_lightness;
	}

	public void setMainPortrait_bg_lightness(int mainPortrait_bg_lightness) {
		this.mainPortrait_bg_lightness = mainPortrait_bg_lightness;
	}
	
	public int getCastbar_bg_lightness() {
		return castbar_bg_lightness;
	}

	public void setCastbar_bg_lightness(int castbar_bg_lightness) {
		this.castbar_bg_lightness = castbar_bg_lightness;
	}
	
	public int getSlot_bg_lightness() {
		return slot_bg_lightness;
	}

	public void setSlot_bg_lightness(int slot_bg_lightness) {
		this.slot_bg_lightness = slot_bg_lightness;
	}
	
	public boolean isMenuIcon_right() {
		return isMenuIcon_right;
	}

	public void setMenuIcon_right(boolean menuIcon_right) {
		isMenuIcon_right = menuIcon_right;
	}
	
	public boolean isMenuIcon_horizontal() {
		return isMenuIcon_horizontal;
	}

	public void setMenuIcon_horizontal(boolean menuIcon_horizontal) {
		isMenuIcon_horizontal = menuIcon_horizontal;
	}

	public int getTargetOfTargetX() {
		return targetOfTargetX;
	}
	
	public void setTargetOfTargetX(int targetOfTargetX) {
		this.targetOfTargetX = targetOfTargetX;
	}
	
	public int getToT_layout_top() {
		return toT_layout_top;
	}
	
	public void setToT_layout_top(int toT_layout_top) {
		this.toT_layout_top = toT_layout_top;
	}
	
	public int getIcon_spacing() {
		return icon_spacing;
	}

	public void setIcon_spacing(int icon_spacing) {
		this.icon_spacing = icon_spacing;
	}

	public int getSlot_size() {
		return slot_size;
	}

	public void setSlot_size(int slot_size) {
		this.slot_size = slot_size;
	}

	public int getPerk_size() {
		return perk_size;
	}

	public void setPerk_size(int perk_size) {
		this.perk_size = perk_size;
	}

	public int getDirSlot_size() {
		return dirSlot_size;
	}

	public void setDirSlot_size(int dirSlot_size) {
		this.dirSlot_size = dirSlot_size;
	}

	public int getBottomBar_row() {
		return bottomBar_row;
	}

	public void setBottomBar_row(int bottomBar_row) {
		this.bottomBar_row = bottomBar_row;
	}

	public int getBottomBar_column() {
		return bottomBar_column;
	}

	public void setBottomBar_column(int bottomBar_column) {
		this.bottomBar_column = bottomBar_column;
	}

	public int getBottomBar_Pos_Y() {
		return bottomBar_Pos_Y;
	}

	public void setBottomBar_Pos_Y(int bottomBar_Pos_Y) {
		this.bottomBar_Pos_Y = bottomBar_Pos_Y;
	}

	public int getLPortrait_x() {
		return lPortrait_x;
	}

	public void setLPortrait_x(int lPortrait_x) {
		this.lPortrait_x = lPortrait_x;
	}

	public int getLPortrait_y() {
		return lPortrait_y;
	}

	public void setLPortrait_y(int lPortrait_y) {
		this.lPortrait_y = lPortrait_y;
	}

	public int getRPortrait_x() {
		return rPortrait_x;
	}

	public void setRPortrait_x(int rPortrait_x) {
		this.rPortrait_x = rPortrait_x;
	}

	public int getRPortrait_y() {
		return rPortrait_y;
	}

	public void setRPortrait_y(int rPortrait_y) {
		this.rPortrait_y = rPortrait_y;
	}

	public int getTot_name_layout_bottom() {
		return tot_name_layout_bottom;
	}

	public void setTot_name_layout_bottom(int tot_name_layout_bottom) {
		this.tot_name_layout_bottom = tot_name_layout_bottom;
	}

	public int getLP_buff_size() {
		return lp_buff_size;
	}

	public void setLP_buff_size(int lp_buff_size) {
		this.lp_buff_size = lp_buff_size;
	}

	public int getLP_buff_spacing() {
		return lp_buff_spacing;
	}

	public void setLP_buff_spacing(int lp_buff_spacing) {
		this.lp_buff_spacing = lp_buff_spacing;
	}

	public int getLP_buff_column() {
		return lp_buff_column;
	}

	public void setLP_buff_column(int lp_buff_column) {
		this.lp_buff_column = lp_buff_column;
	}

	public int getRP_buff_size() {
		return rp_buff_size;
	}

	public void setRP_buff_size(int rp_buff_size) {
		this.rp_buff_size = rp_buff_size;
	}

	public int getRP_buff_spacing() {
		return rp_buff_spacing;
	}

	public void setRP_buff_spacing(int rp_buff_spacing) {
		this.rp_buff_spacing = rp_buff_spacing;
	}

	public int getRP_buff_column() {
		return rp_buff_column;
	}

	public void setRP_buff_column(int rp_buff_column) {
		this.rp_buff_column = rp_buff_column;
	}

	public int getRP_buff_filter() {
		return rp_buff_filter;
	}

	public void setRP_buff_filter(int rp_buff_filter) {
		this.rp_buff_filter = rp_buff_filter;
	}

	public int getSoulshard_x() {
		return soulshard_x;
	}

	public void setSoulshard_x(int soulshard_x) {
		this.soulshard_x = soulshard_x;
	}

	public int getSoulshard_y() {
		return soulshard_y;
	}

	public void setSoulshard_y(int soulshard_y) {
		this.soulshard_y = soulshard_y;
	}

	public int getFloatingPortrait_buff_filter() {
		return floatingPortrait_buff_filter;
	}

	public void setFloatingPortrait_buff_filter(int floatingPortrait_buff_filter) {
		this.floatingPortrait_buff_filter = floatingPortrait_buff_filter;
	}

	public int getFloatingPortrait_buff_size() {
		return floatingPortrait_buff_size;
	}

	public void setFloatingPortrait_buff_size(int floatingPortrait_buff_size) {
		this.floatingPortrait_buff_size = floatingPortrait_buff_size;
	}

	public int getFloatingPortrait_buff_spacing() {
		return floatingPortrait_buff_spacing;
	}

	public void setFloatingPortrait_buff_spacing(int floatingPortrait_buff_spacing) {
		this.floatingPortrait_buff_spacing = floatingPortrait_buff_spacing;
	}

	public int getFloatingPortrait_buff_column() {
		return floatingPortrait_buff_column;
	}

	public void setFloatingPortrait_buff_column(int floatingPortrait_buff_column) {
		this.floatingPortrait_buff_column = floatingPortrait_buff_column;
	}

	public double getFloatingPortrait_hp_hue() {
		return floatingPortrait_hp_hue;
	}

	public void setFloatingPortrait_hp_hue(double floatingPortrait_hp_hue) {
		this.floatingPortrait_hp_hue = floatingPortrait_hp_hue;
	}
	
	public double getFloatingPortrait_hp_chroma() {
		return floatingPortrait_hp_chroma;
	}

	public void setFloatingPortrait_hp_chroma(double floatingPortrait_hp_chroma) {
		this.floatingPortrait_hp_chroma = floatingPortrait_hp_chroma;
	}
	
	public double getFloatingPortrait_hp_lightness() {
		return floatingPortrait_hp_lightness;
	}

	public void setFloatingPortrait_hp_lightness(double floatingPortrait_hp_lightness) {
		this.floatingPortrait_hp_lightness = floatingPortrait_hp_lightness;
	}

	public int getFloatingPortrait_hp_height() {
		return floatingPortrait_hp_height;
	}

	public void setFloatingPortrait_hp_height(int floatingPortrait_hp_height) {
		this.floatingPortrait_hp_height = floatingPortrait_hp_height;
	}

	public int getMiniSlot_count() {
		return miniSlot_count;
	}

	public void setMiniSlot_count(int miniSlot_count) {
		this.miniSlot_count = miniSlot_count;
	}

	public int getMiniSlot_spacing() {
		return miniSlot_spacing;
	}

	public void setMiniSlot_spacing(int miniSlot_spacing) {
		this.miniSlot_spacing = miniSlot_spacing;
	}

	public int getMiniSlot_margin() {
		return miniSlot_margin;
	}

	public void setMiniSlot_margin(int miniSlot_margin) {
		this.miniSlot_margin = miniSlot_margin;
	}

	public int getMiniSlot_size() {
		return miniSlot_size;
	}

	public void setMiniSlot_size(int miniSlot_size) {
		this.miniSlot_size = miniSlot_size;
	}

	public int getMiniSlot_pos_x() {
		return miniSlot_pos_x;
	}

	public void setMiniSlot_pos_x(int miniSlot_pos_x) {
		this.miniSlot_pos_x = miniSlot_pos_x;
	}

	public int getMiniSlot_pos_y() {
		return miniSlot_pos_y;
	}

	public void setMiniSlot_pos_y(int miniSlot_pos_y) {
		this.miniSlot_pos_y = miniSlot_pos_y;
	}

	public int getShowMiniMap() {
		return showMiniMap;
	}

	public void setShowMiniMap(int showMiniMap) {
		this.showMiniMap = showMiniMap;
	}

	public int getIsSimpleMap() {
		return isSimpleMap;
	}

	public void setIsSimpleMap(int isSimpleMap) {
		this.isSimpleMap = isSimpleMap;
	}

	public int getIsCSIHorizontal() {
		return isCSIHorizontal;
	}

	public void setIsCSIHorizontal(int isCSIHorizontal) {
		this.isCSIHorizontal = isCSIHorizontal;
	}

	public int getCastBar_text_location() {
		return castBar_text_location;
	}

	public void setCastBar_text_location(int castBar_text_location) {
		this.castBar_text_location = castBar_text_location;
	}

	public double getCastBar_hue() {
		return castBar_hue;
	}

	public void setCastBar_hue(double castBar_hue) {
		this.castBar_hue = castBar_hue;
	}
	
	public double getCastBar_chroma() {
		return castBar_chroma;
	}

	public void setCastBar_chroma(double castBar_chroma) {
		this.castBar_chroma = castBar_chroma;
	}
	
	public double getCastBar_lightness() {
		return castBar_lightness;
	}

	public void setCastBar_lightness(double castBar_lightness) {
		this.castBar_lightness = castBar_lightness;
	}
	
	public int getCastBar_width() {
		return castBar_width;
	}

	public void setCastBar_width(int castBar_width) {
		this.castBar_width = castBar_width;
	}
	
	public int getCastBar_height() {
		return castBar_height;
	}

	public void setCastBar_height(int castBar_height) {
		this.castBar_height = castBar_height;
	}
	
	public int getCastBar_text_y() {
		return castBar_text_y;
	}

	public void setCastBar_text_y(int castBar_text_y) {
		this.castBar_text_y = castBar_text_y;
	}

	public int getInventory_layout() {
		return inventory_layout;
	}

	public void setInventory_layout(int inventory_layout) {
		this.inventory_layout = inventory_layout;
	}

	public int getInventory_row() {
		return inventory_row;
	}

	public void setInventory_row(int inventory_row) {
		this.inventory_row = inventory_row;
	}

	public int getInventory_column() {
		return inventory_column;
	}

	public void setInventory_column(int inventory_column) {
		this.inventory_column = inventory_column;
	}

	public int getTrader_layout() {
		return trader_layout;
	}

	public void setTrader_layout(int trader_layout) {
		this.trader_layout = trader_layout;
	}

	public int getTrader_row() {
		return trader_row;
	}

	public void setTrader_row(int trader_row) {
		this.trader_row = trader_row;
	}

	public int getTrader_column() {
		return trader_column;
	}

	public void setTrader_column(int trader_column) {
		this.trader_column = trader_column;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getCDFontSize() {
		return cdFontSize;
	}

	public void setCDFontSize(int cdFontSize) {
		this.cdFontSize = cdFontSize;
	}

	public int getTop_buff_size() {
		return top_buff_size;
	}

	public void setTop_buff_size(int top_buff_size) {
		this.top_buff_size = top_buff_size;
	}

	public int getTop_buff_spacing() {
		return top_buff_spacing;
	}

	public void setTop_buff_spacing(int top_buff_spacing) {
		this.top_buff_spacing = top_buff_spacing;
	}

	public int getTop_buff_column() {
		return top_buff_column;
	}

	public void setTop_buff_column(int top_buff_column) {
		this.top_buff_column = top_buff_column;
	}

	public int getPetListSize() {
		return petListSize;
	}

	public void setPetListSize(int petListSize) {
		this.petListSize = petListSize;
	}

	public int getGroupHPColor() {
		return groupHPColor;
	}

	public void setGroupHPColor(int groupHPColor) {
		this.groupHPColor = groupHPColor;
	}

	public int getRaidHPColor() {
		return raidHPColor;
	}

	public void setRaidHPColor(int raidHPColor) {
		this.raidHPColor = raidHPColor;
	}

	public int getOverheadHPWidth() {
		return overheadHPWidth;
	}

	public void setOverheadHPWidth(int overheadHPWidth) {
		this.overheadHPWidth = overheadHPWidth;
	}
	
	public int getOverheadHPHeight() {
		return overheadHPHeight;
	}

	public void setOverheadHPHeight(int overheadHPHeight) {
		this.overheadHPHeight = overheadHPHeight;
	}

	public int getOverheadHPColor() {
		return overheadHPColor;
	}

	public void setOverheadHPColor(int overheadHPColor) {
		this.overheadHPColor = overheadHPColor;
	}

	public int getOverheadFontSize() {
		return overheadFontSize;
	}

	public void setOverheadFontSize(int overheadFontSize) {
		this.overheadFontSize = overheadFontSize;
	}

	public double getNameplate_alpha() {
		return nameplate_alpha;
	}

	public void setNameplate_alpha(double nameplate_alpha) {
		this.nameplate_alpha = nameplate_alpha;
	}

	public int getFloatingPortrait_bg_alpha() {
		return floatingPortrait_bg_alpha;
	}

	public void setFloatingPortrait_bg_alpha(int floatingPortrait_bg_alpha) {
		this.floatingPortrait_bg_alpha = floatingPortrait_bg_alpha;
	}

	public int getCastBar_alpha() {
		return castBar_alpha;
	}

	public void setCastBar_alpha(int castBar_alpha) {
		this.castBar_alpha = castBar_alpha;
	}

	public double getPetListAlpha() {
		return petListAlpha;
	}

	public void setPetListAlpha(double petListAlpha) {
		this.petListAlpha = petListAlpha;
	}

	public double getOverheadHPAlpha() {
		return overheadHPAlpha;
	}

	public void setOverheadHPAlpha(double overheadHPAlpha) {
		this.overheadHPAlpha = overheadHPAlpha;
	}

	public boolean isHideEmptySlots() {
		return isHideEmptySlots;
	}

	public void setHideEmptySlots(boolean hideEmptySlots) {
		isHideEmptySlots = hideEmptySlots;
	}

	public boolean isFloatingPortrait_show_mana() {
		return floatingPortrait_show_mana;
	}

	public void setFloatingPortrait_show_mana(boolean floatingPortrait_show_mana) {
		this.floatingPortrait_show_mana = floatingPortrait_show_mana;
	}

	public boolean isMiniSlotHide() {
		return isMiniSlotHide;
	}

	public void setMiniSlotHide(boolean miniSlotHide) {
		isMiniSlotHide = miniSlotHide;
	}

	public boolean isSlotHideForOptionOnly() {
		return isSlotHideForOptionOnly;
	}

	public void setSlotHideForOptionOnly(boolean slotHideForOptionOnly) {
		isSlotHideForOptionOnly = slotHideForOptionOnly;
	}

	public boolean isHPLabelBold() {
		return isHPLabelBold;
	}

	public void setHPLabelBold(boolean HPLabelBold) {
		isHPLabelBold = HPLabelBold;
	}

	public boolean isPetListSizeLimit() {
		return isPetListSizeLimit;
	}

	public void setPetListSizeLimit(boolean petListSizeLimit) {
		isPetListSizeLimit = petListSizeLimit;
	}

	public String getCDFontWeight() {
		return cdFontWeight;
	}

	public void setCDFontWeight(String cdFontWeight) {
		this.cdFontWeight = cdFontWeight;
	}

	public String getOverheadFontWeight() {
		return overheadFontWeight;
	}

	public void setOverheadFontWeight(String overheadFontWeight) {
		this.overheadFontWeight = overheadFontWeight;
	}
}
