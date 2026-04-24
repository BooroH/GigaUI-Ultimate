package application.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OverheadWindow extends BaseWindow {
	private final WindowManager manager;

	private Slider hpWidthSlider;
	private Label hpWidthLabel;
	private Slider hpHeightSlider;
	private Label hpHeightLabel;
	private Slider hpAlphaSlider;
	private Label hpAlphaLabel;
	private Slider fontWeightSlider;
	private Label fontWeightLabel;
	private Slider fontSizeSlider;
	private Label fontSizeLabel;

	private Button okButton;
	private Button backButton;

	public OverheadWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		int hpWidth = manager.getSettings().getOverheadHPWidth();
		int hpHeight = manager.getSettings().getOverheadHPHeight();
		int hpAlpha = (int) (manager.getSettings().getOverheadHPAlpha() * 100);
		String fontWeight = manager.getSettings().getOverheadFontWeight();
		int fontSize = manager.getSettings().getOverheadFontSize();

		int weightNum = 2;
		fontWeightLabel = new Label("");
		if (fontWeight.equals("GG Text Regular")) {
			weightNum = 1;
			fontWeightLabel.setText("Regular");
		} else if (fontWeight.equals("GG OTF Medium")) {
			weightNum = 2;
			fontWeightLabel.setText("Medium");
		} else if (fontWeight.equals("GG Bold")) {
			weightNum = 3;
			fontWeightLabel.setText("Bold");
		}

		hpWidthSlider = createSlider(99, 227, hpWidth, 2, 280);
		hpWidthLabel = new Label(String.valueOf(hpWidth));

		hpHeightSlider = createSlider(12, 24, hpHeight, 1, 280);
		hpHeightLabel = new Label(String.valueOf(hpHeight));

		hpAlphaSlider = createSlider(70, 100, hpAlpha, 1, 300);
		hpAlphaLabel = new Label(String.valueOf(hpAlpha));

		fontWeightSlider = createSlider(1, 3, weightNum, 1, 150);

		fontSizeSlider = createSlider(30, 48, fontSize, 1, 300);
		fontSizeLabel = new Label(String.valueOf(fontSize));

		backButton = new Button("Back");
		okButton = new Button("OK");
	}

	@Override
	protected Parent createView() {
		HBox hpWidthBox = new HBox(10, new Label("Width"), hpWidthSlider, hpWidthLabel);
		HBox hpHeightBox = new HBox(10, new Label("Height"), hpHeightSlider, hpHeightLabel);
		HBox hpAlphaBox = new HBox(10, new Label("Alpha"), hpAlphaSlider, hpAlphaLabel);
		HBox fontWeightBox = new HBox(10, new Label("Weight"), fontWeightSlider, fontWeightLabel);
		HBox fontSizeBox = new HBox(10, new Label("Size"), fontSizeSlider, fontSizeLabel);

		Label textLabel = new Label("TEXT");
		VBox.setMargin(textLabel, new Insets(20, 0, 0, 0));

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(new Label("HEALTH BAR"), hpWidthBox, hpHeightBox, hpAlphaBox, textLabel,
				fontWeightBox, fontSizeBox);

		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox bottomButtons = new HBox(10, new Label("Restart the game to apply change"), spacer, backButton, okButton);
		bottomButtons.setPadding(new Insets(15));
		bottomButtons.setAlignment(Pos.BOTTOM_RIGHT);

		BorderPane root = new BorderPane();
		root.setCenter(centerBox);
		root.setBottom(bottomButtons);

		return root;
	}

	@Override
	protected void initActions() {
		hpAlphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			hpAlphaSlider.setValue(newVal.intValue());
			hpAlphaLabel.setText(String.valueOf(newVal.intValue()));
		});
		fontWeightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			fontWeightSlider.setValue(newVal.intValue());
			if (newVal.intValue() == 1) {
				fontWeightLabel.setText("Regular");
			} else if (newVal.intValue() == 2) {
				fontWeightLabel.setText("Medium");
			} else if (newVal.intValue() == 3) {
				fontWeightLabel.setText("Bold");
			}
		});
		fontSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			fontSizeSlider.setValue(newVal.intValue());
			fontSizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		hpWidthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			hpWidthSlider.setValue(newVal.intValue());
			hpWidthLabel.setText(String.valueOf(newVal.intValue()));
		});
		hpHeightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			hpHeightSlider.setValue(newVal.intValue());
			hpHeightLabel.setText(String.valueOf(newVal.intValue()));
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			manager.getSettings().setOverheadHPWidth((int) hpWidthSlider.getValue());
			manager.getSettings().setOverheadHPHeight((int) hpHeightSlider.getValue());
			manager.getSettings().setOverheadHPAlpha(hpAlphaSlider.getValue() / 100);
			String fontType = "GG Medium";
			if (((int) fontWeightSlider.getValue()) == 1) {
				fontType = "GG Text Regular";
			} else if (((int) fontWeightSlider.getValue()) == 2) {
				fontType = "GG OTF Medium";
			} else if (((int) fontWeightSlider.getValue()) == 3) {
				fontType = "GG Bold";
			}
			manager.getSettings().setOverheadFontWeight(fontType);
			manager.getSettings().setOverheadFontSize((int) fontSizeSlider.getValue());
			manager.showAdvanced(stage);
		});
	}

	@Override
	protected String getTitle() {
		return "Overhead";
	}
}