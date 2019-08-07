<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 7/27/2019
  Time: 10:46 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h5>Login to you hotel account</h5><br><br>
${error}<br>

<form  name="LoginForm" action="/login" method="post" >
    Email <input type="email" name="email" required><br>
    <h6 id="emailNote"></h6>
    Password <input type="password" name="password" required><br>
    <h6 id="pass1Note"></h6>
    <input type="button" value="Login" onclick="checkFields()"><br>
</form>

<br> don't have an account? <a href=/views/SignUp.jsp> sign up </a><br>

<script>
    function checkFields() {

        var email = document.forms["LoginForm"]["email"];
        var password = document.forms["LoginForm"]["password"];

        var submitValue=true;

        var emailNote=document.getElementById("emailNote");
        var mailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if(email.value.match(mailFormat))
        {
            emailNote.innerText="";
        }
        else
        {
            emailNote.innerHTML="You have entered an invalid email format";
            submitValue=  false;
        }

        var pass1Note=document.getElementById("pass1Note");
        if (password.value.length<0){
            pass1Note.innerText="Please write your password ";
            submitValue=  false;
        }
        else {
            pass1Note.innerText="";
        }


        if (submitValue===true){
            document.forms["LoginForm"].submit();

        }
    }


</script>
</body>
</html>