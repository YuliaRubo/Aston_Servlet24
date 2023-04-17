package com.example.servlet_aston.service;

import com.example.servlet_aston.DTO.TeacherDTO;
import com.example.servlet_aston.Entity.Teacher;

import java.util.List;

public interface TeacherService {
    TeacherDTO findById(int id);
    int deleteById(int id);
    void save(Teacher teacher);
    List<TeacherDTO> findAll();
    TeacherDTO getCourseTeacherById(int id);
}
