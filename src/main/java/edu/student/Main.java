package edu.student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("/optionsframe.fxml"));
        Scene scene=new Scene(root,990,730);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();//variable to get screen values
        stage.setX( (primaryScreenBounds.getWidth()/2) - (scene.getWidth()/1.5) );//window position
        stage.setTitle("Registry student");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
