package edu.student.controller;

import edu.student.model.StudentScore;
import edu.student.services.AttendanceService;
import edu.student.services.StudentService;
import edu.student.services.SubjectService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
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
    @FXML
    TableColumn<StudentScore, Void>dataColumn;
    @FXML
    TableColumn<StudentScore, Void>deleteColumn;
    ChartsController chartsController;
    StudentService studentService;
    SubjectService subjectService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //the methods from this service is used in the first three columns and in the delete column
        studentService=new StudentService();
        //the methods from this service is for filling the subject columns
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

////////////////////////////////////columns with buttons//////////////////////////////////////////////////////////
        dataColumn.setCellFactory(new Callback<TableColumn<StudentScore, Void>, TableCell<StudentScore, Void>>() {
            @Override
            public TableCell<StudentScore, Void> call(TableColumn<StudentScore, Void> param) {
                final TableCell<StudentScore, Void> cell = new TableCell<StudentScore, Void>() {
                    private final Button btn = new Button("show charts");{
                        btn.setOnAction((ActionEvent event) -> {
                            StudentScore studentScore=studentTable.getItems().get(getIndex());
                            System.out.println(studentScore.getStudentId());

                            new OptionsController().showChartsFrame(event,
                                    ""+studentScore.getStudentId());
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) setGraphic(null);
                        else setGraphic(btn);
                    }
                };
                return cell;
            }
        });

        deleteColumn.setCellFactory(new Callback<TableColumn<StudentScore,Void>, TableCell<StudentScore,Void>>() {
            @Override
            public TableCell<StudentScore,Void> call(TableColumn<StudentScore, Void> param) {
                final TableCell<StudentScore, Void> cell = new TableCell<StudentScore, Void>() {
                    private final Button btn = new Button("delete");{
                        btn.setOnAction((ActionEvent event) -> {
                            //get all items from the selected row to get the student id
                            StudentScore studentScore=studentTable.getItems().get(getIndex());
                            if(studentService.deleteStudent(studentScore)==true){
                                studentTable.getItems().remove(studentScore);
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) setGraphic(null);
                        else setGraphic(btn);
                    }
                };
                return cell;
            }
        });

        studentTable.setItems(studentService.showScoresAndStudentOnTable());
    }
}
