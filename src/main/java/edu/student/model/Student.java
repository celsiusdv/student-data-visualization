package edu.student.model;

public class Student {
    private int student_id;
    private String first_name;
    private String last_name;

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

//-----------------------getters
    public int getStudentId() {return student_id;}
    public String getFirstName() {return first_name;}
    public String getLastName() {return last_name;}
}
