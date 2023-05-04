package com.example.servlet_aston.servlet;

import com.example.servlet_aston.DAO.CourseDAO;
import com.example.servlet_aston.DAO.CourseDAOImpl;
import com.example.servlet_aston.config.DBConfig;
import com.example.servlet_aston.service.CourseService;
import com.example.servlet_aston.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CourseServletTest {

    private CourseServlet courseServlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private PrintWriter printWriter;
    private StringWriter stringWriter;


    @BeforeEach
    public void beforeEach() {
        CourseDAOImpl courseDAO = new CourseDAOImpl(new DBConfig("test"));
        CourseService courseService = new CourseServiceImpl(courseDAO);
        courseServlet = new CourseServlet(courseService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

//    @Test
//    public void getAllcourse() throws IOException, ServletException, javax.servlet.ServletException {
//        when(request.getPathInfo()).thenReturn(null);
//        when(response.getWriter()).thenReturn(printWriter);
//        courseServlet.doGet(request,response);
//
//        verify(request, times(2)).getPathInfo();
//        verify(response, times(1)).getWriter();
//        assertThat(stringWriter.toString()).contains("Math");
//    }


//    @Test
//    public void getById() throws IOException, ServletException {
//        when(request.getPathInfo()).thenReturn("genres/2");
//        when(response.getWriter()).thenReturn(printWriter);
//        courseServlet.doGet(request, response);
//
//        assertThat(stringWriter.toString()).contains("Graphic");
//    }



}