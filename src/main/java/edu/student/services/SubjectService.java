package edu.student.services;

import edu.student.dao.SubjectDAO;
import edu.student.model.Student;
import edu.student.model.StudentScore;
import edu.student.model.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
//task 2
public class SubjectService {
    Student student;
    Subject subject;
    StudentScore studentScore;
    SubjectDAO subjectDAO;
    public SubjectService(){
        subject=new Subject();
        student=new Student();
        studentScore=new StudentScore();
        subjectDAO=new SubjectDAO();
    }
//---insert updates from student RecordControllerClass
    public String updateScores(int mathScore, int englishScore, int programmingScore,
                             int physicsScore, int economicsScore, int studentId ){
        String warn=null;
        if(mathScore >= 0) subject.setMathScore(mathScore);
        if(englishScore >= 0) subject.setEnglishScore(englishScore);
        if(programmingScore >= 0) subject.setProgrammingScore(programmingScore);
        if(physicsScore>= 0) subject.setPhysicsScore(physicsScore);
        if(economicsScore >= 0) subject.setEconomicsScore(economicsScore);
        student.setStudentId(studentId);

        if(subjectDAO.isEveryScoreUpdated(subject,student)==true){
            warn="scores added succesfully";

        }else warn="add scores failed";
        return warn;
    }

//---update scores from tableview inputs
    public void updateMathScore(CellEditEvent<StudentScore, Integer> event){
        studentScore=event.getTableView().getSelectionModel().getSelectedItem();//get values from the row to fill subject model class
        subject.setStudentID(studentScore.getStudentId());
        subject.setMathScore(event.getNewValue());

        if(subjectDAO.isScoreFromTableUpdated(subject)==true){
            studentScore.setMathScore(subject.getMathScore());//

            subject.setMathScore(0);//reset subject variable to avoid conflict in SubjectDAO.class
        }
    }
    public void updateEnglishScore(CellEditEvent<StudentScore, Integer> event){
        studentScore=event.getTableView().getSelectionModel().getSelectedItem();
        subject.setStudentID(studentScore.getStudentId());
        subject.setEnglishScore(event.getNewValue());

        if(subjectDAO.isScoreFromTableUpdated(subject)==true){
            studentScore.setEnglishScore(subject.getEnglishScore());//

            subject.setEnglishScore(0);//reset subject variable to avoid conflict in SubjectDAO.class
        }
    }
    public void updateProgrammingScore(CellEditEvent<StudentScore, Integer> event){
        studentScore=event.getTableView().getSelectionModel().getSelectedItem();
        subject.setStudentID(studentScore.getStudentId());
        subject.setProgrammingScore(event.getNewValue());

        if(subjectDAO.isScoreFromTableUpdated(subject)==true){
            studentScore.setProgrammingScore(subject.getProgrammingScore());//

            subject.setProgrammingScore(0);//reset subject variable to avoid conflict in SubjectDAO.class
        }
    }
    public void updatePhysicsScore(CellEditEvent<StudentScore, Integer> event){
        studentScore=event.getTableView().getSelectionModel().getSelectedItem();
        subject.setStudentID(studentScore.getStudentId());
        subject.setPhysicsScore(event.getNewValue());

        if(subjectDAO.isScoreFromTableUpdated(subject)==true){
            studentScore.setPhysicsScore(subject.getPhysicsScore());//

            subject.setPhysicsScore(0);//reset subject variable to avoid conflict in SubjectDAO.class
        }
    }
    public void updateEconomicsScore(CellEditEvent<StudentScore, Integer> event){
        studentScore=event.getTableView().getSelectionModel().getSelectedItem();
        subject.setStudentID(studentScore.getStudentId());
        subject.setEconomicsScore(event.getNewValue());

        if(subjectDAO.isScoreFromTableUpdated(subject)==true){
            studentScore.setEconomicsScore(subject.getEconomicsScore());//

            subject.setEconomicsScore(0);//reset subject variable to avoid conflict in SubjectDAO.class
        }
    }

//---retrieve scores to show in piechart
    public int[] retrieveScores(int id){
        int[] scoresData;
        subject.setStudentID(id);
//retrieve score from the given id in a new score object inside an array to iterate in ChartController.class updateScoresChart()
        Subject score= (Subject) subjectDAO.retrieveScoresById(subject);
        scoresData= new int[]{
                score.getMathScore(),
                score.getEnglishScore(),
                score.getProgrammingScore(),
                score.getPhysicsScore(),
                score.getEconomicsScore()
        };
        subject.setStudentID(0);//reset model to get new value when this metod is called
        return scoresData;

    }
}
