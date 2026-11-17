package application.gui;

import application.util.HexConverter;
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
	
	private ComboBox<String> cdWeightComboBox;

	private CheckBox isDmgTextGigaSolution;

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
		
		cdWeightComboBox = new ComboBox<>();
		cdWeightComboBox.getItems().addAll("Regular", "Medium", "Bold");
		
		if (cdWeight.equals("GG Text Regular"))
			cdWeightComboBox.setValue("Regular");
		else if (cdWeight.equals("GG Medium"))
			cdWeightComboBox.setValue("Medium");
		else if (cdWeight.equals("GG Bold"))
			cdWeightComboBox.setValue("Bold");
		
		cdSizeSlider = createSlider(12, 24, cdSize, 1, 300);
		generalSizeSlider = createSlider(0, 10, generalSize, 1, 180);

		isDmgTextGigaSolution = new CheckBox("");
		isDmgTextGigaSolution.setSelected(manager.getSettings().isDmgTextGigaSolution());

		backButton = new Button("Back");
		okButton = new Button("OK");
	}

	@Override
	protected Parent createView() {
		HBox generalSizeBox = new HBox(10, new Label("Font Size"), generalSizeSlider, generalSizeLabel);
		HBox cdSizeBox = new HBox(10, new Label("Size"), cdSizeSlider, cdSizeLabel);
		HBox cdWeightBox = new HBox(10, new Label("Weight"), cdWeightComboBox);
		HBox isDmgTextGigaSolutionBox = new HBox(10, new Label("Giga Solution"), isDmgTextGigaSolution);

		Label generalSectionLabel = new Label("GENERAL");
		generalSectionLabel.getStyleClass().add("section-title");
		Label cdSectionLabel = new Label("COOLDOWN TIMER");
		cdSectionLabel.getStyleClass().add("section-title");
		VBox.setMargin(cdSectionLabel, new Insets(10, 0, 0, 0));
		Label dmgTextLabel = new Label("Damage Text");
		dmgTextLabel.getStyleClass().add("section-title");
		VBox.setMargin(dmgTextLabel, new Insets(10, 0, 0, 0));

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(generalSectionLabel, generalSizeBox, cdSectionLabel, cdSizeBox, cdWeightBox);

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

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			manager.getSettings().setFontSize((int) generalSizeSlider.getValue());
			manager.getSettings().setCDFontSize((int) cdSizeSlider.getValue());
			
			String cdWeight;
			
			if(cdWeightComboBox.getValue().equals("Regular"))
				cdWeight = "GG Text Regular";
			else if(cdWeightComboBox.getValue().equals("Medium"))
				cdWeight = "GG Medium";
			else
				cdWeight = "GG Bold";

			manager.getSettings().setCDFontWeight(cdWeight);
			
			// manager.getSettings().setDmgTextGigaSolution(isDmgTextGigaSolution.isSelected());
			manager.showAdvanced(stage);
		});
	}

	@Override
	protected double getWidth() {
		return 600;
	}

	@Override
	protected double getHeight() {
		return 300;
	}

	@Override
	protected String getTitle() {
		return "Font";
	}
}