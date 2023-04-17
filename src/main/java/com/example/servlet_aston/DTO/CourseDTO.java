package com.example.servlet_aston.DTO;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private int id;
    private  String nameCourse;
    List<StudentDTO> listStudent;

    public CourseDTO(int id, String nameCourse) {
        this.id = id;
        this.nameCourse = nameCourse;
    }

    public CourseDTO(String name_course) {
    }

    public CourseDTO() {
        listStudent = new ArrayList<>();
   }

    public CourseDTO(int id, String nameCourse, List<StudentDTO> listStudent) {
        this.id = id;
        this.nameCourse = nameCourse;
        this.listStudent = listStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public List<StudentDTO> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<StudentDTO> listStudentDTO) {
        this.listStudent = listStudentDTO;
    }


    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", nameCourse='" + nameCourse + '\'' +
                '}';
    }
}
