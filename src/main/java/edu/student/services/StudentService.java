package edu.student.services;

import edu.student.dao.StudentDAO;
import edu.student.model.Student;
import edu.student.model.StudentScore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn.CellEditEvent;
import java.util.Iterator;
//task 1
public class StudentService {
    Student student;
    StudentScore studentFromTable;
    StudentDAO studentDAO;

    public StudentService(){
        student=new Student();
        studentFromTable = new StudentScore();
        studentDAO=new StudentDAO();
    }

    public ObservableList<StudentScore> showScoresAndStudentOnTable(){
        Iterator<StudentScore> studentIterator=studentDAO.retrieveStudentsAndScores(studentFromTable).iterator();
        ObservableList<StudentScore> scoreList= FXCollections.observableArrayList();

        while(studentIterator.hasNext()){
            StudentScore scores=studentIterator.next();
            scoreList.add(new StudentScore(scores.getStudentId(),scores.getFirstName(), scores.getLastName(),
                                           scores.getMathScore(),scores.getEnglishScore(),scores.getProgrammingScore(),
                                           scores.getPhysicsScore(),scores.getEconomicsScore() ) );
        }
        return scoreList;
    }

    public String createStudent(int studentId, String studentName, String studentLastName){
        String warn=null;
        if(studentDAO.isStudentAlreadyCreated(studentId)==false){
            student.setStudentId(studentId);
            student.setFirstName(studentName);
            student.setLastName(studentLastName);

            if(studentDAO.isStudentCreated(student)==true){
                warn="student created successfully";
            }
        }else{
            warn="student already exists";
        }
        return warn;
    }

//---------service to update student id in TableController
    public void updateStudentId(CellEditEvent<StudentScore, Integer> event){
        StudentScore studentTable=event.getRowValue();//getting old value from idColumn
        Integer currentStudentId=studentTable.getStudentId();//get value to put in sql "WHERE student_id=?" clause

        studentTable.setStudentId(event.getNewValue());//setting new value in the Id Column

        student.setStudentId(studentTable.getStudentId());

        if(studentDAO.isStudentIdUpdated(student,currentStudentId)==true){
            System.out.println("id updated succesfully, output from StudentService.class in method UpdatestudentId");
        }else System.out.println("id update failed");
    }

//---------service to update student name in TableController
    public void updateStudentName(CellEditEvent<StudentScore, String> event){
        studentFromTable =event.getTableView().getSelectionModel().getSelectedItem();//get value to put in sql "WHERE student_id=?" clause
        student.setStudentId(studentFromTable.getStudentId());// get value from TableView<StudentScore> and set to student model
        student.setFirstName(event.getNewValue());

        if(studentDAO.isStudentUpdated(student)==true){
            studentFromTable.setFirstName(student.getFirstName());//setting new value to TableView Name column
/*------setting student first_name variable to null to avoid conflicts in StudentDao.class in isStudentUpdated() after the validation----*/
            student.setFirstName(null);
            System.out.println("student updated");
        }else System.out.println("failed to update student credentials");
    }

//---------service to update student name in TableController
    public void updateStudentLastName(CellEditEvent<StudentScore, String> event){
        studentFromTable =event.getTableView().getSelectionModel().getSelectedItem();
        student.setStudentId(studentFromTable.getStudentId());
        student.setLastName(event.getNewValue());

        if(studentDAO.isStudentUpdated(student)==true){
            studentFromTable.setLastName(student.getLastName());
            student.setLastName(null);
            System.out.println("student updated");
        }else System.out.println("failed to update student credentials");
    }

    public boolean deleteStudent(StudentScore studentScore){
        this.studentFromTable =studentScore;
        student.setStudentId(this.studentFromTable.getStudentId());
        if(studentDAO.deleteStudent(student)==true){
            System.out.println("student deleted sucessfully");
            System.out.println("name: "+this.studentFromTable.getFirstName()+" id: "+this.studentFromTable.getStudentId());
            return true;
        }else return false;
    }
}
