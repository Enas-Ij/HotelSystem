<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 7/25/2019
  Time: 08:47 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello </title>
</head>
<body>
<FORM method="get" action="/testDB">
    <input type="date" name="From" value="From">
    <input type="date" name="TO" value="To">
    <input type="submit" value="submit">
</FORM>
<a href="/test"> <input type="button" value="session" ></a><br>
 <input type="button" value="session" onclick="x()" >
 price= <h6 id="result"></h6>

<script>
    function x() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("result").innerHTML =
                    this.responseText;
            }
        };
        var url = "/test4?txt=10" ;
        xhttp.open("GET", url, true);
        xhttp.send();
    }

</script>
<input type="number" name="quantity" onchange="x()" min="0" max="5" value="0">

</body>
</html>

