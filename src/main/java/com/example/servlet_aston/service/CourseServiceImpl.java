package com.example.servlet_aston.service;

import com.example.servlet_aston.DAO.CourseDAOImpl;
import com.example.servlet_aston.Entity.Course;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.mapping.CourseMapping;

import java.sql.*;
import java.util.List;

public class CourseServiceImpl implements CourseService {

//    public static void main(String[] args) {
//        CourseServiceImpl courseService = new CourseServiceImpl();
//
//        CourseDTO courseDTO = courseService.findCourseWithStudent(4);
//        System.out.println(courseDTO);
//    }

    private CourseDAOImpl courseDAO;

    public CourseServiceImpl() {
        this.courseDAO = new CourseDAOImpl(new DBConfig());
    }

    public CourseServiceImpl(CourseDAOImpl courseDAO) {
        this.courseDAO = courseDAO;
    }


    @Override
    public CourseDTO findById(int id) {
        return courseDAO.findById(id);
    }

    @Override
    public void deleteCourseById(int id) throws SQLException {
        courseDAO.deleteCourseById(id);
    }


    @Override
    public void save(CourseDTO coursedto) {
        Course course = new Course();
        course=CourseMapping.getCourseFromCourseDTO(coursedto);
        courseDAO.save(course);
    }

    @Override
    public List<CourseDTO> findAll() {
        return  courseDAO.findAll();
    }


    @Override
    public CourseDTO findCourseWithStudent(int id) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO  = courseDAO.findCourseWithStudent(id);
        return courseDTO;
    }

    @Override
    public void update(CourseDTO courseDTO) {
        Course course = new Course();
        course=CourseMapping.getCourseFromCourseDTO(courseDTO);
        courseDAO.update(course);
    }
}
