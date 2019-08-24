<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/23/2019
  Time: 6:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid  rgba(4, 0, 16, 0.45);
        text-align: left;
        padding: 8px;
    }

    th {
        background-color: #dddddd;
    }
</style>
<head>
    <title>Bill</title>
</head>
<body>
<h1>Hotel</h1><br> <br>
<h3>Your detailed bill</h3>

<%!
    Long reservationDuration;
    Float roomBill;
    Float restaurantBill;
    Boolean hotelGuest;
%>
<%
    reservationDuration = (Long) request.getAttribute("reservationDuration");
    roomBill = (Float) request.getAttribute("roomBill");
    restaurantBill = (Float) request.getAttribute("restaurantBill");
    hotelGuest = (Boolean) request.getAttribute("hotelGuest");

%>

<%= output() %>
<br><br>


<%!
    //dynamically creating table that represent the bill
    String output() {

        String htmlOutput = " <tr><th>Room total charge </th> <td>" + roomBill
                + " JOD </td>" + "</tr>";

        htmlOutput+= " <tr><th>Restaurant charge </th> <td>" + restaurantBill
                + " JOD </td>" + "</tr>";
        htmlOutput += " <tr><th> </th> <th> </th> </tr>";
        htmlOutput += " <tr><th>total charge </th> <td>" + (restaurantBill+roomBill)
                + " JOD</td>" + "</tr>";
        htmlOutput += "</table>";


        if (hotelGuest){
            htmlOutput+= "<br> <br> <a href=/views/WelcomeHotelGuest.jsp><input type=\"button\" value=\"ok\"></a>";
        }
        else{
            htmlOutput+="<br><br><a href=/views/WelcomeLoggedinCostumer.jsp><input type=\"button\" value=\"ok\"></a>";
        }
        return htmlOutput;
    }

%>


</body>
</html>
