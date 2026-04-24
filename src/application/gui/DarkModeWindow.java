package application.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DarkModeWindow extends BaseWindow {
	private final WindowManager manager;

	private CheckBox darkMode_checkBox;

	private Slider slot_slider;
	private Label slot_label;

	private Slider mainPortrait_slider;
	private Label mainPortrait_label;

	private Slider floatingPortrait_slider;
	private Label floatingPortrait_label;

	private Slider castbar_slider;
	private Label castbar_label;

	private Slider minimap_slider;
	private Label minimap_label;

	private Slider soulshard_slider;
	private Label soulshard_label;

	private Slider csi_slider;
	private Label csi_label;
	
	private Slider windows_slider;
	private Label windows_label;

	private Button okButton;
	private Button backButton;

	public DarkModeWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		boolean isDarkMode = true;
		int slot = manager.getSettings().getSlot_bg_lightness();
		int castbar = manager.getSettings().getCastbar_bg_lightness();
		int mainPortrait = manager.getSettings().getMainPortrait_bg_lightness();
		int floatingPortrait = manager.getSettings().getFloatingPortrait_bg_lightness();
		int minimap = manager.getSettings().getMinimap_bg_lightness();
		int csi = manager.getSettings().getCsi_bg_lightness();
		int soulshard = manager.getSettings().getSoulshard_bg_lightness();
		int windows = manager.getSettings().getWindows_bg_lightness();

		if (slot == 0 && castbar == 0 && mainPortrait == 0 && floatingPortrait == 0 && minimap == 0 && csi == 0 && soulshard == 0 && windows == 0) {
			isDarkMode = false;
		}

		darkMode_checkBox = new CheckBox("");
		darkMode_checkBox.setSelected(isDarkMode);

		slot_slider = createSlider(-100, 0, slot, 1, 300);
		slot_label = new Label("");
		castbar_slider = createSlider(-100, 0, castbar, 1, 300);
		castbar_label = new Label("");
		mainPortrait_slider = createSlider(-100, 0, mainPortrait, 1, 300);
		mainPortrait_label = new Label("");
		floatingPortrait_slider = createSlider(-100, 0, floatingPortrait, 1, 300);
		floatingPortrait_label = new Label("");
		minimap_slider = createSlider(-100, 0, minimap, 1, 300);
		minimap_label = new Label("");
		csi_slider = createSlider(-100, 0, csi, 1, 300);
		csi_label = new Label("");
		soulshard_slider = createSlider(-100, 0, soulshard, 1, 300);
		soulshard_label = new Label("");
		windows_slider = createSlider(-100, 0, windows, 1, 300);
		windows_label = new Label("");

		if (isDarkMode) {
			slot_label.setText(String.valueOf(slot) + "%");
			castbar_label.setText(String.valueOf(castbar) + "%");
			mainPortrait_label.setText(String.valueOf(mainPortrait) + "%");
			floatingPortrait_label.setText(String.valueOf(floatingPortrait) + "%");
			minimap_label.setText(String.valueOf(minimap) + "%");
			csi_label.setText(String.valueOf(csi) + "%");
			soulshard_label.setText(String.valueOf(soulshard) + "%");
			windows_label.setText(String.valueOf(windows) + "%");
		} else {
			slot_slider.setDisable(true);
			castbar_slider.setDisable(true);
			mainPortrait_slider.setDisable(true);
			floatingPortrait_slider.setDisable(true);
			minimap_slider.setDisable(true);
			csi_slider.setDisable(true);
			soulshard_slider.setDisable(true);
			windows_slider.setDisable(true);
		}

		backButton = new Button("Back");
		okButton = new Button("OK");
	}

	@Override
	protected Parent createView() {
		HBox darkModeBox = new HBox(10, new Label("Dark Mode"), darkMode_checkBox);
		HBox slotBox = new HBox(10, new Label("Action bar"), slot_slider, slot_label);
		HBox castbarBox = new HBox(10, new Label("Castbar"), castbar_slider, castbar_label);
		HBox mainPortraittBox = new HBox(10, new Label("Main Portraits"), mainPortrait_slider, mainPortrait_label);
		HBox floatingPortraitBox = new HBox(10, new Label("Floating Portraits"), floatingPortrait_slider,
				floatingPortrait_label);
		HBox minimapBox = new HBox(10, new Label("Minimap"), minimap_slider, minimap_label);
		HBox csiBox = new HBox(10, new Label("Combo Sequencer"), csi_slider, csi_label);
		HBox soulshardBox = new HBox(10, new Label("Soulshards"), soulshard_slider, soulshard_label);
		HBox windowsBox = new HBox(10, new Label("Windows"), windows_slider, windows_label);

		Label brightnessLabel = new Label("BRIGHTNESS");
		VBox.setMargin(brightnessLabel, new Insets(10, 0, 0, 0));

		HBox bottomButtons = new HBox(10, backButton, okButton);
		bottomButtons.setPadding(new Insets(15));
		bottomButtons.setAlignment(Pos.BOTTOM_RIGHT);

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(darkModeBox, brightnessLabel, slotBox, castbarBox, mainPortraittBox,
				floatingPortraitBox, csiBox, soulshardBox, minimapBox, windowsBox);

		BorderPane root = new BorderPane();
		root.setCenter(centerBox);
		root.setBottom(bottomButtons);

		return root;
	}

	@Override
	protected void initActions() {
		darkMode_checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
			if (isSelected) {
				slot_slider.setDisable(false);
				castbar_slider.setDisable(false);
				mainPortrait_slider.setDisable(false);
				floatingPortrait_slider.setDisable(false);
				minimap_slider.setDisable(false);
				csi_slider.setDisable(false);
				soulshard_slider.setDisable(false);
				windows_slider.setDisable(false);
				
				slot_slider.setValue(-64);
				castbar_slider.setValue(-63);
				mainPortrait_slider.setValue(-77);
				floatingPortrait_slider.setValue(-77);
				csi_slider.setValue(-66);
				soulshard_slider.setValue(-66);
				minimap_slider.setValue(-77);
				windows_slider.setValue(-67);
				
				slot_label.setText(String.valueOf((int) slot_slider.getValue()) + "%");
				castbar_label.setText(String.valueOf((int) castbar_slider.getValue()) + "%");
				mainPortrait_label.setText(String.valueOf((int) mainPortrait_slider.getValue()) + "%");
				floatingPortrait_label.setText(String.valueOf((int) floatingPortrait_slider.getValue()) + "%");
				minimap_label.setText(String.valueOf((int) minimap_slider.getValue()) + "%");
				csi_label.setText(String.valueOf((int) csi_slider.getValue()) + "%");
				soulshard_label.setText(String.valueOf((int) soulshard_slider.getValue()) + "%");
				windows_label.setText(String.valueOf((int) windows_slider.getValue()) + "%");
			} else {
				slot_slider.setDisable(true);
				castbar_slider.setDisable(true);
				mainPortrait_slider.setDisable(true);
				floatingPortrait_slider.setDisable(true);
				minimap_slider.setDisable(true);
				csi_slider.setDisable(true);
				soulshard_slider.setDisable(true);
				windows_slider.setDisable(true);
				
				slot_slider.setValue(0);
				castbar_slider.setValue(0);
				mainPortrait_slider.setValue(0);
				floatingPortrait_slider.setValue(0);
				minimap_slider.setValue(0);
				csi_slider.setValue(0);
				soulshard_slider.setValue(0);
				windows_slider.setValue(0);
				
				slot_label.setText("");
				castbar_label.setText("");
				mainPortrait_label.setText("");
				floatingPortrait_label.setText("");
				minimap_label.setText("");
				csi_label.setText("");
				soulshard_label.setText("");
				windows_label.setText("");
			}
		});
		slot_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			slot_slider.setValue(newVal.intValue());
			slot_label.setText(String.valueOf(newVal.intValue()) + "%");
		});
		castbar_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			castbar_slider.setValue(newVal.intValue());
			castbar_label.setText(String.valueOf(newVal.intValue()) + "%");
		});
		mainPortrait_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			mainPortrait_slider.setValue(newVal.intValue());
			mainPortrait_label.setText(String.valueOf(newVal.intValue()) + "%");
		});
		floatingPortrait_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			floatingPortrait_slider.setValue(newVal.intValue());
			floatingPortrait_label.setText(String.valueOf(newVal.intValue()) + "%");
		});
		minimap_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			minimap_slider.setValue(newVal.intValue());
			minimap_label.setText(String.valueOf(newVal.intValue()) + "%");
		});
		csi_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			csi_slider.setValue(newVal.intValue());
			csi_label.setText(String.valueOf(newVal.intValue()) + "%");
		});
		soulshard_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			soulshard_slider.setValue(newVal.intValue());
			soulshard_label.setText(String.valueOf(newVal.intValue()) + "%");
		});
		windows_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			windows_slider.setValue(newVal.intValue());
			windows_label.setText(String.valueOf(newVal.intValue()) + "%");
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			manager.getSettings().setSlot_bg_lightness((int) slot_slider.getValue());
			manager.getSettings().setCastbar_bg_lightness((int) castbar_slider.getValue());
			manager.getSettings().setMainPortrait_bg_lightness((int) mainPortrait_slider.getValue());
			manager.getSettings().setFloatingPortrait_bg_lightness((int) floatingPortrait_slider.getValue());
			manager.getSettings().setMinimap_bg_lightness((int) minimap_slider.getValue());
			manager.getSettings().setCsi_bg_lightness((int) csi_slider.getValue());
			manager.getSettings().setSoulshard_bg_lightness((int) soulshard_slider.getValue());
			manager.getSettings().setWindows_bg_lightness((int) windows_slider.getValue());

			manager.showAdvanced(stage);
		});
	}

	@Override
	protected double getWidth() {
		return 550;
	}

	@Override
	protected double getHeight() {
		return 420;
	}

	@Override
	protected String getTitle() {
		return "Dark Mode";
	}

}
