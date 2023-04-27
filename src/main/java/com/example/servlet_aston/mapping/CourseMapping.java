package com.example.servlet_aston.mapping;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.Entity.Course;

public class CourseMapping {

    public static CourseDTO getCourseDTOFromCourse (Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setNameCourse(course.getNameCourse());
        courseDTO.setTeacherId(course.getTeacherId());
        return courseDTO;
    }

    public static  Course getCourseFromCourseDTO(CourseDTO courseDTO){
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setNameCourse(courseDTO.getNameCourse());
        course.setTeacherId(courseDTO.getTeacherId());
        return course;
    }
}
