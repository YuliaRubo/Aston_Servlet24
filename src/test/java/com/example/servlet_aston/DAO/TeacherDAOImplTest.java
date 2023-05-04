package com.example.servlet_aston.DAO;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.DTO.TeacherDTO;
import com.example.servlet_aston.Entity.Student;
import com.example.servlet_aston.Entity.Teacher;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.service.StudentServiceImpl;
import com.example.servlet_aston.service.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.linesOf;
import static org.junit.jupiter.api.Assertions.*;

class TeacherDAOImplTest {
    private static TeacherDAOImpl teacherDAO;
    private static TeacherServiceImpl teacherService;

    @BeforeEach
    public void beforeEach() throws SQLException, IOException {
        Connection connection = new DBConfig("test").getConnection();
        DBConfig.initForTest(connection);
        teacherDAO = new TeacherDAOImpl(new DBConfig("test"));
        teacherService = new TeacherServiceImpl(teacherDAO);
    }

    @Test
    void findById() {
        TeacherDTO teacherDTO = teacherDAO.findById(2);
        assertThat(teacherDTO.getName()).isEqualTo("Simon");
    }

    @Test
    void deleteById() {
        teacherDAO.deleteById(1);
        List<TeacherDTO> list = teacherDAO.findAll();
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void save() {
        teacherDAO.save(new Teacher("Bony", "Green_Blue"));
        List<TeacherDTO> all = teacherDAO.findAll();
        assertThat(all.get(all.size() - 1).getName()).isEqualTo("Bony");
    }

    @Test
    void update() {
        teacherDAO.update(new Teacher(1,"Lili","Bingo"));
        TeacherDTO teacherDTO = teacherDAO.findById(1);
        assertThat(teacherDTO.getName()).isEqualTo("Lili");
    }

    @Test
    void findAll() {
        List<TeacherDTO> all = teacherDAO.findAll();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void getCourseTeacherById() {
        TeacherDTO teacherDTO = teacherDAO.getCourseTeacherById(1);
        List<CourseDTO>all = teacherDTO.getListCourse();
        CourseDTO courseDTO = all.get(0);
        assertThat(courseDTO.getNameCourse()).isEqualTo("Math");

    }
}