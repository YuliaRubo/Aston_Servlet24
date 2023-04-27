<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 20.04.2023
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
    <link href ="main.css" rel= "stylesheet"/>
<body>

<form:form method="POST" action="/save-student"
           modelAttribute="student">
    <form:label path="name">Name</form:label>
    <form:input path="name" value="${courses.name}"/>

    <form:label path="surname">Surname</form:label>
    <form:input path="surname" value="${student.surname}"/>

    <form:label path="age">Age</form:label>
    <form:input path="age" value="${student.age}"/>

    <form:label path="id" cssStyle = "display:none">Id</form:label>
    <form:input path="id" cssStyle = "display:none" value="${courses.id}"/>

    <input type="submit" value="Submit" />
</form:form>
</body>

</html>
