package edu.student.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressBarController implements Initializable {
    @FXML ProgressBar progressBar;
    @FXML Label score;
    @FXML Label maximum;

    public void setScoreRange(int scoreRange){
        int maximum= Integer.parseInt(this.maximum.getText());//300
        double percentage=(double)scoreRange/maximum*100;
        var count = new Object() {int progress = 0;};

        if(scoreRange > 0){
            Timeline timeline=new Timeline(new KeyFrame(Duration.millis(0), e -> {
                count.progress++;
                IntegerProperty scoreLabel= new SimpleIntegerProperty(count.progress *(maximum/100));//setting the amount of scores from the percentage
                score.textProperty().bind(scoreLabel.asString());//animating the label with the value of the score earned
                progressBar.setProgress((double) count.progress /100);//setting amount to the bar recursively
            }),new KeyFrame(Duration.millis(25) ));
            timeline.setCycleCount((int) percentage);//loops according to the percentage
            timeline.play();
            progressBar.setProgress(0.0);

        }else{
            IntegerProperty scoreLabel= new SimpleIntegerProperty(0);
            score.textProperty().bind(scoreLabel.asString());
            progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
    }
}
