package com.example.servlet_aston.servlet;

import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.service.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/student/*")
public class StudentServlet extends HttpServlet {

    private StudentServiceImpl service;

    @Override
    public void init() throws ServletException {
        this.service = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestPath = request.getRequestURI();
        request.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String[] url1 = requestPath.split("/");
        if (url1.length == 2 && url1[1].equals("student")) {
            List<StudentDTO> list = service.findAll();
            printWriter.write("\n" + "Our Student" + "\n");
            printWriter.write(list.toString());
        } else if (url1.length == 3) {
            StudentDTO studentDTO = service.findById(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(studentDTO));
        } else if (url1.length == 4 && url1[3].equals("withCourse")) {
            StudentDTO studentDTO = service.findAllCourseStudent(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(studentDTO));
        } else if (url1.length > 4) {
            String s = "Mistake";
            PrintWriter p = response.getWriter();
            p.write(s);
        }  //printWriter.write(String.valueOf(course));


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String[] url1 = requestPath.split("/");
        String idForDelete = url1[2];
        try {
            service.deleteById(Integer.parseInt(url1[2]));
        } catch (NumberFormatException e) {
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
        StudentDTO studentDTO = mapper.readValue(json, StudentDTO.class);
        service.save(studentDTO);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String json = req.getReader().lines().collect(Collectors.joining());

        StudentDTO courseDTO = mapper.readValue(json, StudentDTO.class);
        String[] parts = requestPath.split("/");
        String studentId = parts[2];
        courseDTO.setId(Integer.parseInt(studentId));
        service.update(courseDTO);
    }


    public void destroy() {
    }
}