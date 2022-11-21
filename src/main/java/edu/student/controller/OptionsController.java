package edu.student.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController implements EventHandler<ActionEvent>, Initializable {
    @FXML
    BorderPane mainPane;
    @FXML
    Button option1;
    @FXML
    Button option2;
    @FXML
    Button option3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.showRecordFrame();
    }
    @Override
    public void handle(ActionEvent event) {
        if(option1.equals(event.getSource())){
            System.out.println("show record frame");
            showRecordFrame();
        }
        if(option2.equals(event.getSource())){
            System.out.println("show list student frame");
            showTableFrame();
        }
        if(option3.equals(event.getSource())){
            System.out.println("show data student frame");
        }
    }
//----------------methods to handle fxml files
    private void showRecordFrame(){
        try {
            Parent recordFrame= FXMLLoader.load(getClass().getResource("/recordframe.fxml"));
            mainPane.setCenter(recordFrame);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showTableFrame(){
        try {
            Parent tableFrame= FXMLLoader.load(getClass().getResource("/tableframe.fxml"));
            mainPane.setCenter(tableFrame);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
