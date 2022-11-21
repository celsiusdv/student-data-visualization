package edu.student.dao;

import edu.student.database.Connector;
import edu.student.model.Student;
import edu.student.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
//task 2
public class SubjectDAO extends Connector {
///////////////////////////////////////////////////////////////////////////
//----------------------------retrieve scores from database--------------
///////////////////////////////////////////////////////////////////////////
    public Set<Subject> retrieveScores(Subject subject){
        Set<Subject> subjectScores=new HashSet<>();

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sqlQuery="SELECT * FROM subjects";
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(sqlQuery);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                subject=new Subject(resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),
                                    resultSet.getInt(5),resultSet.getInt(6));
                subjectScores.add(subject);//fill list and set later in student service on ObservableList if is required
            }
        }catch (SQLException e){
            System.out.println("error in method retrieveScores() from SubjectDAO.class");
            e.printStackTrace();
        }
        return subjectScores;
    }

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
