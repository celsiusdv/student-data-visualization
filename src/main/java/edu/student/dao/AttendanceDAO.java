package edu.student.dao;

import edu.student.database.Connector;
import edu.student.model.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceDAO extends Connector {
///////////////////////////////////////////////////////////////////////////
//----------------------------retrieve math attendances p/ month from database-----------
///////////////////////////////////////////////////////////////////////////
    public int[] retrieveMathAttendances(Attendance attendance){
        int[] mathAttendances=new int[9];
        String[] months={"'march'","'april'","'may'","'june'","'july'","'august'","'september'","'october'","'november'"};
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String countAttendances= "SELECT count(math_attendance)FROM attendances" +
                " WHERE student_attendance_id=? && math_attendance=true && monthname(attendance_month)=";
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            for (int i=0; i< months.length; i++){
                preparedStatement = connection.prepareStatement(countAttendances+months[i]);
                preparedStatement.setInt(1,attendance.getStudentAttendanceId());//WHERE student_attendance_id=?
                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()) {
                    //get count of attendances from each month
                    mathAttendances[i]=resultSet.getInt(1);
                }
            }
            return mathAttendances;
        }catch (SQLException e){
            System.out.println("error from retrieveMathAttendances from AttendanceDAO.class");
            e.printStackTrace();
            return null;
        }finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
        }
    }
}
