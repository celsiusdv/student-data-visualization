package edu.student.dao;

import edu.student.database.Connector;
import edu.student.model.Student;
import edu.student.model.StudentScore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
//task 1
/*
 * create connection
 * create statement - for insert/update/delete PreparedStatement=execute() || Statement=executeUpdate()
 * execute query
 * create resultset if read is necessary (executeQuery()for resulset)
 * */
public class StudentDAO extends Connector {
///////////////////////////////////////////////////////////////////////////
//----------------------------retrieve students from database--------------
///////////////////////////////////////////////////////////////////////////
    public Set<Student> retrieveStudents(Student student){
        Set<Student> students=new HashSet<>();

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sqlQuery="SELECT * FROM students";
        ResultSet resultSet=null;
        try {
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(sqlQuery);
            resultSet= preparedStatement.executeQuery();

            while (resultSet.next() == true){
                student=new Student(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                students.add(student);//fill list and set later in student service on ObservableList if is required
            }
        }catch (SQLException e){
            System.out.println("error in method retrieveStudents() from StudentDAO.class");
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
        }
        return students;
    }
/////////////////////////////////////////////////////////////////////////////
//----retrieve students and subject scores from database with a join query---
/////////////////////////////////////////////////////////////////////////////
    public Set<StudentScore> retrieveStudentsAndScores(StudentScore studentScore){
        Set<StudentScore> scores=new HashSet<>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sqlQuery="SELECT students.student_id, students.first_name, students.last_name, \n" +
                        "    subjects.math_score, subjects.english_score, subjects.programming_score,\n" +
                        "    subjects.physics_score, subjects.economics_score\n" +
                        "    FROM students \n" +
                        "RIGHT JOIN subjects\n" +
                        "ON students.student_id = subjects.studentID;";//right join query of students and subjects table
        try{
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(sqlQuery);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                studentScore=new StudentScore(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                                              resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),
                                              resultSet.getInt(7),resultSet.getInt(8));
                scores.add(studentScore);//fill list and set later in student service in an ObservableList if is required
            }
        }catch (SQLException e){
            System.out.println("error in method retrieveStudentsAndScores() from StudentDAO.class");
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
        }
        return scores;
    }
////////////////////////////////////////////////////////////////////////////////////
//-----------------check if student already exist in database before insert---------
////////////////////////////////////////////////////////////////////////////////////
    public boolean isStudentAlreadyCreated(int student_id){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String countQuery="SELECT count(student_id)FROM students WHERE student_id=?";//query to count students with the given id
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(countQuery);
            preparedStatement.setInt(1,student_id);//---> parameter: WHERE student_id=?
            resultSet=preparedStatement.executeQuery();
/*-----------iterate the resultset and count the amount of student_id's------*/
            while(resultSet.next()){
                if(resultSet.getInt(1) == 0){//this expression should return 0, if the result is 0, then there is not registered student yet
                    return false;
                }
            }
            return true;//true if the student_id already exist
        }catch (SQLException e){
            System.out.println("error in method isStudentAlreadyCreated() from StudentDAO.class");
            e.printStackTrace();
            return true;
        }finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
        }
    }
/////////////////////////////////////////////////////////////////////////////
//-----------------------insert student to database------------------
/////////////////////////////////////////////////////////////////////////////
    public boolean isStudentCreated(Student student){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String insertQuery="INSERT INTO students (student_id, first_name, last_name)" +
                        " values(?,?,?)";
        try{
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,student.getStudentId());
            preparedStatement.setString(2,student.getFirstName());
            preparedStatement.setString(3,student.getLastName());
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            System.out.println("error in method isStudentCreated() from StudentDAO.class");
            e.printStackTrace();
            return false;
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
        }
    }
/////////////////////////////////////////////////////////////////////////////
//-----------------------update student id-----------------------------------
/////////////////////////////////////////////////////////////////////////////
    public boolean isStudentIdUpdated(Student student, Integer student_id){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String updateQuery="UPDATE students SET student_id=? WHERE student_id=?";
        try{
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1,student.getStudentId());
            preparedStatement.setInt(2,student_id);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            System.out.println("error in method isStudentIdUpdated() from StudentDAO.class");
            e.printStackTrace();
            return false;
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
        }
    }
/////////////////////////////////////////////////////////////////////////////
//-----------------------update student credentials--------------------------
/////////////////////////////////////////////////////////////////////////////
    public boolean isStudentUpdated(Student student){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String updateName="UPDATE students SET first_name=? WHERE student_id=?";
        String updateLastName="UPDATE students SET last_name=? WHERE student_id=?";
        try{
            connection=super.getConnection();
            if(student.getFirstName()!=null){
                preparedStatement=connection.prepareStatement(updateName);
                preparedStatement.setString(1,student.getFirstName());
                preparedStatement.setInt(2,student.getStudentId());
                preparedStatement.execute();
            }
            if(student.getLastName() !=null){
                preparedStatement=connection.prepareStatement(updateLastName);
                preparedStatement.setString(1,student.getLastName());
                preparedStatement.setInt(2,student.getStudentId());
                preparedStatement.execute();
            }
            return true;
        }catch (SQLException e){
            System.out.println("error in method isStudentUpdated() from StudentDAO.class");
            e.printStackTrace();
            return false;
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {throw new RuntimeException(e);}
        }
    }
}
