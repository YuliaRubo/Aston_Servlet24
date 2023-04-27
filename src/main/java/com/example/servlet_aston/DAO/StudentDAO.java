package com.example.servlet_aston.DAO;

import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.Entity.Student;

import java.util.List;

public interface StudentDAO {
    StudentDTO findById(int id);
    void deleteById(int id);
    int save(Student student);
    List<StudentDTO> findAll();
    StudentDTO findAllCourseStudent(int id);
    void update(Student student);

}
