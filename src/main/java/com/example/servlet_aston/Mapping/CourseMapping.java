package com.example.servlet_aston.Mapping;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.Entity.Course;

public class CourseMapping {

    public static CourseDTO getCourseDTOFromCourse (Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setNameCourse(course.getNameCourse());
        return courseDTO;
    }

    public static  Course getCourseFromCourseDTO(CourseDTO courseDTO){
        Course course = new Course();
        courseDTO.setId(course.getId());
        courseDTO.setNameCourse(course.getNameCourse());
        return course;
    }
}
