package com.example.servlet_aston.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public DBConfig(String test) {
        if (test.equals("test")) {
            this.driver = "org.h2.Driver";
            this.url = "jdbc:h2:D:\\project IDEA\\Aston_Servlet24\\db\\studentOnCourse";
            this.username = "sa";
            this.password = "";
        }
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

    public static void initForTest(Connection connection) throws SQLException, IOException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String initSql = getFileContent("src/test/resources/seed.sql");
            statement.execute(initSql);
        }
    }

    private static String getFileContent(String fileName) throws IOException {
        Path pathToSolution = Paths.get(fileName).toAbsolutePath();
        return Files.readString(pathToSolution).trim();
    }

}

