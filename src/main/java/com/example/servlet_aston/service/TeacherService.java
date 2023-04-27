package com.example.servlet_aston.service;

import com.example.servlet_aston.DTO.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO findById(int id);
    void deleteById(int id);
    void save(TeacherDTO teacher);
    List<TeacherDTO> findAll();
    TeacherDTO getCourseTeacherById(int id);
    void update(TeacherDTO teacher);

}
