package edu.student.controller;

import com.gluonhq.charm.glisten.control.ProgressIndicator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class ProgressIndicatorController {
    @FXML ProgressIndicator mathProgressIndicator;
    @FXML ProgressIndicator englishProgressIndicator;
    @FXML ProgressIndicator progProgressIndicator;
    @FXML ProgressIndicator physicsProgressIndicator;
    @FXML ProgressIndicator econProgressIndicator;
    @FXML Label mathProgress;
    @FXML Label englishProgress;
    @FXML Label progProgress;
    @FXML Label physicsProgress;
    @FXML Label econProgress;

    public void setMathAverageAttendance(int[] attendancesPerMonth){
        Platform.runLater(() -> {
            var labelValue = new Object() {int count = 0;};
            double total = 0;
            for(int i=0; i<attendancesPerMonth.length;i++){
                total+=attendancesPerMonth[i];//sum all values from the array to get the total of attendances
            }
            total=total/attendancesPerMonth.length;//divide total of attendances by the amount of months
            mathProgressIndicator.setProgress(total/100);//convert average to decimal

            Timeline timeline=new Timeline(new KeyFrame(Duration.millis(0), event -> {//counting and animating the values to be set in the label indicator
                labelValue.count++;
                IntegerProperty doubleProperty= new SimpleIntegerProperty(labelValue.count);
                mathProgress.textProperty().bind(doubleProperty.asString());

            }),new KeyFrame(Duration.millis( ((2000+total)/total)  ) ) );
            timeline.setCycleCount((int) total);//loops according to the value set on the progressIndicator
            timeline.play();
        });
    }

    public void setEnglishAverageAttendance(int[] attendancesPerMonth){
        Platform.runLater(() -> {
            var labelValue = new Object() {int count = 0;};
            double total = 0;
            for(int i=0; i<attendancesPerMonth.length;i++){
                total+=attendancesPerMonth[i];//sum all values from the array to get the total of attendances
            }
            total=total/attendancesPerMonth.length;//divide total of attendances by the amount of months
            englishProgressIndicator.setProgress(total/100);//convert average to decimal

            Timeline timeline=new Timeline(new KeyFrame(Duration.millis(0), event -> {//counting and animating the values to be set in the label indicator
                labelValue.count++;
                IntegerProperty doubleProperty= new SimpleIntegerProperty(labelValue.count);
                englishProgress.textProperty().bind(doubleProperty.asString());

            }),new KeyFrame(Duration.millis( ((2000+total)/total)  ) ) );
            timeline.setCycleCount((int) total);//loops according to the value set on the progressIndicator
            timeline.play();
        });
    }

    public void setProgrammingAverageAttendance(int[] attendancesPerMonth){
        Platform.runLater(() -> {
            var labelValue = new Object() {int count = 0;};
            double total = 0;
            for(int i=0; i<attendancesPerMonth.length;i++){
                total+=attendancesPerMonth[i];//sum all values from the array to get the total of attendances
            }
            total=total/attendancesPerMonth.length;//divide total of attendances by the amount of months
            progProgressIndicator.setProgress(total/100);//convert average to decimal

            Timeline timeline=new Timeline(new KeyFrame(Duration.millis(0), event -> {//counting and animating the values to be set in the label indicator
                labelValue.count++;
                IntegerProperty doubleProperty= new SimpleIntegerProperty(labelValue.count);
                progProgress.textProperty().bind(doubleProperty.asString());

            }),new KeyFrame(Duration.millis( ((2000+total)/total)  ) ) );
            timeline.setCycleCount((int) total);//loops according to the value set on the progressIndicator
            timeline.play();
        });
    }

    public void setPhysicsAverageAttendance(int[] attendancesPerMonth){
        Platform.runLater(() -> {
            var labelValue = new Object() {int count = 0;};
            double total = 0;
            for(int i=0; i<attendancesPerMonth.length;i++){
                total+=attendancesPerMonth[i];//sum all values from the array to get the total of attendances
            }
            total=total/attendancesPerMonth.length;//divide total of attendances by the amount of months
            physicsProgressIndicator.setProgress(total/100);//convert average to decimal

            Timeline timeline=new Timeline(new KeyFrame(Duration.millis(0), event -> {//counting and animating the values to be set in the label indicator
                labelValue.count++;
                IntegerProperty doubleProperty= new SimpleIntegerProperty(labelValue.count);
                physicsProgress.textProperty().bind(doubleProperty.asString());

            }),new KeyFrame(Duration.millis( ((2000+total)/total)  ) ) );
            timeline.setCycleCount((int) total);//loops according to the value set on the progressIndicator
            timeline.play();
        });
    }

    public void setEconomicsAverageAttendance(int[] attendancesPerMonth){
        Platform.runLater(() -> {
            var labelValue = new Object() {int count = 0;};
            double total = 0;
            for(int i=0; i<attendancesPerMonth.length;i++){
                total+=attendancesPerMonth[i];//sum all values from the array to get the total of attendances
            }
            total=total/attendancesPerMonth.length;//divide total of attendances by the amount of months
            econProgressIndicator.setProgress(total/100);//convert average to decimal

            Timeline timeline=new Timeline(new KeyFrame(Duration.millis(0), event -> {//counting and animating the values to be set in the label indicator
                labelValue.count++;
                IntegerProperty doubleProperty= new SimpleIntegerProperty(labelValue.count);
                econProgress.textProperty().bind(doubleProperty.asString());

            }),new KeyFrame(Duration.millis( ((2000+total)/total)  ) ) );
            timeline.setCycleCount((int) total);//loops according to the value set on the progressIndicator
            timeline.play();
        });
    }
}
