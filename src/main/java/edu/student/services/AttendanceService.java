package edu.student.services;

import edu.student.dao.AttendanceDAO;
import edu.student.model.Attendance;

public class AttendanceService {
    Attendance attendance;
    AttendanceDAO attendanceDAO;
    String[] months;
    public AttendanceService(){
        months= new String[]{"'march'", "'april'", "'may'",
                             "'june'", "'july'", "'august'",
                             "'september'", "'october'", "'november'"};
        attendance= new Attendance();
        attendanceDAO=new AttendanceDAO();
    }

    public int[] showMathAttendances(int student_attendance_id){
        int[] mathAttendances=new int[9];
        if(student_attendance_id > 0){
            attendance.setStudentAttendanceId(student_attendance_id);
            mathAttendances=attendanceDAO.retrieveMathAttendances(attendance,months);
        }
        return mathAttendances;
    }

    public int[] showEnglishAttendances(int student_attendance_id){
        int[] englishAttendances=new int[9];
        if(student_attendance_id > 0){
            attendance.setStudentAttendanceId(student_attendance_id);
            englishAttendances=attendanceDAO.retrieveEnglishAttendances(attendance,months);
        }
        return englishAttendances;
    }

    public int[] showProgrammingAttendances(int student_attendance_id){
        int[] programmingAttendances=new int[9];
        if(student_attendance_id > 0){
            attendance.setStudentAttendanceId(student_attendance_id);
            programmingAttendances=attendanceDAO.retrieveProgrammingAttendances(attendance,months);
        }
        return programmingAttendances;
    }

    public int[] showPhysicsAttendances(int student_attendance_id){
        int[] physicsAttendances=new int[9];
        if(student_attendance_id > 0){
            attendance.setStudentAttendanceId(student_attendance_id);
            physicsAttendances=attendanceDAO.retrievePhysicsAttendances(attendance,months);
        }
        return physicsAttendances;
    }

    public int[] showEconomicsAttendances(int student_attendance_id){
        int[] economicsAttendances=new int[9];
        if(student_attendance_id > 0){
            attendance.setStudentAttendanceId(student_attendance_id);
            economicsAttendances=attendanceDAO.retrieveEconomicsAttendances(attendance,months);
        }
        return economicsAttendances;
    }
}
