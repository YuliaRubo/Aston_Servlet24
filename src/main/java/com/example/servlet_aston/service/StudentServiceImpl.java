package com.example.servlet_aston.service;

import com.example.servlet_aston.confic.DBConnection;
import com.example.servlet_aston.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
//    public static void main(String[] args) {
//        StudentServiceImpl service = new StudentServiceImpl();
//        Student student = service.findById(5);
//        System.out.println(student);
//        Student student1= new Student("Lily", "Sorr", 18, "F");
//        service.save(student1);
//        List<Student>list = service.findAll();
//        for (Student st: list){
//            System.out.println(st);
//        }
//        service.deleteById(8);
//        List<Student>list1 = service.findAll();
//        for (Student st: list1){
//            System.out.println(st);
//        }
//    }

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
    public int deleteById(int id) {
        String DELETE_FROM_STUDENT = "Delete from student where id=?";
        try(Connection con = DBConnection.getDbConnectionOnly()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_FROM_STUDENT)) {
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

        @Override
    public int save(Student student) {
        String INSERT_IN_STUDENT = "Insert into student (id, name, surname, age, gender) values (?, ? , ?, ?, ?)";
        System.out.println(INSERT_IN_STUDENT+" "+ "zapros insert");
        try(Connection con = DBConnection.getDbConnectionOnly()){
            try(PreparedStatement preparedStatement = con.prepareStatement(INSERT_IN_STUDENT)){
                preparedStatement.setInt(1, student.getId());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setString(3,student.getSurname());
                preparedStatement.setInt(4, student.getAge());
                preparedStatement.setString(5, student.getGender());
                return  preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
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

