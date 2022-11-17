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
}
