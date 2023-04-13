package com.example.servlet_aston.servlet;

import com.example.servlet_aston.confic.Parser;
import com.example.servlet_aston.model.Course;
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

        String url = req.getRequestURI();
        String[] url1 = url.split("/");
        PrintWriter printWriter = resp.getWriter();

        if (url1.length == 2 && url1[1].equals("course")) {
            List<Course> list = service.findAll();
            printWriter.write("\n" + "Our Course" + "\n");
            printWriter.write(String.valueOf(list));
        } else if (url1.length == 3) {
            Course course = service.findById(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(course));
        } else if (url1.length == 4 && url1[3].equals("withStudent")) {
            Course course = service.findCourseWithStudent(Integer.parseInt(url1[2]));
            printWriter.write(String.valueOf(course));
        } else if (url1.length > 4) {
            String s = "Mistake";
            PrintWriter p = resp.getWriter();
            p.write(s);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}






