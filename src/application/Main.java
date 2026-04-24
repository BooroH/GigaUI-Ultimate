package application;

import javafx.application.Application;
import javafx.stage.Stage;
import application.gui.WindowManager;

public class Main extends Application {
	 @Override
	    public void start(Stage primaryStage) {
	        // WindowManager에서 시작 화면 열기
	        WindowManager manager = new WindowManager(primaryStage);
	        manager.showStart();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
}