package com.example.servlet_aston.servlet;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.service.CourseService;
import com.example.servlet_aston.service.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "course", urlPatterns = "/course/*")
public class CourseServlet extends HttpServlet {

    CourseService service = new CourseServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
//
//        String id = Parser.getId(req);
//        String pathVariable = Parser.getPathVariable(req);
//        PrintWriter printWriter = resp.getWriter();
//
//
//        if (id == null) {
//            List<Course> list = service.findAll();
//            printWriter.write(String.valueOf(list));
//
//        } else if (pathVariable != null && pathVariable.equals("with")) {
//            Course course = service.findCourseWithStudent(Integer.parseInt(id));
//            printWriter.write(String.valueOf(course));
//        } else {
//            Course course = service.findById(Integer.parseInt(id));
//            printWriter.write(String.valueOf(course));
//            //json = objectMapper.writeValueAsString(genre);
//        }
//
//        //printWriter.print(json);
//        resp.setStatus(HttpServletResponse.SC_OK);
//    }


//        String url = req.getRequestURI();
//        String[] url1 = url.split("/");
//        PrintWriter printWriter = resp.getWriter();
//        String idCourse = Parser.getId(req);
//        System.out.println(idCourse);
//        String courseWithStudent = Parser.getCourseWithStudent(req);
//        System.out.println(courseWithStudent);
//        String course = Parser.getCourse(req);
//        System.out.println(course);
//        String course1 = Parser.getCourse(req);
//
//        if (Parser.getLength(req)==2&course.equals("course")) {
//            List<Course> list = service.findAll();
//            printWriter.write("\n" + "Our Course" + "\n");
//            printWriter.write(String.valueOf(list));
//        } else if (Parser.getLength(req) == 3) {
//            Course course1 = service.findById(Integer.parseInt(idCourse));
//            printWriter.write(String.valueOf(course1));
//        } else if (Parser.getLength(req) == 4 && courseWithStudent.equals("withStudent")) {
//            Course course2 = service.findCourseWithStudent(Integer.parseInt(idCourse));
//            printWriter.write(String.valueOf(course2));
//        } else if (Parser.getLength(req) > 4) {
//            String s = "Mistake";
//            PrintWriter p = resp.getWriter();
//            p.write(s);
        // }


        String url = req.getRequestURI();
        String[] url1 = url.split("/");
        PrintWriter printWriter = resp.getWriter();

        if (url1.length == 2 && url1[1].equals("course")) {
            List<CourseDTO> list = service.findAll();
            printWriter.write("\n" + "Our Course" + "\n");
            printWriter.write(String.valueOf(list));
        } else if (url1.length == 3) {
            CourseDTO course = service.findById(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(course));
        } else if (url1.length == 4 && url1[3].equals("withStudent")) {
            CourseDTO course = service.findCourseWithStudent(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(course));
        } else if (url1.length > 4) {
            String s = "Mistake";
            PrintWriter p = resp.getWriter();
            p.write(s);
        }

    }

}

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String idCourse = Parser.getId(req);
//        service.deleteCourseById(Integer.parseInt(idCourse));
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.sendRedirect("/course");
////        String id = Parser.getId(req);
////        genreService.delete(Long.parseLong(id));
////        resp.setStatus(HttpServletResponse.SC_OK);
////        resp.sendRedirect("/genres");
//
   //}
//}






