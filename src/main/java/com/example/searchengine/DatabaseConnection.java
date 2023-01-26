package com.example.searchengine;

import java.sql.*;

public class DatabaseConnection {

    Constants constants = new Constants();
    String MySQLURL = "jdbc:mysql://localhost:3306/search_engine?useSSL=false";
    String username = constants.username;
    String password = constants.password;
    Connection con = null;
    DatabaseConnection() throws SQLException {
        con = DriverManager.getConnection(MySQLURL, username, password);            //connecting sql with java
        System.out.println("Connection made!");
//        executeQuery();
    }
    public ResultSet executeQuery(String query) throws SQLException {
        ResultSet ans = null;
        Statement statement = con.createStatement();
        ans = statement.executeQuery(query);
        return ans;
    }

    public  int executeUpdate(String query) throws SQLException {
        int ans = 0;
        Statement statement = con.createStatement();
        ans = statement.executeUpdate(query);
        return ans;
    }
}
