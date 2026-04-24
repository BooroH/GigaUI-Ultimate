package application.installer;

import application.util.FileManager;
import application.xmlManager.*;

public class Installer {

	public static boolean runInstaller(String aocDir) {
		String installPath = aocDir + "/Data/Gui";
		String customizedDir = installPath + "/Customized";

		// 기존 Customized 폴더 및 파일 삭제
		FileManager.deleteDirectory(customizedDir);
		// 복사
		FileManager.copyFolder("Data/Gui", installPath);
		
		return true;
	}

	public static boolean runEditor(String aocDir) {
		String installPath = aocDir + "/Data/Gui";
		String customizedDir = installPath + "/Customized";
		
		FileManager.deleteDirectory(customizedDir+"/Views/HUD/BottomBar.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/BuffGUI/BuffListViewIcon.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/HUD/CharPortraitLeft.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/HUD/CharPortraitRight.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/CommandTimerBar.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/ComboSequenceIndicator/CSIView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/HUD/FloatingPortraitView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Fonts.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/HUD/HUDView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/MainGUI/InventoryView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/MainGUI/MainSplitView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/MapGUI/MapWindowSkin.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/MapGUI/MinimapView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/OverheadText.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/PetAndFollowerGUI/PetListView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/TeamGUI/TeamListBigEntryView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/TeamGUI/TeamListEntryView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/TradePostGUI/TradePostMainView.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/HUD/FloatingShortcutBarH.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/HUD/FloatingShortcutBarV.bxml");	
		FileManager.deleteDirectory(customizedDir+"/Views/HUD/SoulFragmentBar.bxml");
		FileManager.deleteDirectory(customizedDir+"/WindowSkins/Normal.bxml");
		FileManager.deleteDirectory(customizedDir+"/Views/MainGUI/SPWearView.bxml");
		
		BottomBar.writeBottomBar(customizedDir);
		BuffListViewIcon.writeBuffListViewIcon(customizedDir);
		CharPortraitLeft.writeCharPortraitLeft(customizedDir);
		CharPortraitRight.writeCharPortraitRight(customizedDir);
		CommandTimerBar.writeCommandTimerBar(customizedDir);
		CSIView.writeCSIView(customizedDir);
		FloatingPortraitView.writeFloatingPortraitView(customizedDir);
		Fonts.writeFonts(customizedDir);
		HUDView.writeHUDView(customizedDir);
		InventoryView.writeInventoryView(customizedDir);
		MainSplitView.writeMainSplitView(customizedDir);
		MapWindowSkin.writeMapWindowSkin(customizedDir);
		MinimapView.writeMinimapView(customizedDir);
		OverheadText.writeOverheadText(customizedDir);
		PetListView.writePetListView(customizedDir);
		TeamListBigEntryView.writeTeamListBigEntryView(customizedDir);
		TeamListEntryView.writeTeamListEntryView(customizedDir);
		TradePostMainView.writeTradePostMainView(customizedDir);
		FloatingShortcutBarH.writeFloatingShortcutBarH(customizedDir);
		FloatingShortcutBarV.writeFloatingShortcutBarV(customizedDir);
		SoulFragmentBar.writeSoulFragmentBar(customizedDir);
		WindowSkins.writeWindowSkins(customizedDir);
		SPWearView.writeSPWearView(customizedDir);
		
		return true;
	}
}
