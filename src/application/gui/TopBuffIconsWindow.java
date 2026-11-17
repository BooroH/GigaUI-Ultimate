package application.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.paint.Color;

public class TopBuffIconsWindow extends BaseWindow {
    private final WindowManager manager;
    
    private Slider sizeSlider;
    private Label sizeLabel;
    private Slider gapSlider;
    private Label gapLabel;
    private Slider colSlider;
    private Label colLabel;
    
    private ComboBox<String> timerWeightComboBox;
    private ComboBox<String> stackWeightComboBox;
	private ComboBox<String> BuffStackLocComboBox;
	private ComboBox<String> timerLayoutComboBox;
	
	private ColorPicker timerColor;
	private ColorPicker stackColor;
	
	private Slider stackPlateAlphaSlider;
    private Label stackPlateAlphaLabel;
	
    private Button backButton;
    private Button okButton;

    public TopBuffIconsWindow(Stage stage, WindowManager manager) {
        super(stage);
        this.manager = manager;
    }
    
    @Override
	protected void initComponents() {
    	int size = manager.getSettings().getTop_buff_size();
		int gap = manager.getSettings().getTop_buff_spacing();
		int col = manager.getSettings().getTop_buff_column();
		
		sizeSlider = createSlider(31, 119, size, 1, 360);
		sizeLabel = new Label(String.valueOf(size));
		
		gapSlider = createSlider(0, 5, gap, 1, 200);
		gapLabel = new Label(String.valueOf(gap));
		
		colSlider = createSlider(10, 40, col, 1, 320);
		colLabel = new Label(String.valueOf(col));
		
		//스택플레이트알파
		int stackPlateAlpha = (int) (manager.getSettings().getStackPlateAlpha() * 100.0);
		stackPlateAlphaSlider = createSlider(0, 100, stackPlateAlpha, 1, 320);
		stackPlateAlphaLabel = new Label(String.valueOf(stackPlateAlpha));
		
		//스택 레이아웃
		BuffStackLocComboBox = new ComboBox<>();
		BuffStackLocComboBox.getItems().addAll("On", "Above");

		int buffStackLoc = manager.getSettings().getBuff_stack_loc();
		
		if (buffStackLoc == 0) {
			BuffStackLocComboBox.setValue("On");
		} else {
			BuffStackLocComboBox.setValue("Above");
		}
		
		timerLayoutComboBox = new ComboBox<>();
		timerLayoutComboBox.getItems().addAll("On", "Below");
		timerLayoutComboBox.setValue(manager.getSettings().getTimerLayout());
		
		timerColor = new ColorPicker(Color.web(manager.getSettings().getBuffTimerColor()));
		timerColor.getStyleClass().add(ColorPicker.STYLE_CLASS_BUTTON);
		timerColor.setStyle("-fx-padding: 0;");
		
		stackColor = new ColorPicker(Color.web(manager.getSettings().getStackColor()));
		stackColor.getStyleClass().add(ColorPicker.STYLE_CLASS_BUTTON);
		stackColor.setStyle("-fx-padding: 0;");
		
		stackWeightComboBox = new ComboBox<>();
		stackWeightComboBox.getItems().addAll("Medium", "Bold");
		String stackWeight = manager.getSettings().getStackWeight();
		stackWeightComboBox.setValue(stackWeight);
		
		timerWeightComboBox = new ComboBox<>();
		timerWeightComboBox.getItems().addAll("Medium", "Bold");
		if(manager.getSettings().isBuffTimerBold()) {
			timerWeightComboBox.setValue("Bold");
		}
		else {
			timerWeightComboBox.setValue("Medium");
		}

		backButton = new Button("Back");
        okButton = new Button("OK");
	}

