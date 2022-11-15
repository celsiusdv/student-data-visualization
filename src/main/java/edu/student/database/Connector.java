package edu.student.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private final String BASE="StudentsRecords";
    private final String ROOT="root";
    private final String PASSWORD="123";
    private final String URL="jdbc:mariadb://localhost:3306/"+BASE;
    private Connection connection;
    public Connector(){}

    public Connection getConnection(){//open connection to database
        try {
            connection= DriverManager.getConnection(URL,ROOT,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
