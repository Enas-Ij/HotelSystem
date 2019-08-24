<%@ page import="root.messaging.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="root.actors.Costumer" %><%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/19/2019
  Time: 10:59 PM
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
    <title>View messages</title>
</head>
<body>

<h1> Costumer Service</h1><br> <br>
<h3>Unanswered messages  </h3>

<form id="viewForm" action="/viewMessages" method="post">
    <%!
        // declare the variables
        List<Message> messageList;
    %>

    <%
        //bring the variables values from the model that is in the request
        messageList=(List) request.getAttribute("messageList");
    %>

    <%!
        //dynamically creating table that represent the message and the costumer info
        String output(){
            String htmlOutput="<table> <tr> <th> Room Number</th>" +
                    "<th> Name </th>" +
                    "<th> Phone </th>" +
                    "<th> Email</th>" +
                    "<th> Message</th>" +
                    "<th> Time </th>" +
                    "<th> Change status </th> </tr>";
            for (Message message: messageList ) {

                Costumer costumer= message.getCostumer();

                    htmlOutput+=" <tr>"+
                            "<td>"+message.getRoomNumber() +"</td>"+
                            "<td>"+ costumer.getFirstName()+" "+costumer.getLastName()+"</td>"+
                            "<td>"+costumer.getPhoneNum() +"</td>"+
                            "<td>"+ costumer.getEmail() +"</td>"+
                            "<td>"+ message.getMessageContent()+"</td>"+
                            "<td>"+ message.getSubmittingTime()+"</td>"+
                            "<td>"+ "<select id=\""+message.getMessageId()+"Status\">" +
                            "  <option value=\"contacted/resolved\">contacted/Resolved</option>" +
                            "  <option value=\"contacted/notResolved\">contacted/not resolved</option>" +
                            "</select>" +
                            "<input type=\"button\" onclick=\"updateStatus()\" value=\"change Status\" " +
                            "id=\""+message.getMessageId()+"\" >"+"</td>"+
                            "</tr>";

                System.out.println(message.getSubmittingTime());

            }

            htmlOutput+="</table>";
            return htmlOutput;
        }

    %>
    <%= output() %>

</form>

<script>
    function updateStatus( ) {


        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.forms["viewForm"].submit();

            }
        };

        var id= event.srcElement.id;
        var status= document.getElementById((id+"Status")).value;
        var statusUri=encodeURI(status);
        var url = "/messageStatus?"+"messageId=" +id+"&messageStatus="+statusUri;
        xhttp.open("GET", url, true);
        xhttp.send();

    }

</script>
</body>
</html>
</html>
