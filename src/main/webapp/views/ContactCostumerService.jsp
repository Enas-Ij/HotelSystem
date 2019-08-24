<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Costumer Service</title>
</head>
<body>

<h3>
    Contact the hotel staff to get more support.<br>
    We will contact you back as soon as possible.
</h3>

<form  action="/submitMessage" method="post">
    Message:<br>
    <textarea rows="8" cols="50" name="costumerMessage" ></textarea>
    <br>

    <%!
        // declare the variables
        List<Integer> currentReservations;
        Map<Integer, String> reservationRoomMap;

    %>

    <%
        //bring the variables values from the model that is in the request
        currentReservations=(List) request.getAttribute("currentReservations");
        reservationRoomMap= (Map)request.getAttribute("reservationRoomMap");
    %>
    <%!
        //dynamically creating table that represent the radio button that presents the costumer
        //current reservations
        String outputRadioButtons(){
            String htmlOutput="For room: <br>";
            for (Integer reservationId: currentReservations ) {
                htmlOutput+=" <input type=\'radio\' name=\'reservationId\' value=\'"+reservationId+"\'> "
                        + reservationRoomMap.get(reservationId);

            }
            return htmlOutput;
        }

    %>

     <%= outputRadioButtons() %>


    <br> <input type="submit" value="confirm">
</form>

</body>
</html>
