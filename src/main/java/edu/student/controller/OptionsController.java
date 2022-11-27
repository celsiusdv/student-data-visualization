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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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

    public OptionsController(){}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.showRecordFrame();
    }
    @Override
    public void handle(ActionEvent event) {
        if(option1.equals(event.getSource())){
            showRecordFrame();
        }
        if(option2.equals(event.getSource())){
            showTableFrame();
        }
        if(option3.equals(event.getSource())){
            showChartsFrame(event);
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
    public void showChartsFrame(ActionEvent event){
        try {
            Parent chartsFrame= FXMLLoader.load(getClass().getResource("/chartsframe.fxml"));
            Scene scene=new Scene(chartsFrame);
            Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Student Data");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
