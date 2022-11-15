package edu.student.model;

public class Subject {
    private int studentID;
    private String math_score;
    private String english_score;
    private String programming_score;
    private String physics_score;
    private String economics_score;

    public Subject(){}

    public Subject(String math_score, String english_score, String programming_score, String physics_score, String economics_score) {
        this.math_score = math_score;
        this.english_score = english_score;
        this.programming_score = programming_score;
        this.physics_score = physics_score;
        this.economics_score = economics_score;
    }

    public void setMath_score(String math_score) {this.math_score = math_score;}
    public void setEnglish_score(String english_score) {this.english_score = english_score;}
    public void setProgramming_score(String programming_score) {this.programming_score = programming_score;}
    public void setPhysics_score(String physics_score) {this.physics_score = physics_score;}
    public void setEconomics_score(String economics_score) {this.economics_score = economics_score;}
    public void setStudentID(int studentID){this.studentID=studentID;}

    public String getMath_score() {return math_score;}
    public String getEnglish_score() {return english_score;}
    public String getProgramming_score() {return programming_score;}
    public String getPhysics_score() {return physics_score;}
    public String getEconomics_score() {return economics_score;}
    public int getStudentID(){return studentID;}
}
