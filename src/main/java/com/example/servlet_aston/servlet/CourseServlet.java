package com.example.servlet_aston.servlet;

import com.example.servlet_aston.DTO.CourseDTO;
import com.example.servlet_aston.service.CourseService;
import com.example.servlet_aston.service.CourseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/course/*")
public class CourseServlet extends HttpServlet {

    private CourseService service;

    public CourseServlet(CourseService service) {
        this.service = service;
    }

    @Override
    public void init() throws ServletException {
        this.service = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //resp.setContentType("application/json");
        String url = req.getRequestURI();
        resp.setCharacterEncoding("utf-8");
        PrintWriter printWriter = resp.getWriter();
        String[] url1 = url.split("/");

        if (url1.length == 2 && url1[1].equals("course")) {
            List<CourseDTO> list = service.findAll();
            printWriter.write("\n" + "Our Course" + "\n");
            printWriter.write(String.valueOf(list));
        } else if (url1.length == 3) {
            CourseDTO course = service.findById(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(course));
        } else if (url1.length == 4 && url1[3].equals("withStudent")) {
            System.out.println(url1.length+" "+ url1[3]);
            CourseDTO course = service.findCourseWithStudent(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(course));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String[] url1 = requestPath.split("/");
        String idForDelete = url1[2];
        try {
            service.deleteCourseById(Integer.parseInt(url1[2]));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = resp.getWriter();
        out.write("Delete " + idForDelete);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI(); //получаю урл
        req.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper(); //объект маппера json
        String json = req.getReader().lines().collect(Collectors.joining()); // вытаскиваю из тела запроса весь текст и собираю в единую строку
        CourseDTO course = mapper.readValue(json,CourseDTO.class);
        service.save(course);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String json = req.getReader().lines().collect(Collectors.joining());

        CourseDTO courseDTO = mapper.readValue(json, CourseDTO.class);
        String[] parts = requestPath.split("/");
        String courseId = parts[2];
        courseDTO.setId(Integer.parseInt(courseId));
        service.update(courseDTO);
    }

    public void destroy() {
    }

}






