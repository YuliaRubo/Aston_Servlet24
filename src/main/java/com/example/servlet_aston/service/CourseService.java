package com.example.servlet_aston.service;

import com.example.servlet_aston.model.Course;
import com.example.servlet_aston.model.Student;

import java.util.List;

public interface CourseService {
    String findById(int id);
    void deleteById();
    Course save();
    List<Course> findAll();
}
