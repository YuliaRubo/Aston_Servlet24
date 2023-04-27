package com.example.servlet_aston.DAO;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.Entity.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    CourseDTO findById(int id);

    // Course findById(int id);
    void deleteCourseById(int id) throws SQLException;
    void save(Course course);
    List<CourseDTO> findAll();
    CourseDTO findCourseWithStudent(int id);
    void update(Course course);
}
