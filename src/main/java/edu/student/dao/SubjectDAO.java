package edu.student.dao;

import edu.student.database.Connector;
import edu.student.model.Student;
import edu.student.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubjectDAO extends Connector {
/////////////////////////////////////////////////////////////////////////////
//-----------------------insert/update scores to database------------------
/////////////////////////////////////////////////////////////////////////////
    public boolean isEveryScoreAdded(Subject subject, Student student){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sqlQuery="UPDATE subjects SET" +
                        " math_score=?, english_score=?, programming_score=?, physics_score=?, economics_score=?" +
                        " WHERE studentID=?";
        try{
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,subject.getMathScore());
            preparedStatement.setInt(2,subject.getEnglishScore());
            preparedStatement.setInt(3,subject.getProgrammingScore());
            preparedStatement.setInt(4,subject.getPhysicsScore());
            preparedStatement.setInt(5,subject.getEconomicsScore());
            preparedStatement.setInt(6,student.getStudentId());
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            System.out.println("error in method isEveryScoreAdded() from SubjectDAO.class");
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
