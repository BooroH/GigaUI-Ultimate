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

public class MiniSlotWindow extends BaseWindow {
	private final WindowManager manager;

	private Slider main_slotSizeSlider;
	private Label main_slotSizeLabel;
	private Slider main_rowSlider;
	private Label main_rowLabel;
	private Slider main_colSlider;
	private Label main_colLabel;
	private CheckBox main_hideEmptySlotCheckBox;
	
	private Slider colSlider;
	private Label colLabel;
	private Slider sizeSlider;
	private Label sizeLabel;
	private Slider gapSlider;
	private Label gapLabel;
	private Slider moveXSlider;
	private Label moveXLabel;
	private Slider moveYSlider;
	private Label moveYLabel;
	private Slider mainGapSlider;
	private Label mainGapLabel;
	private Slider mainMoveYSlider;
	private Label mainMoveYLabel;
	private Slider alphaSlider;
	private Label alphaLabel;
	private Slider sidebarSizeSlider;
	private Label sidebarSizeLabel;

	private Button okButton;
	private Button backButton;

	private CheckBox hideCheckBox;

	public MiniSlotWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		int main_row = manager.getSettings().getBottomBar_row();
		int main_col = manager.getSettings().getBottomBar_column();
		int main_slotSize = manager.getSettings().getSlot_size();
		boolean main_hide = manager.getSettings().isHideEmptySlots();
		
		int mainGap = manager.getSettings().getIcon_spacing();
		int mainY = manager.getSettings().getBottomBar_Pos_Y();
		int alpha = manager.getSettings().getSlot_bg_alpha();
		int col = manager.getSettings().getMiniSlot_count();
		int size = manager.getSettings().getMiniSlot_size();
		int gap = manager.getSettings().getMiniSlot_spacing();
		int x = manager.getSettings().getMiniSlot_pos_x();
		int y = manager.getSettings().getMiniSlot_pos_y();
		int sidebarSize = manager.getSettings().getSidebar_slot_size();
		boolean hide = manager.getSettings().isMiniSlotHide();
		
		main_rowSlider = createSlider(2, 3, main_row, 1, 120);
		main_rowLabel = new Label(String.valueOf(main_row));

		main_colSlider = createSlider(15, 29, main_col, 2, 280);
		main_colLabel = new Label(String.valueOf(main_col));

		main_slotSizeSlider = createSlider(47, 83, main_slotSize, 2, 300);
		main_slotSizeLabel = new Label(String.valueOf(main_slotSize));

		main_hideEmptySlotCheckBox = new CheckBox();
		main_hideEmptySlotCheckBox.setSelected(main_hide);
		

		mainGapSlider = createSlider(-15, 0, mainGap, 1, 300);
		mainGapLabel = new Label(String.valueOf(mainGap));

		mainMoveYSlider = createSlider(0, 800, mainY, 1, 300);
		mainMoveYLabel = new Label(String.valueOf(mainY));
		
		alphaSlider = createSlider(20, 90, alpha, 5, 300);
		alphaLabel = new Label(String.valueOf(alpha)+"%");

		colSlider = createSlider(0, 5, col, 1, 200);
		colLabel = new Label(String.valueOf(col));

		sizeSlider = createSlider(31, 83, size, 2, 360);
		sizeLabel = new Label(String.valueOf(size));

		gapSlider = createSlider(-15, 0, gap, 1, 360);
		gapLabel = new Label(String.valueOf(gap));

		moveXSlider = createSlider(-1000, 1000, x, 1, 350);
		moveXLabel = new Label(String.valueOf(x));

		moveYSlider = createSlider(0, 800, y, 1, 350);
		moveYLabel = new Label(String.valueOf(y));

		hideCheckBox = new CheckBox("");
		hideCheckBox.setSelected(hide);
		
		sidebarSizeSlider = createSlider(31, 83, sidebarSize, 2, 360);
		sidebarSizeLabel = new Label(String.valueOf(sidebarSize));

		if (col == 0) {
			sizeSlider.setDisable(true);
			sizeLabel.setText("");
			gapSlider.setDisable(true);
			gapLabel.setText("");
			moveXSlider.setDisable(true);
			moveXLabel.setText("");
			moveYSlider.setDisable(true);
			moveYLabel.setText("");
			hideCheckBox.setDisable(true);
		}

