package com.example.servlet_aston.service;

import com.example.servlet_aston.DAO.CourseDAOImpl;
import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.config.DBConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CourseServiceImplTest {
    private static CourseService courseService;
    private static CourseDAOImpl courseDAO;

    @BeforeEach
    public void beforeEach() throws SQLException, IOException {
        Connection connection = new DBConfig("test").getConnection();
        DBConfig.initForTest(connection);
        courseDAO = new CourseDAOImpl(new DBConfig("test"));
        courseService = new CourseServiceImpl(courseDAO);
    }

    @Test
    void findAllService() {
        List<CourseDTO> all = courseService.findAll();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void deleteCourseById() throws SQLException {
        courseService.deleteCourseById(3);
        List<CourseDTO> all = courseDAO.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void save() {
        CourseDTO courseDto = new CourseDTO();
        courseService.save(new CourseDTO("pyton", 2));
        List<CourseDTO> all = courseDAO.findAll();
        assertThat(all.size()).isEqualTo(4);
        assertThat(all.get(all.size() - 1).getNameCourse()).isEqualTo("pyton");
    }

    @Test
    void findByIdService() {
        CourseDTO byId = courseService.findById(2);
        assertThat(byId.getNameCourse()).isEqualTo("Graphic");
    }

    @Test
    void update() {
        courseService.update(new CourseDTO(1, "Math4", 3));
        CourseDTO dtoById = courseDAO.findById(1);
        assertThat(dtoById.getNameCourse()).isEqualTo("Math4");
    }
}