package application.gui;

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

public class SoulShardWindow extends BaseWindow {
	private final WindowManager manager;

	private Slider heightSlider;
	private Label heightLabel;
	private Slider moveXSlider;
	private Label moveXLabel;
	private Slider moveYSlider;
	private Label moveYLabel;

	private Slider minimapSizeSlider;
	private Label minimapSizeLabel;
	private ComboBox<String> mapTypeComboBox;

	private Slider sidePanelSizeSlider;
	private Label sidePanelSizeLabel;
	
	private ComboBox<String> invenComboBox;
	private ComboBox<String> traderComboBox;

	private ComboBox<String> menuIconLocationComboBox;
	private ComboBox<String> menuIconLayoutComboBox;

	private CheckBox removeMinimap;
	private CheckBox removeHelp;
	private CheckBox removeRank;
	private CheckBox removeXpBar;

	private Button okButton;
	private Button backButton;

	public SoulShardWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		// 소울샤드
		int height = manager.getSettings().getSoulshard_height();
		int x = manager.getSettings().getSoulshard_x();
		int y = manager.getSettings().getSoulshard_y();

		heightSlider = createSlider(24, 44, height, 2, 250);
		int height_p = 100 - Math.round((44 - (int) heightSlider.getValue()) / 0.28f);
		heightLabel = new Label(String.valueOf(height_p) + "%");

		moveXSlider = createSlider(-900, 900, x, 1, 350);
		moveXLabel = new Label(String.valueOf(x));
		moveYSlider = createSlider(0, 800, y, 1, 350);
		moveYLabel = new Label(String.valueOf(y));

		// 맵,미니맵
		int minimalMap = manager.getSettings().getIsSimpleMap();
		int minimapSize = manager.getSettings().getMinimap_size();

		minimapSizeSlider = createSlider(228, 404, minimapSize, 2, 300);
		minimapSizeLabel = new Label(String.valueOf(minimapSize));
		
		mapTypeComboBox = new ComboBox<>();
		mapTypeComboBox.getItems().addAll("Default", "Minimal");
		
		if (minimalMap == 0)
			mapTypeComboBox.setValue("Default");
		else
			mapTypeComboBox.setValue("Minimal");

		// 사이드패널,인벤토리
		int inven = manager.getSettings().getInventory_layout();
		int trader = manager.getSettings().getTrader_layout();
		int sidePanelSize;

		if (manager.getSettings().getSidePanelWidth() < 469) {
			sidePanelSize = 1;
		} else if (manager.getSettings().getSidePanelWidth() < 525) {
			sidePanelSize = 2;
		} else if (manager.getSettings().getSidePanelWidth() < 605) {
			sidePanelSize = 3;
		} else {
			sidePanelSize = 4;
		}
		
		sidePanelSizeSlider = createSlider(1, 4, sidePanelSize, 1, 200);
		sidePanelSizeLabel = new Label(String.valueOf(sidePanelSize));
		
		invenComboBox = new ComboBox<>();
		invenComboBox.getItems().addAll("8 x 10", "10 x 13", "12 x 15");
		
		if (inven == 1)
			invenComboBox.setValue("8 x 10");
		else if (inven == 2)
			invenComboBox.setValue("10 x 13");
		else
			invenComboBox.setValue("12 x 15");

		traderComboBox = new ComboBox<>();
		traderComboBox.getItems().addAll("10 x 5", "15 x 7");
		
		if (trader == 1)
			traderComboBox.setValue("10 x 5");
		else
			traderComboBox.setValue("15 x 7");

		// 메뉴아이콘
		int isMenuIcon_right;
		if (manager.getSettings().isMenuIcon_right())
			isMenuIcon_right = 1;
		else
			isMenuIcon_right = 0;

		int isMenuIcon_horizontal;
		if (manager.getSettings().isMenuIcon_horizontal())
			isMenuIcon_horizontal = 1;
		else
			isMenuIcon_horizontal = 0;
		
		menuIconLocationComboBox = new ComboBox<>();
		menuIconLocationComboBox.getItems().addAll("Left Bottom", "Right Bottom");
		
