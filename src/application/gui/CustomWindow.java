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

		importButton = new Button("Import");
		exportButton = new Button("Export");
		advancedButton = new Button("Advanced");
		backButton = new Button("Back");
		saveButton = new Button("Save");
	}

	@Override
	protected Parent createView() {
		Label quickSetup = new Label("Quick Setup");
		Label scaleLabel = new Label("Scale");
		Label themeLabel = new Label("Theme");
		VBox.setMargin(themeLabel, new Insets(20, 0, 0, 0));
		VBox.setMargin(scaleLabel, new Insets(6, 0, 0, 0));

		HBox sizeBox = new HBox(10, sizeComboBox,restartLabel);
		HBox themeBox = new HBox(10, gigaThemeButton, darkThemeButton);

		HBox topRow = new HBox(10);
		topRow.setAlignment(Pos.CENTER_LEFT);
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		topRow.getChildren().addAll(quickSetup, spacer, importButton, exportButton);

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(topRow, scaleLabel, sizeBox, themeLabel, themeBox);

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
}
