package edu.student.services;

import edu.student.dao.StudentDAO;
import edu.student.model.Student;
import edu.student.model.StudentScore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;
//task 1
public class StudentService {
    Student student;
    StudentScore studentScore;
    StudentDAO studentDAO;

    public StudentService(){
        student=new Student();
        studentScore= new StudentScore();
        studentDAO=new StudentDAO();
    }

    public ObservableList<StudentScore> showScoresAndStudentOnTable(){
        Iterator<StudentScore> studentIterator=studentDAO.retrieveStudentsAndScores(studentScore).iterator();
        ObservableList<StudentScore> scoreList= FXCollections.observableArrayList();

        while(studentIterator.hasNext()){
            StudentScore scores=studentIterator.next();
            scoreList.add(new StudentScore(scores.getStudentId(),scores.getFirstName(), scores.getLastName(),
                                           scores.getMathScore(),scores.getEnglishScore(),scores.getProgrammingScore(),
                                           scores.getPhysicsScore(),scores.getEconomicsScore() ) );
        }
        return scoreList;
    }
    public void createStudent(int studentId, String studentName, String studentLastName){
        if(studentDAO.isStudentAlreadyCreated(studentId)==false){

            student.setStudentId(studentId);
            student.setFirstName(studentName);
            student.setLastName(studentLastName);

            if(studentDAO.isStudentCreated(student)==true){
                System.out.println("student created succesfully, validation output from method" +
                        " createStudent() in StudentService.class");
            }
        }else System.out.println("student already exist");
    }
}
