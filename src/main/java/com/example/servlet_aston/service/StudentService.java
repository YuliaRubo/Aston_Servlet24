package com.example.servlet_aston.service;

import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.Entity.Student;

import java.util.List;

public interface StudentService {

    StudentDTO findById(int id);
    int deleteById(int id);
    int save(Student student);
    List<StudentDTO> findAll();
    StudentDTO findAllCourseStudent(int id);
}
