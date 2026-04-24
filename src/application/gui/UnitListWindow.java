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

public class UnitListWindow extends BaseWindow {
	private final WindowManager manager;

	private CheckBox petLimitCheckBox;

	private Slider petLimitSlider;
	private Label petLimitLabel;

	private Slider petAlphaSlider;
	private Label petAlphaLabel;
	
	private Slider groupAlphaSlider;
	private Label groupAlphaLabel;
	
	private Slider groupWidthSlider;
	private Label groupWidthLabel;
	
	private Slider groupHeightSlider;
	private Label groupHeightLabel;
	
	private Slider nameBgSlider;
	private Label nameBgLabel;
	
	private Slider raidAlphaSlider;
	private Label raidAlphaLabel;
	
	private Slider raidWdithSlider;
	private Label raidWdithLabel;
	
	private Slider raidHeightSlider;
	private Label raidHeightLabel;

	private Button okButton;
	private Button backButton;

	public UnitListWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		boolean petLimit = manager.getSettings().isPetListSizeLimit();
		int petMaxHeight = manager.getSettings().getPetListSize();
		int petAlpha = (int) (manager.getSettings().getPetListAlpha() * 100);
		int groupAlpha = (int) (manager.getSettings().getGroupHPAlpha() * 100);
		int raidAlpha = (int) (manager.getSettings().getRaidHPAlpha() * 100);
		int groupWidth = manager.getSettings().getGroup_hp_width();
		int groupHeight = manager.getSettings().getGroup_hp_height();
		int raidWidth = manager.getSettings().getRaid_hp_width();
		int raidHeight = manager.getSettings().getRaid_hp_height();
		int nameBgAlpha = (int) (manager.getSettings().getGroup_nameBg_alpha() * 100);
		
		groupWidthSlider = createSlider(131, 227, groupWidth, 2, 300);
		groupWidthLabel = new Label(String.valueOf(groupWidth));
		
		groupHeightSlider = createSlider(11, 29, groupHeight, 2, 300);
		groupHeightLabel = new Label(String.valueOf(groupHeight));
		
		groupAlphaSlider = createSlider(80, 100, groupAlpha, 1, 300);
		groupAlphaLabel = new Label(String.valueOf(groupAlpha));

		nameBgSlider = createSlider(50, 100, nameBgAlpha, 1, 300);
		nameBgLabel = new Label(String.valueOf(nameBgAlpha));
		
		raidWdithSlider = createSlider(119, 227, raidWidth, 2, 300);
		raidWdithLabel = new Label(String.valueOf(raidWidth));
		
		raidHeightSlider = createSlider(9, 29, raidHeight, 2, 300);
		raidHeightLabel = new Label(String.valueOf(raidHeight));
		
		raidAlphaSlider = createSlider(80, 100, raidAlpha, 1, 300);
		raidAlphaLabel = new Label(String.valueOf(raidAlpha));
		
		petLimitCheckBox = new CheckBox("");

		petLimitSlider = createSlider(180, 330, petMaxHeight, 1, 300);
		petLimitLabel = new Label(String.valueOf(petMaxHeight));

		petAlphaSlider = createSlider(80, 100, petAlpha, 1, 300);
		petAlphaLabel = new Label(String.valueOf(petAlpha));

		petLimitCheckBox.setSelected(petLimit);
		if (!petLimit) {
			petLimitSlider.setDisable(true);
			petLimitLabel.setText("");
		}

