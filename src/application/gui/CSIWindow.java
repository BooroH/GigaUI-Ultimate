package application.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CSIWindow extends BaseWindow {
	private final WindowManager manager;

	private ComboBox<String> csiTypeComboBox;
	
	private Slider csiSizeSlider;
	private Label csiSizeLabel;
	
	private Slider csiGapSlider;
	private Label csiGapLabel;
	
	private Slider csiBgAlphaSlider;
	private Label csiBgAlphaLabel;
	
	private Slider csiHighlightAlphaSlider;
	private Label csiHighlightAlphaLabel;

	private Button okButton;
	private Button backButton;

	public CSIWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}
	
	@Override
	protected void initComponents() {
		int csiType = manager.getSettings().getIsCSIHorizontal();
		int size = manager.getSettings().getCsi_size();
		int gap = manager.getSettings().getCsi_gap();
		int bgAlpha = manager.getSettings().getCsi_bg_alpha();
		double highLightAlpha = manager.getSettings().getCsi_highlight_alpha();
		
		csiTypeComboBox = new ComboBox<>();
		csiTypeComboBox.getItems().addAll("Vertical", "Horizontal");
		if(csiType==0)
			csiTypeComboBox.setValue("Vertical");
		else if(csiType==1)
			csiTypeComboBox.setValue("Horizontal");


		
		csiSizeSlider = createSlider(47, 83, size, 2, 320);
		csiSizeLabel = new Label(String.valueOf(size));
		
		csiGapSlider = createSlider(-12, -3, gap, 1, 200);
		csiGapLabel = new Label(String.valueOf(gap));
		
		csiBgAlphaSlider = createSlider(0, 90, bgAlpha, 5, 320);
		csiBgAlphaLabel = new Label(String.valueOf(bgAlpha)+"%");
		
		csiHighlightAlphaSlider = createSlider(50, 100, (int)(highLightAlpha*100), 1, 320);
		csiHighlightAlphaLabel = new Label(String.valueOf((int)(highLightAlpha*100))+"%");
	}

	@Override
	protected Parent createView() {
		BorderPane root = new BorderPane();

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		
		Label csiLabel = new Label("COMBO SEQUENCER");
		csiLabel.getStyleClass().add("section-title");

		HBox csiBox = new HBox(10, new Label("Type"), csiTypeComboBox);
		HBox csiSizeBox = new HBox(10, new Label("Size"), csiSizeSlider, csiSizeLabel);
		HBox csiGapBox = new HBox(10, new Label("Gap"), csiGapSlider, csiGapLabel);
		HBox csiBgAlphaBox = new HBox(10, new Label("bg Alpha"), csiBgAlphaSlider, csiBgAlphaLabel);
		HBox csiHighLightAlphaBox = new HBox(10, new Label("Highlight"), csiHighlightAlphaSlider, csiHighlightAlphaLabel);

		backButton = new Button("Back");
		okButton = new Button("OK");

		HBox bottomButtons = new HBox(10, backButton, okButton);
		bottomButtons.setPadding(new Insets(15));
		bottomButtons.setAlignment(Pos.BOTTOM_RIGHT);

		centerBox.getChildren().addAll(csiLabel,csiBox,csiSizeBox,csiGapBox,csiHighLightAlphaBox,csiBgAlphaBox);

		root.setCenter(centerBox);
		root.setBottom(bottomButtons);

		return root;
	}

	@Override
	protected void initActions() {
		csiSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			csiSizeSlider.setValue(newVal.intValue());
			csiSizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		csiGapSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			csiGapSlider.setValue(newVal.intValue());
			csiGapLabel.setText(String.valueOf(newVal.intValue()));
		});
		csiBgAlphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			csiBgAlphaSlider.setValue(newVal.intValue());
			csiBgAlphaLabel.setText(String.valueOf(newVal.intValue())+"%");
		});
		csiHighlightAlphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			csiHighlightAlphaSlider.setValue(newVal.intValue());
			csiHighlightAlphaLabel.setText(String.valueOf(newVal.intValue())+"%");
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			int csiType;
			
			if(csiTypeComboBox.getValue().equals("Vertical"))
				csiType=0;
			else
				csiType=1;
			
			manager.getSettings().setIsCSIHorizontal(csiType);
			
			manager.getSettings().setCsi_size((int)csiSizeSlider.getValue());
			manager.getSettings().setCsi_gap((int)csiGapSlider.getValue());
			manager.getSettings().setCsi_bg_alpha((int)csiBgAlphaSlider.getValue());
			manager.getSettings().setCsi_highlight_alpha(csiHighlightAlphaSlider.getValue()/100);
			manager.showAdvanced(stage);
		});
	}
	
	@Override
	protected double getWidth() {
		return 550;
	}

	@Override
	protected double getHeight() {
		return 360;
	}

	@Override
	protected String getTitle() {
		return "Combo Sequencer";
	}

}