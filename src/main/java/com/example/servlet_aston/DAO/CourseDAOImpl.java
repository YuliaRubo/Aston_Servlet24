package com.example.servlet_aston.DAO;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.Entity.Course;
import com.example.servlet_aston.Entity.Student;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.mapping.CourseMapping;
import com.example.servlet_aston.mapping.StudentMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    public static void main(String[] args) throws SQLException {
        CourseDAOImpl courseDAO = new CourseDAOImpl(new DBConfig());
//
//        courseDAO.update(new Course(4, "Ibnn", 5));
//        courseDAO.deleteCourseById(9);
//
//
//
//        List<CourseDTO>list = courseDAO.findAll();
//        for (CourseDTO d: list){
//            System.out.println(d);
//        }
//
        CourseDTO courseDTO = courseDAO.findCourseWithStudent(4);
        System.out.println(courseDTO);
////
//        //courseDAO.save(new Course("ggjfifj", 3));
//        //3,Popullar,Liliop
//        List<CourseDTO> list = courseDAO.findAll();
//        for (CourseDTO d : list) {
//            System.out.println(d);
//        }

//        CourseDTO courseDTO1 = courseDAO.findCourseWithStudent(2);
//        System.out.println(courseDTO1);
    }

    private DBConfig config;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public CourseDAOImpl(DBConfig config) {
        this.config = config;
    }

    public static final String FIND_BY_ID = "Select id, name_course, id_teacher from course where id = ?";
    public static final String INSERT_COURSE = "Insert into course(name_course, id_teacher) values (?,?)";
    public static final String UPDATE_COURSE = "UPDATE course  set name_course=?, id_teacher=? where id = ?";

    public static final String sqlUpdate = "DELETE from  student_course where id_course = ?";
    public static final String DELETE_COURSE = "Delete from course where id =?";
    public static final String GET_ALL_COURSE = "select id, name_course, id_teacher  from course";
    public static final String GET_CURSE_WITH_STUDENT_BY_ID = "select s.id as student_id, s.name, s.surname, s.age, s.gender, c.id as course_id, c.name_course from course as c join student_course as sc on c.id = sc.id_course join student s on s.id = sc.id_student where id_course =?";

    @Override
    public CourseDTO findById(int id) {

        CourseDTO courseDTO = new CourseDTO();
        Connection con = config.getConnection();
        Course course = new Course();
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //int id_course = rs.getInt(1);
                int id_course = Integer.parseInt(rs.getString("id"));
                //String name = rs.getString(2);
                String name = rs.getString("name_course");
                int idTeacher = Integer.parseInt(rs.getString("id_teacher"));
                course = new Course(id_course, name, idTeacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseDTO = CourseMapping.getCourseDTOFromCourse(course);
        return courseDTO;
    }

    @Override
    public void deleteCourseById(int id) throws SQLException {
        Connection connection = config.getConnection();
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(sqlUpdate);
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            System.out.println("SQLException during close(): ");
        }
    }

    @Override
    public void save(Course course) {
        //"Insert into course(name_course, id_teacher) values (?,?)";
        Connection connection = config.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE)) {
            preparedStatement.setString(1, course.getNameCourse());
            preparedStatement.setInt(2, course.getTeacherId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Course course) {
        Connection connection = config.getConnection();
        //"UPDATE course  set name_course=?, id_teacher=? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE)) {
            preparedStatement.setInt(3, course.getId());
            preparedStatement.setString(1, course.getNameCourse());
            preparedStatement.setInt(2, course.getTeacherId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<CourseDTO> findAll() {
        Connection connection = config.getConnection();
        List<Course> courseList = new ArrayList<>();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        CourseDTO courseDTO = new CourseDTO();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_COURSE);
            while (rs.next()) {
                Course course = new Course(rs.getInt("id"),
                        rs.getString("name_course"),
                        rs.getInt("id_teacher"));
                courseList.add(course);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Course c : courseList) {
            courseDTO = CourseMapping.getCourseDTOFromCourse(c);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    @Override
    public CourseDTO findCourseWithStudent(int id) {
        CourseDTO courseDTO = new CourseDTO();
        Course course = new Course();
        List<Student> list1 = new ArrayList<>();
        List<StudentDTO> listDto = new ArrayList<>();

        //String GET_CURSE_WITH_STUDENT_BY_ID = "select s.id as student_id, s.name, s.surname, s.age, s.gender, c.id as course_id, c.name_course from course as c join student_course as sc on c.id = sc.id_course join student s on s.id = sc.id_student where id_course =?";
        Connection connection = config.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CURSE_WITH_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                course.setId(rs.getInt("course_id"));
                course.setNameCourse(rs.getString("name_course"));

                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                list1.add(student);


                for (Student s : list1) {
                    StudentDTO studentDTO = StudentMapping.getStudentDTOFromStudent(s);
                    listDto.add(studentDTO);
                }
                courseDTO = CourseMapping.getCourseDTOFromCourse(course);
                courseDTO.setListStudent(listDto);


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return courseDTO;
    }


}
