package com.example.servlet_aston.mapping;

import com.example.servlet_aston.DTO.TeacherDTO;
import com.example.servlet_aston.Entity.Teacher;

public class TeacherMapping {


    public static TeacherDTO getTeacherDTOFromTeacher(Teacher teacher){
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setName(teacher.getName());
        teacherDTO.setSurname(teacher.getSurname());
        return teacherDTO;
    }

    public static Teacher getTeacherFromTeacherDTO(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.getId());
        teacher.setName(teacherDTO.getName());
        teacher.setSurname(teacherDTO.getSurname());
        return teacher;
    }
}
