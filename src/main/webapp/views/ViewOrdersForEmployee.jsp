<%@ page import="root.restaurant.order.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/16/2019
  Time: 1:05 AM
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
    <title>View orders</title>
</head>
<body>

<h1> Hotel restaurant</h1><br> <br>
<h3>Meals on waiting List</h3>

<form id="viewForm" action="/viewOrders" method="post">
    <%!
        // declare the variables
        List<Order> orderList;
    %>

    <%
        //bring the variables values from the model that is in the request
        orderList=(List) request.getAttribute("waitingOrders");
    %>
    
    <%!
        //dynamically creating table that represent the menu |item|price|Quantity
        String output(){
            String htmlOutput="<table>";
            for (Order order: orderList ) {

                htmlOutput+=" <tr> <th>Order: "+order.getOrderId()+ "</th> " +
                        " <th>  Room: "+order.getRoomNumber()+"</th>" +
                        " <th>"+
                        "<input type=\"button\" value=\'delivered\' onclick=\"updateOrders()\" name=\'"+ order.getOrderId()+"\'" +
                        " id=\'"+ order.getOrderId()+"\'>"
                        +"</th>" + " </tr> ";

                for (String item : order.getItemQuantityMap().keySet() ) {

                        htmlOutput+=" <tr>"+ "<td> </td>"+
                                " <td>"+item+"</td>"+
                                "<td> "+order.getItemQuantityMap().get(item)+" </td>"
                                +"</tr>";

                }
            }

            htmlOutput+="</table>";
            return htmlOutput;
        }

    %>

    <%= output() %>

</form>

<script>
    function updateOrders( ) {

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.forms["viewForm"].submit();

            }
        };
        var url = "/orderDelivered?"+"orderId=" + event.srcElement.id;
        xhttp.open("GET", url, true);
        xhttp.send();

    }
    
</script>
</body>
</html>
