package application.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class MainPortraitWindow extends BaseWindow {
	private final WindowManager manager;

	private Slider l_hp_height_slider;
	private Label l_hp_height_label;
	private Slider l_bg_alpha_slider;
	private Label l_bg_alpha_label;

	private Slider l_colSlider;
	private Label l_colLabel;
	private Slider l_sizeSlider;
	private Label l_sizeLabel;
	private Slider l_gapSlider;
	private Label l_gapLabel;
	private Slider l_moveXSlider;
	private Label l_moveXLabel;
	private Slider l_moveYSlider;
	private Label l_moveYLabel;

	private Slider r_colSlider;
	private Label r_colLabel;
	private Slider r_sizeSlider;
	private Label r_sizeLabel;
	private Slider r_gapSlider;
	private Label r_gapLabel;
	private Slider r_moveXSlider;
	private Label r_moveXLabel;
	private Slider r_moveYSlider;
	private Label r_moveYLabel;
	private Slider r_buffFilterSlider;
	private Label r_buffFilterLabel;

	private Slider ttXSlider;
	private Label ttXLabel;
	private Slider ttYSlider;
	private Label ttYLabel;

	private Button okButton;
	private Button backButton;

	CheckBox syncCheckBox;

	ChangeListener<Number> lPortraitXListener;
	ChangeListener<Number> lPortraitYListener;
	ChangeListener<Number> lpBuffSizeListener;
	ChangeListener<Number> lpBuffSpacingListener;
	ChangeListener<Number> lpBuffColumnListener;

	public MainPortraitWindow(Stage stage, WindowManager manager) {
		super(stage);
		this.manager = manager;
	}

	@Override
	protected void initComponents() {
		int l_hp_height = manager.getSettings().getLPortrait_hp_height();
		int l_bg_alpha = manager.getSettings().getLPortrait_bg_alpha();

		int l_size = manager.getSettings().getLP_buff_size();
		int l_gap = manager.getSettings().getLP_buff_spacing();
		int l_col = manager.getSettings().getLP_buff_column();
		int l_x = manager.getSettings().getLPortrait_x();
		int l_y = manager.getSettings().getLPortrait_y();

		int r_size = manager.getSettings().getRP_buff_size();
		int r_gap = manager.getSettings().getRP_buff_spacing();
		int r_col = manager.getSettings().getRP_buff_column();
		int r_x = manager.getSettings().getRPortrait_x();
		int r_y = manager.getSettings().getRPortrait_y();
		int r_buffFilter = manager.getSettings().getRP_buff_filter();

		int tt_x = manager.getSettings().getTargetOfTargetX() / 2;
		int tt_layout_top = manager.getSettings().getToT_layout_top();

		int portrait_size;

		if (l_hp_height == 55) {
			portrait_size = 92;
		} else if (l_hp_height == 51) {
			portrait_size = 84;
		} else if (l_hp_height == 49) {
			portrait_size = 76;
		} else if (l_hp_height == 45) {
			portrait_size = 68;
		} else if (l_hp_height == 43) {
			portrait_size = 60;
		} else if (l_hp_height == 39) {
			portrait_size = 52;
		} else if (l_hp_height == 35) {
			portrait_size = 44;
		} else if (l_hp_height == 33) {
			portrait_size = 36;
		} else if (l_hp_height == 29) {
			portrait_size = 28;
		} else {
			portrait_size = 100;
		}

		l_hp_height_slider = createSlider(28, 100, portrait_size, 8, 300);
		l_hp_height_label = new Label(String.valueOf(portrait_size) + "%");

		l_bg_alpha_slider = createSlider(20, 90, l_bg_alpha, 5, 300);
		l_bg_alpha_label = new Label(String.valueOf(l_bg_alpha));

		l_moveXSlider = createSlider(-800, -113, l_x, 1, 400);
		l_moveXLabel = new Label(String.valueOf(l_x));

		l_moveYSlider = createSlider(0, 800, l_y, 1, 400);
		l_moveYLabel = new Label(String.valueOf(l_y));

		l_sizeSlider = createSlider(31, 59, l_size, 1, 400);
		l_sizeLabel = new Label(String.valueOf(l_size));

		l_gapSlider = createSlider(0, 5, l_gap, 1, 200);
		l_gapLabel = new Label(String.valueOf(l_gap));

		l_colSlider = createSlider(5, 20, l_col, 1, 200);
		l_colLabel = new Label(String.valueOf(l_col));

		syncCheckBox = new CheckBox("");

		r_moveXSlider = createSlider(113, 800, r_x, 1, 400);
		r_moveXLabel = new Label(String.valueOf(r_x));

		r_moveYSlider = createSlider(0, 800, r_y, 1, 400);
		r_moveYLabel = new Label(String.valueOf(r_y));

		r_sizeSlider = createSlider(31, 59, r_size, 1, 400);
		r_sizeLabel = new Label(String.valueOf(r_size));

		r_gapSlider = createSlider(0, 5, r_gap, 1, 200);
		r_gapLabel = new Label(String.valueOf(r_gap));

		r_colSlider = createSlider(5, 20, r_col, 1, 200);
		r_colLabel = new Label(String.valueOf(r_col));

		r_buffFilterSlider = createSlider(1, 3, r_buffFilter, 2, 100);
		r_buffFilterLabel = new Label("");

		ttXSlider = createSlider(-300, 300, tt_x, 1, 300);
		ttXLabel = new Label(String.valueOf(tt_x));

		ttYSlider = createSlider(-60, 60, tt_layout_top, 1, 300);
		ttYLabel = new Label(String.valueOf(tt_layout_top));

		if (r_buffFilter == 1)
			r_buffFilterLabel.setText("Hostile only");
		else if (r_buffFilter == 3)
			r_buffFilterLabel.setText("Hostile and Friendly");
		else
			r_buffFilterLabel.setText("");

		lPortraitXListener = (ObservableValue<? extends Number> o, Number oldVal, Number newVal) -> {
			r_moveXSlider.setValue(-newVal.intValue());
		};

		lPortraitYListener = (o, oldVal, newVal) -> {
			r_moveYSlider.setValue(newVal.intValue());
		};

		lpBuffSizeListener = (o, oldVal, newVal) -> {
			r_sizeSlider.setValue(newVal.intValue());
		};

		lpBuffSpacingListener = (o, oldVal, newVal) -> {
			r_gapSlider.setValue(newVal.intValue());
		};

		lpBuffColumnListener = (o, oldVal, newVal) -> {
			r_colSlider.setValue(newVal.intValue());
		};

		if ((-l_x) == r_x && l_y == r_y && l_size == r_size && l_gap == r_gap && l_col == r_col) {
			syncCheckBox.setSelected(true);
			r_moveXSlider.setDisable(true);
			r_moveYSlider.setDisable(true);
			r_sizeSlider.setDisable(true);
			r_gapSlider.setDisable(true);
			r_colSlider.setDisable(true);

			// lPortrait 슬라이더 값이 바뀌면 rPortrait 값 동기화
			l_moveXSlider.valueProperty().addListener(lPortraitXListener);
			l_moveYSlider.valueProperty().addListener(lPortraitYListener);
			l_sizeSlider.valueProperty().addListener(lpBuffSizeListener);
			l_gapSlider.valueProperty().addListener(lpBuffSpacingListener);
			l_colSlider.valueProperty().addListener(lpBuffColumnListener);
		}

		backButton = new Button("Back");
		okButton = new Button("OK");
	}

	@Override
	protected Parent createView() {
		HBox l_hp_height_box = new HBox(10, new Label("Portraits size"), l_hp_height_slider, l_hp_height_label);
		HBox l_bg_alpha_box = new HBox(10, new Label("BG alpha"), l_bg_alpha_slider, l_bg_alpha_label);

		Label lp = new Label("Left Portrait");
		VBox.setMargin(lp, new Insets(20, 0, 0, 0));

		HBox l_sizeBox = new HBox(10, new Label("Buff size"), l_sizeSlider, l_sizeLabel);
		HBox l_gapBox = new HBox(10, new Label("Buff gap"), l_gapSlider, l_gapLabel);
		HBox l_colBox = new HBox(10, new Label("Buff column"), l_colSlider, l_colLabel);
		HBox l_moveXBox = new HBox(10, new Label("Move x"), l_moveXSlider, l_moveXLabel);
		HBox l_moveYBox = new HBox(10, new Label("Move y"), l_moveYSlider, l_moveYLabel);
		HBox syncBox = new HBox(10, new Label("Sync Left/Right"), syncCheckBox);
		VBox.setMargin(syncBox, new Insets(20, 0, 0, 0));

		Label right = new Label("Rright Portrait");
		VBox.setMargin(right, new Insets(5, 0, 0, 0));

		HBox r_sizeBox = new HBox(10, new Label("Buff size"), r_sizeSlider, r_sizeLabel);
		HBox r_gapBox = new HBox(10, new Label("Buff gap"), r_gapSlider, r_gapLabel);
		HBox r_colBox = new HBox(10, new Label("Buff column"), r_colSlider, r_colLabel);
		HBox r_moveXBox = new HBox(10, new Label("Move x"), r_moveXSlider, r_moveXLabel);
		HBox r_moveYBox = new HBox(10, new Label("Move y"), r_moveYSlider, r_moveYLabel);
		HBox r_buffFilterBox = new HBox(10, new Label("Filter"), r_buffFilterSlider, r_buffFilterLabel);

		Label tt = new Label("Target of Target");
		VBox.setMargin(tt, new Insets(20, 0, 0, 0));

		HBox ttXBox = new HBox(10, new Label("Move x"), ttXSlider, ttXLabel);
		HBox ttYBox = new HBox(10, new Label("Spacing y"), ttYSlider, ttYLabel);

		VBox centerBox = new VBox(15);
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(new Label("General"), l_hp_height_box, l_bg_alpha_box, lp, l_moveXBox,
				l_moveYBox, l_sizeBox, l_gapBox, l_colBox, syncBox, right, r_moveXBox, r_moveYBox, r_sizeBox, r_gapBox,
				r_colBox, r_buffFilterBox, tt, ttXBox, ttYBox);

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
		l_hp_height_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			l_hp_height_slider.setValue(newVal.intValue());
			l_hp_height_label.setText(String.valueOf(newVal.intValue()) + "%");
		});
		l_bg_alpha_slider.valueProperty().addListener((obs, oldVal, newVal) -> {
			l_bg_alpha_slider.setValue(newVal.intValue());
			l_bg_alpha_label.setText(String.valueOf(newVal.intValue()));
		});

		syncCheckBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
			if (isSelected) {
				// rPortrait 슬라이더 비활성화
				r_moveXSlider.setDisable(true);
				r_moveYSlider.setDisable(true);
				r_sizeSlider.setDisable(true);
				r_gapSlider.setDisable(true);
				r_colSlider.setDisable(true);
				// 초기값
				r_moveXSlider.setValue(-l_moveXSlider.getValue());
				r_moveYSlider.setValue(l_moveYSlider.getValue());
				r_sizeSlider.setValue(l_sizeSlider.getValue());
				r_gapSlider.setValue(l_gapSlider.getValue());
				r_colSlider.setValue(l_colSlider.getValue());

				// lPortrait 슬라이더 값이 바뀌면 rPortrait 값 동기화
				l_moveXSlider.valueProperty().addListener(lPortraitXListener);
				l_moveYSlider.valueProperty().addListener(lPortraitYListener);
				l_sizeSlider.valueProperty().addListener(lpBuffSizeListener);
				l_gapSlider.valueProperty().addListener(lpBuffSpacingListener);
				l_colSlider.valueProperty().addListener(lpBuffColumnListener);

			} else {
				// rPortrait 슬라이더 다시 활성화
				r_moveXSlider.setDisable(false);
				r_moveYSlider.setDisable(false);
				r_sizeSlider.setDisable(false);
				r_gapSlider.setDisable(false);
				r_colSlider.setDisable(false);

				// 동기화끊기
				l_moveXSlider.valueProperty().removeListener(lPortraitXListener);
				l_moveYSlider.valueProperty().removeListener(lPortraitYListener);
				l_sizeSlider.valueProperty().removeListener(lpBuffSizeListener);
				l_gapSlider.valueProperty().removeListener(lpBuffSpacingListener);
				l_colSlider.valueProperty().removeListener(lpBuffColumnListener);
			}
		});

		l_colSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			l_colSlider.setValue(newVal.intValue());
			l_colLabel.setText(String.valueOf(newVal.intValue()));

		});
		l_gapSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			l_gapSlider.setValue(newVal.intValue());
			l_gapLabel.setText(String.valueOf(newVal.intValue()));
		});
		l_sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			l_sizeSlider.setValue(newVal.intValue());
			l_sizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		l_moveXSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			l_moveXSlider.setValue(newVal.intValue());
			l_moveXLabel.setText(String.valueOf(newVal.intValue()));
		});
		l_moveYSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			l_moveYSlider.setValue(newVal.intValue());
			l_moveYLabel.setText(String.valueOf(newVal.intValue()));
		});

		r_colSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			r_colSlider.setValue(newVal.intValue());
			r_colLabel.setText(String.valueOf(newVal.intValue()));

		});
		r_gapSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			r_gapSlider.setValue(newVal.intValue());
			r_gapLabel.setText(String.valueOf(newVal.intValue()));
		});
		r_sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			r_sizeSlider.setValue(newVal.intValue());
			r_sizeLabel.setText(String.valueOf(newVal.intValue()));
		});
		r_moveXSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			r_moveXSlider.setValue(newVal.intValue());
			r_moveXLabel.setText(String.valueOf(newVal.intValue()));
		});
		r_moveYSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			r_moveYSlider.setValue(newVal.intValue());
			r_moveYLabel.setText(String.valueOf(newVal.intValue()));
		});
		r_buffFilterSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			r_buffFilterSlider.setValue(newVal.intValue());
			if (newVal.intValue() == 1)
				r_buffFilterLabel.setText("Hostile only");
			else if (newVal.intValue() == 3)
				r_buffFilterLabel.setText("Hostile and Friendly");
			else
				r_buffFilterLabel.setText("");
		});
		ttXSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			ttXSlider.setValue(newVal.intValue());
			ttXLabel.setText(String.valueOf(newVal.intValue()));
		});
		ttYSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			ttYSlider.setValue(newVal.intValue());
			ttYLabel.setText(String.valueOf(newVal.intValue()));
		});

		backButton.setOnAction(e -> manager.showAdvanced(stage));
		okButton.setOnAction(e -> {
			int portrait_size = (int) l_hp_height_slider.getValue();
			int l_hp_height = 39;
			if (portrait_size == 28) {
				l_hp_height = 29;
			} else if (portrait_size == 36) {
				l_hp_height = 33;
			} else if (portrait_size == 44) {
				l_hp_height = 35;
			} else if (portrait_size == 52) {
				l_hp_height = 39;
			} else if (portrait_size == 60) {
				l_hp_height = 43;
			} else if (portrait_size == 68) {
				l_hp_height = 45;
			} else if (portrait_size == 76) {
				l_hp_height = 49;
			} else if (portrait_size == 84) {
				l_hp_height = 51;
			} else if (portrait_size == 92) {
				l_hp_height = 55;
			} else if (portrait_size == 100) {
				l_hp_height = 59;
			}
			manager.getSettings().setLPortrait_hp_height(l_hp_height);
			manager.getSettings().setLPortrait_bg_alpha((int) l_bg_alpha_slider.getValue());

			manager.getSettings().setToT_layout_top((int) ttYSlider.getValue());
			manager.getSettings().setTargetOfTargetX(((int) ttXSlider.getValue()) * 2);

			manager.getSettings().setLPortrait_x((int) l_moveXSlider.getValue());
			manager.getSettings().setLPortrait_y((int) l_moveYSlider.getValue());
			manager.getSettings().setLP_buff_size((int) l_sizeSlider.getValue());
			manager.getSettings().setLP_buff_spacing((int) l_gapSlider.getValue());
			manager.getSettings().setLP_buff_column((int) l_colSlider.getValue());

			manager.getSettings().setRPortrait_x((int) r_moveXSlider.getValue());
			manager.getSettings().setRPortrait_y((int) r_moveYSlider.getValue());
			manager.getSettings().setRP_buff_size((int) r_sizeSlider.getValue());
			manager.getSettings().setRP_buff_spacing((int) r_gapSlider.getValue());
			manager.getSettings().setRP_buff_column((int) r_colSlider.getValue());
			manager.getSettings().setRP_buff_filter((int) r_buffFilterSlider.getValue());

			manager.showAdvanced(stage);
		});
	}

	@Override
	protected double getWidth() {
		return 600;
	}

	@Override
	protected double getHeight() {
		return 880;
	}

	@Override
	protected String getTitle() {
		return "Left, Right Portrait";
	}

}
