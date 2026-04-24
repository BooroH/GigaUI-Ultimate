package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;

public class TradePostMainView {

	public static void readTradePostMainView(String customizedDir) {

		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/TradePostGUI/TradePostMainView.xml";
		
		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		// 트레이더 레이아웃
		p = Pattern.compile("cell_count=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setTrader_column(Integer.parseInt(m.group(1)));
			settings.setTrader_row(Integer.parseInt(m.group(2)));
		}

		if (settings.getTrader_column() == 10 && settings.getTrader_row() == 5) {
			settings.setTrader_layout(1);
		} else if (settings.getTrader_column() == 15 && settings.getTrader_row() == 7) {
			settings.setTrader_layout(2);
		}

	}

	public static void writeTradePostMainView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/TradePostGUI/TradePostMainView.xml";
		
		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 트레이더 레이아웃
		if (settings.getTrader_layout() == 1) {
			settings.setTrader_column(10);
			settings.setTrader_row(5);
		} else if (settings.getTrader_layout() == 2) {
			settings.setTrader_column(15);
			settings.setTrader_row(7);
		}

		content = content.replaceAll("var_column", String.valueOf(settings.getTrader_column()));
		content = content.replaceAll("var_row", String.valueOf(settings.getTrader_row()));

		FileManager.stringToFile(targetDir, content);
	}

}
