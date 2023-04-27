package com.example.servlet_aston.train;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.DTO.TeacherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.stream.Collectors;

public class trainMain {

    public static void main(String[] args) throws JsonProcessingException {

        TeacherDTO teacherDTO = new TeacherDTO( "Tim", "Moris" );
                //StudentDTO studentDTO = new StudentDTO(2, "Toli", "Kolina", 23, "M");
        ObjectMapper mapper = new ObjectMapper(); //объект маппера json
        String json = "";
        json = mapper.writeValueAsString(teacherDTO);
        System.out.println(json);

//        StudentDTO studentDTO = new StudentDTO(2, "Toli", "Kolina", 23, "M");
//        ObjectMapper mapper = new ObjectMapper(); //объект маппера json
//        String json = "";
//        json = mapper.writeValueAsString(studentDTO);
//        System.out.println(json);

//        CourseDTO courseDTO = new CourseDTO(2,"Magic", 3);
//        ObjectMapper mapper = new ObjectMapper(); //объект маппера json
//        String json = "";
//        json = mapper.writeValueAsString(courseDTO);
//        System.out.println(json);
//
//        String json1 = "";
//        String json = "{\n" +
//                "    \"nameCourse\": \"Magic\", \n" +
//                "    \"teacherId\" :2\n" +
//                "    }";
//        courseDTO = mapper.convertValue(json, CourseDTO.class);
//        System.out.println(courseDTO);
    }
}