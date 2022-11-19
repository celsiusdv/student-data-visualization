package edu.student.model;

public class Subject {
    private int studentID;
    private int math_score;
    private int english_score;
    private int programming_score;
    private int physics_score;
    private int economics_score;

    public Subject(){}

    public Subject(int math_score, int english_score, int programming_score, int physics_score, int economics_score) {
        this.math_score = math_score;
        this.english_score = english_score;
        this.programming_score = programming_score;
        this.physics_score = physics_score;
        this.economics_score = economics_score;
    }

    public void setMathScore(int math_score) {this.math_score = math_score;}
    public void setEnglishScore(int english_score) {this.english_score = english_score;}
    public void setProgrammingScore(int programming_score) {this.programming_score = programming_score;}
    public void setPhysicsScore(int physics_score) {this.physics_score = physics_score;}
    public void setEconomicsScore(int economics_score) {this.economics_score = economics_score;}
    public void setStudentID(int studentID){this.studentID=studentID;}

    public int getMathScore() {return math_score;}
    public int getEnglishScore() {return english_score;}
    public int getProgrammingScore() {return programming_score;}
    public int getPhysicsScore() {return physics_score;}
    public int getEconomicsScore() {return economics_score;}
    public int getStudentID(){return studentID;}
}