		if (isMenuIcon_right == 0)
			menuIconLocationComboBox.setValue("Left Bottom");
		else
			menuIconLocationComboBox.setValue("Right Bottom");
		
		menuIconLayoutComboBox = new ComboBox<>();
		menuIconLayoutComboBox.getItems().addAll("Vertical", "Horizontal");
		
		if (isMenuIcon_horizontal == 0)
			menuIconLayoutComboBox.setValue("Vertical");
		else
			menuIconLayoutComboBox.setValue("Horizontal");

		// 최적화
		int minimap = manager.getSettings().getShowMiniMap();
		removeMinimap = new CheckBox("");
		if (minimap == 1)
			removeMinimap.setSelected(false);
		else {
			removeMinimap.setSelected(true);
		}
		removeHelp = new CheckBox("");
		removeHelp.setSelected(manager.getSettings().isRemoveHelpButton());
		removeRank = new CheckBox("");
		removeRank.setSelected(manager.getSettings().isRemoveRankButton());
		removeXpBar = new CheckBox("");
		removeXpBar.setSelected(manager.getSettings().isRemoveXpBar());

		backButton = new Button("Back");
		okButton = new Button("OK");
	}

	@Override
	protected Parent createView() {
		Label souldShardLabel = new Label("ASSASSIN SOULSHARD");
		souldShardLabel.getStyleClass().add("section-title");
		HBox heightBox = new HBox(10, new Label("Size"), heightSlider, heightLabel);
		HBox moveXBox = new HBox(10, new Label("Move x"), moveXSlider, moveXLabel);
		HBox moveYBox = new HBox(10, new Label("Move y"), moveYSlider, moveYLabel);

		// 맵,미니맵
		Label mapLabel = new Label("MINIMAP & MAP");
		mapLabel.getStyleClass().add("section-title");
		VBox.setMargin(mapLabel, new Insets(10, 0, 0, 0));
		HBox minimapSizeBox = new HBox(10, new Label("Minimap Size"), minimapSizeSlider, minimapSizeLabel);
		HBox minimalMapBox = new HBox(10, new Label("Map Window Type"), mapTypeComboBox);

		// 사이드패널,인벤토리
		Label sidePanelInventoryLabel = new Label("SIDE PANEL & INVENTORY");
		sidePanelInventoryLabel.getStyleClass().add("section-title");
		VBox.setMargin(sidePanelInventoryLabel, new Insets(10, 0, 0, 0));
		HBox sidePanelSizeBox = new HBox(10, new Label("Side Panel Size"), sidePanelSizeSlider, sidePanelSizeLabel);
		HBox invenBox = new HBox(10, new Label("Inventory Layout"), invenComboBox);
		HBox traderBox = new HBox(10, new Label("Trader Layout"), traderComboBox);

		// 메뉴아이콘
		Label menuIconsLabel = new Label("MENU ICONS");
		menuIconsLabel.getStyleClass().add("section-title");
		VBox.setMargin(menuIconsLabel, new Insets(10, 0, 0, 0));
		HBox locationBox = new HBox(10, new Label("Location"), menuIconLocationComboBox);
		HBox layoutBox = new HBox(10, new Label("Layout"), menuIconLayoutComboBox);
		
		Label optimizeLabel = new Label("OPTIMIZE");
		optimizeLabel.getStyleClass().add("section-title");
		VBox.setMargin(optimizeLabel, new Insets(10, 0, 0, 0));
		HBox removeMinimapBox = new HBox(10, new Label("Remove Minimap, Clock"), removeMinimap);
		HBox removeHelpBox = new HBox(10, new Label("Remove Help Button"), removeHelp);
		HBox removeRankBox = new HBox(10, new Label("Remove Rank Button"), removeRank);
		HBox removeXpBarBox = new HBox(10, new Label("Remove XP Bar"), removeXpBar);

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(souldShardLabel, heightBox, moveXBox, moveYBox, mapLabel, minimapSizeBox,
				minimalMapBox, sidePanelInventoryLabel, sidePanelSizeBox, invenBox, traderBox, menuIconsLabel,
				locationBox, layoutBox, optimizeLabel, removeMinimapBox, removeHelpBox, removeRankBox, removeXpBarBox);

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
		heightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			heightSlider.setValue(newVal.intValue());
			int height_p = 100 - Math.round((44 - newVal.intValue()) / 0.28f);
			heightLabel.setText(String.valueOf(height_p) + "%");
		});
		moveXSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			moveXSlider.setValue(newVal.intValue());
			moveXLabel.setText(String.valueOf(newVal.intValue()));
		});
		moveYSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			moveYSlider.setValue(newVal.intValue());
			moveYLabel.setText(String.valueOf(newVal.intValue()));
		});

		// 맵
		minimapSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			minimapSizeSlider.setValue(newVal.intValue());
			minimapSizeLabel.setText(String.valueOf(newVal.intValue()));
		});

		// 사이드패널,인벤토리
		sidePanelSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			sidePanelSizeSlider.setValue(newVal.intValue());
			sidePanelSizeLabel.setText(String.valueOf(newVal.intValue()));
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			manager.getSettings().setSoulshard_height((int) heightSlider.getValue());
			manager.getSettings().setSoulshard_x((int) moveXSlider.getValue());
			manager.getSettings().setSoulshard_y((int) moveYSlider.getValue());

			// 맵
			manager.getSettings().setMinimap_size((int) minimapSizeSlider.getValue());
			
			int mapType;
			if(mapTypeComboBox.getValue().equals("Default"))
				mapType=0;
			else
				mapType=1;
			
			manager.getSettings().setIsSimpleMap(mapType);

			//인벤토리
			int inven;
			if(invenComboBox.getValue().equals("8 x 10"))
				inven=1;
			else if(invenComboBox.getValue().equals("10 x 13"))
				inven=2;
			else
				inven=3;
			
			manager.getSettings().setInventory_layout(inven);
			
			//트레이더
			int trader;
			if(traderComboBox.getValue().equals("10 x 5"))
				trader=1;
			else
				trader=2;
			
			manager.getSettings().setTrader_layout(trader);
			
			//사이드메뉴
			if ((int) sidePanelSizeSlider.getValue() == 1) {
				manager.getSettings().setSidePanelWidth(389);
			} else if ((int) sidePanelSizeSlider.getValue() == 2) {
				manager.getSettings().setSidePanelWidth(469);
			} else if ((int) sidePanelSizeSlider.getValue() == 3) {
				manager.getSettings().setSidePanelWidth(525);
			}
			else {
				manager.getSettings().setSidePanelWidth(605);
			}

			// 메뉴아이콘 위치
			boolean isMenuIcon_right;
			if(menuIconLocationComboBox.getValue().equals("Left Bottom"))
				isMenuIcon_right=false;
			else
				isMenuIcon_right=true;

			manager.getSettings().setMenuIcon_right(isMenuIcon_right);

			//메뉴아이콘 레이아웃
			boolean isMenuIcon_horizontal;
			if(menuIconLayoutComboBox.getValue().equals("Vertical"))
				isMenuIcon_horizontal=false;
			else
				isMenuIcon_horizontal=true;
			
			manager.getSettings().setMenuIcon_horizontal(isMenuIcon_horizontal);

			// 최적화
			if (removeMinimap.isSelected()) {
				manager.getSettings().setShowMiniMap(0);
			} else {
				manager.getSettings().setShowMiniMap(1);
			}
			manager.getSettings().setRemoveHelpButton(removeHelp.isSelected());
			manager.getSettings().setRemoveRankButton(removeRank.isSelected());
			manager.getSettings().setRemoveXpBar(removeXpBar.isSelected());

			manager.showAdvanced(stage);
		});
	}

	@Override
	protected double getWidth() {
		return 600;
	}

	@Override
	protected double getHeight() {
		return 800;
	}

	@Override
	protected String getTitle() {
		return "Misc";
	}

}