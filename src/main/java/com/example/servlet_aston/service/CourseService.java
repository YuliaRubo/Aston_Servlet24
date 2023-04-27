package com.example.servlet_aston.service;

import com.example.servlet_aston.DTO.CourseDTO;

import java.sql.SQLException;
import java.util.List;

public interface CourseService {
    CourseDTO findById(int id);

    // Course findById(int id);
    void deleteCourseById(int id) throws SQLException;
    void save(CourseDTO course);
    List<CourseDTO> findAll();
    CourseDTO findCourseWithStudent(int id);
    void update(CourseDTO course);

}
