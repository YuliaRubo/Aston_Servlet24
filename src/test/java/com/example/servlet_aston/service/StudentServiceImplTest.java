package com.example.servlet_aston.service;

import com.example.servlet_aston.DAO.StudentDAOImpl;
import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.config.DBConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudentServiceImplTest {
    private static StudentService studentService;
    private static StudentDAOImpl studentDAO;

    @BeforeEach
    public void beforeEach() throws SQLException, IOException {
        Connection connection = new DBConfig("test").getConnection();
        DBConfig.initForTest(connection);
        studentDAO = new StudentDAOImpl(new DBConfig("test"));
        studentService = new StudentServiceImpl(studentDAO);
    }

    @Test
    void findAllService() {
        List<StudentDTO> all = studentService.findAll();
        assertThat(all.size()).isEqualTo(5);
    }

    @Test
    void deleteById() {
        studentService.deleteById(3);
        List<StudentDTO> all = studentDAO.findAll();
        assertThat(all.size()).isEqualTo(4);
    }

    @Test
    void save() {
        StudentDTO studentDto = new StudentDTO();
        int save = studentService.save(new StudentDTO("Mary", "Groovy", 26, "F"));
        List<StudentDTO> all = studentDAO.findAll();
        assertThat(all.size()).isEqualTo(6);
        assertThat(all.get(all.size() - 1).getSurname()).isEqualTo("Groovy");
    }

    @Test
    void findById() {
        StudentDTO byId = studentService.findById(2);
        assertThat(byId.getSurname()).isEqualTo("Green");
    }
    @Test
    void update() {
        studentService.update(new StudentDTO(1, "OneLy-ly", "Two", 45, "F"));
        StudentDTO dtoById = studentDAO.findById(1);
        assertThat(dtoById.getName()).isEqualTo("OneLy-ly");
    }
}