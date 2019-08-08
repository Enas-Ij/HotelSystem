<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/4/2019
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check booking details</title>
</head>
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
<body>
<h2>Hotel</h2><br>
<br> <br>
<h3>Check your bookings details:</h3>
<%!  Map<Integer, String> reservationIdRoomMap;
    Map<Integer, Date> reservationToDateMap;
    Map<Integer, Date> reservationFromDateMap;   %>

<%reservationIdRoomMap=(Map<Integer, String>) request.getAttribute("reservationIdRoomMap");
    reservationFromDateMap=(Map<Integer, Date>) request.getAttribute("reservationFromDateMap");
    reservationToDateMap=(Map<Integer, Date>) request.getAttribute("reservationToDateMap");%>
<%!
  String output(){
    String htmlOutput="";
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    if (reservationIdRoomMap.size()==0){
        htmlOutput= "<h2>No booked rooms</h2>";
        return htmlOutput;
    }

       htmlOutput="<table> <tr> <th>Reservation Id</th> <th>room number</th> " +
              "<th>From Date</th>  <th>To Date</th> < </tr>";
      for (Integer root.reservation: reservationIdRoomMap.keySet() ) {

                  htmlOutput+=" <tr> <td>"+reservation+"</td>"+
                          "<td> "+reservationIdRoomMap.get(reservation)+" </td>"+
                          "<td>"+ formatter.format(reservationFromDateMap.get(reservation))+"</td>"+
                          "<td>"+ formatter.format(reservationToDateMap.get(reservation))+"</td>"
                          +"</tr>";

      }

      htmlOutput+="</table>";
    return htmlOutput;
  }
%>
<%= output() %>


</body>
</html>
