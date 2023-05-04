package com.example.servlet_aston.service;

import com.example.servlet_aston.DAO.TeacherDAOImpl;
import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.DTO.TeacherDTO;
import com.example.servlet_aston.config.DBConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TeacherServiceImplTest {
    private static TeacherService teacherService;
    private static TeacherDAOImpl teacherDAO;

    @BeforeEach
    public void beforeEach() throws SQLException, IOException {
        Connection connection = new DBConfig("test").getConnection();
        DBConfig.initForTest(connection);
        teacherDAO = new TeacherDAOImpl(new DBConfig("test"));
        teacherService = new TeacherServiceImpl(teacherDAO);
    }

    @Test
    void findById() {
        TeacherDTO byId =teacherService.findById(2);
        assertThat(byId.getSurname()).isEqualTo("Vesta");
    }

    @Test
    void deleteById() {
        teacherService.deleteById(3);
        List<TeacherDTO> all = teacherDAO.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void save() {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherService.save(new TeacherDTO("Kira", "Topol"));
        List<TeacherDTO> all = teacherDAO.findAll();
        assertThat(all.size()).isEqualTo(4);
        assertThat(all.get(all.size() - 1).getSurname()).isEqualTo("Topol");
    }

    @Test
    void findAll() {
        List<TeacherDTO> all = teacherService.findAll();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void update() {
        teacherService.update(new TeacherDTO(1, "Oliviya", "Lion"));
        TeacherDTO dtoById = teacherDAO.findById(1);
        assertThat(dtoById.getName()).isEqualTo("Oliviya");
    }
}