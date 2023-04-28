package com.example.servlet_aston.DAO;

import com.example.servlet_aston.Entity.Course;
import com.example.servlet_aston.Entity.Student;
import com.example.servlet_aston.Entity.Teacher;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


class CourseDAOImplTest {

    private static CourseDAOImpl courseDAO;
    private static CourseServiceImpl courseService;


    @BeforeEach
    public void beforeEach() throws SQLException, IOException {
        Connection connection = new DBConfig("test").getConnection();
        DBConfig.initForTest(connection);
        courseDAO = new CourseDAOImpl(new DBConfig("test"));
        courseService = new CourseServiceImpl(courseDAO);
    }

//
//    private static final Course MATH = new Course(1, "Math",3);
//    private  static  final Student MARK= new Student("Mark","Merkovka", 25, "M");
//    private  static  final Teacher SEMEN = new Teacher("Semen", "Semenovich");



    @Test
    @DisplayName("Get Course by id test")
    void findById() {

        int id = 1;
        String nameCourse = "";
        int teacherId = 10;


    }

    @Test
    void deleteCourseById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findCourseWithStudent() {
    }
}