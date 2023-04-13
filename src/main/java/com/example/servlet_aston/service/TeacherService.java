package com.example.servlet_aston.service;

import com.example.servlet_aston.model.Student;
import com.example.servlet_aston.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher findById(int id);
    int deleteById(int id);
    void save(Teacher teacher);
    List<Teacher> findAll();
    Teacher getCourseTeacherById(int id);
}
