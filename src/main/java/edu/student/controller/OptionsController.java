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
        //this.showTableFrame();
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
            showChartsFrame(event,"");
        }
    }

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
    public void showChartsFrame(ActionEvent event,String studentId){
        try {
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/chartsframe.fxml"));
            Parent chartFrame=loader.load();

//instantiate controller here to set id from TableController.class, this object is called when an event is fired inside the column of show data buttons
            ChartsController chartsController=loader.getController();
            chartsController.getIdField().setText(studentId);

//this flow control allows to show the chart frame if the textfield is empty or the user want to manually insert id to show data
            if(chartsController.getIdField().getText().isEmpty()){
                System.out.println("show chart frame without id and data, log from OptionsController.class");
            }else chartsController.getSearchBtn().fire();

            Scene scene=new Scene(chartFrame);
            stage.setScene(scene);
            stage.setTitle("Student Data");
            stage.show();

        } catch (IOException e) {throw new RuntimeException(e);}
    }

}
