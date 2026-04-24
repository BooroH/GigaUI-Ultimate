package application.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class BaseWindow {
	protected Stage stage;

	public BaseWindow(Stage stage) {
		this.stage = stage;
	}

	protected abstract void initComponents();

	protected abstract Parent createView();

	protected abstract void initActions();

	/*
	public void show() {
		initComponents();
		Scene scene = new Scene(createView(), getWidth(), getHeight());
		scene.getStylesheets().add(BaseWindow.class.getResource("/application/application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle(getTitle());
		stage.getIcons().add(new Image(BaseWindow.class.getResourceAsStream("/application/icon7.png")));
		Font.loadFont("file:Data/GUI/Customized/Fonts/GG-Medium.otf", 10);
		initActions();
		stage.show();
	}
	*/
	public void show() {
	    initComponents();

	    Parent content = createView();

	    ScrollPane scrollPane = new ScrollPane(content);
	    scrollPane.setFitToWidth(true);   // 가로 꽉 채움
	    scrollPane.setFitToHeight(true);  // 필요하면 제거 가능

	    Scene scene = new Scene(scrollPane, getWidth(), getHeight());
	    scene.getStylesheets().add(BaseWindow.class.getResource("/application/application.css").toExternalForm());

	    stage.setScene(scene);
	    stage.setTitle(getTitle());
	    stage.getIcons().add(new Image(BaseWindow.class.getResourceAsStream("/application/icon7.png")));
	    Font.loadFont("file:Data/GUI/Customized/Fonts/GG-Medium.ttf", 10);

	    initActions();
	    stage.show();
	}

	protected double getWidth() {
		return 600;
	} // 초기 가로 크기

	protected double getHeight() {
		return 400;
	} // 초기 세로 크기

	protected String getTitle() {
		return "GigaUI Ultimate v2.3.1";
	}

	protected Slider createSlider(int min, int max, int value, int tick, int width) {
		Slider slider = new Slider(min, max, value);
		slider.setShowTickLabels(false);
		if (Math.abs(max - min) / tick > 75)
			slider.setShowTickMarks(false);
		else
			slider.setShowTickMarks(true);
		slider.setMajorTickUnit(tick);
		slider.setMinorTickCount(0);
		slider.setBlockIncrement(tick);
		slider.setSnapToTicks(true);
		slider.setPrefWidth(width);
		return slider;
	}
}