    @Override
    protected Parent createView() {
    	Label topBuffLabel = new Label("TOP BUFF ICONS");
    	topBuffLabel.getStyleClass().add("section-title");
		
		HBox sizeBox = new HBox(10, new Label("Size"), sizeSlider, sizeLabel);
		HBox gapBox = new HBox(10, new Label("Gap"), gapSlider, gapLabel);
		HBox colBox = new HBox(10, new Label("Column"), colSlider, colLabel);
		
		HBox isBuffTimerBoldBox = new HBox(10, new Label("Weight"), timerWeightComboBox);
		HBox buffStackLocBox = new HBox(10, new Label("Layout"), BuffStackLocComboBox);
		HBox buffTimerColorBox = new HBox(10, new Label("Color"), timerColor);
		HBox stackPlateAlphaBox = new HBox(10, new Label("BG Alpha"), stackPlateAlphaSlider, stackPlateAlphaLabel);
		
		HBox stackWeightdBox = new HBox(10, new Label("Weight"), stackWeightComboBox);
		HBox stackColorBox = new HBox(10, new Label("Color"), stackColor);
		HBox timerLayoutBox = new HBox(10, new Label("Layout"), timerLayoutComboBox);
		
		Label stackSectionLabel = new Label("STACK TEXT");
		stackSectionLabel.getStyleClass().add("section-title");
		VBox.setMargin(stackSectionLabel, new Insets(10, 0, 0, 0));
		
		Label timerSectionLabel = new Label("TIMER TEXT");
		timerSectionLabel.getStyleClass().add("section-title");
		VBox.setMargin(timerSectionLabel, new Insets(10, 0, 0, 0));

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
        centerBox.getChildren().addAll(topBuffLabel, sizeBox, gapBox, colBox, stackSectionLabel, stackColorBox, stackWeightdBox, buffStackLocBox, timerSectionLabel, buffTimerColorBox, isBuffTimerBoldBox, timerLayoutBox, stackPlateAlphaBox);
        
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
		sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			sizeSlider.setValue(newVal.intValue());
			sizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		gapSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			gapSlider.setValue(newVal.intValue());
			gapLabel.setText(String.valueOf(newVal.intValue()));
		});
		colSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			colSlider.setValue(newVal.intValue());
			colLabel.setText(String.valueOf(newVal.intValue()));
		});
		stackPlateAlphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			stackPlateAlphaSlider.setValue(newVal.intValue());
			stackPlateAlphaLabel.setText(String.valueOf(newVal.intValue()));
		});
		
    	backButton.setOnAction(e -> manager.showAdvanced(stage)); 
    	okButton.setOnAction(e -> {
    		manager.getSettings().setTop_buff_size((int) sizeSlider.getValue());
    		manager.getSettings().setTop_buff_spacing((int) gapSlider.getValue());
    		manager.getSettings().setTop_buff_column((int) colSlider.getValue());
    		
    		//스택두께
    		String stackWeight = stackWeightComboBox.getValue();
			manager.getSettings().setStackWeight(stackWeight);
			
    		//타이머 두께
    		boolean isTimerBold;
    		if(timerWeightComboBox.getValue().equals("Bold")) {
    			isTimerBold = true;
    		}
    		else {
    			isTimerBold = false;
    		}
    		manager.getSettings().setBuffTimerBold(isTimerBold);
			
    		//스택레이아웃
			int buffStackLoc;
			
			if(BuffStackLocComboBox.getValue().equals("On"))
				buffStackLoc = 0;
			else
				buffStackLoc = 1;
			
			manager.getSettings().setBuff_stack_loc(buffStackLoc);
			
			//타이머레이아웃
			manager.getSettings().setTimerLayout(timerLayoutComboBox.getValue());
			
			//타이머색
			Color timerC = timerColor.getValue();
			manager.getSettings().setBuffTimerColor(String.format("%02x%02x%02x",
			        (int) Math.round(timerC.getRed() * 255),
			        (int) Math.round(timerC.getGreen() * 255),
			        (int) Math.round(timerC.getBlue() * 255)));
			
			//스택색
			Color stackC = stackColor.getValue();
			manager.getSettings().setStackColor(String.format("%02x%02x%02x",
			        (int) Math.round(stackC.getRed() * 255),
			        (int) Math.round(stackC.getGreen() * 255),
			        (int) Math.round(stackC.getBlue() * 255)));
			
			//플레이트 알파
			double plateAlpha = stackPlateAlphaSlider.getValue()/100.0;
			manager.getSettings().setStackPlateAlpha(plateAlpha);
			
    		manager.showAdvanced(stage);}); 
    }
    
    @Override
	protected double getWidth() { return 600; }
	@Override
    protected double getHeight() { return 620; }
    @Override
    protected String getTitle() { return "Buff Icons"; }
}
