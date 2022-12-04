package edu.student.controller;

import edu.student.services.AttendanceService;
import edu.student.services.SubjectService;
import javafx.beans.binding.Bindings;
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
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ChartsController implements Initializable, EventHandler<ActionEvent> {
    @FXML private Button backButton;
    @FXML private Button searchBtn;
    @FXML private TextField idField;

    @FXML LineChart<String,Number> attendancesChart;//container of months and attendances values
    @FXML CategoryAxis subjectCategoryAxis;//horizontal axis for months names
    @FXML NumberAxis subjectAttendanceAxis;//vertical axis indicator for attendances values
    XYChart.Series<String, Number> mathSeries;//collection of values(attendances, months) to set in the line chart
    XYChart.Series<String, Number> engSeries;
    XYChart.Series<String, Number> progSeries;
    XYChart.Series<String, Number> physicsSeries;
    XYChart.Series<String, Number> economicsSeries;
    private String[] months;//months to set in the linechart
    private AttendanceService attendanceService;//service to get student's attendance of subjects per month
    int[] mathAttendances=new int[9];//array to set amount of attendances per month

    @FXML private PieChart scoresChart;//container of total scores per subjects
    private ObservableList<PieChart.Data> scoresData;//collection of values(subject, scores) to set in the pie chart
    private SubjectService subjectService;//service to get student's score per subject

    @FXML private Pane averageAttendancesContainer;
    private ProgressIndicatorController indicatorController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        months= new String[]{"mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov"};
        attendanceService=new AttendanceService();
        setAttendancesChart();
        setChartVerticalRange();

        subjectService=new SubjectService();
        setScoresChart();
        setValueOnSlice();

        setAverageAttendancesIndicator();
    }

//--------------------------creating line chart for attendances
    public void setAttendancesChart(){
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
        attendancesChart.getData().addAll(mathSeries,engSeries,progSeries,physicsSeries,economicsSeries);
    }
    public void updateChartAttendancesValues(int id){
        //update values for every subject in chart
        mathAttendances=attendanceService.showMathAttendances(id);
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

//---------------------------creating pie chart for subjects scores
    public void setScoresChart(){
        scoresData= FXCollections.observableArrayList();
        scoresData.addAll(
                new PieChart.Data("math",10),
                new PieChart.Data("english",10),
                new PieChart.Data("programming",10),
                new PieChart.Data("physics",10),
                new PieChart.Data("economics",10));
        scoresChart.setData(scoresData);
        scoresChart.setAnimated(true);
    }
    public void updateScoresChart(int id){
        int[] scores= subjectService.retrieveScores(id);//this array contains total scores from every subject
        for(int i=0; i< scores.length; i++){
            scoresChart.getData().get(i).setPieValue(scores[i]);
        }
    }
    public void setValueOnSlice(){
        for(int i=0; i<scoresData.size(); i++){
            scoresData.get(i).nameProperty().bind(
                    Bindings.concat(
                            scoresData.get(i).getName()," ",scoresData.get(i).pieValueProperty(),""));
        }
    }

//--------------------------creating progress indicator for average attendances per subject
    public void setAverageAttendancesIndicator(){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/progressindicator.fxml"));
            Parent progressFrame=loader.load();
            indicatorController=loader.getController();
            averageAttendancesContainer.getChildren().add(progressFrame);
        } catch (IOException e) {throw new RuntimeException(e);}
    }
    public void updateAverageIndicatorPanel(){
// if a new id is set, update the indicator by removing and setting it again with the method in the following line
        averageAttendancesContainer.getChildren().remove(0);
        setAverageAttendancesIndicator();
        indicatorController.setMathAverageAttendance(mathAttendances);
    }
    @Override
    public void handle(ActionEvent event) {
        if(backButton.equals(event.getSource())){
            showMainMenuFrame(event);
        }
        if(searchBtn.equals(event.getSource())){
            try{
                if(!idField.getText().isEmpty()){
                    int id= Integer.parseInt(idField.getText());
                    updateChartAttendancesValues(id);
                    updateScoresChart(id);
                    updateAverageIndicatorPanel();
                }else System.out.println("the field for the id is empty");
            }catch (NumberFormatException e){
                System.out.println("wrong input, only numbers are allowed");
            }
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
