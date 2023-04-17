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

public class StudentServiceImpl implements StudentService {
//    public static void main(String[] args) {
//        StudentServiceImpl service = new StudentServiceImpl();
//        Student student = service.findById(5);
//        System.out.println(student);
//        Student student1= new Student("Lily", "Sorr", 18, "F");
//        service.save(student1);
//        List<StudentDTO> list = service.findAll();
//        for (StudentDTO st : list) {
//            System.out.println(st);
//        }
//        StudentDTO student = service.findAllCourseStudent(1);
//        System.out.println(student);
//
//    }
//        service.deleteById(8);
//        List<Student>list1 = service.findAll();
//        for (Student st: list1){
//            System.out.println(st);
//        }
//    }
        DBConnection connect = new DBConnection();
        Statement statement = connect.getDbConnection();


        @Override
        public StudentDTO findById (int id){
            StudentDTO student = null;
            String GET_STUDENT_BY_ID = "Select * from student where id = ?";
            System.out.println(GET_STUDENT_BY_ID + " " + "zapros");
            try (Connection con = DBConnection.getDbConnectionOnly()) {
                try (PreparedStatement preparedStatement = con.prepareStatement(GET_STUDENT_BY_ID)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int idStudent = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String surname = resultSet.getString(3);
                        int age = resultSet.getInt(4);
                        String gender = resultSet.getString(5);
                        student = new StudentDTO(idStudent, name, surname, age, gender);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return student;
        }


        @Override
        public int deleteById ( int id){
            String DELETE_FROM_STUDENT = "Delete from student where id=?";
            try (Connection con = DBConnection.getDbConnectionOnly()) {
                try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_FROM_STUDENT)) {
                    preparedStatement.setInt(1, id);
                    return preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        public int save (Student student){
            String INSERT_IN_STUDENT = "Insert into student (id, name, surname, age, gender) values (?, ? , ?, ?, ?)";
            System.out.println(INSERT_IN_STUDENT + " " + "zapros insert");
            try (Connection con = DBConnection.getDbConnectionOnly()) {
                try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_IN_STUDENT)) {
                    preparedStatement.setInt(1, student.getId());
                    preparedStatement.setString(2, student.getName());
                    preparedStatement.setString(3, student.getSurname());
                    preparedStatement.setInt(4, student.getAge());
                    preparedStatement.setString(5, student.getGender());
                    return preparedStatement.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        public List<StudentDTO> findAll () {
            statement = connect.getDbConnection();
            String GET_ALL_STUDENT = "Select * from student";
            StudentDTO studentDTO = new StudentDTO();

            List<Student> listStr = new ArrayList<>();
            List<StudentDTO>studentDTOList = new ArrayList<>();

            try {
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

            for (Student d: listStr){
                studentDTO = StudentMapping.getStudentDTOFromStudent(d);
                studentDTOList.add(studentDTO);

            }
            return studentDTOList;
        }

        @Override
        public StudentDTO findAllCourseStudent (int id){
            StudentDTO studentDTO = new StudentDTO();
            List<Course>courseList = new ArrayList<>();
            List<CourseDTO> list1 = new ArrayList<>();
            Student student = new Student();
            CourseDTO courseDTO = new CourseDTO();

            String GET_STUDENT_WITH_COURSE_BY_ID = "select s.id as student_id, s.name, s.surname, s.age, s.gender, c.id as course_id, c.name_course from course as c join student_course as sc on c.id = sc.id_course join student s on s.id = sc.id_student where id_student =?";
            try (Connection connection = DBConnection.getDbConnectionOnly()) {
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
                    for (Course c:courseList){
                        courseDTO = CourseMapping.getCourseDTOFromCourse(c);
                        list1.add(courseDTO);
                    }
                    studentDTO = StudentMapping.getStudentDTOFromStudent(student);
                    studentDTO.setCourseList(list1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return studentDTO;
        }
    }


