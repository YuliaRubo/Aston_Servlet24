package com.example.servlet_aston.service;

import com.example.servlet_aston.confic.DBConnection;
import com.example.servlet_aston.model.Student;
import com.example.servlet_aston.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements TeacherService{
//
//    public static void main(String[] args) {
//
//        TeacherServiceImpl teacherService = new TeacherServiceImpl();
//        List<String> list = teacherService.findAll();
//        for (String s:list) {
//            System.out.println(s);
//
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
    public Teacher save() {
        return null;
    }

    @Override
    public List<String> findAll() {
        statement = connect.getDbConnection();
        String GET_ALL_TEACHER = "Select * from teacher";
        String str = null;
        List<Teacher>listStr = new ArrayList<>();
        List<String>stringList = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery(GET_ALL_TEACHER);
            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"));
                listStr.add(teacher);
            }
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        for (Teacher t: listStr) {
            str = t.getName() +" "+ t.getSurname();
            stringList.add(str);
        }

        return stringList;
    }

}
