<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/22/2019
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose Reservation</title>
</head>
<body>
<form action="/checkOut" method="get">
    <%!
        // declare the variables
        Map<Integer, String> reservationRoomMap;

    %>

    <%
        //bring the variables values from the model that is in the request
        reservationRoomMap= (Map)request.getAttribute("reservationRoomMap");
    %>
    <%!
        //dynamically creating table that represent the radio button that presents the costumer
        //current reservations
        String outputRadioButtons(){
            String htmlOutput="Choose reservation that you want check out from: <br>";
            for (Integer reservationId: reservationRoomMap.keySet() ) {
                htmlOutput+=" <input type=\'radio\' name=\'reservationId\' value=\'"+reservationId+"\'> "
                        + reservationRoomMap.get(reservationId);

            }
            return htmlOutput;
        }

    %>

    <%= outputRadioButtons() %>

    <br><br>
    <input type="submit" value="view bill" >
</form>
</body>
</html>
