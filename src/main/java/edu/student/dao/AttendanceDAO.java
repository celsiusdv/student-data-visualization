package edu.student.dao;

import edu.student.database.Connector;
import edu.student.model.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceDAO extends Connector {
////////////////////////////////////////////////////////////////////////////////
//------------------retrieve math attendances p/ month from database-----------
///////////////////////////////////////////////////////////////////////////////
    public int[] retrieveMathAttendances(Attendance attendance,String[] months){
        int[] mathAttendances=new int[9];
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String countAttendances= "SELECT count(math_attendance)FROM attendances" +
                " WHERE student_attendance_id=? && math_attendance=true && monthname(attendance_month)=";
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            for (int i=0; i<months.length; i++){
                preparedStatement = connection.prepareStatement(countAttendances+months[i]);//month variable parameter is filled in AttendanceService.class
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
/////////////////////////////////////////////////////////////////////////////////////////
//-------------------------retrieve english attendances p/ month from database-----------
/////////////////////////////////////////////////////////////////////////////////////////
    public int[] retrieveEnglishAttendances(Attendance attendance,String[] months){
        int[] englishAttendances=new int[9];
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String countAttendances= "SELECT count(english_attendance)FROM attendances" +
                " WHERE student_attendance_id=? && english_attendance=true && monthname(attendance_month)=";
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            for(int i=0; i<months.length; i++){
                preparedStatement = connection.prepareStatement(countAttendances+months[i]);
                preparedStatement.setInt(1,attendance.getStudentAttendanceId());
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    //get count of attendances from each month
                    englishAttendances[i]=resultSet.getInt(1);
                }
            }
            return englishAttendances;
        }catch (SQLException e){
            System.out.println("error from retrieveEnglishAttendances from AttendanceDAO.class");
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
/////////////////////////////////////////////////////////////////////////////////////////
//-------------------------retrieve programming attendances p/ month from database-----------
/////////////////////////////////////////////////////////////////////////////////////////
    public int[] retrieveProgrammingAttendances(Attendance attendance,String[] months){
        int[] progAttendances=new int[9];
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String countAttendances= "SELECT count(programming_attendance)FROM attendances" +
                " WHERE student_attendance_id=? && programming_attendance=true && monthname(attendance_month)=";
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            for(int i=0; i<months.length; i++){
                preparedStatement = connection.prepareStatement(countAttendances+months[i]);
                preparedStatement.setInt(1,attendance.getStudentAttendanceId());
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    //get count of attendances from each month
                    progAttendances[i]=resultSet.getInt(1);
                }
            }
            return progAttendances;
        }catch (SQLException e){
            System.out.println("error from retrieveProgrammingAttendances from AttendanceDAO.class");
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
/////////////////////////////////////////////////////////////////////////////////////////
//-------------------------retrieve physics attendances p/ month from database-----------
/////////////////////////////////////////////////////////////////////////////////////////
    public int[] retrievePhysicsAttendances(Attendance attendance,String[] months){
        int[] physicsAttendances=new int[9];
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String countAttendances= "SELECT count(physics_attendance)FROM attendances" +
                " WHERE student_attendance_id=? && physics_attendance=true && monthname(attendance_month)=";
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            for(int i=0; i<months.length; i++){
                preparedStatement = connection.prepareStatement(countAttendances+months[i]);
                preparedStatement.setInt(1,attendance.getStudentAttendanceId());
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    //get count of attendances from each month
                    physicsAttendances[i]=resultSet.getInt(1);
                }
            }
            return physicsAttendances;
        }catch (SQLException e){
            System.out.println("error from retrievePhysicsAttendances from AttendanceDAO.class");
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
///////////////////////////////////////////////////////////////////////////////////////////
//-------------------------retrieve economics attendances p/ month from database-----------
/////////////////////////////////////////////////////////////////////////////////////////
    public int[] retrieveEconomicsAttendances(Attendance attendance,String[] months){
        int[] economicsAttendances=new int[9];
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String countAttendances= "SELECT count(economics_attendance)FROM attendances" +
                " WHERE student_attendance_id=? && economics_attendance=true && monthname(attendance_month)=";
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            for(int i=0; i<months.length; i++){
                preparedStatement = connection.prepareStatement(countAttendances+months[i]);
                preparedStatement.setInt(1,attendance.getStudentAttendanceId());
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    //get count of attendances from each month
                    economicsAttendances[i]=resultSet.getInt(1);
                }
            }
            return economicsAttendances;
        }catch (SQLException e){
            System.out.println("error from retrieveEconomicsAttendances from AttendanceDAO.class");
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
