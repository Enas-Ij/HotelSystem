<%@ page import="SystemActors.Costumer" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/2/2019
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Costumer</title>
</head>
<body>

<h2>Hotel</h2>
<%Costumer costumer= (Costumer) session.getAttribute("costumer");%>
<h6>Welcome <%=costumer.getFirstName() %></h6>

<a href=/views/FindRoom.jsp> Find & Reserve </a><br>

</body>
</html>
