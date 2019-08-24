package root.restaurant.employee.Common;

import org.springframework.beans.factory.annotation.Autowired;
import root.Authentication.CheckAuthentication;
import root.actors.Employee;
import root.permission.PermissionType;

import javax.servlet.http.HttpSession;

public class OrderManagementAuthentication {

    @Autowired
    CheckAuthentication checkAuthentication;

    //check that the user is authenticated to use the service,
    // (has a session, is an employee, and has ORDER_MANAGEMENT permission)
    public boolean authentication(HttpSession session ){

        if (!checkAuthentication.hasSession(session)){
            return false;
        }

        Employee employee= (Employee) session.getAttribute("employee");
        if (employee==null){
            return false;
        }

        if ( !checkAuthentication.isAuthenticated
                (employee.getPermissions(), PermissionType.ORDER_MANAGEMENT)){
            return false;
        }

        return true;
    }

}
