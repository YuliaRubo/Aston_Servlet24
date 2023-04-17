package com.example.servlet_aston.service;

import com.example.servlet_aston.Entity.Course;
import com.example.servlet_aston.Entity.Student;
import com.example.servlet_aston.Mapping.CourseMapping;
import com.example.servlet_aston.Mapping.StudentMapping;
import com.example.servlet_aston.confic.DBConnection;
import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.DTO.StudentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    public static void main(String[] args) {
      CourseServiceImpl courseService = new CourseServiceImpl();
      Course course1 = new Course();
      courseService.save(course1);
      //courseService.deleteById(7);

      List<CourseDTO> list = courseService.findAll();
        for (CourseDTO c: list) {
            System.out.println(c);
        }
        System.out.println(courseService.findById(7));
      CourseDTO c = courseService.findCourseWithStudent(4);
        System.out.println(c);


    }

    DBConnection connect = new DBConnection();
    Statement statement = connect.getDbConnection();

    @Override
    public CourseDTO findById(int id) {
        String FIND_BY_ID = "Select * from course where id = ?";
        Course course = null;
        try (Connection con = DBConnection.getDbConnectionOnly()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(FIND_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    int id_course = rs.getInt(1);
                    String name = rs.getString(2);
                    course = new Course(id_course, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CourseDTO courseDTO = CourseMapping.getCourseDTOFromCourse(course);
        return courseDTO;
    }

    @Override
    public void deleteCourseById(int id) {


        String DELETE_COURSE = "Delete from course where id =?";
        try (Connection connection = DBConnection.getDbConnectionOnly()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Course course) {
        String INSERT_COURSE = "Insert into course(id, name_course) values (?,?)";
        try(Connection connection = DBConnection.getDbConnectionOnly()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE)){
                preparedStatement.setInt(1,course.getId());
                preparedStatement.setString(2,course.getNameCourse());
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
        public List<CourseDTO> findAll() {
            String GET_ALL_COURSE = "select * from course";
            List<Course> courseList = new ArrayList<>();
            try {
                ResultSet rs = statement.executeQuery(GET_ALL_COURSE);
                while (rs.next()) {
                    Course course = new Course(rs.getInt("id"),
                            rs.getString("name_course"));
                    courseList.add(course);
                }
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<CourseDTO> courseDTOList = new ArrayList<>();
            for(Course c:courseList){
                CourseDTO courseDTO = CourseMapping.getCourseDTOFromCourse(c);
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

        String GET_CURSE_WITH_STUDENT_BY_ID = "select s.id as student_id, s.name, s.surname, s.age, s.gender, c.id as course_id, c.name_course from course as c join student_course as sc on c.id = sc.id_course join student s on s.id = sc.id_student where id_course =?";
        try (Connection connection = DBConnection.getDbConnectionOnly()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CURSE_WITH_STUDENT_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    course.setId(rs.getInt("course_id"));
                    course.setNameCourse(rs.getString("name_course"));

                    Student student = new Student();
                    student.setId(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setAge(rs.getInt("age"));
                    student.setGender(rs.getString("gender"));
                    list1.add(student);
                }

                for(Student s:list1){
                    StudentDTO studentDTO = StudentMapping.getStudentDTOFromStudent(s);
                    listDto.add(studentDTO);
                }
                courseDTO = CourseMapping.getCourseDTOFromCourse(course);
                courseDTO.setListStudent(listDto);

                    //course.setListStudent(list1);
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return courseDTO;
    }
}

