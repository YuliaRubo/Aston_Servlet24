package com.example.servlet_aston.service;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.Entity.Course;

import java.util.List;

public interface CourseService {
    CourseDTO findById(int id);

    // Course findById(int id);
    void deleteCourseById(int id);
    void save(Course course);
    List<CourseDTO> findAll();
    CourseDTO findCourseWithStudent(int id);
}
