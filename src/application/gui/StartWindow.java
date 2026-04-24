package application.gui;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class StartWindow extends BaseWindow {
	private TextField pathField;
	private Button searchButton;
    private Button nextButton;
    private WindowManager manager;

    public StartWindow(Stage stage, WindowManager manager) {
        super(stage);
        this.manager = manager;
    }
    
    @Override
	protected void initComponents() {
    	pathField = new TextField();
        searchButton = new Button("Browse");
        nextButton = new Button("Next");
	}

    @Override
    protected Parent createView() {
        Label label = new Label("Installed Age of Conan path?");

        HBox pathBox = new HBox(10);
        pathBox.getChildren().addAll(pathField, searchButton);
        HBox.setHgrow(pathField, Priority.ALWAYS); // TextField가 가능한 넓게 차지

        VBox centerBox = new VBox(15); 
		centerBox.setPadding(new Insets(20));
		centerBox.getChildren().addAll(label, pathBox);
		
		HBox bottomButtons = new HBox(10, nextButton);
        bottomButtons.setPadding(new Insets(15));
        bottomButtons.setAlignment(Pos.BOTTOM_RIGHT);

		BorderPane root = new BorderPane();
		root.setCenter(centerBox);
		root.setBottom(bottomButtons);

		return root;
        
    }

    @Override
    protected void initActions() {
        nextButton.setOnAction(e -> {
        	String selectedPath = pathField.getText();
			Path exePath = Paths.get(selectedPath, "AgeOfConan.exe");
			if (!Files.exists(exePath)) {
				ShowAlert.showError("Path Error", "Browse correct path");
				return;
			}
			manager.setAoCDir(selectedPath);
            manager.showInstallTypeWindow();
        });
        searchButton.setOnAction(e -> {
        	DirectoryChooser chooser = new DirectoryChooser();
    		chooser.setTitle("Age of Conan folder path");
    		File selected = chooser.showDialog(stage);
    		if (selected != null) {
    			pathField.setText(selected.getAbsolutePath());
    		}
        });
    }

}
