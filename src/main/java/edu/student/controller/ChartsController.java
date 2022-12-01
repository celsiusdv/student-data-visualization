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

    @FXML LineChart<String,Number> mathChart;//container of months and values
    @FXML CategoryAxis mathCategoryAxis;//horizontal axis for months names
    @FXML NumberAxis mathAttendanceAxis;//vertical axis indicator for attendances values
    XYChart.Series<String, Number> mathSeries;//collection of values(attendances, months) to set in the chart

    @FXML LineChart<String,Number> englishChart;
    @FXML CategoryAxis engCategoryAxis;
    @FXML NumberAxis engAttendanceAxis;
    XYChart.Series<String, Number> engSeries;

    @FXML LineChart<String,Number> programmingChart;
    @FXML CategoryAxis progCategoryAxis;
    @FXML NumberAxis progAttendanceAxis;
    XYChart.Series<String, Number> progSeries;

    @FXML LineChart<String,Number> physicsChart;
    @FXML CategoryAxis physicsCategoryAxis;
    @FXML NumberAxis physicsAttendanceAxis;
    XYChart.Series<String, Number> physicsSeries;

    @FXML LineChart<String,Number> economicsChart;
    @FXML CategoryAxis economicsCategoryAxis;
    @FXML NumberAxis economicsAttendanceAxis;
    XYChart.Series<String, Number> economicsSeries;

    String[] months;

    AttendanceService attendanceService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        months= new String[]{"mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov"};
        attendanceService=new AttendanceService();
        setMathChartDescription();
        setMathChart();

        setEnglishChartDescription();
        setEnglishChart();

        setProgrammingChartDescription();
        setProgrammingChart();

        setPhysicsChartDescription();
        setPhysicsChart();

        setEconomicsChartDescription();
        setEconomicsChart();
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
    public void setEnglishChart(){
        engSeries=new XYChart.Series<>();
        engSeries.setName("English");
        for(int i=0; i<months.length; i++){
            //adding values to the chart
            engSeries.getData().add(new XYChart.Data(months[i], 0));
        }
        englishChart.getData().add(engSeries);
    }
    public void setProgrammingChart(){
        progSeries=new XYChart.Series<>();
        progSeries.setName("Programming");
        for(int i=0; i<months.length; i++){
            //adding values to the chart
            progSeries.getData().add(new XYChart.Data(months[i], 0));
        }
        programmingChart.getData().add(progSeries);
    }
    public void setPhysicsChart(){
        physicsSeries=new XYChart.Series<>();
        physicsSeries.setName("Physics");
        for(int i=0; i<months.length; i++){
            //adding values to the chart
            physicsSeries.getData().add(new XYChart.Data(months[i], 0));
        }
        physicsChart.getData().add(physicsSeries);
    }
    public void setEconomicsChart(){
        economicsSeries=new XYChart.Series<>();
        economicsSeries.setName("Economics");
        for(int i=0; i<months.length; i++){
            //adding values to the chart
            economicsSeries.getData().add(new XYChart.Data(months[i], 0));
        }
        economicsChart.getData().add(economicsSeries);
    }



    public void setMathChartValues(int id){
        //update values for math chart
        int[] attendancesPerMonth=attendanceService.showMathAttendances(id);
        for (int i=0;i<months.length; i++){
            mathSeries.getData().get(i).setYValue(attendancesPerMonth[i]);
        }
    }
    public void setEnglishChartValues(int id){
        //update values for english chart
        int[] attendancesPerMonth=attendanceService.showEnglishAttendances(id);
        for (int i=0;i<months.length; i++){
            engSeries.getData().get(i).setYValue(attendancesPerMonth[i]);
        }
    }
    public void setProgrammingChartValues(int id){
        //update values for programming chart
        int[] attendancesPerMonth=attendanceService.showProgrammingAttendances(id);
        for (int i=0;i<months.length; i++){
            progSeries.getData().get(i).setYValue(attendancesPerMonth[i]);
        }
    }
    public void setPhysicsChartValues(int id){
        //update values for physics chart
        int[] attendancesPerMonth=attendanceService.showPhysicsAttendances(id);
        for (int i=0;i<months.length; i++){
            physicsSeries.getData().get(i).setYValue(attendancesPerMonth[i]);
        }
    }
    public void setEconomicsChartValues(int id){
        //update values for economics chart
        int[] attendancesPerMonth=attendanceService.showEconomicsAttendances(id);
        for (int i=0;i<months.length; i++){
            economicsSeries.getData().get(i).setYValue(attendancesPerMonth[i]);
        }
    }


    public void setMathChartDescription(){
        //setting vertical indicator for the line
        mathAttendanceAxis.setAutoRanging(false);
        mathAttendanceAxis.setLowerBound(0);
        mathAttendanceAxis.setUpperBound(30);
        mathAttendanceAxis.setTickUnit(5);
        mathAttendanceAxis.setLabel("attendances");
    }
    public void setEnglishChartDescription(){
        //setting vertical indicator for the line
        engAttendanceAxis.setAutoRanging(false);
        engAttendanceAxis.setLowerBound(0);
        engAttendanceAxis.setUpperBound(30);
        engAttendanceAxis.setTickUnit(5);
        engAttendanceAxis.setLabel("attendances");
    }
    public void setProgrammingChartDescription(){
        //setting vertical indicator for the line
        progAttendanceAxis.setAutoRanging(false);
        progAttendanceAxis.setLowerBound(0);
        progAttendanceAxis.setUpperBound(30);
        progAttendanceAxis.setTickUnit(5);
        progAttendanceAxis.setLabel("attendances");
    }
    public void setPhysicsChartDescription(){
        //setting vertical indicator for the line
        physicsAttendanceAxis.setAutoRanging(false);
        physicsAttendanceAxis.setLowerBound(0);
        physicsAttendanceAxis.setUpperBound(30);
        physicsAttendanceAxis.setTickUnit(5);
        physicsAttendanceAxis.setLabel("attendances");
    }
    public void setEconomicsChartDescription(){
        //setting vertical indicator for the line
        economicsAttendanceAxis.setAutoRanging(false);
        economicsAttendanceAxis.setLowerBound(0);
        economicsAttendanceAxis.setUpperBound(30);
        economicsAttendanceAxis.setTickUnit(5);
        economicsAttendanceAxis.setLabel("attendances");
    }



    @Override
    public void handle(ActionEvent event) {
        if(backButton.equals(event.getSource())){
            showMainMenuFrame(event);
        }
        if(searchBtn.equals(event.getSource())){
            int id= Integer.parseInt(idField.getText());
            setMathChartValues(id);
            setEnglishChartValues(id);
            setProgrammingChartValues(id);
            setPhysicsChartValues(id);
            setEconomicsChartValues(id);
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
