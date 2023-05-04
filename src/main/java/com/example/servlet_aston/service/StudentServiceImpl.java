package com.example.servlet_aston.service;

import com.example.servlet_aston.DAO.StudentDAOImpl;
import com.example.servlet_aston.Entity.Student;
import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.mapping.StudentMapping;

import java.util.List;

public class StudentServiceImpl implements StudentService {

//    public static void main(String[] args) {
//        StudentServiceImpl service = new StudentServiceImpl();
//
//        service.save(new StudentDTO("B", "F", 23, "F"));
//        List<StudentDTO>list = service.findAll();
//        for (StudentDTO s:list){
//            System.out.println(s);
//        }

//
//        StudentDTO studentDTO = service.findById(2);
//        System.out.println(studentDTO);
//
//        StudentDTO studentDTO1 = service.findAllCourseStudent(2);
//        System.out.println(studentDTO1);
  //}


    private StudentDAOImpl studentDAO;

    public StudentServiceImpl() {
        this.studentDAO = new StudentDAOImpl(new DBConfig());
    }

    public StudentServiceImpl(StudentDAOImpl studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public StudentDTO findById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        studentDAO.deleteById(id);
    }

    @Override
    public int save(StudentDTO student) {
        Student student1 = new Student();
        student1 = StudentMapping.getStudentFromStudentDTO(student);
        return studentDAO.save(student1);
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public StudentDTO findAllCourseStudent(int id) {
        return studentDAO.findAllCourseStudent(id);
    }

    @Override
    public void update(StudentDTO student) {
        Student student1 = new Student();
        student1 = StudentMapping.getStudentFromStudentDTO(student);
        studentDAO.update(student1);
    }
}

