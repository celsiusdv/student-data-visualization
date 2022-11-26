package edu.student.model;


import javafx.scene.control.Button;

public class StudentScore {
    private int student_id;
    private String first_name;
    private String last_name;
    private int math_score;
    private int english_score;
    private int programming_score;
    private int physics_score;
    private int economics_score;

    public StudentScore(int student_id, String first_name, String last_name) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public StudentScore(int math_score, int english_score, int programming_score, int physics_score, int economics_score) {
        this.math_score = math_score;
        this.english_score = english_score;
        this.programming_score = programming_score;
        this.physics_score = physics_score;
        this.economics_score = economics_score;
    }

    public StudentScore(int student_id, String first_name, String last_name,
                        int math_score, int english_score, int programming_score,
                        int physics_score, int economics_score) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.math_score = math_score;
        this.english_score = english_score;
        this.programming_score = programming_score;
        this.physics_score = physics_score;
        this.economics_score = economics_score;
    }
    public StudentScore(){}

    public void setStudentId(int studentId) {this.student_id = studentId;}
    public void setFirstName(String firstName) {this.first_name = firstName;}
    public void setLastName(String lastName) {this.last_name = lastName;}
    public void setMathScore(int mathScore) {this.math_score = mathScore;}
    public void setEnglishScore(int englishScore) {this.english_score = englishScore;}
    public void setProgrammingScore(int programmingScore) {this.programming_score = programmingScore;}
    public void setPhysicsScore(int physicsScore) {this.physics_score = physicsScore;}
    public void setEconomicsScore(int economicsScore) {this.economics_score = economicsScore;}


    public int getStudentId() {return student_id;}
    public String getFirstName() {return first_name;}
    public String getLastName() {return last_name;}
    public int getMathScore() {return math_score;}
    public int getEnglishScore() {return english_score;}
    public int getProgrammingScore() {return programming_score;}
    public int getPhysicsScore() {return physics_score;}
    public int getEconomicsScore() {return economics_score;}
}
