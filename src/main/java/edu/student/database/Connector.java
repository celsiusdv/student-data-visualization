package edu.student.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
//db4free.net
/*    private final String BASE="studentsrecords1";
    private final String ROOT="memberdb";
    private final String PASSWORD="uXjJEfVDvVw8mvu";
    private final String URL="jdbc:mysql://db4free.net:3306/"+BASE+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private Connection connection;*/

/*//freedb.tech
    private final String BASE="freedb_StudentsRecords";
    private final String ROOT="freedb_owner";
    private final String PASSWORD="$n9vXPkVY%$j%Z5";
    private final String URL="jdbc:mysql://sql.freedb.tech:3306/"+BASE+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private Connection connection;*/


    private final String BASE="StudentsRecords";
    private final String ROOT="root";
    private final String PASSWORD="123";
    private final String URL="jdbc:mariadb://localhost:3306/"+BASE+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
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
