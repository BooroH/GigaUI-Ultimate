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

public class FloatingPortraitWindow extends BaseWindow {
	private final WindowManager manager;

	private Slider nameBgAlphaSlider;
	private Label nameBgAlphaLabel;
	private Slider buffSizeSlider;
	private Label buffSizeLabel;
	private Slider buffGapSlider;
	private Label buffGapLabel;
	private Slider buffColSlider;
	private Label buffColLabel;
	private Slider barAlphaSlider;
	private Label barAlphaLabel;
	private Slider hp_height_slider;
	private Label hp_height_label;

	private Button okButton;
	private Button backButton;

	CheckBox showManaCheckBox;
	CheckBox showDebuffCheckBox;

	public FloatingPortraitWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		int height = manager.getSettings().getFloatingPortrait_hp_height();
		int alpha = manager.getSettings().getFloatingPortrait_bg_alpha();
		int nameBgAlpha = (int) (manager.getSettings().getNameplate_alpha() * 100);
		boolean showMana = manager.getSettings().isFloatingPortrait_show_mana();

		int debuffFilter = manager.getSettings().getFloatingPortrait_buff_filter();
		int debuffSize = manager.getSettings().getFloatingPortrait_buff_size();
		int debuffGap = manager.getSettings().getFloatingPortrait_buff_spacing();
		int debuffCol = manager.getSettings().getFloatingPortrait_buff_column();
		
		int portrait_size;

		if (height == 55) {
			portrait_size = 92;
		} else if (height == 51) {
			portrait_size = 84;
		} else if (height == 49) {
			portrait_size = 76;
		} else if (height == 45) {
			portrait_size = 68;
		} else if (height == 43) {
			portrait_size = 60;
		} else if (height == 39) {
			portrait_size = 52;
		} else if (height == 35) {
			portrait_size = 44;
		} else if (height == 33) {
			portrait_size = 36;
		} else if (height == 29) {
			portrait_size = 28;
		} else {
			portrait_size = 100;
		}

		hp_height_slider = createSlider(28, 100, portrait_size, 8, 300);
		hp_height_label = new Label(String.valueOf(portrait_size)+"%");

		barAlphaSlider = createSlider(20, 90, alpha, 5, 300);
		barAlphaLabel = new Label(String.valueOf(alpha));

		nameBgAlphaSlider = createSlider(0, 100, nameBgAlpha, 1, 300);
		nameBgAlphaLabel = new Label(String.valueOf(nameBgAlpha));

		showManaCheckBox = new CheckBox("");
		showDebuffCheckBox = new CheckBox("");

		buffSizeSlider = createSlider(19, 49, debuffSize, 1, 300);
		buffSizeLabel = new Label(String.valueOf(debuffSize));

		buffGapSlider = createSlider(0, 5, debuffGap, 1, 200);
		buffGapLabel = new Label(String.valueOf(debuffGap));

		buffColSlider = createSlider(5, 20, debuffCol, 1, 300);
		buffColLabel = new Label(String.valueOf(debuffCol));

		showManaCheckBox.setSelected(showMana);

		if (debuffFilter == 0) {
			showDebuffCheckBox.setSelected(false);
			buffSizeSlider.setDisable(true);
			buffSizeLabel.setText("");
			buffGapSlider.setDisable(true);
			buffGapLabel.setText("");
			buffColSlider.setDisable(true);
			buffColLabel.setText("");
		} else {
			showDebuffCheckBox.setSelected(true);
			buffColSlider.setDisable(false);
			buffColLabel.setText(String.valueOf(debuffCol));
		}

