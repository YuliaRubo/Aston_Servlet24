<%--
  Created by IntelliJ IDEA.
  User: Juli
  Date: 19.04.2023
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Courses</title>
    <%--        <link href ="student.css" rel= "stylesheet"/>--%>
    <style>
        td {
            border-style: double; /* Стиль линии вокруг абзаца */
            padding: 5px; /* Поля вокруг текста */
        }
    </style>

</head>
<body>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>SurName</th>
        <th>Age</th>
        <%--        <th>Actions</th>--%>
    </tr>
    </thead>
    <tbody>
<%--    <a href="/course/"><input type="button" value = "Courses"></a>--%>
    <%--    <a href="/teacher/"><input type="button" value = "Courses"></a>--%>
    <br>
    <br>
    <br>
    <br>

    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.name}</td>
            <td>${student.surname}</td>
            <td>${student.age}</td>
                <%--            <td><a href="http://Localhost:8080/add-new-student"><input type="button" value = "Add"></a></td>--%>
                <%--            <td><a href="http://Localhost:8080/update-student?id=${student.id}"><input type="button" value = "Update"></a></td>--%>
            <td><input type="button" value = "Delete"></td>

        </tr>
    </c:forEach>
    </tbody>
</table>





<%--<h2><%= "OUR STUDENTS!" %>--%>
<%--<c:forEach items="${students}" var="student">--%>
<%--    <h3>--%>
<%--        <c:out value="${student.name}"/>--%>
<%--        <c:out value="${student.surname}"/>--%>
<%--    </h3>--%>
<%--</c:forEach>--%>

</body>
</html>