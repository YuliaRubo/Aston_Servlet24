package com.example.servlet_aston.DTO;

import java.util.List;

public class TeacherDTO {
    private int id;
    private  String name;
    private  String surname;
    List<CourseDTO> listCourse;


    public TeacherDTO(int id, String name, String surname, List<CourseDTO> listCourse) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.listCourse = listCourse;
    }

    public TeacherDTO(String name, String surname, List<CourseDTO> listCourse) {
        this.name = name;
        this.surname = surname;
        this.listCourse = listCourse;
    }

    public TeacherDTO() {
    }

    public TeacherDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<CourseDTO> getListCourse() {
        return listCourse;
    }

    public void setListCourse(List<CourseDTO> listCourse) {
        this.listCourse = listCourse;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", listCourse=" + listCourse +
                '}';
    }
}
