package edu.student.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChartsController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    Button backButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void handle(ActionEvent event) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/optionsframe.fxml"));
            Scene scene=new Scene(root);
            Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Registry student");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
