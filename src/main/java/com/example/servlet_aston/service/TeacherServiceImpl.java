package com.example.servlet_aston.service;

import com.example.servlet_aston.confic.DBConnection;
import com.example.servlet_aston.model.Course;
import com.example.servlet_aston.model.Student;
import com.example.servlet_aston.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements TeacherService{

    public static void main(String[] args) {

        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        List<Teacher> list = teacherService.findAll();
        for (Teacher s:list) {
            System.out.println(s);
        }}
//        System.out.println(teacherService.findById( 2));
////       Teacher teacher = new Teacher("Nion", "Ferrary");
////       teacherService.save(teacher);
//
////       int in = teacherService.deleteById(11);
////        System.out.println(in);
//        List<Teacher> list2 = teacherService.findAll();
//        for (Teacher s:list2) {
//            System.out.println(s);
//        }
//        teacherService.deleteById(11);
//        List<Teacher> list3 = teacherService.findAll();
//        for (Teacher s:list3) {
//            System.out.println(s);
//        }
//
//    }
    DBConnection connect = new DBConnection();
    Statement statement = connect.getDbConnection();

    @Override
    public Teacher findById(int id) {
     String FIND_BY_ID = "Select * from teacher where id=?";
     Teacher teacher = null;
     try(Connection connection = DBConnection.getDbConnectionOnly()){
         try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
             preparedStatement.setInt(1, id);
             ResultSet rs = preparedStatement.executeQuery();
             if(rs.next()){
             int id_teacher = rs.getInt(1);
             String name = rs.getString(2);
             String surname = rs.getString(3);
             teacher =new Teacher(id_teacher, name, surname);
            }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
        return teacher;
    }

    @Override
    public int deleteById(int id) {
        String DELETE_FROM_TRACHER = "Delete from teacher where id=?";
        try(Connection con = DBConnection.getDbConnectionOnly()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_FROM_TRACHER)) {
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void save(Teacher teacher) {
        String INSERT_TEACHER = "Insert into teacher(id, name, surname) values (?,?,?)";
        try(Connection connection = DBConnection.getDbConnectionOnly()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEACHER)){
                preparedStatement.setInt(1,teacher.getId());
                preparedStatement.setString(2,teacher.getName());
                preparedStatement.setString(3, teacher.getSurname());
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Teacher> findAll() {
        statement = connect.getDbConnection();
        String GET_ALL_TEACHER = "Select * from teacher";
        String str = null;
        List<Teacher>listStr = new ArrayList<>();
        List<String>stringList = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery(GET_ALL_TEACHER);
            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"));
                listStr.add(teacher);
            }
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        for (Teacher t: listStr) {
            str = t.getName() +" "+ t.getSurname();
            stringList.add(str);
        }

        return listStr;
    }

    @Override
    public Teacher getCourseTeacherById(int id) {
        Teacher teacher = new Teacher();
        List<Course> courseList = new ArrayList<>();
        String GET_ALL_COURSE_FOR_TEACHER = "Select t.id , t.name, t. surname,  c.id as course_id , c.name_course  from teacher  as t join course as c  on t.id = c.id_teacher where t.id =?";
        try(Connection connection = DBConnection.getDbConnectionOnly()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COURSE_FOR_TEACHER)){
                preparedStatement.setInt(1,id);
                ResultSet rs  = preparedStatement.executeQuery();
                while (rs.next()){
                    teacher.setId(rs.getInt("id"));
                    teacher.setName(rs.getString("name"));
                    teacher.setName(rs.getString("surname"));

                    Course course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setNameCourse(rs.getString("name_course"));
                    courseList.add(course);
                }
                teacher.setListCourse(courseList);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }
}
