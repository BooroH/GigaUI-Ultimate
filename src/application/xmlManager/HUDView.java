package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;

public class HUDView {

	public static void readdHUDView(String customizedDir) {

		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/HUDView.xml";
		
		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		p = Pattern.compile("icon_size=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);

		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));

			settings.setTop_buff_size(width);
		}

		p = Pattern.compile("icon_spacing=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);

		if (m.find()) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));

			settings.setTop_buff_spacing(width);
		}

		p = Pattern.compile("max_columns=\"(\\d+)\"");
		m = p.matcher(content);

		if (m.find()) {
			int maxColumns = Integer.parseInt(m.group(1));

			settings.setTop_buff_column(maxColumns);
		}
		
		
		//랭킹,헬프버튼, xp바
		if (content.contains("GuildRankingsButton")) {
			settings.setRemoveRankButton(false);
		}
		else {
			settings.setRemoveRankButton(true);
		}
		
		if (content.contains("HelpButton")) {
			settings.setRemoveHelpButton(false);
		}
		else {
			settings.setRemoveHelpButton(true);
		}
		
		if (content.contains("XPBarViewDock")) {
			settings.setRemoveXpBar(false);
		}
		else {
			settings.setRemoveXpBar(true);
		}

	}

	public static void writeHUDView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/HUD/HUDView.xml";
		
		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		String miniMap_on = new String("");
		String miniMap_off = new String("");

		if (settings.getShowMiniMap() == 1) {
			miniMap_on = "<View view_layout=\"vertical\" h_local_alignment=\"RIGHT\">\r\n"
					+ "		<View name=\"RadarViewDock\" view_layout=\"vertical\"/>\r\n"
					+ "		<View name=\"WorldClockDock\" view_layout=\"vertical\"/>\r\n"
					+ "	</View>";
			miniMap_off = "";
		} else {
			miniMap_on = "";
			miniMap_off = "<View name=\"RadarViewDock\" view_layout=\"vertical\"/>";
		}

		content = content.replaceAll("var_minimap_off", miniMap_off);
		content = content.replaceAll("var_minimap_on", miniMap_on);

		// hud.xml 상단버프
		content = content.replaceAll("var_icon_size", String.valueOf(settings.getTop_buff_size()));
		content = content.replaceAll("var_icon_spacing", String.valueOf(settings.getTop_buff_spacing()));
		content = content.replaceAll("var_icon_column", String.valueOf(settings.getTop_buff_column()));
		
		//랭킹,헬프버튼
		String rankButton = "";
		String helpButton = "";
		int helpButton_layout_right;
		int rankButton_layout_right;
		int guildButton_layout_right;
		
		if (settings.getShowMiniMap() ==1) {
			helpButton_layout_right = -14;
			
			if(settings.isRemoveHelpButton()) {
				rankButton_layout_right = -14;
				if(settings.isRemoveRankButton()) {
					guildButton_layout_right = -13;
				}
				else {
					guildButton_layout_right = -16;
				}
			}
			else {
				rankButton_layout_right = -17;
				guildButton_layout_right = -16;
			}
		}
		else {
			helpButton_layout_right = -17;
			rankButton_layout_right = -17;
			guildButton_layout_right = -16;
		}
		
		content = content.replaceAll("var_guild_layout_right", String.valueOf(guildButton_layout_right));
		content = content.replaceAll("var_guild_interaction_right", String.valueOf(-guildButton_layout_right));
		
		if(settings.isRemoveRankButton()) {
			rankButton="";
		}
		else {
			rankButton="<Button name=\"GuildRankingsButton\" template:source=\"ClearButton\" label=\"Rank\" label_font=\"SC_BOLD\" layout_borders=\"Rect(-17,0,"+String.valueOf(rankButton_layout_right)+",0)\" interaction_borders=\"Rect(17,0,"+String.valueOf(-rankButton_layout_right)+",0)\"/>";
		}
		content = content.replaceAll("var_rank_button", rankButton);
		
		if(settings.isRemoveHelpButton()) {
			helpButton="";
		}
		else {
			helpButton="<Button name=\"HelpButton\" template:source=\"ClearButton\" label=\"Help\" label_font=\"SC_BOLD\" layout_borders=\"Rect(-17,0,"+String.valueOf(helpButton_layout_right)+",0)\" interaction_borders=\"Rect(17,0,"+String.valueOf(-helpButton_layout_right)+",0)\"/>";
		}
		content = content.replaceAll("var_help_button", helpButton);
		
		
		String bottombar_code;
		int xpbar_width = 750+(settings.getSlot_size()-59)*15;
		
		if(settings.isRemoveXpBar()) {
			bottombar_code = "<BottomBar v_local_alignment=\"BOTTOM\"/>";
		}
		else {
			bottombar_code = "<View view_layout=\"vertical\" v_local_alignment=\"BOTTOM\">\r\n"
					+ "		<BottomBar/>\r\n"
					+ "		<View name=\"XPBarViewDock\" view_layout=\"vertical\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\" max_size_limit=\"Point("+String.valueOf(xpbar_width)+",COORD_MAX)\" layout_borders=\"Rect(0,-2,0,0)\"/>\r\n"
					+ "		<View name=\"PvPXPBarViewDock\" view_layout=\"vertical\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\" max_size_limit=\"Point("+String.valueOf(xpbar_width)+",COORD_MAX)\" layout_borders=\"Rect(0,-11,0,0)\"/>\r\n"
					+ "    	</View>";
		}
		
		content = content.replaceAll("var_bottombar", bottombar_code);

		FileManager.stringToFile(targetDir, content);

	}

}
