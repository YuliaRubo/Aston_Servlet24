package com.example.servlet_aston.Entity;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private  String nameCourse;
    private Integer teacherId;
    List<Student> list = new ArrayList<>();

    public Course(String nameCourse, Integer teacherId, List<Student> list) {
        this.nameCourse = nameCourse;
        this.teacherId = teacherId;
        this.list = list;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public Course(int id, String nameCourse, Integer teacherId) {
        this.id = id;
        this.nameCourse = nameCourse;
        this.teacherId = teacherId;
    }

    public Course(String nameCourse, Integer teacherId) {
        this.nameCourse = nameCourse;
        this.teacherId = teacherId;
    }

    public Course(int id, String nameCourse) {
        this.id = id;
        this.nameCourse = nameCourse;
    }

    public Course() {
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", nameCourse='" + nameCourse + '\'' +
                '}';
    }
}
