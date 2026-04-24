package application.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TopBuffIconsWindow extends BaseWindow {
    private final WindowManager manager;
    
    private Slider sizeSlider;
    private Label sizeLabel;
    private Slider gapSlider;
    private Label gapLabel;
    private Slider colSlider;
    private Label colLabel;
    
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
		
		sizeSlider = createSlider(31, 99, size, 1, 360);
		sizeLabel = new Label(String.valueOf(size));
		
		gapSlider = createSlider(0, 5, gap, 1, 200);
		gapLabel = new Label(String.valueOf(gap));
		
		colSlider = createSlider(10, 40, col, 1, 320);
		colLabel = new Label(String.valueOf(col));

		backButton = new Button("Back");
        okButton = new Button("OK");
	}

    @Override
    protected Parent createView() {
		HBox sizeBox = new HBox(10, new Label("Size"), sizeSlider, sizeLabel);
		HBox gapBox = new HBox(10, new Label("Gap"), gapSlider, gapLabel);
		HBox colBox = new HBox(10, new Label("Column"), colSlider, colLabel);

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
        centerBox.getChildren().addAll(sizeBox, gapBox, colBox);
        
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
		
    	backButton.setOnAction(e -> manager.showAdvanced(stage)); 
    	okButton.setOnAction(e -> {
    		manager.getSettings().setTop_buff_size((int) sizeSlider.getValue());
    		manager.getSettings().setTop_buff_spacing((int) gapSlider.getValue());
    		manager.getSettings().setTop_buff_column((int) colSlider.getValue());
    		manager.showAdvanced(stage);}); 
    }
    
    @Override
	protected double getWidth() { return 550; }
	@Override
    protected double getHeight() { return 260; }
    @Override
    protected String getTitle() { return "Top Buff Icons"; }
}
