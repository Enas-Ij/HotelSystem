<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/16/2019
  Time: 7:17 PM
--%>
To change this template use File | Settings | File Templates.
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
    <title>Restaurant Menu</title>
</head>
<body>

<h1> Hotel restaurant</h1><br> <br>
<h3>Edit the restaurant menu</h3>

<form id="form1" action="/viewMenu" method="get">
        <%!
            // declare the variables
        List<String>itemName;
        Map<String,Float> itemPrice; %>

