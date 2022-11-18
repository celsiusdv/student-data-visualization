package edu.student.dao;

import edu.student.database.Connector;
import edu.student.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
 * create connection
 * create statement
 * execute query
 * create resultset if read is necessary (executeQuery()for resulset), for insert/update/delete executeUpdate()/execute()
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
////////////////////////////////////////////////////////////////////////////////////
//-----------------------check if student already exist in database before insert
////////////////////////////////////////////////////////////////////////////////////
    public boolean isStudentAlreadyCreated(int student_id){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sqlQuery="SELECT count(student_id)FROM students WHERE student_id=?";
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(sqlQuery);
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
        String sqlQuery="INSERT INTO students (student_id, first_name, last_name)" +
                        " values(?,?,?)";
        try{
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(sqlQuery);
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
}
