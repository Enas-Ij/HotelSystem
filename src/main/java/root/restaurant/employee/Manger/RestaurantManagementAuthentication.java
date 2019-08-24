package root.restaurant.employee.Manger;

import org.springframework.beans.factory.annotation.Autowired;
import root.Authentication.CheckAuthentication;
import root.actors.Employee;
import root.permission.PermissionType;

import javax.servlet.http.HttpSession;

public class RestaurantManagementAuthentication {

    @Autowired
    CheckAuthentication checkAuthentication;

   // checks if the user is an Employee that has RESTAURANT_MANAGEMENT permission
    public boolean isAuthenticated(HttpSession session){

        if (!checkAuthentication.hasSession(session)){

            return false;

        }


        Employee employee = (Employee) session.getAttribute("employee");

        if (employee != null) {

            if (checkAuthentication.isAuthenticated(employee.getPermissions(), PermissionType.RESTAURANT_MANAGEMENT)){

                return true;
            }

        }

        return false;
    }

}
