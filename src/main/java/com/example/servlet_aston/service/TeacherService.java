package com.example.servlet_aston.service;

import com.example.servlet_aston.model.Student;
import com.example.servlet_aston.model.Teacher;

import java.util.List;

public interface TeacherService {
    String findById(int id);
    void deleteById();
    Teacher save();
    List<String> findAll();
}
