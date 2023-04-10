package com.example.servlet_aston.servlet;

import com.example.servlet_aston.model.Course;
import com.example.servlet_aston.model.Student;
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

@WebServlet(name = "course", urlPatterns = "/course")
public class CourseServlet extends HttpServlet {

    CourseService service = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> list = service.findAll();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("\n" + "Our Course" + "\n");
        printWriter.write(String.valueOf(list));
    }
}
