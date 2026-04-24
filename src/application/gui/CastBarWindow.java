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

public class CastBarWindow extends BaseWindow {
	private final WindowManager manager;

	private Slider hueSlider;
	private Label hueLabel;
	private Slider textLocSlider;
	private Label textLocLabel;
	private Slider widthSlider;
	private Label widthLabel;
	private Slider heightSlider;
	private Label heightLabel;
	private Slider alphaSlider;
	private Label alphaLabel;
	private Slider text_y_slider;
	private Label text_y_label;
	private CheckBox isSparkleOn;

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
		String color = "";
		if (hue<=-170) {
			color = "Deep Blue";
		}
		else if(hue<=-160) {
			color = "Indigo Blue";
		}
		else if(hue<=-150) {
			color = "Indigo";
		}
		else if(hue<=-140) {
			color = "Violet";
		}
		else if(hue<=-130) {
			color = "Purple";
		}
		else if(hue<=-120) {
			color = "Magenta";
		}
		else if(hue<=-110) {
			color = "Light Pink";
		}
		else if(hue<=-100) {
			color = "Pink";
		}
		else if(hue<=-90) {
			color = "Hot Pink";
		}
		else if(hue<=-80) {
			color = "Deep Pink";
		}
		else if(hue<=-70) {
			color = "Light Rose";
		}
		else if(hue<=-60){
			color = "Rose";
		}
		else if(hue<=-50){
			color = "Light Peach";
		}
		else if(hue<=-40){
			color = "Peach";
		}
		else if(hue<=-30){
			color = "Deep Peach";
		}
		else if(hue<=-20){
			color = "Vermilion";
		}
		else if(hue<=-10){
			color = "Orange Red";
		}
		else if(hue<0){
			color = "Orange";
		}
		else if(hue<=10){
			color = "Gold";
		}
		else if(hue<=20){
			color = "Yellow";
		}
		else if(hue<=30){
			color = "Lemon";
		}
		else if(hue<=40){
			color = "Lime";
		}
		else if(hue<=50){
			color = "Light Green";
		}
		else if(hue<=60){
			color = "Green";
		}
		else if(hue<=70){
			color = "Deep Green";
		}
		else if(hue<=80){
			color = "Teal";
		}
		else if(hue<=90){
			color = "Jade";
		}
		else if(hue<=100){
			color = "Turquoise";
		}
		else if(hue<=110){
			color = "Emerald";
		}
		else if(hue<=120){
			color = "Cyan Green";
		}
		else if(hue<=130){
			color = "Cyan";
		}
		else if(hue<=140){
			color = "Aqua";
		}
		else if(hue<=150){
			color = "Azure";
		}
		else if(hue<=160){
			color = "Sky Blue";
		}
		else if(hue<=170){
			color = "Light Blue";
		}
		else if(hue<=180){
			color = "Blue";
		}
		else {
			color = "white";
		}
		
		hueLabel = new Label(color+" ("+String.valueOf(hue) + ")");

		textLocSlider = createSlider(1, 3, textLoc, 1, 120);
		textLocLabel = new Label("");
		
		text_y_slider = createSlider(-7, 7, text_y, 1, 200);
		text_y_label = new Label(String.valueOf(text_y));

		widthSlider = createSlider(197, 345, width, 2, 300);
		widthLabel = new Label(String.valueOf(width));
		
		heightSlider = createSlider(31, 55, height, 2, 300);
		heightLabel = new Label(String.valueOf(height));

		alphaSlider = createSlider(50, 90, alpha, 5, 200);
		alphaLabel = new Label(String.valueOf(alpha));

		if (textLoc == 1) {
			textLocLabel.setText("Top");

		} else if (textLoc == 2) {
			textLocLabel.setText("Inside");
		} else if (textLoc == 3) {
			textLocLabel.setText("Bottom");
		}
		
		isSparkleOn = new CheckBox("");
		isSparkleOn.setSelected(manager.getSettings().isCastbarSparkleOn());
		