		backButton = new Button("Back");
		okButton = new Button("OK");
	}

	@Override
	protected Parent createView() {
		HBox limitBox = new HBox(10, new Label("Limit height"), petLimitCheckBox);
		HBox petLimitBox = new HBox(10, new Label("Maximum height"), petLimitSlider, petLimitLabel);
		HBox petAlphaBox = new HBox(10, new Label("Alpha"), petAlphaSlider, petAlphaLabel);
		HBox groupAlphaBox = new HBox(10, new Label("Alpha"), groupAlphaSlider, groupAlphaLabel);
		HBox raidAlphaBox = new HBox(10, new Label("Alpha"), raidAlphaSlider, raidAlphaLabel);
		HBox groupWidthBox = new HBox(10, new Label("Width"), groupWidthSlider, groupWidthLabel);
		HBox groupHeightBox = new HBox(10, new Label("Height"), groupHeightSlider, groupHeightLabel);
		HBox nameBgBox = new HBox(10, new Label("Name area alpha"), nameBgSlider, nameBgLabel);
		HBox raidWdithBox = new HBox(10, new Label("Width"), raidWdithSlider, raidWdithLabel);
		HBox raidHeightBox = new HBox(10, new Label("Height"), raidHeightSlider, raidHeightLabel);

		Label petLabel = new Label("PET");
		VBox.setMargin(petLabel, new Insets(20, 0, 0, 0));
		
		Label raidLabel = new Label("Raid");
		VBox.setMargin(raidLabel, new Insets(20, 0, 0, 0));
		
		Label groupLabel = new Label("Group");
		VBox.setMargin(groupLabel, new Insets(20, 0, 0, 0));
		
		Label generalLabel = new Label("General");

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(generalLabel, nameBgBox, groupLabel, groupWidthBox, groupHeightBox, groupAlphaBox, raidLabel, raidWdithBox, raidHeightBox, raidAlphaBox,petLabel, limitBox,
				petLimitBox, petAlphaBox);

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
		groupWidthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			groupWidthSlider.setValue(newVal.intValue());
			groupWidthLabel.setText(String.valueOf(newVal.intValue()));
		});
		groupHeightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			groupHeightSlider.setValue(newVal.intValue());
			groupHeightLabel.setText(String.valueOf(newVal.intValue()));
		});
		groupAlphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			groupAlphaSlider.setValue(newVal.intValue());
			groupAlphaLabel.setText(String.valueOf(newVal.intValue()));
		});
		nameBgSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			nameBgSlider.setValue(newVal.intValue());
			nameBgLabel.setText(String.valueOf(newVal.intValue()));
		});
		
		raidWdithSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			raidWdithSlider.setValue(newVal.intValue());
			raidWdithLabel.setText(String.valueOf(newVal.intValue()));
		});
		raidHeightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			raidHeightSlider.setValue(newVal.intValue());
			raidHeightLabel.setText(String.valueOf(newVal.intValue()));
		});
		raidAlphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			raidAlphaSlider.setValue(newVal.intValue());
			raidAlphaLabel.setText(String.valueOf(newVal.intValue()));
		});
		
		petLimitCheckBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
			petLimitSlider.setDisable(!isSelected);
			if (isSelected) {
				petLimitLabel.setText(String.valueOf((int) petLimitSlider.getValue()));
			} else {
				petLimitLabel.setText("");
			}
		});
		petLimitSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			petLimitSlider.setValue(newVal.intValue());
			petLimitLabel.setText(String.valueOf(newVal.intValue()));
		});
		petAlphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			petAlphaSlider.setValue(newVal.intValue());
			petAlphaLabel.setText(String.valueOf(newVal.intValue()));
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			manager.getSettings().setPetListSizeLimit(petLimitCheckBox.isSelected());
			manager.getSettings().setPetListSize((int) petLimitSlider.getValue());
			manager.getSettings().setPetListAlpha(petAlphaSlider.getValue() / 100);
			manager.getSettings().setGroupHPAlpha(groupAlphaSlider.getValue() / 100);
			manager.getSettings().setRaidHPAlpha(raidAlphaSlider.getValue() / 100);
			manager.getSettings().setGroup_hp_width((int) groupWidthSlider.getValue());
			manager.getSettings().setGroup_hp_height((int) groupHeightSlider.getValue());
			manager.getSettings().setRaid_hp_width((int) raidWdithSlider.getValue());
			manager.getSettings().setRaid_hp_height((int) raidHeightSlider.getValue());
			//네임 bg알파 두개 묶어서
			manager.getSettings().setGroup_nameBg_alpha(nameBgSlider.getValue() / 100);
			manager.getSettings().setRaid_nameBg_alpha(nameBgSlider.getValue() / 100);
			
			
			manager.showAdvanced(stage);
		});
	}

	@Override
	protected double getWidth() { return 550; }
	@Override
    protected double getHeight() { return 700; }
	@Override
	protected String getTitle() {
		return "Group, Raid, Pet List";
	}

}