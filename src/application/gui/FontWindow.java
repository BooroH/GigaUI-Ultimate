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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FontWindow extends BaseWindow {
	private final WindowManager manager;

	private Slider generalSizeSlider;
	private Label generalSizeLabel;
	
	private Slider cdSizeSlider;
	private Label cdSizeLabel;

	private Slider cdWeightSlider;
	private Label cdWeightLabel;

	private CheckBox isHpNumBold;
	private CheckBox isBuffTimerBold;
	
	private Slider BuffStackLocSlider;
	private Label BuffStackLocLabel;

	private Button okButton;
	private Button backButton;

	public FontWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}
	
	@Override
	protected void initComponents() {
		int generalSize = manager.getSettings().getFontSize();
		int cdSize = manager.getSettings().getCDFontSize();
		String cdWeight = manager.getSettings().getCDFontWeight();
		boolean hpNumBold = manager.getSettings().isHPLabelBold();
		
		generalSizeLabel = new Label(String.valueOf(generalSize));
		cdSizeLabel = new Label(String.valueOf(cdSize));
		cdWeightLabel = new Label("");
		
		int cd_Font_Weight = 3;
		if (cdWeight.equals("GG Text Regular")) {
			cd_Font_Weight = 1;
			cdWeightLabel.setText("Regular");
		} else if (cdWeight.equals("GG Medium")) {
			cd_Font_Weight = 2;
			cdWeightLabel.setText("Medium");
		} else if (cdWeight.equals("GG Bold")) {
			cd_Font_Weight = 3;
			cdWeightLabel.setText("Bold");
		}
		
		generalSizeSlider = createSlider(0, 6, generalSize, 1, 180);
		cdSizeSlider = createSlider(12, 24, cdSize, 1, 300);
		cdWeightSlider = createSlider(1, 3, cd_Font_Weight, 1, 150);
		isHpNumBold = new CheckBox("");
		isHpNumBold.setSelected(hpNumBold);
		
		isBuffTimerBold = new CheckBox("");
		isBuffTimerBold.setSelected(manager.getSettings().isBuffTimerBold());
		
		int buffStackLoc = manager.getSettings().getBuff_stack_loc();
		BuffStackLocSlider = createSlider(0, 1, buffStackLoc, 1, 100);
		BuffStackLocLabel = new Label("");
		if(buffStackLoc==0) {
			BuffStackLocLabel.setText("On the icon");
		}
		else {
			BuffStackLocLabel.setText("Above the icon");
		}
		
		backButton = new Button("Back");
		okButton = new Button("OK");
	}

	@Override
	protected Parent createView() {
		HBox generalSizeBox = new HBox(10, new Label("Font Size"), generalSizeSlider, generalSizeLabel);
		HBox cdSizeBox = new HBox(10, new Label("Size"), cdSizeSlider, cdSizeLabel);
		HBox cdWeightBox = new HBox(10, new Label("Weight"), cdWeightSlider, cdWeightLabel);
		HBox isHpNumBoldBox = new HBox(10, new Label("Bold"), isHpNumBold);
		HBox isBuffTimerBoldBox = new HBox(10, new Label("Bold"), isBuffTimerBold);
		HBox buffStackLocBox = new HBox(10, new Label("Stack number location"), BuffStackLocSlider, BuffStackLocLabel);
		
		Label generalSectionLabel = new Label("General");
		Label cdSectionLabel = new Label("Cooldown Timer");
		VBox.setMargin(cdSectionLabel, new Insets(15, 0, 0, 0));
		Label hpSectionLabel = new Label("HP Numbers");
		VBox.setMargin(hpSectionLabel, new Insets(15, 0, 0, 0));
		Label buffSectionLabel = new Label("Buff Timers");
		VBox.setMargin(buffSectionLabel, new Insets(15, 0, 0, 0));

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(generalSectionLabel, generalSizeBox, cdSectionLabel, cdSizeBox, cdWeightBox, hpSectionLabel, isHpNumBoldBox, buffSectionLabel,isBuffTimerBoldBox,buffStackLocBox);
		
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
		generalSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			generalSizeSlider.setValue(newVal.intValue());
			generalSizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		cdSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			cdSizeSlider.setValue(newVal.intValue());
			cdSizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		cdWeightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			cdWeightSlider.setValue(newVal.intValue());
			if (newVal.intValue() == 1) {
				cdWeightLabel.setText("Regular");
			} else if (newVal.intValue() == 2) {
				cdWeightLabel.setText("Medium");
			} else if (newVal.intValue() == 3) {
				cdWeightLabel.setText("Bold");
			}
		});
		BuffStackLocSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			BuffStackLocSlider.setValue(newVal.intValue());
			if(newVal.intValue()==0) {
				BuffStackLocLabel.setText("On the icon");
			}
			else {
				BuffStackLocLabel.setText("Above the icon");
			}
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			manager.getSettings().setFontSize((int)generalSizeSlider.getValue());
			manager.getSettings().setCDFontSize((int)cdSizeSlider.getValue());
			String fontType = "GG Bold";
			if (((int) cdWeightSlider.getValue()) == 1) {
				fontType = "GG Text Regular";
			} else if (((int) cdWeightSlider.getValue()) == 2) {
				fontType = "GG Medium";
			} else if (((int) cdWeightSlider.getValue()) == 3) {
				fontType = "GG Bold";
			}
			manager.getSettings().setCDFontWeight(fontType);
			manager.getSettings().setHPLabelBold(isHpNumBold.isSelected());
			manager.getSettings().setBuffTimerBold(isBuffTimerBold.isSelected());
			manager.getSettings().setBuff_stack_loc((int)BuffStackLocSlider.getValue());
			manager.showAdvanced(stage);
		});
	}
	
	@Override
	protected double getWidth() { return 600; }
	@Override
    protected double getHeight() { return 510; }
	@Override
	protected String getTitle() {
		return "Font";
	}

}