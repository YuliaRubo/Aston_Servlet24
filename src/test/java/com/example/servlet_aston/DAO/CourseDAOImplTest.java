package com.example.servlet_aston.DAO;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.Entity.Course;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.service.CourseService;
import com.example.servlet_aston.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CourseDAOImplTest {

    private static CourseDAOImpl courseDAO;
    private static CourseService courseService;


    @BeforeEach
    public void beforeEach() throws SQLException, IOException {
        Connection connection = new DBConfig("test").getConnection();
        DBConfig.initForTest(connection);
        courseDAO = new CourseDAOImpl(new DBConfig("test"));
        courseService = new CourseServiceImpl(courseDAO);
    }

    @Test
    @DisplayName("Get Course by id test")
    void findByIdCourse() {
        CourseDTO courseDTO = courseDAO.findById(2);
        assertThat(courseDTO.getNameCourse()).isEqualTo("Graphic");

    }

    @Test
    void deleteCourseById() throws SQLException {
        courseDAO.deleteCourseById(2);
        List<CourseDTO> all = courseDAO.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void save() {
        courseDAO.save(new Course("Dance", 2));
        List<CourseDTO> all = courseDAO.findAll();
        assertThat(all.size()).isEqualTo(4);
        assertThat(all.get(all.size() - 1).getNameCourse()).isEqualTo("Dance");
    }

    @Test
    void update() {
        courseDAO.update(new Course(1, "Math3", 1));
        CourseDTO course = courseDAO.findById(1);
        assertThat(course.getNameCourse()).isEqualTo("Math3");

    }

    @Test
    void findAllTestSize() {
        List<CourseDTO> all = courseDAO.findAll();
        assertThat(all.size()).isNotEqualTo(5);
    }

    @Test
    void findCourseWithStudent() {
        CourseDTO courseDTO = courseDAO.findCourseWithStudent(2);
        List<StudentDTO> listStudent = courseDTO.getListStudent();
        StudentDTO studentDTO = listStudent.get(0);
        assertThat(studentDTO.getName()).isEqualTo("One");
    }


}
