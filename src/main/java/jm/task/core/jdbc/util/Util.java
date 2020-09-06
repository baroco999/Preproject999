package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    public static Connection toMySql() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String username = "root";
        String password = "ьгышсф1979";
        return  DriverManager.getConnection(url, username, password);
    }
}