		if (main_hide) {
			hideCheckBox.setDisable(true);
			hideCheckBox.setSelected(true);
		}

		if (main_col > 17) {
			if (main_col > 27 || main_row > 2) {
				colSlider.setDisable(true);
				colLabel.setText("");
				sizeSlider.setDisable(true);
				sizeLabel.setText("");
				gapSlider.setDisable(true);
				gapLabel.setText("");
				moveXSlider.setDisable(true);
				moveXLabel.setText("");
				moveYSlider.setDisable(true);
				moveYLabel.setText("");
				colSlider.setValue(0);
			} else {
				colSlider.setDisable(false);
				colLabel.setText(String.valueOf((int)colSlider.getValue()));
				sizeSlider.setDisable(false);
				sizeLabel.setText(String.valueOf((int)sizeSlider.getValue()));
				gapSlider.setDisable(false);
				gapLabel.setText(String.valueOf((int)gapSlider.getValue()));
				moveXSlider.setDisable(false);
				moveXLabel.setText(String.valueOf((int)moveXSlider.getValue()));
				moveYSlider.setDisable(false);
				moveYLabel.setText(String.valueOf((int)moveYSlider.getValue()));
			}
		}

		backButton = new Button("Back");
		okButton = new Button("OK");
	}

	@Override
	protected Parent createView() {
		HBox main_rowBox = new HBox(10, new Label("Row"), main_rowSlider, main_rowLabel);
		HBox main_colBox = new HBox(10, new Label("Column"), main_colSlider, main_colLabel);
		HBox main_slotSizeBox = new HBox(10, new Label("Size"), main_slotSizeSlider, main_slotSizeLabel);
		HBox main_hideEmptySlotBox = new HBox(10, new Label("Hide Empty Slots"), main_hideEmptySlotCheckBox);
		VBox.setMargin(main_hideEmptySlotBox, new Insets(5, 0, 0, 0));
		
		HBox mainGapBox = new HBox(10, new Label("Gap"), mainGapSlider, mainGapLabel);
		HBox mainMoveYBox = new HBox(10, new Label("Move y"), mainMoveYSlider, mainMoveYLabel);
		HBox alphaBox = new HBox(10, new Label("BG Alpha"), alphaSlider, alphaLabel);

		HBox colBox = new HBox(10, new Label("Column"), colSlider, colLabel);
		HBox sizeBox = new HBox(10, new Label("Size"), sizeSlider, sizeLabel);
		HBox gapBox = new HBox(10, new Label("Gap"), gapSlider, gapLabel);
		HBox moveXBox = new HBox(10, new Label("Move x"), moveXSlider, moveXLabel);
		HBox moveYBox = new HBox(10, new Label("Move y"), moveYSlider, moveYLabel);
		HBox hideBox = new HBox(10, new Label("Hide Empty Slots"), hideCheckBox);
		HBox sidebarSizeBox = new HBox(10, new Label("Size"), sidebarSizeSlider, sidebarSizeLabel);
		
		Label miniBarLabel = new Label("Mini Bar");
		VBox.setMargin(miniBarLabel, new Insets(20, 0, 0, 0));
		
		Label sideBarLabel = new Label("Side Bar");
		VBox.setMargin(sideBarLabel, new Insets(20, 0, 0, 0));
		
		Label generalLabel = new Label("General");
		VBox.setMargin(generalLabel, new Insets(20, 0, 0, 0));

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(new Label("Bottom Bar"),main_slotSizeBox,main_rowBox,main_colBox, mainGapBox, mainMoveYBox,main_hideEmptySlotBox, miniBarLabel, colBox,
				sizeBox, gapBox, moveXBox, moveYBox, hideBox,sideBarLabel, sidebarSizeBox,generalLabel,alphaBox);

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
		main_rowSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			main_rowSlider.setValue(newVal.intValue());
			main_rowLabel.setText(String.valueOf(newVal.intValue()));

			if (newVal.intValue() > 2) {
				if (((int) main_colSlider.getValue()) > 19) {
					main_colSlider.setValue(19);
					main_colLabel.setText(String.valueOf((int) main_colSlider.getValue()));
				}
				
				//미니슬롯 비활성화
				if(main_colSlider.getValue()>17) {
					colSlider.setDisable(true);
					colLabel.setText("");
					sizeSlider.setDisable(true);
					sizeLabel.setText("");
					gapSlider.setDisable(true);
					gapLabel.setText("");
					moveXSlider.setDisable(true);
					moveXLabel.setText("");
					moveYSlider.setDisable(true);
					moveYLabel.setText("");
					colSlider.setValue(0);
				}
				else {
					colSlider.setDisable(false);
					colLabel.setText(String.valueOf((int)colSlider.getValue()));
					if(colSlider.getValue()>0) {
						sizeSlider.setDisable(false);
						gapSlider.setDisable(false);
						moveXSlider.setDisable(false);
						moveYSlider.setDisable(false);
					}
				}
			}
			else {
				if(main_colSlider.getValue()>27) {
					colSlider.setDisable(true);
					colLabel.setText("");
					sizeSlider.setDisable(true);
					sizeLabel.setText("");
					gapSlider.setDisable(true);
					gapLabel.setText("");
					moveXSlider.setDisable(true);
					moveXLabel.setText("");
					moveYSlider.setDisable(true);
					moveYLabel.setText("");
					colSlider.setValue(0);
				}
				else {
					colSlider.setDisable(false);
					colLabel.setText(String.valueOf((int)colSlider.getValue()));
					if(colSlider.getValue()>0) {
						sizeSlider.setDisable(false);
						gapSlider.setDisable(false);
						moveXSlider.setDisable(false);
						moveYSlider.setDisable(false);
					}
				}
			}
			
		});
		main_colSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			main_colSlider.setValue(newVal.intValue());
			main_colLabel.setText(String.valueOf(newVal.intValue()));

			if (newVal.intValue() > 19) {
				if (((int) main_rowSlider.getValue()) > 2) {
					main_rowSlider.setValue(2);
					main_rowLabel.setText(String.valueOf((int) main_rowSlider.getValue()));
				}
			}
			
			//미니슬롯 비활성화
			if (newVal.intValue() > 17) {
				if (newVal.intValue() > 27 || main_rowSlider.getValue() > 2) {
					colSlider.setDisable(true);
					colLabel.setText("");
					sizeSlider.setDisable(true);
					sizeLabel.setText("");
					gapSlider.setDisable(true);
					gapLabel.setText("");
					moveXSlider.setDisable(true);
					moveXLabel.setText("");
					moveYSlider.setDisable(true);
					moveYLabel.setText("");
					colSlider.setValue(0);
				} else {
					colSlider.setDisable(false);
					colLabel.setText(String.valueOf((int)colSlider.getValue()));
					if(colSlider.getValue()>0) {
						sizeSlider.setDisable(false);
						gapSlider.setDisable(false);
						moveXSlider.setDisable(false);
						moveYSlider.setDisable(false);
					}
				}
			}
			else {
				colSlider.setDisable(false);
				colLabel.setText(String.valueOf((int)colSlider.getValue()));
				if(colSlider.getValue()>0) {
					sizeSlider.setDisable(false);
					gapSlider.setDisable(false);
					moveXSlider.setDisable(false);
					moveYSlider.setDisable(false);
				}
			}
		});
		main_slotSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			main_slotSizeSlider.setValue(newVal.intValue());
			main_slotSizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		
		mainGapSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			mainGapSlider.setValue(newVal.intValue());
			mainGapLabel.setText(String.valueOf(newVal.intValue()));
		});
		mainMoveYSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			mainMoveYSlider.setValue(newVal.intValue());
			mainMoveYLabel.setText(String.valueOf(newVal.intValue()));
		});
		main_hideEmptySlotCheckBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
			if (!isSelected) {
				if(colSlider.getValue()>0) {
					hideCheckBox.setDisable(false);
				}
				hideCheckBox.setSelected(false);
			} else {
				hideCheckBox.setDisable(true);
				hideCheckBox.setSelected(true);
			}
		});
		alphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			alphaSlider.setValue(newVal.intValue());
			alphaLabel.setText(String.valueOf(newVal.intValue())+"%");
		});
		
		colSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			colSlider.setValue(newVal.intValue());
			colLabel.setText(String.valueOf(newVal.intValue()));

			if (newVal.intValue() == 0) {
				sizeSlider.setDisable(true);
				sizeLabel.setText("");
				gapSlider.setDisable(true);
				gapLabel.setText("");
				moveXSlider.setDisable(true);
				moveXLabel.setText("");
				moveYSlider.setDisable(true);
				moveYLabel.setText("");
				if (main_hideEmptySlotCheckBox.isSelected())
					hideCheckBox.setSelected(true);
				else
					hideCheckBox.setSelected(false);
				hideCheckBox.setDisable(true);
			} else {
				sizeSlider.setDisable(false);
				sizeLabel.setText(String.valueOf((int) sizeSlider.getValue()));
				gapSlider.setDisable(false);
				gapLabel.setText(String.valueOf((int) gapSlider.getValue()));
				moveXSlider.setDisable(false);
				moveXLabel.setText(String.valueOf((int) moveXSlider.getValue()));
				moveYSlider.setDisable(false);
				moveYLabel.setText(String.valueOf((int) moveYSlider.getValue()));
				if (main_hideEmptySlotCheckBox.isSelected()) {
					hideCheckBox.setDisable(true);
					hideCheckBox.setSelected(true);
				} else {
					hideCheckBox.setDisable(false);
				}
			}
		});
		gapSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			gapSlider.setValue(newVal.intValue());
			gapLabel.setText(String.valueOf(newVal.intValue()));
		});
		sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			sizeSlider.setValue(newVal.intValue());
			sizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		moveXSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			moveXSlider.setValue(newVal.intValue());
			moveXLabel.setText(String.valueOf(newVal.intValue()));
		});
		moveYSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			moveYSlider.setValue(newVal.intValue());
			moveYLabel.setText(String.valueOf(newVal.intValue()));
		});
		
		sidebarSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			sidebarSizeSlider.setValue(newVal.intValue());
			sidebarSizeLabel.setText(String.valueOf(newVal.intValue()));
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			manager.getSettings().setBottomBar_row((int) main_rowSlider.getValue());
			manager.getSettings().setBottomBar_column((int) main_colSlider.getValue());
			manager.getSettings().setSlot_size((int) main_slotSizeSlider.getValue());
			manager.getSettings().setHideEmptySlots(main_hideEmptySlotCheckBox.isSelected());
			
			manager.getSettings().setIcon_spacing((int) mainGapSlider.getValue());
			manager.getSettings().setBottomBar_Pos_Y((int) mainMoveYSlider.getValue());
			manager.getSettings().setSlot_bg_alpha((int) alphaSlider.getValue());
			
			if ((int) main_colSlider.getValue() > 17) {
				if ((int) main_colSlider.getValue() > 27 || (int) main_rowSlider.getValue() > 2) {
					manager.getSettings().setMiniSlot_count(0);
				} else {
					manager.getSettings().setMiniSlot_count((int) colSlider.getValue());
				}
			}
			else {
				manager.getSettings().setMiniSlot_count((int) colSlider.getValue());
			}
			
			manager.getSettings().setMiniSlot_size((int) sizeSlider.getValue());
			manager.getSettings().setMiniSlot_spacing((int) gapSlider.getValue());
			manager.getSettings().setMiniSlot_pos_x((int) moveXSlider.getValue());
			manager.getSettings().setMiniSlot_pos_y((int) moveYSlider.getValue());
			manager.getSettings().setMiniSlotHide(hideCheckBox.isSelected());
			
			manager.getSettings().setSidebar_slot_size((int) sidebarSizeSlider.getValue());
			
			manager.showAdvanced(stage);
		});
	}

	@Override
	protected double getWidth() {
		return 600;
	}

	@Override
	protected double getHeight() {
		return 820;
	}
	@Override
	protected String getTitle() {
		return "Action Bar";
	}
}
