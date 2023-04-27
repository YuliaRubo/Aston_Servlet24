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

public class StudentDAOImpl implements StudentDAO {

//    public static void main(String[] args) {
//        StudentDAOImpl studentDAO = new StudentDAOImpl(new DBConfig());
//
//        StudentDTO studentDTO = studentDAO.findById(1);
//        System.out.println(studentDTO);
//
//        studentDAO.update(new Student(1, "Uliut", "Flinera", 23, "F"));
//        //1,Ylya,Green,23,F
//        StudentDTO studentDTO1 = studentDAO.findById(1);
//        System.out.println(studentDTO1);
//
//
//        System.out.println(studentDAO.findAllCourseStudent(2));
//    }

    private DBConfig config;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public StudentDAOImpl(DBConfig config) {
        this.config = config;
    }

    public static final String GET_STUDENT_BY_ID = "Select id, name, surname, age, gender from student where id = ?";
    public static final String sqlUpdate = "DELETE from  student_course where id_student = ?";
    public static final String DELETE_FROM_STUDENT = "Delete from student where id=?";
    public static final String INSERT_IN_STUDENT = "Insert into student (id, name, surname, age, gender) values (?, ? , ?, ?, ?)";
    public static final String GET_ALL_STUDENT = "Select id, name, surname, age, gender from student";
    public static final String GET_STUDENT_WITH_COURSE_BY_ID = "select s.id as student_id, s.name, s.surname, s.age, s.gender, c.id as course_id, c.name_course from course as c join student_course as sc on c.id = sc.id_course join student s on s.id = sc.id_student where id_student =?";
    public static final String UPDATE_STUDENT = "UPDATE student set name=?, surname =?, age =?, gender = ? where id=?";

    @Override
    public StudentDTO findById(int id) {
        StudentDTO studentDTO = new StudentDTO();
        Connection con = config.getConnection();
        System.out.println(GET_STUDENT_BY_ID + " " + "zapros");
        Student student = new Student();

        try (PreparedStatement preparedStatement = con.prepareStatement(GET_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idStudent = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String gender = resultSet.getString(5);
                student = new Student(idStudent, name, surname, age, gender);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentDTO = StudentMapping.getStudentDTOFromStudent(student);
        return studentDTO;
    }

    @Override
    public void deleteById(int id) {
        Connection connection = config.getConnection();
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(sqlUpdate);
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_STUDENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            System.out.println("SQLException during close(): ");
        }
    }

    @Override
    public int save(Student student) {
        System.out.println(INSERT_IN_STUDENT + " " + "zapros insert");
        Connection con = config.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_IN_STUDENT)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSurname());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setString(5, student.getGender());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void update(Student student) {

        // "UPDATE student set name=?, surname =?, age =?, gender = ? where id=?
        Connection con = config.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setInt(5, student.getId());
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGender());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<StudentDTO> findAll() {
        Connection connection = config.getConnection();

        StudentDTO studentDTO = new StudentDTO();

        List<Student> listStr = new ArrayList<>();
        List<StudentDTO> studentDTOList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_STUDENT);
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        rs.getString("gender"));
                listStr.add(student);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Student d : listStr) {
            studentDTO = StudentMapping.getStudentDTOFromStudent(d);
            studentDTOList.add(studentDTO);

        }
        return studentDTOList;
    }

    @Override
    public StudentDTO findAllCourseStudent(int id) {
        StudentDTO studentDTO = new StudentDTO();
        List<Course> courseList = new ArrayList<>();
        List<CourseDTO> list1 = new ArrayList<>();
        Student student = new Student();
        CourseDTO courseDTO = new CourseDTO();
        Connection connection = config.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_WITH_COURSE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));

                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setNameCourse(rs.getString("name_course"));
                courseList.add(course);
            }
            for (Course c : courseList) {
                courseDTO = CourseMapping.getCourseDTOFromCourse(c);
                list1.add(courseDTO);
            }
            studentDTO = StudentMapping.getStudentDTOFromStudent(student);
            studentDTO.setCourseList(list1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return studentDTO;
    }


}

