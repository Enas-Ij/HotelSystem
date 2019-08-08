<%@ page import="root.restaurant.order.Order" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/6/2019
  Time: 6:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Bill</title>
</head>
<body>
<h1>Hotel</h1><br> <br>
<h3>Your order detailed bill</h3>

<%! Order order;
    Map<String, Float> itemPrice;%>
<%
    order=(Order) request.getAttribute("order");
    itemPrice= (Map)request.getAttribute("itemPriceMap");
%>

<%= output() %>
<br><br>
<a href=/views/WelcomeHotelGuest.jsp><input type="button" value="ok"></a>


<%!
    //dynamically creating table that represent the bill |item|price|Quantity
    String output(){
        String htmlOutput="<table> <tr> <th>Item</th> <th>Price/1 Item</th> " +
                "<th>Quantity</th> </tr>";
        for (String item: itemPrice.keySet() ) {
            if (order.getItemQuantityMap().get(item)==0){
                continue;
            }

                    htmlOutput+=" <tr> <td>"+item+"</td>"+
                            "<td> "+itemPrice.get(item)+" </td>"+
                            "<td> "+ order.getItemQuantityMap().get(item)+ "</td>"
                            +"</tr>";


        }

        htmlOutput+= " <tr> <td>  </td>"+
                "<td> Price </td>"+
                "<td> "+ order.getTotalWithoutTax()+ "</td>"
                +"</tr>";
        htmlOutput+= " <tr> <td>  </td>"+
                "<td> Tax </td>"+
                "<td> "+(order.getTotalWithTax()- order.getTotalWithoutTax())+
                "</td>" +"</tr>";
        htmlOutput+= " <tr> <td>  </td>"+
                "<td> Total </td>"+
                "<td> "+ order.getTotalWithTax()+ "</td>"
                +"</tr>";
        htmlOutput+="</table>";
        return htmlOutput;
    }

%>



        </body>
</html>