		backButton = new Button("Back");
		okButton = new Button("OK");
	}

	@Override
	protected Parent createView() {
		HBox nameBgAlphaBox = new HBox(10, new Label("Nameplate alpha"), nameBgAlphaSlider, nameBgAlphaLabel);
		HBox buffSizeBox = new HBox(10, new Label("Size"), buffSizeSlider, buffSizeLabel);
		HBox buffGapBox = new HBox(10, new Label("Gap"), buffGapSlider, buffGapLabel);
		HBox buffColBox = new HBox(10, new Label("Column"), buffColSlider, buffColLabel);
		HBox barAlphaBox = new HBox(10, new Label("BG alpha"), barAlphaSlider, barAlphaLabel);
		HBox sizeBox = new HBox(10, new Label("Size"), hp_height_slider, hp_height_label);
		HBox showManaBox = new HBox(10, new Label("Show mana/stam"), showManaCheckBox);
		HBox showDebuffBox = new HBox(10, new Label("Show debuff"), showDebuffCheckBox);

		Label barLabel = new Label("STATUS BAR");
		Label debuffLabel = new Label("DEBUFF");
		VBox.setMargin(debuffLabel, new Insets(20, 0, 0, 0));

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(barLabel, sizeBox, nameBgAlphaBox, barAlphaBox,
				showManaBox, debuffLabel, showDebuffBox, buffSizeBox, buffGapBox, buffColBox);

		HBox bottomButtons = new HBox(10, backButton, okButton);
		bottomButtons.setPadding(new Insets(15));
		bottomButtons.setAlignment(Pos.BOTTOM_RIGHT);

		BorderPane root = new BorderPane();
		root.setCenter(centerBox);
		root.setBottom(bottomButtons);

		return root;
	}

	@Override
	protected void initActions() {

		hp_height_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			hp_height_slider.setValue(newVal.intValue());
			hp_height_label.setText(String.valueOf(newVal.intValue())+"%");
		});

		barAlphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			barAlphaSlider.setValue(newVal.intValue());
			barAlphaLabel.setText(String.valueOf(newVal.intValue()));
		});

		nameBgAlphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			nameBgAlphaSlider.setValue(newVal.intValue());
			nameBgAlphaLabel.setText(String.valueOf(newVal.intValue()));
		});

		showDebuffCheckBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
			if (!isSelected) {
				buffSizeSlider.setDisable(true);
				buffSizeLabel.setText("");
				buffGapSlider.setDisable(true);
				buffGapLabel.setText("");
				buffColSlider.setDisable(true);
				buffColLabel.setText("");
			} else {
				buffSizeSlider.setDisable(false);
				buffSizeLabel.setText(String.valueOf((int) buffSizeSlider.getValue()));
				buffGapSlider.setDisable(false);
				buffGapLabel.setText(String.valueOf((int) buffGapSlider.getValue()));
				buffColSlider.setDisable(false);
				buffColLabel.setText(String.valueOf((int) buffColSlider.getValue()));
			}
		});

		buffSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			buffSizeSlider.setValue(newVal.intValue());
			buffSizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		buffGapSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			buffGapSlider.setValue(newVal.intValue());
			buffGapLabel.setText(String.valueOf(newVal.intValue()));
		});
		buffColSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			buffColSlider.setValue(newVal.intValue());
			buffColLabel.setText(String.valueOf(newVal.intValue()));
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			int portrait_size = (int) hp_height_slider.getValue();
			int hp_height = 35;
			if (portrait_size == 28) {
				hp_height = 29;
			} else if (portrait_size == 36) {
				hp_height = 33;
			} else if (portrait_size == 44) {
				hp_height = 35;
			} else if (portrait_size == 52) {
				hp_height = 39;
			} else if (portrait_size == 60) {
				hp_height = 43;
			} else if (portrait_size == 68) {
				hp_height = 45;
			} else if (portrait_size == 76) {
				hp_height = 49;
			} else if (portrait_size == 84) {
				hp_height = 51;
			} else if (portrait_size == 92) {
				hp_height = 55;
			} else if (portrait_size == 100) {
				hp_height = 59;
			}
			manager.getSettings().setFloatingPortrait_hp_height(hp_height);
			manager.getSettings().setFloatingPortrait_bg_alpha((int) barAlphaSlider.getValue());
			manager.getSettings().setNameplate_alpha(nameBgAlphaSlider.getValue() / 100);
			manager.getSettings().setFloatingPortrait_show_mana(showManaCheckBox.isSelected());
			if (showDebuffCheckBox.isSelected()) {
				manager.getSettings().setFloatingPortrait_buff_filter(1);
			} else {
				manager.getSettings().setFloatingPortrait_buff_filter(0);
			}
			manager.getSettings().setFloatingPortrait_buff_size((int) buffSizeSlider.getValue());
			manager.getSettings().setFloatingPortrait_buff_spacing((int) buffGapSlider.getValue());
			manager.getSettings().setFloatingPortrait_buff_column((int) buffColSlider.getValue());
			manager.showAdvanced(stage);
		});
	}

	@Override
	protected double getWidth() {
		return 600;
	}

	@Override
	protected double getHeight() {
		return 500;
	}

	@Override
	protected String getTitle() {
		return "Floating Portrait";
	}

}
