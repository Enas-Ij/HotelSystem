# HotelSystem
In order to run the project correctly please do the following:

1- Create the Db using the sql code in the file( DB.sql).
Note: do not change anything, or the project wont work correctly.

2- change database connection information in DispatcherServletRep-servlet.xml in the bean which id="dataSourceBean"

3- after runing the project start testing at the following url: http://localhost:8080/views/WelcomeCostumer.jsp

--------------------------------------------------------------------------------------------------------------------

other notes:

4- I have not worked on the employee part yet so login employee link does not work

5-  you may come across few methods or parameters that are not used yet, that is becuase I am planning to use them later

6- Login controller and sign up controller are not very clean, I will cleane them but I did not have time, otherwise the
rest of the controllers code is cleane.

----------------------------------------------------------------------------------------------------------------------


7-The programme does the following functionalities now:

a- allow the unregistered costumer to Sign up, check the uniqueness of his/her email, and the validation of the password
   then stores the costumer in the DB.

b- allow the user(not logged in) to check the available rooms at certain dates, 
but when try to reserve a room the system requires login.

c- allow the costumer to login to their aacount.

d- allow the logged in costumer to find available rooms at certain dates and book them.
the booked in rooms and the dates are stored in DB.

e- allow the costumer that has reservation to check his/her reservation details.
reservations are stored in the DB.

f- allow the reserved costumer to view restaurant menu, order items and get a bill.
orders are stored in the db.
