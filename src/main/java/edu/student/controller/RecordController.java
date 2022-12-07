package edu.student.controller;

import edu.student.services.AttendanceService;
import edu.student.services.StudentService;
import edu.student.services.SubjectService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class RecordController implements Initializable, EventHandler<ActionEvent> {
    @FXML TextField idField;
    @FXML TextField nameField;
    @FXML TextField lastNameField;
    @FXML Button addStudentBtn;
    @FXML Label addStudentLabel;

    @FXML ComboBox<Integer> mathScore;
    @FXML ComboBox<Integer> englishScore;
    @FXML ComboBox<Integer> physicsScore;
    @FXML ComboBox<Integer> codingScore;
    @FXML ComboBox<Integer> economicsScore;
    @FXML Button addScoreBtn;
    @FXML Label addScoreLabel;

    StudentService studentService;
    SubjectService subjectService;
    AttendanceService attendanceService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=0;i<=60;i++){//adding items to the combobox
            mathScore.getItems().add(i);
            mathScore.getSelectionModel().selectFirst();
            englishScore.getItems().add(i);
            englishScore.getSelectionModel().selectFirst();
            physicsScore.getItems().add(i);
            physicsScore.getSelectionModel().selectFirst();
            codingScore.getItems().add(i);
            codingScore.getSelectionModel().selectFirst();
            economicsScore.getItems().add(i);
            economicsScore.getSelectionModel().selectFirst();
        }
        studentService=new StudentService();
        subjectService=new SubjectService();
        attendanceService=new AttendanceService();
    }
    @Override
    public void handle(ActionEvent e) {
        if(addStudentBtn.equals(e.getSource())){
            //check first if text fields are empty
            if(idField.getText().isBlank() || nameField.getText().isBlank() || lastNameField.getText().isBlank()){
                addStudentLabel.setText("one or all student fields are empty");

            }else {
                int studentId=Integer.parseInt(idField.getText());
                String name=nameField.getText();
                String lastName=lastNameField.getText();
                addStudentLabel.setText(
                        studentService.createStudent(studentId, name, lastName)
                );
            }
        }
        if(addScoreBtn.equals(e.getSource())){
            Integer math=Integer.parseInt(String.valueOf(mathScore.getValue()));
            Integer english=Integer.parseInt(String.valueOf(englishScore.getValue()));
            Integer coding=Integer.parseInt(String.valueOf(codingScore.getValue()));
            Integer physics=Integer.parseInt(String.valueOf(physicsScore.getValue()));
            Integer economics=Integer.parseInt(String.valueOf(economicsScore.getValue()));
            int studentId=Integer.parseInt(idField.getText());
            addScoreLabel.setText(
                    subjectService.updateScores(math,english,coding,physics,economics,studentId)+"\n"+
                    //create attendances samples after setting score values
                    attendanceService.createAttendancesSample(studentId)
            );
        }
    }
}
