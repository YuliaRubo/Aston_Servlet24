package com.example.servlet_aston.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "studentById", urlPatterns = "/student_id/*")
public class GetStudentByIdServlet extends HttpServlet {
}
