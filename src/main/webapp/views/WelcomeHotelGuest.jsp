<%@ page import="root.actors.Costumer" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 7/31/2019
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome back</title>
</head>
<body>

<h2>Hotel</h2>
Guest<br>
<%Costumer costumer= (Costumer) session.getAttribute("costumer");%>
<h6>Welcome <%=costumer.getFirstName() %></h6>

<a href=/views/FindRoom.jsp> Find & Reserve </a><br>
<a href=/viewReservedRooms> View your booked rooms</a><br>
<a href=/ViewRestaurant> Restaurant</a><br>
<a href=/contact-service> Contact Costumer service</a><br>
<a href=/chooseReservation>Check out</a><br>

</body>
</html>
