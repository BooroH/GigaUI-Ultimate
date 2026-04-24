package application.gui;

import java.nio.file.Files;
import java.nio.file.Paths;

import application.installer.Installer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InstallTypeWindow extends BaseWindow {
	private WindowManager manager;

	private RadioButton quickInstall;
	private RadioButton customInstall;
	private Button nextButton;
	private Button backButton;

	private ToggleGroup installGroup;

	public InstallTypeWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		installGroup = new ToggleGroup();
		quickInstall = new RadioButton("Install");
		customInstall = new RadioButton("Customize");
		quickInstall.setToggleGroup(installGroup);
		customInstall.setToggleGroup(installGroup);

		nextButton = new Button("Next");
		backButton = new Button("Back");

		if (Files.exists(Paths.get(manager.getAoCDir(), "Data", "Gui", "Customized", "GigaUI_Patch_Note.txt"))) {
			customInstall.setSelected(true);
			nextButton.setText("Next");
		} else {
			quickInstall.setSelected(true);
			nextButton.setText("Install");
		}

	}

	@Override
	protected Parent createView() {
		VBox centerBox = new VBox(15); // 여백
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(new Label("What to do?"), quickInstall, customInstall);

		HBox bottomButtons = new HBox(10, backButton, nextButton);
		bottomButtons.setPadding(new Insets(15));
		bottomButtons.setAlignment(Pos.BOTTOM_RIGHT);

		BorderPane root = new BorderPane();
		root.setCenter(centerBox);
		root.setBottom(bottomButtons);

		return root;
	}

	@Override
	protected void initActions() {
		installGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
			if (quickInstall.isSelected()) {
				nextButton.setText("Install");
			} else {
				nextButton.setText("Next");
			}
		});

		backButton.setOnAction(e -> {
			manager.showStart();
		});
		nextButton.setOnAction(e -> {
			if (quickInstall.isSelected()) {
				if (Files.exists(
						Paths.get(manager.getAoCDir(), "Data", "Gui", "Customized", "Views", "HUD", "BottomBar.xml"))) {
					if (ShowAlert.showConfirm("Continue?", "Existing 'Cutomized' folder will be removed")) {
						if (Installer.runInstaller(manager.getAoCDir())) {
							ShowAlert.showInfo("Complete", "Install Done");
						}
					}
				} else {
					if (Installer.runInstaller(manager.getAoCDir())) {
						ShowAlert.showInfo("Complete", "Install Done");
					}
				}
			} else {
				if (!Files
						.exists(Paths.get(manager.getAoCDir(), "Data", "Gui", "Customized", "GigaUI_Patch_Note.txt"))) {
					ShowAlert.showError("File not found", "Install the UI first");
					return;
				} else
					manager.showCustom();
			}
		});
	}
}
