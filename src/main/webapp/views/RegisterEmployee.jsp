<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 8/14/2019
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Register new Employee</title>
</head>
<body>
<h3>Register new Employee </h3>
<h4>Please make sure the information is correct before submitting</h4>
<br><br>
<form  name="RegForm" action="/register" method="post" >
    <h4 id="note"></h4>
    First Name<input type="text"  name="firstName" required> <br>
    <h6 id="fNameNote"></h6>
    Last Name <input type="text" name="lastName" required><br>
    <h6 id="lNameNote"></h6>
    Phone <input type="tel" name="phoneNum" required><br>
    <h6 id="phoneNote"></h6>
    Email <input type="email" name="email" required><br>
    <h6 id="emailNote"></h6>
    Password <input type="password" name="password" required><br>
    <h6 id="pass1Note"></h6>
    Confirm Password <input type="password" name="password2" required><br>
    <h6 id="pass2Note"></h6>
    Role <input type="text" name="role" required><br>
    <h6 id="roleNote"></h6>
    Salary <input type="number" name="salary" required><br>
    <h6 id="salaryNote"></h6>

    <input type="button" value="Sign Up" onclick="checkFields()"><br>
</form>


<script>
    function checkFields() {

        var firstName = document.forms["RegForm"]["firstName"];
        var lastName = document.forms["RegForm"]["lastName"];
        var email = document.forms["RegForm"]["email"];
        var phoneNum = document.forms["RegForm"]["phoneNum"];
        var password = document.forms["RegForm"]["password"];
        var password2 = document.forms["RegForm"]["password2"];
        var submitValue=true;

        var fNote=document.getElementById("fNameNote");
        if (firstName.value==="") {
            fNote.innerHTML="First Name is required";
            submitValue= false;
        }
        else {
            fNote.innerHTML="";
        }

        var lNote=document.getElementById("lNameNote");
        if (lastName.value==="") {
            lNote.innerHTML="Last Name is required";
            submitValue=  false;
        }
        else {
            lNote.innerHTML="";
        }

        var emailNote=document.getElementById("emailNote");
        var mailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if(email.value.match(mailFormat))
        {
            emailNote.innerText="";
        }
        else
        {
            emailNote.innerHTML="You have entered an invalid email address!";
            submitValue=  false;
        }

        var phoneFormat = /^[+]*[(]?[0-9]{1,3}[)]?[-\s\./0-9]*$/g;
        var phoneNote=document.getElementById("phoneNote");
        if(phoneNum.value.match(phoneFormat))
        {
            phoneNote.innerText="";
        }
        else
        {
            phoneNote.innerHTML="You have entered an invalid phone number";
            submitValue=  false;
        }

        var pass1Note=document.getElementById("pass1Note");
        if (password.value.length<8){
            pass1Note.innerText="Password length should be at least 8";
            submitValue=  false;
        }
        else {
            pass1Note.innerText="";
        }

        var pass2Note=document.getElementById("pass2Note");
        if (password2.value===password.value){
            pass2Note.innerText="";
        }
        else {
            pass2Note.innerText="Your passwords don't match";
            submitValue=  false;
        }

        if (submitValue===true){
            document.forms["RegForm"].submit();

        }
    }

</script>

</body>
</html>
