package com.example.servlet_aston.service;

import com.example.servlet_aston.model.Student;

import java.util.List;

public interface StudentService {

    Student findById(int id);
    int deleteById(int id);
    int save(Student student);
    List<Student> findAll();
}
