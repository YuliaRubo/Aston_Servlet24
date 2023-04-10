package com.example.servlet_aston.model;

import java.util.List;

public class Course {
    private int id;
    private  String nameCourse;
    List<Student> listStudent;

    public Course(int id, String nameCourse) {
        this.id = id;
        this.nameCourse = nameCourse;
    }

    public Course(String name_course) {
    }

    public Course(int id, String nameCourse, List<Student> listStudent) {
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

    public List<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<Student> listStudent) {
        this.listStudent = listStudent;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", nameCourse='" + nameCourse + '\'' +
                ", listStudent=" + listStudent +
                '}';
    }
}
