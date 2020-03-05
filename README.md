# HotelSystem Mangement system 

# Table of Contents

1. [Porject Description](#porject-description)
2. [Technologies](#technologies)
3. [How To Run](#how-to-run)

# Porject Description:

The project is web-based hotel management system. The focus of this project is on the **backend** part. The system provides the following services:

- Costumer: Sign up, Login, Finding available rooms in certain dates and details, Room reservation, Ordering from the hotel restaurant menu, Messaging costumer service, Check out, Viewing his/her bill.

- Employee (Restuarant Employee): Login, View costumers restaurant orders and manage them.

- Employee (Costumar service Employee): Login,  View costumers’ messages and manage them.

- Employee (General Manager): Login, has access to the restuarant and costumar service, add new employees.


# Technologies:

- Java 8
- Spring MVC
- Spring JDBC
- MySQL
- Git
- HTML
- JavaScript
- AJAX
- Maven
- Apache Tomcat

# How To Run

In order to run the project correctly please do the following:

1- clone the repositry. 

2- Create mysql database using the sql code in the file [DB.sql](https://github.com/Enas-Ij/MapReduce/tree/master/reducerNode).

3- Change the database connection information in DispatcherServletRep-servlet.xml in the bean which id="dataSourceBean"

4- Add TOMCAT configration

5- Build and run the project

6- Start browsing from  http://localhost:8080/views/WelcomeCostumer.jsp
