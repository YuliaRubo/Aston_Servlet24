package com.example.servlet_aston.config;

import javax.servlet.http.HttpServletRequest;

public class Parser {

        public static String getId(HttpServletRequest request) {
            String pathInfo = request.getPathInfo();
            if (pathInfo == null) {
                return null;
            }
            String[] pathPasts = pathInfo.split("/");
            return pathPasts[1];
        }
        public static  String getPathVariable(HttpServletRequest request) {
            String pathInfo = request.getPathInfo();
            if (pathInfo == null) {
                return null;
            }
            String[] pathPasts = pathInfo.split("/");
            return pathPasts.length < 3 ? null : pathPasts[2];
        }

    }

//    public static int getLength (javax.servlet.http.HttpServletRequest req){
//        String url = req.getRequestURI();
//        String[] url1 = url.split("/");
//        return url1.length;
//    }
//
//    public static String getCourse(javax.servlet.http.HttpServletRequest req) {
//        String url = req.getRequestURI();
//        String[] url1 = url.split("/");
//        String course = url1[1];
//        if(getLength(req)==2) ;
//            return course;
//
//    }
//
//    public static String getId(javax.servlet.http.HttpServletRequest req){
//        String url = req.getRequestURI();
//        String[] url1 = url.split("/");
//        return url1[2];
//    }
//
//    public static String getCourseWithStudent(javax.servlet.http.HttpServletRequest req){
//        String url = req.getRequestURI();
//        String[] url1 = url.split("/");
//
//        return url1[3];
//    }



