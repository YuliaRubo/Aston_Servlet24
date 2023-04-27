package com.example.servlet_aston.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

        private String driver;

        private String url;

        private String username;

        private String password;

        public DBConfig() {
            this.driver = "com.mysql.jdbc.Driver";
            this.url = "jdbc:mysql://Localhost:3306/studentoncourse";
            this.username = "root";
            this.password = "root";
        }



        public final Connection getConnection() {
            Connection connection = null;
            try {
                try {
                    Class.forName(driver);
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return connection;
        }

        public static void closeConnection(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }

