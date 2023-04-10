package com.example.servlet_aston.service;

import com.example.servlet_aston.confic.DBConnection;
import com.example.servlet_aston.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService{
    public static void main(String[] args) {
      CourseServiceImpl courseService = new CourseServiceImpl();
      List<Course> list = courseService.findAll();
        for (Course c: list) {
            System.out.println(c);


        }

    }

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
    public Course save() {
        return null;
    }

    @Override
    public List<Course> findAll() {
        String GET_ALL_COURSE = "select * from course";
        List<Course>courseList = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(GET_ALL_COURSE);
            while (rs.next()){
                Course course = new Course(rs.getInt("id"),
                        rs.getString("name_course"));
                courseList.add(course);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  courseList;
    }
}
