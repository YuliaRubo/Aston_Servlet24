package com.example.servlet_aston.service;

import com.example.servlet_aston.DTO.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO findById(int id);
    void deleteById(int id);
    int save(StudentDTO student);
    List<StudentDTO> findAll();
    StudentDTO findAllCourseStudent(int id);
    void update(StudentDTO student);
}
