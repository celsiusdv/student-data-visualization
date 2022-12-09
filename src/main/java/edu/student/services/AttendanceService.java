package edu.student.services;

import edu.student.dao.AttendanceDAO;
import edu.student.model.Attendance;
import java.sql.Date;
import java.util.Random;

public class AttendanceService {
    Attendance attendance;
    AttendanceDAO attendanceDAO;
    String[] months;
    public AttendanceService(){
        months= new String[]{"'march'", "'april'", "'may'",
                             "'june'", "'july'", "'august'",
                             "'september'", "'october'", "'november'"};//this array will be iterated in AttendanceDAO class
        attendance= new Attendance();
        attendanceDAO=new AttendanceDAO();
    }

    public int[] showMathAttendances(int student_attendance_id){
        int[] mathAttendances=new int[9];//the values from this array is used in the linechart
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

    /*this method is used to create random attendances after a new student is created in RecordController.class*/
    public String createAttendancesSample(int student_attendance_id){
        int samplesAdded=0;
        Date date=null;
        int month=0;
        for(month=3; month<12; month++){
            for(int day=1;day<31; day++){
                date= Date.valueOf("2020"+"-"+month+"-"+day);
                Random mathAttendance = new Random();
                Random engAttendance = new Random();
                Random progAttendance = new Random();
                Random physAttendance = new Random();
                Random econAttendance = new Random();
                attendance.setStudentAttendanceId(student_attendance_id);
                attendance.setMathAttendance(mathAttendance.nextBoolean());
                attendance.setEnglishAttendance(engAttendance.nextBoolean());
                attendance.setProgrammingAttendance(progAttendance.nextBoolean());
                attendance.setPhysicsAttendance(physAttendance.nextBoolean());
                attendance.setEconomicsAttendance(econAttendance.nextBoolean());
                attendance.setAttendanceMonth(date);

                if(attendanceDAO.areAttendancesInserted(attendance))samplesAdded++;
            }
        }
        return samplesAdded+" attendances generated randomly\n" +
                "for samples in the line chart";
    }
}
