package com.example.servlet_aston.servlet;

import com.example.servlet_aston.DTO.TeacherDTO;
import com.example.servlet_aston.service.TeacherService;
import com.example.servlet_aston.service.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "teacher", urlPatterns = "/teacher")
public class TeacherServlet extends HttpServlet {

    TeacherService service= new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TeacherDTO> list = service.findAll();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("\n" + "Our Teacher" + "\n");
        printWriter.write(String.valueOf(list));

    }
}
