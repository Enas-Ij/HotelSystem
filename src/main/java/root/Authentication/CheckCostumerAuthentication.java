package root.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import root.actors.Costumer;
import root.permission.PermissionType;

import javax.servlet.http.HttpSession;

public class CheckCostumerAuthentication {

    @Autowired
    CheckAuthentication checkAuthentication;

    public boolean isLoggedIn(HttpSession session){

        if (!checkAuthentication.hasSession(session)){
            return false;
        }

        Costumer costumer= (Costumer) session.getAttribute("costumer");
        if (costumer==null){
            return false;
        }

        boolean logIn =checkAuthentication.isAuthenticated
                (costumer.getPermissions(), PermissionType.LOGGED_IN_COSTUMER);

        return logIn;
    }

    public boolean isReserved(HttpSession session){

        if (!isLoggedIn(session)){
            return false;
        }

        Costumer costumer= (Costumer) session.getAttribute("costumer");
        boolean reserved= checkAuthentication.isAuthenticated
                (costumer.getPermissions(), PermissionType.HAS_A_RESERVATION);

        return reserved;
    }


}

