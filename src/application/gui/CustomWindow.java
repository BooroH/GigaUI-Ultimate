package application.gui;

import java.io.File;

import application.installer.Installer;
import application.setting.SettingManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CustomWindow extends BaseWindow {
	private WindowManager manager;

	private Button saveButton;
	private Button backButton;
	private Button advancedButton;
	private Button importButton;
	private Button exportButton;

	private ComboBox<String> sizeComboBox;
	private Label restartLabel;

	private ToggleGroup themeGroup;
	private RadioButton gigaThemeButton;
	private RadioButton darkThemeButton;
	
	private ToggleGroup colorGroup;
	private RadioButton hyborianButton;
	private RadioButton clarityButton;
	private RadioButton oldSchoolButton;

	public CustomWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		SettingManager.loadSettings(manager.getAoCDir());

		sizeComboBox = new ComboBox<>();
		sizeComboBox.getItems().addAll("100%", "110%", "120%", "130%", "140%", "150%", "160%", "Custom");

		restartLabel = new Label("");

		themeGroup = new ToggleGroup();
		gigaThemeButton = new RadioButton("Giga");
		darkThemeButton = new RadioButton("Dark");

		gigaThemeButton.setToggleGroup(themeGroup);
		darkThemeButton.setToggleGroup(themeGroup);
		
		colorGroup = new ToggleGroup();		
		clarityButton  = createThemeRadio("Clarity", "/preview/clarity.png", colorGroup, "Maximum visibility. For best performance in pvp/raid.");
		hyborianButton  = createThemeRadio("Hyborian", "/preview/hyborian.png", colorGroup, "Calm and antique. For relaxing gameplay.");
		oldSchoolButton  = createThemeRadio("Old School", "/preview/oldschool.png", colorGroup, "Rough and classic. If you’re looking for original vibe.");
		
		String colorSet = manager.getSettings().getColorSet();
		
		if(colorSet.equals("clr")) {
			clarityButton.setSelected(true);
		}
		else if(colorSet.equals("hyb")) {
			hyborianButton.setSelected(true);
		}
		else if(colorSet.equals("ols")) {
			oldSchoolButton.setSelected(true);
		}
		
		importButton = new Button("Import");
		exportButton = new Button("Export");
		advancedButton = new Button("Advanced");
		backButton = new Button("Back");
		saveButton = new Button("Save");
	}

	@Override
	protected Parent createView() {
		Label quickSetup = new Label("QUICK SETUP");
		quickSetup.getStyleClass().add("section-title");
		Label scaleLabel = new Label("Scale");
		Label themeLabel = new Label("Theme");
		Label colorLabel = new Label("Color Set");
		VBox.setMargin(themeLabel, new Insets(10, 0, 0, 0));
		VBox.setMargin(colorLabel, new Insets(10, 0, 0, 0));

		HBox sizeBox = new HBox(10, sizeComboBox,restartLabel);
		HBox themeBox = new HBox(10, gigaThemeButton, darkThemeButton);
		HBox colorBox = new HBox(10, clarityButton, hyborianButton, oldSchoolButton);

		HBox topRow = new HBox(10);
		topRow.setAlignment(Pos.CENTER_LEFT);
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		topRow.getChildren().addAll(quickSetup, spacer, importButton, exportButton);

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(topRow, scaleLabel, sizeBox, themeLabel, themeBox, colorLabel, colorBox);

		Region spacer2 = new Region();
		HBox.setHgrow(spacer2, Priority.ALWAYS);

		HBox bottomButtons = new HBox(10, advancedButton, spacer2, backButton, saveButton);
		bottomButtons.setPadding(new Insets(15));
		bottomButtons.setAlignment(Pos.BOTTOM_RIGHT);

		BorderPane root = new BorderPane();
		root.setCenter(centerBox);
		root.setBottom(bottomButtons);

		return root;
	}

	@Override
	protected void initActions() {
		sizeComboBox.setOnAction(e -> {
			String selected = sizeComboBox.getValue();

			switch (selected) {
			case "100%":
				SettingManager.loadProfile(new File("profile/100.txt"));
				restartLabel.setText("");
				break;
			case "110%":
				SettingManager.loadProfile(new File("profile/110.txt"));
				restartLabel.setText("restart the game to apply font size");
				break;
			case "120%":
				SettingManager.loadProfile(new File("profile/120.txt"));
				restartLabel.setText("restart the game to apply font size");
				break;
			case "130%":
				SettingManager.loadProfile(new File("profile/130.txt"));
				restartLabel.setText("restart the game to apply font size");
				break;
			case "140%":
				SettingManager.loadProfile(new File("profile/140.txt"));
				restartLabel.setText("restart the game to apply font size");
				break;
			case "150%":
				SettingManager.loadProfile(new File("profile/150.txt"));
				restartLabel.setText("restart the game to apply font size");
				break;
			case "160%":
				SettingManager.loadProfile(new File("profile/160.txt"));
				restartLabel.setText("restart the game to apply font size");
				break;
			case "Custom":
				restartLabel.setText("");
				break;
			}
		});
		
		themeGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
			if (gigaThemeButton.isSelected()) {
				manager.getSettings().setSlot_bg_lightness(0);
				manager.getSettings().setCastbar_bg_lightness(0);
				manager.getSettings().setMainPortrait_bg_lightness(0);
				manager.getSettings().setFloatingPortrait_bg_lightness(0);
				manager.getSettings().setMinimap_bg_lightness(0);
				manager.getSettings().setCsi_bg_lightness(0);
				manager.getSettings().setSoulshard_bg_lightness(0);
				manager.getSettings().setWindows_bg_lightness(0);
			} else if (darkThemeButton.isSelected()) {
				manager.getSettings().setSlot_bg_lightness(-64);
				manager.getSettings().setCastbar_bg_lightness(-63);
				manager.getSettings().setMainPortrait_bg_lightness(-77);
				manager.getSettings().setFloatingPortrait_bg_lightness(-77);
				manager.getSettings().setMinimap_bg_lightness(-77);
				manager.getSettings().setCsi_bg_lightness(-66);
				manager.getSettings().setSoulshard_bg_lightness(-66);
				manager.getSettings().setWindows_bg_lightness(-67);
			} else {

			}
		});
		
		saveButton.setOnAction(e -> {
			if (hyborianButton.isSelected()) {
				manager.getSettings().setColorSet("hyb");
			}
			else if (clarityButton.isSelected()) {
				manager.getSettings().setColorSet("clr");
			}
			else if (oldSchoolButton.isSelected()) {
				manager.getSettings().setColorSet("ols");
			}
			else {	
			}
			
			if (Installer.runEditor(manager.getAoCDir())) {
				ShowAlert.showInfo("Complete", "Customizing Done");
			}
		});
		backButton.setOnAction(e -> {
			manager.showInstallTypeWindow();
		});
		importButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Import Profile");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

			// 🔹 초기 경로를 바탕화면으로 설정
			File desktopDir = new File(System.getProperty("user.home"), "Desktop");
			if (desktopDir.exists()) {
				fileChooser.setInitialDirectory(desktopDir);
			}

			File file = fileChooser.showOpenDialog(stage.getScene().getWindow());
			if (file != null) {
				if (SettingManager.loadProfile(file)) {
					sizeComboBox.setValue("Custom");
					ShowAlert.showInfo("Complete", "Profile is successfully loaded");
					
					String colorSet = manager.getSettings().getColorSet();
					
					if(colorSet.equals("clr")) {
						clarityButton.setSelected(true);
					}
					else if(colorSet.equals("hyb")) {
						hyborianButton.setSelected(true);
					}
					else if(colorSet.equals("ols")) {
						oldSchoolButton.setSelected(true);
					}
				} else {
					ShowAlert.showError("Error", "Wrong file");
				}
			}
		});
		exportButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Export Settings");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			fileChooser.setInitialFileName("profile.txt");

			// 🔹 초기 경로를 바탕화면으로 설정
			File desktopDir = new File(System.getProperty("user.home"), "Desktop");
			if (desktopDir.exists()) {
				fileChooser.setInitialDirectory(desktopDir);
			}

			File file = fileChooser.showSaveDialog(stage.getScene().getWindow());
			if (file != null) {
				SettingManager.saveProfile(file);
				ShowAlert.showInfo("Complete", "Your profile has been successfully saved");
			}

		});
		advancedButton.setOnAction(e -> {
			sizeComboBox.setValue("Custom");
			manager.showAdvanced();
		});
	}
	
	private RadioButton createThemeRadio(String name, String imagePath, ToggleGroup group, String toolTip) {
        RadioButton rb = new RadioButton(name);
        rb.setToggleGroup(group);

        // 이미지는 한 번만 로드해서 재사용
        Image img = new Image(
            getClass().getResourceAsStream(imagePath),
            240, 0, true, true   // 너비 240, 비율 유지, 부드럽게 축소
        );
        ImageView view = new ImageView(img);

        Tooltip tip = new Tooltip(toolTip);
        tip.setGraphic(view);
        tip.setShowDelay(Duration.millis(250));   // 0.5초 후 표시
        tip.setHideDelay(Duration.millis(0));     // 커서가 벗어나면 바로 숨김
        tip.setShowDuration(Duration.INDEFINITE); // 마우스를 올려둔 동안 유지

        rb.setTooltip(tip);
        return rb;
    }
}
