package application.gui;

import application.util.ImageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CastBarWindow extends BaseWindow {
	private final WindowManager manager;

	private Slider hueSlider;
	private Label hueLabel;
	private Slider widthSlider;
	private Label widthLabel;
	private Slider heightSlider;
	private Label heightLabel;
	private Slider alphaSlider;
	private Label alphaLabel;
	private Slider text_y_slider;
	private Label text_y_label;
	private CheckBox isSparkleOn;
	
	private ComboBox<String> textLocComboBox;
	
	private static final Color DEFAULT_CAST_COLOR = Color.web("#e0982b");
	private Rectangle hueSwatch;

	private Button okButton;
	private Button backButton;

	public CastBarWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		int width = manager.getSettings().getCastBar_width();
		int height = manager.getSettings().getCastBar_height();
		int hue = (int) manager.getSettings().getCastBar_hue();
		int textLoc = manager.getSettings().getCastBar_text_location();
		int text_y = manager.getSettings().getCastBar_text_y();
		int alpha = manager.getSettings().getCastBar_alpha();

		hueSlider = createSlider(-180, 180, hue, 1, 360);
		hueLabel = new Label(String.valueOf(hue));
		hueSwatch = new Rectangle(32, 32);
		hueSwatch.setArcWidth(4);
		hueSwatch.setArcHeight(4);
		hueSwatch.setStroke(Color.gray(0.5));
		hueSwatch.setFill(shiftColor(hue));

		textLocComboBox = new ComboBox<>();
		textLocComboBox.getItems().addAll("Top", "Inside", "Bottom");
		if(textLoc==1)
			textLocComboBox.setValue("Top");
		else if(textLoc==2)
			textLocComboBox.setValue("Inside");
		else if(textLoc==3)
			textLocComboBox.setValue("Bottom");
		
		text_y_slider = createSlider(-7, 7, text_y, 1, 200);
		text_y_label = new Label(String.valueOf(text_y));

		widthSlider = createSlider(197, 345, width, 2, 300);
		widthLabel = new Label(String.valueOf(width));
		
		heightSlider = createSlider(31, 55, height, 2, 300);
		heightLabel = new Label(String.valueOf(height));

		alphaSlider = createSlider(50, 90, alpha, 5, 200);
		alphaLabel = new Label(String.valueOf(alpha));
		
		isSparkleOn = new CheckBox("");
		isSparkleOn.setSelected(manager.getSettings().isCastbarSparkleOn());
		
		backButton = new Button("Back");
		okButton = new Button("OK");
		
	}

	@Override
	protected Parent createView() {
		Label castbarLabel = new Label("CASTBAR");
		castbarLabel.getStyleClass().add("section-title");
		
		HBox hueBox = new HBox(10, new Label("Color"), hueSlider, hueSwatch, hueLabel);
		hueBox.setAlignment(Pos.CENTER_LEFT);
		HBox textLocBox = new HBox(10, new Label("Text Layout"), textLocComboBox);
		HBox text_y_box = new HBox(10, new Label("Text Move y"), text_y_slider, text_y_label);
		HBox widthBox = new HBox(10, new Label("Width"), widthSlider, widthLabel);
		HBox heightBox = new HBox(10, new Label("Height"), heightSlider, heightLabel);
		HBox alphaBox = new HBox(10, new Label("Background Alpha"), alphaSlider, alphaLabel);
		HBox isSparkleOnBox = new HBox(10, new Label("Sparkling Effect"), isSparkleOn);

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(castbarLabel, widthBox, heightBox, hueBox, isSparkleOnBox, textLocBox, text_y_box, alphaBox);

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
		alphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			alphaSlider.setValue(newVal.intValue());
			alphaLabel.setText(String.valueOf(newVal.intValue()));
		});
		hueSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
		    hueSlider.setValue(newVal.intValue());
		    hueSwatch.setFill(shiftColor(newVal.intValue()));
		    hueLabel.setText(String.valueOf(newVal.intValue()));
		});
		widthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			widthSlider.setValue(newVal.intValue());
			widthLabel.setText(String.valueOf(newVal.intValue()));
		});
		heightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			heightSlider.setValue(newVal.intValue());
			heightLabel.setText(String.valueOf(newVal.intValue()));
		});
		text_y_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			text_y_slider.setValue(newVal.intValue());
			text_y_label.setText(String.valueOf(newVal.intValue()));
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			manager.getSettings().setCastBar_hue(hueSlider.getValue());
			
			int textLoc;
			
			if(textLocComboBox.getValue().equals("Top"))
				textLoc=1;
			else if(textLocComboBox.getValue().equals("Inside"))
				textLoc=2;
			else
				textLoc=3;
			
			manager.getSettings().setCastBar_text_location(textLoc);
			
			manager.getSettings().setCastBar_alpha((int) alphaSlider.getValue());
			manager.getSettings().setCastBar_width((int) widthSlider.getValue());
			manager.getSettings().setCastBar_height((int) heightSlider.getValue());
			manager.getSettings().setCastBar_text_y((int) text_y_slider.getValue());
			manager.getSettings().setCastbarSparkleOn(isSparkleOn.isSelected());
			manager.showAdvanced(stage);
		});
	}

	@Override
	protected String getTitle() {
		return "Castbar";
	}
	
	private Color shiftColor(double hue) {
	    if (hue == 0) return DEFAULT_CAST_COLOR; // 0이면 원본 그대로 (왕복 변환 오차 방지)

	    int r = (int) Math.round(DEFAULT_CAST_COLOR.getRed() * 255);
	    int g = (int) Math.round(DEFAULT_CAST_COLOR.getGreen() * 255);
	    int b = (int) Math.round(DEFAULT_CAST_COLOR.getBlue() * 255);

	    int rgb = ImageManager.transform(r, g, b, hue, 0, 0);
	    return Color.rgb((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
	}

}
