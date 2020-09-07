package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    public static final String URL = "jdbc:mysql://localhost:3306/mybase";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";

    public static Connection toMySql() throws SQLException {
        return  DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
