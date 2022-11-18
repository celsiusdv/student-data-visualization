package edu.student.services;

import edu.student.dao.StudentDAO;
import edu.student.model.Student;

import java.util.Iterator;

public class StudentService {
    Student student;
    StudentDAO studentDAO;
    public StudentService(){
        student=new Student();
        studentDAO=new StudentDAO();
    }

    public void retrieveStudents(){
        Iterator<Student> studentIterator=studentDAO.retrieveStudents(student).iterator();
        while(studentIterator.hasNext()){
            Student student1=studentIterator.next();
            System.out.println(student1.getStudentId()+"||"+student1.getLastName());
        }
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
