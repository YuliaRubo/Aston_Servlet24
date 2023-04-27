package com.example.servlet_aston.DAO;

import com.example.servlet_aston.DTO.TeacherDTO;
import com.example.servlet_aston.Entity.Teacher;

import java.util.List;

public interface TeacherDAO {
    TeacherDTO findById(int id);
    void deleteById(int id);
    void save(Teacher teacher);
    List<TeacherDTO> findAll();
    TeacherDTO getCourseTeacherById(int id);
    void update(Teacher teacher);
}
