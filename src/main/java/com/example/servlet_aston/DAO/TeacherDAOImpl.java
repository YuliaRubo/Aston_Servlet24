package com.example.servlet_aston.DAO;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.DTO.TeacherDTO;
import com.example.servlet_aston.Entity.Course;
import com.example.servlet_aston.Entity.Teacher;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.mapping.CourseMapping;
import com.example.servlet_aston.mapping.TeacherMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {

//    public static void main(String[] args) {
//        TeacherDAOImpl teacherDAO = new TeacherDAOImpl(new DBConfig());
//        TeacherDTO teacherDTO = teacherDAO.getCourseTeacherById(3);
//        System.out.println(teacherDTO);
//    }
//
//        List<TeacherDTO> list = teacherDAO.findAll();
//        for (TeacherDTO t : list) {
//            System.out.println(t);
//        }
//        System.out.println("id");
//        TeacherDTO teacherDTO = teacherDAO.findById(5);
//        System.out.println(teacherDTO);
//
//        System.out.println(teacherDAO.getCourseTeacherById(3));
//
//       teacherDAO.save(new Teacher("Mila", "Bisivich"));
//        teacherDAO.update(new Teacher(3,"Popullar", "Liliop"));
//
//
//        teacherDAO.deleteById(4);
//        List<TeacherDTO> list2 = teacherDAO.findAll();
//        for (TeacherDTO t : list2) {
//            System.out.println(t);
//        }
   // }
    private DBConfig config;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public TeacherDAOImpl(DBConfig config) {
        this.config = config;
    }

    public static final String FIND_BY_ID = "Select id, name, surname from teacher where id=?";
    public static final String DELETE_FROM_TRACHER = "Delete from teacher where id=?";
    public static final String INSERT_TEACHER = "Insert into teacher(name, surname) values (?,?)";
    public static final String GET_ALL_TEACHER = "Select id, name, surname from teacher";
    public static final String GET_ALL_COURSE_FOR_TEACHER = "Select t.id as t_id, t.name, t. surname,  c.id as c_id , c.name_course  from teacher  as t join course as c  on t.id = c.id_teacher where t.id =?";
    public static final String UPDATE_TEACHER = "UPDATE teacher SET name = ?, surname = ?  WHERE id = ?;";

    public static final String sqlUpdate = "UPDATE course SET id_teacher = NULL WHERE id_teacher = ?;";

    @Override
    public TeacherDTO findById(int id) {
        TeacherDTO teacherDTO = new TeacherDTO();
        Connection connection = config.getConnection();
        Teacher teacher = new Teacher();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    int id_teacher = rs.getInt(1);
                    String name = rs.getString(2);
                    String surname = rs.getString(3);
                    teacher = new Teacher(id_teacher, name, surname);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        teacherDTO = TeacherMapping.getTeacherDTOFromTeacher(teacher);
        return teacherDTO;
    }

    @Override
    public void deleteById(int id) {
        Connection con = config.getConnection();

        try (PreparedStatement preparedStatement1 = con.prepareStatement(sqlUpdate)){
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();

            PreparedStatement preparedStatement = con.prepareStatement(DELETE_FROM_TRACHER);            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(Teacher teacher) {
        Connection connection = config.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEACHER)) {
                //preparedStatement.setInt(1, teacher.getId());
                preparedStatement.setString(1, teacher.getName());
                preparedStatement.setString(2, teacher.getSurname());
                preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Teacher teacher) {
        Connection connection = config.getConnection();
//"UPDATE teacher SET name = ?, surname = ?  WHERE id = ?;"
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEACHER)) {
                preparedStatement.setInt(3, teacher.getId());
                preparedStatement.setString(1, teacher.getName());
                preparedStatement.setString(2, teacher.getSurname());
                preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public List<TeacherDTO> findAll() {
        Connection connection  = config.getConnection();
        TeacherDTO teacherDTO = new TeacherDTO();
        List<Teacher> listStr = new ArrayList<>();
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_TEACHER);
            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"));
                listStr.add(teacher);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Teacher t : listStr) {
            teacherDTO = TeacherMapping.getTeacherDTOFromTeacher(t);
            teacherDTOList.add(teacherDTO);
        }

        return teacherDTOList;
    }

    @Override
    public TeacherDTO getCourseTeacherById(int id) {
        Teacher teacher = new Teacher();
        TeacherDTO teacherDTO = new TeacherDTO();
        CourseDTO courseDTO = new CourseDTO();
        List<Course> courseList = new ArrayList<>();
        List<CourseDTO> courseListDTO = new ArrayList<>();
        Connection connection = config.getConnection();


            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COURSE_FOR_TEACHER)) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    teacher.setId(rs.getInt("t_id"));
                    teacher.setName(rs.getString("name"));
                    teacher.setSurname(rs.getString("surname"));

                    Course course = new Course();
                    course.setId(rs.getInt("c_id"));
                    course.setNameCourse(rs.getString("name_course"));
                    courseList.add(course);
                }

                for (Course c : courseList) {
                    courseDTO = CourseMapping.getCourseDTOFromCourse(c);
                    courseListDTO.add(courseDTO);
                }
                teacherDTO = TeacherMapping.getTeacherDTOFromTeacher(teacher);
                teacherDTO.setListCourse(courseListDTO);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherDTO;
    }
}

