package com.example.servlet_aston.Mapping;

import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.Entity.Student;

public class StudentMapping {

    public static Student getStudentFromStudentDTO(StudentDTO studentDTO){
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setAge(studentDTO.getAge());
        student.setGender(studentDTO.getGender());
        return student;
    }

    public static StudentDTO getStudentDTOFromStudent(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setAge(student.getAge());
        studentDTO.setGender(student.getGender());
        return studentDTO;
    }
}
