package edu.student.model;

import java.util.List;

public class Student {
    private int student_id;
    private String first_name;
    private String last_name;
    private List<Subject> subjects;

    public Student(int student_id, String first_name, String last_name, List<Subject> subjects) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.subjects = subjects;
    }

    public Student(int student_id, String first_name, String last_name) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public Student() {}
//------------------------setters
    public void setStudentId(int student_id) {this.student_id = student_id;}
    public void setFirstName(String first_name) {this.first_name = first_name;}
    public void setLastName(String last_name) {this.last_name = last_name;}
    public void setSubjects(List<Subject> subjects) {this.subjects = subjects;}

//-----------------------getters
    public int getStudentId() {return student_id;}
    public String getFirstName() {return first_name;}
    public String getLastName() {return last_name;}
    public List<Subject> getSubjects() {return subjects;}
}
