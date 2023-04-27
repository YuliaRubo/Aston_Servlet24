package com.example.servlet_aston.DTO;

import com.example.servlet_aston.Entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private int id;
    private String nameCourse;
    List<StudentDTO> listStudent =new ArrayList<>();
    private Integer teacherId;

    public CourseDTO() {
    }

    public CourseDTO(String nameCourse, List<StudentDTO> listStudent, Integer teacherId) {
        this.nameCourse = nameCourse;
        this.listStudent = listStudent;
        this.teacherId = teacherId;
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

    public void setListStudent(List<StudentDTO> listStudent) {
        this.listStudent = listStudent;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", nameCourse='" + nameCourse + '\'' +
                ", listStudent=" + listStudent +
                ", teacherId=" + teacherId +
                '}';
    }
}
