package edu.student.controller;

import edu.student.model.Student;
import edu.student.model.StudentScore;
import edu.student.model.Subject;
import edu.student.services.StudentService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    @FXML
    TableView<StudentScore> studentTable;
    @FXML
    TableColumn<StudentScore,Integer>idColumn;
    @FXML
    TableColumn<StudentScore,String>nameColumn;
    @FXML
    TableColumn<StudentScore,String>lastNameColumn;
    @FXML
    TableColumn<StudentScore,Integer>mathColumn;
    @FXML
    TableColumn<StudentScore,Integer>englishColumn;
    @FXML
    TableColumn<StudentScore,Integer>programmingColumn;
    @FXML
    TableColumn<StudentScore,Integer>physicsColumn;
    @FXML
    TableColumn<StudentScore,Integer>economicsColumn;
    StudentService studentService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentService=new StudentService();

        idColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("studentId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,String>("lastName"));
        mathColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("mathScore"));
        englishColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("englishScore"));
        programmingColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("programmingScore"));
        physicsColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("physicsScore"));
        economicsColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("economicsScore"));

        studentTable.setItems(studentService.showScoresAndStudentOnTable());
    }
}
