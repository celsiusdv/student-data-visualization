package edu.student.controller;

import edu.student.model.Student;
import edu.student.model.StudentScore;
import edu.student.model.Subject;
import edu.student.services.StudentService;
import edu.student.services.SubjectService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.converter.IntegerStringConverter;

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
    SubjectService subjectService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentService=new StudentService();
        subjectService=new SubjectService();

        idColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("studentId"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idColumn.setOnEditCommit(event ->studentService.updateStudentId(event));

        nameColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,String>("firstName"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event ->studentService.updateStudentName(event));

        lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,String>("lastName"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(event ->studentService.updateStudentLastName(event));

        mathColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("mathScore"));
        mathColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        mathColumn.setOnEditCommit(event ->subjectService.updateMathScore(event));

        englishColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("englishScore"));
        englishColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        englishColumn.setOnEditCommit(event ->subjectService.updateEnglishScore(event));

        programmingColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("programmingScore"));
        programmingColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        programmingColumn.setOnEditCommit(event ->subjectService.updateProgrammingScore(event));

        physicsColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("physicsScore"));
        physicsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        physicsColumn.setOnEditCommit(event ->subjectService.updatePhysicsScore(event));

        economicsColumn.setCellValueFactory(new PropertyValueFactory<StudentScore,Integer>("economicsScore"));
        economicsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        economicsColumn.setOnEditCommit(event ->subjectService.updateEconomicsScore(event));


        studentTable.setItems(studentService.showScoresAndStudentOnTable());
    }
}