		backButton = new Button("Back");
		okButton = new Button("OK");
		
	}

	@Override
	protected Parent createView() {
		HBox hueBox = new HBox(10, new Label("Color"), hueSlider, hueLabel);
		HBox textLocBox = new HBox(10, new Label("Text Location"), textLocSlider, textLocLabel);
		HBox text_y_box = new HBox(10, new Label("Text Move y"), text_y_slider, text_y_label);
		HBox widthBox = new HBox(10, new Label("Width"), widthSlider, widthLabel);
		HBox heightBox = new HBox(10, new Label("Height"), heightSlider, heightLabel);
		HBox alphaBox = new HBox(10, new Label("Background Alpha"), alphaSlider, alphaLabel);
		HBox isSparkleOnBox = new HBox(10, new Label("Sparkling effect"), isSparkleOn);

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(widthBox, heightBox, hueBox, textLocBox, text_y_box, alphaBox, isSparkleOnBox);

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
		textLocSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			textLocSlider.setValue(newVal.intValue());
			if (newVal.intValue() == 1) {
				textLocLabel.setText("Top");
			} else if (newVal.intValue() == 2) {
				textLocLabel.setText("Inside");
			} else if (newVal.intValue() == 3) {
				textLocLabel.setText("Bottom");
			}
		});
		hueSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			hueSlider.setValue(newVal.intValue());
			int hue = newVal.intValue();
			String color = "";
			if (hue<=-170) {
				color = "Deep Blue";
			}
			else if(hue<=-160) {
				color = "Indigo Blue";
			}
			else if(hue<=-150) {
				color = "Indigo";
			}
			else if(hue<=-140) {
				color = "Violet";
			}
			else if(hue<=-130) {
				color = "Purple";
			}
			else if(hue<=-120) {
				color = "Magenta";
			}
			else if(hue<=-110) {
				color = "Light Pink";
			}
			else if(hue<=-100) {
				color = "Pink";
			}
			else if(hue<=-90) {
				color = "Hot Pink";
			}
			else if(hue<=-80) {
				color = "Deep Pink";
			}
			else if(hue<=-70) {
				color = "Light Rose";
			}
			else if(hue<=-60){
				color = "Rose";
			}
			else if(hue<=-50){
				color = "Light Peach";
			}
			else if(hue<=-40){
				color = "Peach";
			}
			else if(hue<=-30){
				color = "Deep Peach";
			}
			else if(hue<=-20){
				color = "Vermilion";
			}
			else if(hue<=-10){
				color = "Orange Red";
			}
			else if(hue<0){
				color = "Orange";
			}
			else if(hue<=10){
				color = "Gold";
			}
			else if(hue<=20){
				color = "Yellow";
			}
			else if(hue<=30){
				color = "Lemon";
			}
			else if(hue<=40){
				color = "Lime";
			}
			else if(hue<=50){
				color = "Light Green";
			}
			else if(hue<=60){
				color = "Green";
			}
			else if(hue<=70){
				color = "Deep Green";
			}
			else if(hue<=80){
				color = "Teal";
			}
			else if(hue<=90){
				color = "Jade";
			}
			else if(hue<=100){
				color = "Turquoise";
			}
			else if(hue<=110){
				color = "Emerald";
			}
			else if(hue<=120){
				color = "Cyan Green";
			}
			else if(hue<=130){
				color = "Cyan";
			}
			else if(hue<=140){
				color = "Aqua";
			}
			else if(hue<=150){
				color = "Azure";
			}
			else if(hue<=160){
				color = "Sky Blue";
			}
			else if(hue<=170){
				color = "Light Blue";
			}
			else if(hue<=180){
				color = "Blue";
			}
			else {
				color = "white";
			}
			hueLabel.setText(color+" ("+String.valueOf(hue)+")");

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
			manager.getSettings().setCastBar_text_location((int) textLocSlider.getValue());
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

}
