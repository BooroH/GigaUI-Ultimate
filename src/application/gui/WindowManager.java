package application.gui;

import application.setting.Settings;
import javafx.stage.Stage;

public class WindowManager {
	private Stage primaryStage;
	private String aocDir;
	private final Settings settings = Settings.getInstance();

	//싱글턴 Settings 전달
    public Settings getSettings() {
        return settings;
    }

    public WindowManager(Stage stage) {
        this.primaryStage = stage;
    }

    //1번째창
    public void showStart() {
        new StartWindow(primaryStage, this).show();
    }

    public void showInstallTypeWindow() {
        new InstallTypeWindow(primaryStage, this).show();
    }
    
    public void showCustom() {
        new CustomWindow(primaryStage, this).show();
    }
    
    //두번째 스테이지인 Advanced 최초생성
    public void showAdvanced() {
        Stage popup = new Stage();
        popup.initOwner(primaryStage);
        new AdvancedWindow(popup, this).show();
    }
    
    // 다시 Advanced로 돌아가기
    public void showAdvanced(Stage currentStage) {
        new AdvancedWindow(currentStage, this).show();
    }
    
    public void showTopBuffIcons(Stage currentStage) {
        new TopBuffIconsWindow(currentStage, this).show();
    }
    
    public void showMiniSlot(Stage currentStage) {
        new MiniSlotWindow(currentStage, this).show();
    }
    
    public void showMainPortrait(Stage currentStage) {
        new MainPortraitWindow(currentStage, this).show();
    }
    
    public void showFloatingPortrait(Stage currentStage) {
        new FloatingPortraitWindow(currentStage, this).show();
    }
    
    public void showCastBarWindow(Stage currentStage) {
        new CastBarWindow(currentStage, this).show();
    }
    
    public void showCSIWindow(Stage currentStage) {
        new CSIWindow(currentStage, this).show();
    }
    
    public void showSoulShardWindow(Stage currentStage) {
        new SoulShardWindow(currentStage, this).show();
    }
    
    public void showUnitListWindow(Stage currentStage) {
        new UnitListWindow(currentStage, this).show();
    }
    
    public void showOverheadWindow(Stage currentStage) {
        new OverheadWindow(currentStage, this).show();
    }
    
    public void showFontWindow(Stage currentStage) {
        new FontWindow(currentStage, this).show();
    }
    
    public void showDarkModeWindow(Stage currentStage) {
    	new DarkModeWindow(currentStage, this).show();
    }
    

    public void setAoCDir(String dir) {
        this.aocDir = dir;
    }

    public String getAoCDir() {
        return aocDir;
    }
}
