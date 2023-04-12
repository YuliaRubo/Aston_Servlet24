package com.example.servlet_aston.service;

import com.example.servlet_aston.confic.DBConnection;
import com.example.servlet_aston.model.Course;
import com.example.servlet_aston.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    public static void main(String[] args) {
      CourseServiceImpl courseService = new CourseServiceImpl();
     // Course course1 = new Course("N");
      //courseService.save(course1);
      //courseService.deleteById(7);

      List<Course> list = courseService.findAll();
        for (Course c: list) {
            System.out.println(c);
        }
        //System.out.println(courseService.findById(7));
      Course c = courseService.findCourseWithStudent(4);
        System.out.println(c);


    }

    DBConnection connect = new DBConnection();
    Statement statement = connect.getDbConnection();

    @Override
    public Course findById(int id) {
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
        return course;
    }

    @Override
    public void deleteById(int id) {
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
        public List<Course> findAll() {
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
        return courseList;
    }


//                    List<String>students = new ArrayList<>();
//                    Student student = null;
//                    String GET_CURSE_WITH_STUDENT_BY_ID = "select s.name, s.surname from course as c join student_course as sc" +
//                            "on c.id = sc.id_course join student s on s.id = sc.id_student where id_course=?";
//                    try(Connection connection = DBConnection.getDbConnectionOnly()){
//                        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_CURSE_WITH_STUDENT_BY_ID)){
//                            ResultSet resultSet = preparedStatement.executeQuery();
//                            if(resultSet.next()){
//                                String name= resultSet.getString(2);
//                                String surname = resultSet.getString(3);
//                                String info = name + " " + surname + " ";
//                                students.add(info);
                           // }

//
//                        }
//                    }

//                for (Course c: courseList){
//
//                    List<Student> list = c.getListStudent();
//                    Student student = new Student(45, "Tillr" , "Fififi", 34, "M");
//                    list.add(student);
//





    @Override
    public Course findCourseWithStudent(int id) {
        Course course = new Course();
        List<Student> list1 = new ArrayList<>();

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
                    course.setListStudent(list1);
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return course;
    }
}

