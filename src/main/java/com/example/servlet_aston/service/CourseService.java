package com.example.servlet_aston.service;

import com.example.servlet_aston.model.Course;
import com.example.servlet_aston.model.Student;

import java.util.List;

public interface CourseService {
    Course findById(int id);

    // Course findById(int id);
    void deleteById(int id);
   void save(Course course);
    List<Course> findAll();
    Course findCourseWithStudent(int id);
}
