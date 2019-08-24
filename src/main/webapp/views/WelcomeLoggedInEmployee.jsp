<%@ page import="root.actors.Employee" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/14/2019
  Time: 1:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WelcomeLoggedInEmployee</title>
</head>
<body>

<%!
    String role;
%>
<%

    role = (String) request.getAttribute("role");
%>

<%= output() %>
<br><br>


<%!
    String output() {

        String htmlOutput ="";

        if (role.equals("General Manger")){
         htmlOutput = "<a href=/views/RegisterEmployee.jsp> Register new employee </a><br>" +
                "<a href=/viewMenu> Edit Menu </a><br>" +
                "<a href=\"/viewMessages\"> check user messages</a><br>" +
                "<a href=\"/viewOrders\"> check user orders</a><br>";
        }

        else if (role.equals("restaurant Employee")){
            htmlOutput="<a href=\"/viewOrders\"> check user orders</a><br>";
        }
        else if (role.equals("restaurant Manger")){
            htmlOutput="<a href=\"/viewOrders\"> check user orders</a><br>" +
                    "<a href=/viewMenu> Edit Menu </a><br>";
        }
        else if (role.equals("Costumer Service Employee")){
            htmlOutput="<a href=\"/viewMessages\"> check user messages</a><br>";
        }

        return htmlOutput;
    }


%>


</body>
</html>
