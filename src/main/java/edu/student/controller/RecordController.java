package edu.student.controller;

import edu.student.database.Connector;
import edu.student.model.Student;
import edu.student.services.StudentService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class RecordController implements Initializable, EventHandler<ActionEvent> {
    @FXML
    TextField idField;
    @FXML
    TextField nameField;
    @FXML
    TextField lastNameField;
    @FXML
    Button addStudentBtn;
    @FXML
    ComboBox<Integer> mathScore;
    @FXML
    ComboBox<Integer> englishScore;
    @FXML
    ComboBox<Integer> physicsScore;
    @FXML
    ComboBox<Integer> codingScore;
    @FXML
    ComboBox<Integer> economicsScore;
    @FXML
    Button addScoreBtn;
    StudentService studentService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=0;i<=60;i++){
            mathScore.getItems().add(i);
            englishScore.getItems().add(i);
            physicsScore.getItems().add(i);
            codingScore.getItems().add(i);
            economicsScore.getItems().add(i);
        }
        studentService=new StudentService();
    }
    @Override
    public void handle(ActionEvent e) {
        if(addStudentBtn.equals(e.getSource())){
            if(idField.getText().isBlank() || nameField.getText().isBlank() || lastNameField.getText().isBlank()){//check if text fields are empty
                System.out.println("one or all student fields are empty");
            }else {
                int studentId=Integer.parseInt(idField.getText());
                String name=nameField.getText();
                String lastName=lastNameField.getText();
                System.out.println(studentId+" "+name+" "+lastName);
                studentService.createStudent(studentId, name, lastName);
            }
        }
        if(addScoreBtn.equals(e.getSource())){
            System.out.println(
                    " math:"+mathScore.getValue()+
                    " english: "+englishScore.getValue()+
                    " physics: "+physicsScore.getValue()+
                    " coding: "+codingScore.getValue()+
                    " economics: "+economicsScore.getValue());
        }

    }

}
