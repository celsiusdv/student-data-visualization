package edu.student.controller;

import com.gluonhq.charm.glisten.control.ProgressIndicator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressIndicatorController {
    @FXML ProgressIndicator progressIndicator;
    @FXML Label progress;


    public void setMathAverageAttendance(int[] attendancesPerMonth){
        //---------- mar,apr,may,jun,jul,aug,sep,oct,nov from id 8532
        //int[] attendancesPerMonth={10,20, 17, 20, 19,  0,  0,  0, 0};
        Platform.runLater(() -> {
            progressIndicator.setDisable(false);
            double total = 0;
            for(int i=0; i<attendancesPerMonth.length;i++){
                total+=attendancesPerMonth[i];//sum all values from the array to get the total of attendances
            }
            total=total/attendancesPerMonth.length;//divide total of attendances by the amount of months
            progressIndicator.setProgress(total/100);//convert average to decimal
            progress.setText(""+(int)total);
        });
    }
}
