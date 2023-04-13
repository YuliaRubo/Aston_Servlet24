package com.example.servlet_aston.confic;

import jakarta.servlet.http.HttpServletRequest;

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
