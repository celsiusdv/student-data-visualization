package edu.student.services;

import edu.student.dao.SubjectDAO;
import edu.student.model.Student;
import edu.student.model.StudentScore;
import edu.student.model.Subject;
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
    public void updateScores(int mathScore, int englishScore, int programmingScore,
                             int physicsScore, int economicsScore, int studentId ){
        if(mathScore >= 0) subject.setMathScore(mathScore);
        if(englishScore >= 0) subject.setEnglishScore(englishScore);
        if(programmingScore >= 0) subject.setProgrammingScore(programmingScore);
        if(physicsScore>= 0) subject.setPhysicsScore(physicsScore);
        if(economicsScore >= 0) subject.setEconomicsScore(economicsScore);
        student.setStudentId(studentId);

        if(subjectDAO.isEveryScoreUpdated(subject,student)==true){
            System.out.println("scores added succesfully, validation output from method" +
                    " addScores() in SubjectService.class");

        }else System.out.println("scores cannot be added, validation output from method " +
                "addScores() in SubjectService.class");
    }

    public void updateMathScore(TableColumn.CellEditEvent<StudentScore, Integer> event){
        studentScore=event.getTableView().getSelectionModel().getSelectedItem();
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

}
