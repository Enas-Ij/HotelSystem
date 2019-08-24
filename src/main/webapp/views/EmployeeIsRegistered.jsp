<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/14/2019
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration completed</title>
</head>

<h2> Employee is registered successfully</h2><br>

<h3> Employee Information: </h3><br>

name: &nbsp ${employee.firstName} &nbsp ${employee.lastName} <br>
Phone: &nbsp ${employee.phoneNum}<br>
Email: &nbsp ${employee.email}<br>
Password: &nbsp ${employee.password}<br>
Role: &nbsp ${employee.role}<br>
Salary: &nbsp ${employee.salary}<br>

<a href=/views/WelcomeLoggedInEmployee.jsp> Ok </a><br>

<body>

</body>
</html>
