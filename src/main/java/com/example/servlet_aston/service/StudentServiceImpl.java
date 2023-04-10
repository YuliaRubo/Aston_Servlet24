package com.example.servlet_aston.service;

import com.example.servlet_aston.confic.DBConnection;
import com.example.servlet_aston.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
//    public static void main(String[] args) {
//        StudentServiceImpl service = new StudentServiceImpl();
//        List<Student> students = service.findAll();
//        for(Student st:students){
//            System.out.println(st);
//        }
//    }

    DBConnection connect = new DBConnection();
    Statement statement = connect.getDbConnection();


    @Override
    public String findById(int id) {
        return null;
    }

    @Override
    public void deleteById() {

    }

    @Override
    public Student save() {
        return null;
    }

    @Override
    public List<Student> findAll() {
            statement = connect.getDbConnection();
            String GET_ALL_STUDENT = "Select * from student";
            String str = null;
            List<Student>listStr = new ArrayList<>();

            try {
                ResultSet rs = statement.executeQuery(GET_ALL_STUDENT);
                while (rs.next()) {
                    Student student = new Student( rs.getInt("id"), rs.getString("name"),
                            rs.getString("surname"), rs.getInt("age"),
                            rs.getString("gender"));
                    listStr.add(student);
                }

                statement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return listStr;
        }
    }

