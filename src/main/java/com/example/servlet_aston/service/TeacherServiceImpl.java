package com.example.servlet_aston.service;

import com.example.servlet_aston.DAO.TeacherDAOImpl;
import com.example.servlet_aston.DTO.TeacherDTO;
import com.example.servlet_aston.Entity.Teacher;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.mapping.TeacherMapping;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

//    public static void main(String[] args) {
//        TeacherServiceImpl teacherService = new TeacherServiceImpl();
////        System.out.println(teacherService.findById(5));
//        //   teacherService.findAll();
//        List<TeacherDTO> listD = teacherService.findAll();
//
//        for (TeacherDTO t : listD) {
//            System.out.println(t);
////
//////        }
//////        TeacherDTO t = teacherService.getCourseTeacherById(3);
//////        System.out.println(t);
//}
//  }

    private TeacherDAOImpl teacherDAO;

    public TeacherServiceImpl() {
        this.teacherDAO = new TeacherDAOImpl(new DBConfig());
    }

    public TeacherServiceImpl(TeacherDAOImpl teacherDAO) {
        this.teacherDAO = teacherDAO;
    }


    @Override
    public TeacherDTO findById(int id) {
        return teacherDAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        teacherDAO.deleteById(id);
    }

    @Override
    public void save(TeacherDTO teacher) {
        Teacher teacher1 = new Teacher();
        teacher1=TeacherMapping.getTeacherFromTeacherDTO(teacher);
        teacherDAO.save(teacher1);
    }

    @Override
    public List<TeacherDTO> findAll() {
     return teacherDAO.findAll();
    }

    @Override
    public TeacherDTO getCourseTeacherById(int id) {
        return teacherDAO.getCourseTeacherById(id);
    }

    @Override
    public void update(TeacherDTO teacher) {
        Teacher teacher1 = new Teacher();
        teacher1=TeacherMapping.getTeacherFromTeacherDTO(teacher);
        teacherDAO.update(teacher1);
    }
}