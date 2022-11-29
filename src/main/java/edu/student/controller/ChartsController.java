package edu.student.controller;

import edu.student.services.AttendanceService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChartsController implements Initializable, EventHandler<ActionEvent> {
    @FXML Button backButton;
    @FXML Button searchBtn;
    @FXML TextField idField;
    @FXML LineChart<String,Integer> mathChart;//container of months and values
    @FXML CategoryAxis mathCategoryAxis;//horizontal axis for months names
    @FXML NumberAxis mathAttendanceAxis;//vertical axis for attendances values
    String[] months;
    XYChart.Series<String, Integer> mathSeries;//collection of values(attendances, months) to set in the chart

    AttendanceService attendanceService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        months= new String[]{"mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov"};
        attendanceService=new AttendanceService();
        setMathChartDescription();
        setMathChart();
    }

    public void setMathChart(){
        mathSeries = new XYChart.Series();
        mathSeries.setName("Math");
        for(int i=0; i<months.length; i++){
            //adding values to the chart
            mathSeries.getData().add(new XYChart.Data(months[i], 0));
        }
        mathChart.getData().add(mathSeries);
    }
    public void setMathChartValues(int id){
        int[] attendancesPerMonth=attendanceService.showMathAttendances(id);
        for (int i=0;i<months.length; i++){
            mathSeries.getData().get(i).setYValue(attendancesPerMonth[i]);
        }
    }
    public void setMathChartDescription(){
        //setting vertical index for the line
        mathAttendanceAxis.setAutoRanging(false);
        mathAttendanceAxis.setLowerBound(0);
        mathAttendanceAxis.setUpperBound(30);
        mathAttendanceAxis.setTickUnit(5);
        mathAttendanceAxis.setLabel("attendances");
    }

    @Override
    public void handle(ActionEvent event) {
        if(backButton.equals(event.getSource())){
            showMainMenuFrame(event);
        }
        if(searchBtn.equals(event.getSource())){
            int id= Integer.parseInt(idField.getText());
            setMathChartValues(id);
        }
    }

    public void showMainMenuFrame(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/optionsframe.fxml"));
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
