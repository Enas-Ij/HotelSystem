<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/5/2019
  Time: 5:08 PM
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
    <title>root.restaurant</title>
</head>
<body>

<h1> Hotel root.restaurant</h1><br> <br>
<h3>Order your meal</h3>

<form id="form1" action="/order" method="get">
        <%!
            // declare the variables
        List<String>itemName;
        Map<String,Float> itemPrice;
        Map<String,String>itemSection ;
        Set<String> sections;
        %>

        <%
            //bring the variables values from the model that is in the request
        itemName=(List) request.getAttribute("itemName");
        itemPrice=(Map<String, Float>) request.getAttribute("itemPrice");
        itemSection=(Map<String, String>) request.getAttribute("itemSection");
        sections = new HashSet<String>(itemSection.values());
        %>
        <%!
            //dynamically creating table that represent the menu |item|price|Quantity
        String output(){
            String htmlOutput="<table> <tr> <th>Item</th> <th>Price</th> " +
             "<th>Quantity</th> </tr>";
            for (String section: sections ) {
                htmlOutput+=" <tr> <th>"+section+"</th>  <th> </th>  <th> </th></tr> ";

                for (String item : itemSection.keySet() ) {
                    if (itemSection.get(item).equals(section)){
                        htmlOutput+=" <tr> <td>"+item+"</td>"+
                        "<td> "+itemPrice.get(item)+" </td>"+
                        "<td> <input type=\"number\" name=\""+item+
                        "\" min=\"0\" max=\"5\"  value=\"0\" onchange=\"findPrice()\"> </td>"
                         +"</tr>";
                    }
                }
            }

            htmlOutput+="</table>";
            return htmlOutput;
        }

        %>

        <%= output() %>
<br><br><h3 id="total_price"></h3>
 <br> <input type="submit" value="confirm">
</form>


<script>
    //function that send the items  quantity to the server to calculate the price with
    //and without tax and write them into the html page
   function findPrice(){
       var kvpairs = [];
       var form = document.forms.form1;

       for ( var i = 0; i < form.elements.length; i++ ){
           var e = form.elements[i];
           if (e==="confirm") {}
           else {
           kvpairs.push(encodeURIComponent(e.name) + "=" + encodeURIComponent(e.value));
           }}

       var queryString = kvpairs.join("&");


       var xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function() {
           if (this.readyState == 4 && this.status == 200) {
               document.getElementById("total_price").innerHTML =
                   this.responseText;
           }
       };
       var url = "/price?"+queryString ;
       xhttp.open("GET", url, true);
       xhttp.send();

   }
</script>

</body>
</html>
