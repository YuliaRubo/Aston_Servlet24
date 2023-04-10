package com.example.servlet_aston.service;

import com.example.servlet_aston.confic.DBConnection;
import com.example.servlet_aston.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    public static void main(String[] args) {
        StudentServiceImpl service = new StudentServiceImpl();
        Student student = service.findById(5);
        System.out.println(student);
    }

    DBConnection connect = new DBConnection();
    Statement statement = connect.getDbConnection();



    @Override
    public Student findById(int id) {
        Student student = null;
        String GET_STUDENT_BY_ID = "Select * from student where id = ?";
        System.out.println(GET_STUDENT_BY_ID + " "+ "zapros");
        try(Connection con = DBConnection.getDbConnectionOnly()){
        try (PreparedStatement preparedStatement = con.prepareStatement(GET_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idStudent = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String gender = resultSet.getString(5);
                student = new Student(idStudent, name, surname, age, gender);
            }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
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
                    Student student = new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("age"),
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

