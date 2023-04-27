package com.example.servlet_aston.servlet;

import com.example.servlet_aston.DTO.TeacherDTO;
import com.example.servlet_aston.service.TeacherServiceImpl;
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

@WebServlet(urlPatterns = "/teacher/*")
public class TeacherServlet extends HttpServlet {
    private TeacherServiceImpl service;

    @Override
    public void init()throws ServletException  {
        this.service = new TeacherServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        resp.setCharacterEncoding("utf-8");
        PrintWriter printWriter = resp.getWriter();
        String[] url1 = url.split("/");
        if (url1.length == 2 && url1[1].equals("teacher")) {
            List<TeacherDTO> list = service.findAll();
            printWriter.write("\n" + "Our Teacher" + "\n");
            printWriter.write(String.valueOf(list));
        } else if (url1.length == 3) {
            TeacherDTO teacherDTO = service.findById(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(teacherDTO));
        } else if (url1.length == 4 && url1[3].equals("courseHis")) {
           TeacherDTO teacherDTO = service.getCourseTeacherById(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(teacherDTO));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String[] url1 = requestPath .split("/");
        String idForDelete = url1[2];
        try {
            service.deleteById(Integer.parseInt(url1[2]));
        } finally {
            System.out.println("Не удлось удалить");
        }
        //PrintWriter writer = resp.getWriter();
        printWriter.write("Delete " + idForDelete);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String json = req.getReader().lines().collect(Collectors.joining());

        TeacherDTO teacherDTO = mapper.readValue(json, TeacherDTO.class);
        String[] parts = requestPath.split("/");
        String teacherID = parts[2];
        teacherDTO.setId(Integer.parseInt(teacherID));
        service.update(teacherDTO);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI(); //получаю урл
        req.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper(); //объект маппера json
        String json = req.getReader().lines().collect(Collectors.joining()); // вытаскиваю из тела запроса весь текст и собираю в единую строку
        TeacherDTO teacherDTO = mapper.readValue(json, TeacherDTO.class);
        service.save(teacherDTO);
    }  }