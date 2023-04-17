package com.example.servlet_aston.Entity;

public class Course {
    private int id;
    private  String nameCourse;

    public Course(int id, String nameCourse) {
        this.id = id;
        this.nameCourse = nameCourse;
    }

    public Course() {
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
