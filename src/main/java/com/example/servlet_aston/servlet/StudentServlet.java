package com.example.servlet_aston.servlet;

import com.example.servlet_aston.DTO.StudentDTO;
import com.example.servlet_aston.service.StudentServiceImpl;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {

StudentServiceImpl service = new StudentServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<StudentDTO> list = service.findAll();
        PrintWriter printWriter = response.getWriter();
        printWriter.write("\n" + "Our Student" + "\n");
        printWriter.write(String.valueOf(list));

    }

    public void destroy() {
    }
}