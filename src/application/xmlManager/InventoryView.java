package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;

public class InventoryView {

	public static void readInventoryView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/MainGUI/InventoryView.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		p = Pattern.compile("cell_count=\"Point\\((\\d+),(\\d+)\\)\"");
		m = p.matcher(content);
		if (m.find()) {
			settings.setInventory_column(Integer.parseInt(m.group(1)));
			settings.setInventory_row(Integer.parseInt(m.group(2)));
		}

		if (settings.getInventory_column() == 8 && settings.getInventory_row() == 10) {
			settings.setInventory_layout(1);
		} else if (settings.getInventory_column() == 10 && settings.getInventory_row() == 13) {
			settings.setInventory_layout(2);
		} else if (settings.getInventory_column() == 12 && settings.getInventory_row() == 15) {
			settings.setInventory_layout(3);
		}

	}

	public static void writeInventoryView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/MainGUI/InventoryView.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 인벤토리 레이아웃
		int sidePanelWidth = settings.getSidePanelWidth();
		int inventory_icon_margin = 2;
		int inventory_icon_size = 47;
		if (sidePanelWidth < 469) {
			if (settings.getInventory_layout() == 1) {
				settings.setInventory_column(8);
				settings.setInventory_row(10);
				inventory_icon_margin = 2;
				inventory_icon_size = 47;
			} else if (settings.getInventory_layout() == 2) {
				settings.setInventory_column(10);
				settings.setInventory_row(13);
				inventory_icon_margin = 2;
				inventory_icon_size = 39;
			} else if (settings.getInventory_layout() == 3) {
				settings.setInventory_column(12);
				settings.setInventory_row(15);
				inventory_icon_margin = 2;
				inventory_icon_size = 33;
			}
		}
		else if(sidePanelWidth < 525) {
			if (settings.getInventory_layout() == 1) {
				settings.setInventory_column(8);
				settings.setInventory_row(10);
				inventory_icon_margin = 2;
				inventory_icon_size = 57;
			} else if (settings.getInventory_layout() == 2) {
				settings.setInventory_column(10);
				settings.setInventory_row(13);
				inventory_icon_margin = 2;
				inventory_icon_size = 45;
			} else if (settings.getInventory_layout() == 3) {
				settings.setInventory_column(12);
				settings.setInventory_row(15);
				inventory_icon_margin = 2;
				inventory_icon_size = 37;
			}
		}
		else if(sidePanelWidth < 605){
			if (settings.getInventory_layout() == 1) {
				settings.setInventory_column(8);
				settings.setInventory_row(10);
				inventory_icon_margin = 2;
				inventory_icon_size = 63;
			} else if (settings.getInventory_layout() == 2) {
				settings.setInventory_column(10);
				settings.setInventory_row(13);
				inventory_icon_margin = 2;
				inventory_icon_size = 51;
			} else if (settings.getInventory_layout() == 3) {
				settings.setInventory_column(12);
				settings.setInventory_row(15);
				inventory_icon_margin = 2;
				inventory_icon_size = 41;
			}
		}
		else {
			if (settings.getInventory_layout() == 1) {
				settings.setInventory_column(8);
				settings.setInventory_row(10);
				inventory_icon_margin = 2;
				inventory_icon_size = 73;
			} else if (settings.getInventory_layout() == 2) {
				settings.setInventory_column(10);
				settings.setInventory_row(13);
				inventory_icon_margin = 2;
				inventory_icon_size = 59;
			} else if (settings.getInventory_layout() == 3) {
				settings.setInventory_column(12);
				settings.setInventory_row(15);
				inventory_icon_margin = 2;
				inventory_icon_size = 49;
			}
		}
		content = content.replaceAll("var_inventory_column", String.valueOf(settings.getInventory_column()));
		content = content.replaceAll("var_inventory_row", String.valueOf(settings.getInventory_row()));
		content = content.replaceAll("var_inventory_icon_margin", String.valueOf(inventory_icon_margin));
		content = content.replaceAll("var_inventory_icon_size", String.valueOf(inventory_icon_size));

		FileManager.stringToFile(targetDir, content);

	}
}
