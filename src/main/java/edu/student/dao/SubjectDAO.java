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

public class SubjectDAO extends Connector{
//----------------------------retrieve scores from database------------
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
            return subjectScores;
        }catch (SQLException e){
            System.out.println("error in method retrieveScores() from SubjectDAO.class");
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

////----------------------------retrieve scores from database--------------
    public Subject retrieveScoresById(Subject subject){
        Subject subjectObj=new Subject();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String retrieveScore="SELECT math_score, english_score, programming_score, physics_score, economics_score\n" +
                "FROM subjects WHERE studentID=?";
        ResultSet resultSet=null;
        try{
            connection=super.getConnection();
            preparedStatement=connection.prepareStatement(retrieveScore);
            preparedStatement.setInt(1,subject.getStudentID());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                subjectObj.setMathScore(resultSet.getInt("math_score"));
                subjectObj.setEnglishScore(resultSet.getInt("english_score"));
                subjectObj.setProgrammingScore(resultSet.getInt("programming_score"));
                subjectObj.setPhysicsScore(resultSet.getInt("physics_score"));
                subjectObj.setEconomicsScore(resultSet.getInt("economics_score"));
            }
            return subjectObj;
        }catch (SQLException e){
            System.out.println("error in method retrieveScores() from SubjectDAO.class");
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

//-----------------insert/update scores from record frame input--------------
    public boolean isEveryScoreUpdated(Subject subject, Student student){
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

////---------------------update  to database from table input------------------
    public boolean isScoreFromTableUpdated(Subject subject){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String updateMathScore="UPDATE subjects SET math_score=? WHERE studentID=?";
        String updateEnglishScore="UPDATE subjects SET english_score=? WHERE studentID=?";
        String updateProgrScore="UPDATE subjects SET programming_score=? WHERE studentID=?";
        String updatePhysicsScore="UPDATE subjects SET physics_score=? WHERE studentID=?";
        String updateEconomicsScore="UPDATE subjects SET economics_score=? WHERE studentID=?";
        try{
            connection=super.getConnection();
            if(subject.getMathScore() > 0){
                preparedStatement=connection.prepareStatement(updateMathScore);
                preparedStatement.setInt(1,subject.getMathScore());
                preparedStatement.setInt(2,subject.getStudentID());
                preparedStatement.execute();
            }
            if(subject.getEnglishScore() > 0){
                preparedStatement=connection.prepareStatement(updateEnglishScore);
                preparedStatement.setInt(1,subject.getEnglishScore());
                preparedStatement.setInt(2,subject.getStudentID());
                preparedStatement.execute();
            }
            if(subject.getProgrammingScore() > 0){
                preparedStatement=connection.prepareStatement(updateProgrScore);
                preparedStatement.setInt(1,subject.getProgrammingScore());
                preparedStatement.setInt(2,subject.getStudentID());
                preparedStatement.execute();
            }
            if(subject.getPhysicsScore() > 0){
                preparedStatement=connection.prepareStatement(updatePhysicsScore);
                preparedStatement.setInt(1,subject.getPhysicsScore());
                preparedStatement.setInt(2,subject.getStudentID());
                preparedStatement.execute();
            }
            if(subject.getEconomicsScore() > 0){
                preparedStatement=connection.prepareStatement(updateEconomicsScore);
                preparedStatement.setInt(1,subject.getEconomicsScore());
                preparedStatement.setInt(2,subject.getStudentID());
                preparedStatement.execute();
            }
            return true;
        }catch (SQLException e){
            System.out.println("error in method isScoreFromTableUpdated() from SubjectDAO.class");
            e.printStackTrace();
            return false;
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
