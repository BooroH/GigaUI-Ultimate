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
	private Slider minimalMapSlider;
	private Label minimalMapLabel;

	private Slider sidePanelSizeSlider;
	private Label sidePanelSizeLabel;
	private Slider invenSlider;
	private Label invenLabel;
	private Slider traderSlider;
	private Label traderLabel;

	private Slider menuIcon_location_slider;
	private Label menuIcon_location_label;
	private Slider menuIcon_layout_slider;
	private Label menuIcon_layout_label;

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
		minimalMapSlider = createSlider(0, 1, minimalMap, 1, 150);
		minimalMapLabel = new Label("");

		if (minimalMap == 0)
			minimalMapLabel.setText("No");
		else if (minimalMap == 1)
			minimalMapLabel.setText("Yes");

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

		invenSlider = createSlider(1, 3, inven, 1, 200);
		invenLabel = new Label("");

		traderSlider = createSlider(1, 2, trader, 1, 200);
		traderLabel = new Label("");

		if (inven == 1)
			invenLabel.setText("8 x 10");
		else if (inven == 2)
			invenLabel.setText("10 x 13");
		else if (inven == 3)
			invenLabel.setText("12 x 15");

		if (trader == 1)
			traderLabel.setText("10 x 5");
		else if (trader == 2)
			traderLabel.setText("15 x 7");

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

		menuIcon_location_slider = createSlider(0, 1, isMenuIcon_right, 1, 130);
		menuIcon_location_label = new Label("");

		menuIcon_layout_slider = createSlider(0, 1, isMenuIcon_horizontal, 1, 130);
		menuIcon_layout_label = new Label("");

		if (isMenuIcon_right == 0)
			menuIcon_location_label.setText("left");
		else if (isMenuIcon_right == 1)
			menuIcon_location_label.setText("right");

		if (isMenuIcon_horizontal == 0)
			menuIcon_layout_label.setText("vertical");
		else if (isMenuIcon_horizontal == 1)
			menuIcon_layout_label.setText("horizontal");

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
		Label souldShardLabel = new Label("Assassin Soulshards");
		HBox heightBox = new HBox(10, new Label("Size"), heightSlider, heightLabel);
		HBox moveXBox = new HBox(10, new Label("Move x"), moveXSlider, moveXLabel);
		HBox moveYBox = new HBox(10, new Label("Move y"), moveYSlider, moveYLabel);

		// 맵,미니맵
		Label mapLabel = new Label("Minimap & Map");
		VBox.setMargin(mapLabel, new Insets(20, 0, 0, 0));
		HBox minimapSizeBox = new HBox(10, new Label("Minimap size"), minimapSizeSlider, minimapSizeLabel);
		HBox minimalMapBox = new HBox(10, new Label("Minimal map"), minimalMapSlider, minimalMapLabel);

		// 사이드패널,인벤토리
		Label sidePanelInventoryLabel = new Label("Side panel & Inventory");
		VBox.setMargin(sidePanelInventoryLabel, new Insets(20, 0, 0, 0));
		HBox sidePanelSizeBox = new HBox(10, new Label("Side panel size"), sidePanelSizeSlider, sidePanelSizeLabel);
		HBox invenBox = new HBox(10, new Label("Inventory layout"), invenSlider, invenLabel);
		HBox traderBox = new HBox(10, new Label("Trader layout"), traderSlider, traderLabel);

		// 메뉴아이콘
		Label menuIconsLabel = new Label("Menu Icons");
		VBox.setMargin(menuIconsLabel, new Insets(20, 0, 0, 0));
		HBox locationBox = new HBox(10, new Label("Location"), menuIcon_location_slider, menuIcon_location_label);
		HBox layoutBox = new HBox(10, new Label("Layout"), menuIcon_layout_slider, menuIcon_layout_label);

		Label optimizeLabel = new Label("Optimize");
		VBox.setMargin(optimizeLabel, new Insets(20, 0, 0, 0));
		HBox removeMinimapBox = new HBox(10, new Label("Remove minimap, clock"), removeMinimap);
		HBox removeHelpBox = new HBox(10, new Label("Remove help button"), removeHelp);
		HBox removeRankBox = new HBox(10, new Label("Remove rank button"), removeRank);
		HBox removeXpBarBox = new HBox(10, new Label("Remove xp bar"), removeXpBar);

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
		minimalMapSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			minimalMapSlider.setValue(newVal.intValue());
			if (newVal.intValue() == 0)
				minimalMapLabel.setText("no");
			else if (newVal.intValue() == 1)
				minimalMapLabel.setText("yes");
		});

		// 사이드패널,인벤토리
		sidePanelSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			sidePanelSizeSlider.setValue(newVal.intValue());
			sidePanelSizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		invenSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			invenSlider.setValue(newVal.intValue());
			if (newVal.intValue() == 1)
				invenLabel.setText("8 x 10");
			else if (newVal.intValue() == 2)
				invenLabel.setText("10 x 13");
			else if (newVal.intValue() == 3)
				invenLabel.setText("12 x 15");
		});

		traderSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			traderSlider.setValue(newVal.intValue());
			if (newVal.intValue() == 1)
				traderLabel.setText("10 x 5");
			else if (newVal.intValue() == 2)
				traderLabel.setText("15 x 7");
		});

		// 메뉴아이콘
		menuIcon_location_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			menuIcon_location_slider.setValue(newVal.intValue());
			if (newVal.intValue() == 0)
				menuIcon_location_label.setText("left");
			else if (newVal.intValue() == 1)
				menuIcon_location_label.setText("right");
		});
		menuIcon_layout_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			menuIcon_layout_slider.setValue(newVal.intValue());
			if (newVal.intValue() == 0)
				menuIcon_layout_label.setText("vertical");
			else if (newVal.intValue() == 1)
				menuIcon_layout_label.setText("horizontal");
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			manager.getSettings().setSoulshard_height((int) heightSlider.getValue());
			manager.getSettings().setSoulshard_x((int) moveXSlider.getValue());
			manager.getSettings().setSoulshard_y((int) moveYSlider.getValue());

			// 맵
			manager.getSettings().setMinimap_size((int) minimapSizeSlider.getValue());
			manager.getSettings().setIsSimpleMap((int) minimalMapSlider.getValue());

			// 사이드패널,인벤토리
			manager.getSettings().setInventory_layout((int) invenSlider.getValue());
			manager.getSettings().setTrader_layout((int) traderSlider.getValue());
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

			// 메뉴아이콘
			if (((int) menuIcon_location_slider.getValue()) == 1)
				manager.getSettings().setMenuIcon_right(true);
			else
				manager.getSettings().setMenuIcon_right(false);

			if (((int) menuIcon_layout_slider.getValue()) == 1)
				manager.getSettings().setMenuIcon_horizontal(true);
			else
				manager.getSettings().setMenuIcon_horizontal(false);

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
		return 840;
	}

	@Override
	protected String getTitle() {
		return "Misc";
	}

}