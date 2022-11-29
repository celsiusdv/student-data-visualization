package edu.student.services;

import edu.student.dao.AttendanceDAO;
import edu.student.model.Attendance;

public class AttendanceService {
    Attendance attendance;
    AttendanceDAO attendanceDAO;
    public AttendanceService(){
        attendance= new Attendance();
        attendanceDAO=new AttendanceDAO();
    }

    public int[] showMathAttendances(int student_attendance_id){
        int[] mathAttendances=new int[9];
        if(student_attendance_id > 0){
            attendance.setStudentAttendanceId(student_attendance_id);
            mathAttendances=attendanceDAO.retrieveMathAttendances(attendance);
        }
        return mathAttendances;
    }
}
