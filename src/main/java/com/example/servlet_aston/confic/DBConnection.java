package com.example.servlet_aston.confic;

import java.sql.*;

public class DBConnection {

    public Statement getDbConnection() {
        Connection con = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://Localhost:3306/studentoncourse",
                    "root",
                    "root"
            );
            statement = con.createStatement();
            System.out.println("Connection to BD");

        } catch (SQLException e) {
            System.out.println("Unable to connection BD");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return statement;
    }

    public static Connection getDbConnectionOnly() {
        Connection con = null;
       PreparedStatement prStatement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://Localhost:3306/studentoncourse",
                    "root",
                    "root"
            );
           // prStatement.executeQuery();

            System.out.println("Connection to BD");

        } catch (SQLException e) {
            System.out.println("Unable to connection BD");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }
}
