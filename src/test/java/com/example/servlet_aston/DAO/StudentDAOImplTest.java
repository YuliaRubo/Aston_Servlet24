package com.example.servlet_aston.DAO;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.Entity.Course;
import com.example.servlet_aston.Entity.Student;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudentDAOImplTest {
    private static StudentDAOImpl studentDAO;
    private static StudentServiceImpl service;

    @BeforeEach
    public void beforeEach() throws SQLException, IOException {
        Connection connection = new DBConfig("test").getConnection();
        DBConfig.initForTest(connection);
        studentDAO = new StudentDAOImpl(new DBConfig("test"));
        service = new StudentServiceImpl(studentDAO);
    }

    @Test
    void findById() {
        StudentDTO studentDTO = studentDAO.findById(2);
        assertThat(studentDTO.getName()).isEqualTo("Two");
    }


    @Test
    void deleteById() {
        studentDAO.deleteById(2);
        List<StudentDTO> all = studentDAO.findAll();
        assertThat(all.size()).isEqualTo(4);
    }

    @Test
    void save() {
        studentDAO.save(new Student("Vasya", "Pink", 23, "F"));
        List<StudentDTO> all = studentDAO.findAll();
        assertThat(all.get(all.size() - 1).getName()).isEqualTo("Vasya");
    }

    @Test
    void update() {
        studentDAO.update(new Student(1, "Nine", "Blue", 34, "F"));
        StudentDTO studentDTO = studentDAO.findById(1);
        assertThat(studentDTO.getName()).isEqualTo("Nine");
    }

    @Test
    void findAll() {
        List<StudentDTO> all = studentDAO.findAll();
        assertThat(all.size()).isEqualTo(5);
    }

    @Test
    void findAllCourseStudent() {
        StudentDTO studentDTO = studentDAO.findAllCourseStudent(1);
        List<CourseDTO>list = studentDTO.getCourseList();
        CourseDTO courseDTO = list.get(0);
        assertThat(studentDTO.getName()).isEqualTo("One");
    }
}