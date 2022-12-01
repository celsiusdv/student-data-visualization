package edu.student.controller;

import edu.student.services.AttendanceService;
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

    @FXML LineChart<String,Number> subjectsChart;//container of months and values
    @FXML CategoryAxis subjectCategoryAxis;//horizontal axis for months names
    @FXML NumberAxis subjectAttendanceAxis;//vertical axis indicator for attendances values

    XYChart.Series<String, Number> mathSeries;//collection of values(attendances, months) to set in the chart
    XYChart.Series<String, Number> engSeries;
    XYChart.Series<String, Number> progSeries;
    XYChart.Series<String, Number> physicsSeries;
    XYChart.Series<String, Number> economicsSeries;

    String[] months;

    AttendanceService attendanceService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        months= new String[]{"mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov"};
        attendanceService=new AttendanceService();
        setChartVerticalRange();
        setSubjectChart();
    }

    public void setSubjectChart(){
        mathSeries = new XYChart.Series();
        mathSeries.setName("Math");
        engSeries = new XYChart.Series();
        engSeries.setName("English");
        progSeries = new XYChart.Series();
        progSeries.setName("Programming");
        physicsSeries = new XYChart.Series();
        physicsSeries.setName("Physics");
        economicsSeries = new XYChart.Series();
        economicsSeries.setName("Economics");
        for(int i=0; i<months.length; i++){
            //adding default values to the chart
            mathSeries.getData().add(new XYChart.Data(months[i], 0));
            engSeries.getData().add(new XYChart.Data(months[i], 1));
            progSeries.getData().add(new XYChart.Data(months[i], 2));
            economicsSeries.getData().add(new XYChart.Data(months[i], 3));
            physicsSeries.getData().add(new XYChart.Data(months[i], 4));
        }
        subjectsChart.getData().addAll(mathSeries,engSeries,progSeries,physicsSeries,economicsSeries);
    }

    public void setChartValues(int id){
        //update values for every subject in chart
        int[] mathAttendances=attendanceService.showMathAttendances(id);
        int[] engAttendances=attendanceService.showEnglishAttendances(id);
        int[] progAttendances=attendanceService.showProgrammingAttendances(id);
        int[] physAttendances=attendanceService.showPhysicsAttendances(id);
        int[] econAttendances=attendanceService.showEconomicsAttendances(id);
        for (int i=0;i<months.length; i++){
            mathSeries.getData().get(i).setYValue(mathAttendances[i]);
            engSeries.getData().get(i).setYValue(engAttendances[i]);
            progSeries.getData().get(i).setYValue(progAttendances[i]);
            physicsSeries.getData().get(i).setYValue(physAttendances[i]);
            economicsSeries.getData().get(i).setYValue(econAttendances[i]);
        }
    }
    public void setChartVerticalRange(){
        //setting vertical indicator for the line
        subjectAttendanceAxis.setAutoRanging(false);
        subjectAttendanceAxis.setLowerBound(0);
        subjectAttendanceAxis.setUpperBound(30);
        subjectAttendanceAxis.setTickUnit(5);
        subjectAttendanceAxis.setLabel("attendances");
    }

    @Override
    public void handle(ActionEvent event) {
        if(backButton.equals(event.getSource())){
            showMainMenuFrame(event);
        }
        if(searchBtn.equals(event.getSource())){
            int id= Integer.parseInt(idField.getText());
            setChartValues(id);
        }
    }

    public void showMainMenuFrame(ActionEvent event){
        try {
            Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/optionsframe.fxml"));
            Scene scene=new Scene(root);
            stage.setTitle("Registry student");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Button getSearchBtn() {return searchBtn;}
    public TextField getIdField() {return idField;}
}
