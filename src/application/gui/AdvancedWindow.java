package application.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdvancedWindow extends BaseWindow {
	private WindowManager manager;

	private Button topBuffIconsButton;
	private Button miniSlotButton;
	private Button mainPortraitButton;
	private Button floatingPortraitButton;
	private Button castBarButton;
	private Button csiButton;
	private Button soulshardButton;
	private Button unitListButton;
	private Button overheadButton;
	private Button fontButton;
	private Button darkModeButton;
	private Button closeButton;

	public AdvancedWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		topBuffIconsButton = new Button("Top Buff Icons");
		miniSlotButton = new Button("Action Bar");
		mainPortraitButton = new Button("Left & Right Portrait");
		floatingPortraitButton = new Button("Floating Portrait");
		castBarButton = new Button("Castbar");
		csiButton = new Button("Combo Sequencer");
		soulshardButton = new Button("Misc");
		unitListButton = new Button("Group, Raid, Pet");
		overheadButton = new Button("Overhead");
		fontButton = new Button("Font");
		darkModeButton = new Button("Dark Mode");
		closeButton = new Button("Close");

	}

	@Override
	protected Parent createView() {
		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(miniSlotButton, castBarButton, csiButton, mainPortraitButton,
				floatingPortraitButton, unitListButton, topBuffIconsButton, overheadButton, fontButton, darkModeButton,
				soulshardButton);

		HBox bottomButtons = new HBox(10, closeButton);
		bottomButtons.setPadding(new Insets(15));
		bottomButtons.setAlignment(Pos.BOTTOM_RIGHT);

		BorderPane root = new BorderPane();
		root.setCenter(centerBox);
		root.setBottom(bottomButtons);

		return root;
	}

	@Override
	protected void initActions() {
		topBuffIconsButton.setOnAction(e -> {
			manager.showTopBuffIcons(stage);
		});
		miniSlotButton.setOnAction(e -> {
			manager.showMiniSlot(stage);
		});
		mainPortraitButton.setOnAction(e -> {
			manager.showMainPortrait(stage);
		});
		floatingPortraitButton.setOnAction(e -> {
			manager.showFloatingPortrait(stage);
		});
		castBarButton.setOnAction(e -> {
			manager.showCastBarWindow(stage);
		});
		csiButton.setOnAction(e -> {
			manager.showCSIWindow(stage);
		});
		soulshardButton.setOnAction(e -> {
			manager.showSoulShardWindow(stage);
		});
		unitListButton.setOnAction(e -> {
			manager.showUnitListWindow(stage);
		});
		overheadButton.setOnAction(e -> {
			manager.showOverheadWindow(stage);
		});
		fontButton.setOnAction(e -> {
			manager.showFontWindow(stage);
		});
		darkModeButton.setOnAction(e -> {
			manager.showDarkModeWindow(stage);
		});
		closeButton.setOnAction(e -> stage.close());
	}

	@Override
	protected double getWidth() {
		return 440;
	}

	@Override
	protected double getHeight() {
		return 610;
	}

	@Override
	protected String getTitle() {
		return "Advanced Settings";
	}
}