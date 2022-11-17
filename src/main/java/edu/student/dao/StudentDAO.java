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
 * create resultset if read is necessary(executeQuery()for resulset) for insert/update/delete executeUpdate()/execute()
 * */
public class StudentDAO extends Connector {
    Set<Student> students=new HashSet<>();
//----------------------------retrieve students from database--------------
    public Set<Student> retrieveStudents(Student student){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sqlQuery="SELECT * FROM students";
        ResultSet resultSet=null;
        try {
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(sqlQuery);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()==true){
                student=new Student(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                students.add(student);//fill list with all students
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
}
