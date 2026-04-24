package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.HexConverter;

public class BuffListViewIcon {

	public static void readBuffListViewIcon(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/BuffGUI/BuffListViewIcon.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		// 폰트 볼드인지 확인
		if (content.contains("font=\"NORMAL_BOLD\"")) {
			settings.setBuffTimerBold(true);
		} else {
			settings.setBuffTimerBold(false);
		}

		// 스택넘버 로케이션
		if (content.contains("<View view_layout=\"stacked\">")) {
			settings.setBuff_stack_loc(0);
		} else {
			settings.setBuff_stack_loc(1);
		}

	}

	public static void writeBuffListViewIcon(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/BuffGUI/BuffListViewIcon.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		String fullCode;
		
		// 버프타이머 폰트
		String font;
		
		if (settings.isBuffTimerBold()) {
			font = " font=\"NORMAL_BOLD\"";
		} else {
			font = "";
		}
		
		int stackLoc = settings.getBuff_stack_loc();
		String stack_layout_borders = " layout_borders=\"Rect(0,-1,0,-2)\"";

		if (stackLoc == 0) {
			fullCode = "<View view_layout=\"stacked\">\r\n"
					+ "		<ItemIconView name=\"IconView\" view_flags=\"F_DISABLE_COOLDOWN|F_ALWAYS_SHOW_ENABLED\"/>\r\n"
					+ "		<TextView name=\"StackSizeView\" default_color=\"#ffffff\""+font+" feature_flags=\"TVF_RENDER_SHADOW|TVF_RENDER_HALO\" v_local_alignment=\"TOP\" h_local_alignment=\"RIGHT\"/>\r\n"
					+ "	</View>\r\n"
					+ "	<View name=\"RemainingTimeContainer\" view_layout=\"horizontal\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\">\r\n"
					+ "		<TextView name=\"RemainingTimeView\" default_color=\"#dbdddf\""+font+" feature_flags=\"TVF_RENDER_SHADOW|TVF_RENDER_HALO\"/>\r\n"
					+ "	</View>";
		} else {
			fullCode = "<View name=\"StackSizeContainer\" view_layout=\"horizontal\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\">\r\n"
					+ "		<TextView name=\"StackSizeView\" default_color=\"#dbdddf\""+font+" feature_flags=\"TVF_RENDER_SHADOW|TVF_RENDER_HALO\""+stack_layout_borders+"/>\r\n"
					+ "	</View>\r\n"
					+ "	<ItemIconView name=\"IconView\" view_flags=\"F_DISABLE_COOLDOWN|F_ALWAYS_SHOW_ENABLED\"/>\r\n"
					+ "	<View name=\"RemainingTimeContainer\" view_layout=\"horizontal\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\">\r\n"
					+ "		<TextView name=\"RemainingTimeView\" default_color=\"#dbdddf\""+font+" feature_flags=\"TVF_RENDER_SHADOW|TVF_RENDER_HALO\"/>\r\n"
					+ "	</View>";
		}

		content = content.replaceAll("var_code", fullCode);

		FileManager.stringToFile(targetDir, content);

	}
}
