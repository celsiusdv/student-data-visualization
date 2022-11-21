package edu.student.services;

import edu.student.dao.SubjectDAO;
import edu.student.model.Student;
import edu.student.model.Subject;
//task 2
public class SubjectService {
    Student student;
    Subject subject;
    SubjectDAO subjectDAO;
    public SubjectService(){
        subject=new Subject();
        student=new Student();
        subjectDAO=new SubjectDAO();
    }
    public void addScores(int mathScore, int englishScore, int programmingScore,
                          int physicsScore, int economicsScore, int studentId ){
        subject.setMathScore(mathScore);
        subject.setEnglishScore(englishScore);
        subject.setProgrammingScore(programmingScore);
        subject.setPhysicsScore(physicsScore);
        subject.setEconomicsScore(economicsScore);
        student.setStudentId(studentId);
        if(subjectDAO.isEveryScoreAdded(subject,student)==true){
            System.out.println("scores added succesfully, validation output from method" +
                    " addScores() in SubjectService.class");

        }else System.out.println("scores cannot be added, validation output from method " +
                "addScores() in SubjectService.class");

    }
}
