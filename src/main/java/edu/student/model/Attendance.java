package edu.student.model;

import java.sql.Date;
import java.time.LocalDate;

public class Attendance {
    private int student_attendance_id;
    private boolean math_attendance;
    private boolean english_attendance;
    private boolean programming_attendance;
    private boolean physics_attendance;
    private boolean economics_attendance;
    private Date attendance_month;

    public Attendance(int student_attendance_id, boolean math_attendance, boolean english_attendance,
                      boolean programming_attendance, boolean physics_attendance,
                      boolean economics_attendance, Date attendance_month) {
        this.student_attendance_id = student_attendance_id;
        this.math_attendance = math_attendance;
        this.english_attendance = english_attendance;
        this.programming_attendance = programming_attendance;
        this.physics_attendance = physics_attendance;
        this.economics_attendance = economics_attendance;
        this.attendance_month = attendance_month;
    }
    public Attendance(){};

    public void setStudentAttendanceId(int student_attendance_id) {this.student_attendance_id = student_attendance_id;}
    public void setMathAttendance(boolean math_attendance) {this.math_attendance = math_attendance;}
    public void setEnglishAttendance(boolean english_attendance) {this.english_attendance = english_attendance;}
    public void setProgramming_attendance(boolean programming_attendance) {this.programming_attendance = programming_attendance;}
    public void setPhysicsAttendance(boolean physics_attendance) {this.physics_attendance = physics_attendance;}
    public void setEconomicsAttendance(boolean economics_attendance) {this.economics_attendance = economics_attendance;}
    public void setAttendanceMonth(Date attendance_month) {this.attendance_month = attendance_month;}

    public int getStudentAttendanceId() {return student_attendance_id;}
    public boolean isMathAttendance() {return math_attendance;}
    public boolean isEnglishAttendance() {return english_attendance;}
    public boolean isProgrammingAttendance() {return programming_attendance;}
    public boolean isPhysicsAttendance() {return physics_attendance;}
    public boolean isEconomicsAttendance() {return economics_attendance;}
    public Date getAttendanceMonth() {return attendance_month;}
}
