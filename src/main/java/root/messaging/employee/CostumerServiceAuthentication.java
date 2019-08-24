package root.messaging.employee;

import org.springframework.beans.factory.annotation.Autowired;
import root.Authentication.CheckAuthentication;
import root.actors.Employee;
import root.permission.PermissionType;

import javax.servlet.http.HttpSession;


public class CostumerServiceAuthentication {

    @Autowired
    private CheckAuthentication checkAuthentication;

    public boolean isAuthenticated(HttpSession session){

        if (!checkAuthentication.hasSession(session)){
            return false;
        }

        Employee employee= (Employee) session.getAttribute("employee");
        if (employee==null){
            return false;
        }

        boolean authentication =checkAuthentication.isAuthenticated
                (employee.getPermissions(), PermissionType.COSTUMER_SERVICE);

        return authentication;
    }



}
