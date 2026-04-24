package application.xmlManager;

import application.setting.Settings;
import application.util.FileManager;

public class SPWearView {
	public static void writeSPWearView(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/MainGUI/SPWearView.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		// 사이드패널 사이즈별 아이콘 크기
		int sidePanelWidth = settings.getSidePanelWidth();
		int iconSize1;
		int iconSize2;
		int previewSize_width;
		int previewSize_height;
		if (sidePanelWidth < 469) {
			iconSize1 = 63;
			iconSize2 = 58;
			previewSize_width = 167;
			previewSize_height = 372;
		} else if (sidePanelWidth < 525) {
			iconSize1 = 73;
			iconSize2 = 68;
			previewSize_width = 203;
			previewSize_height = 452;
		} else if (sidePanelWidth < 605) {
			iconSize1 = 83;
			iconSize2 = 78;
			previewSize_width = 233;
			previewSize_height = 519;
		}
		else {
			iconSize1 = 95;
			iconSize2 = 87;
			previewSize_width = 251;
			previewSize_height = 557;
		}

		content = content.replaceAll("var_icon_size_1", String.valueOf(iconSize1));
		content = content.replaceAll("var_icon_size_2", String.valueOf(iconSize2));
		
		content = content.replaceAll("var_previewSize_width", String.valueOf(previewSize_width));
		content = content.replaceAll("var_previewSize_height", String.valueOf(previewSize_height));

		FileManager.stringToFile(targetDir, content);

	}
}
