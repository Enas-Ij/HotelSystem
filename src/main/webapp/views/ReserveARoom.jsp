<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/1/2019
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find/Reserve a room</title>
</head>
<body>

<form action="/find" method="get">
    <h2>Hotel Reservation</h2>
    <h3> Enter Your Dates </h3><br><br>
    ${DateMessage}<br><br>

    From:<input type="date" name="fromDate"  id="fromDate" >
    To: <input type="date" name="toDate"   id="toDate" >


    <input type="submit" value="Find Room">
</form><br><br>


<form method="get" action="/reserve">

    ${htmlAvblRoomRadio}

    <input type="submit" value="Confirm">
</form>



<SCRIPT>
    var today = new Date();
    var tomorrow = new Date(today);
    tomorrow.setDate(today.getDate()+1);
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }
    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("fromDate").setAttribute("min", today);
    var tdd = tomorrow.getDate();
    var tmm = tomorrow.getMonth()+1; //January is 0!
    var tyyyy = tomorrow.getFullYear();
    if(tdd<10){
        tdd='0'+tdd
    }
    if(tmm<10){
        tmm='0'+tmm
    }
    tommorow = tyyyy+'-'+tmm+'-'+tdd;

    document.getElementById("toDate").setAttribute("min", tommorow);
    document.getElementById("fromDate").defaultValue =today;
    document.getElementById("toDate").defaultValue =tommorow;
</SCRIPT>



</body>
</html>
