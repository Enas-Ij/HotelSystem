package Checkers;

import javax.servlet.http.HttpSession;

public class LoginTryTimesChecker {

    /*checks if the user had 5 or wrong root.login try if so it blocks him/her for the until the session ends
    to prevent password attacks*/
    public boolean isAllowedToTry(HttpSession session){

        int loginTry= loginTryHandler(session);

        if (loginTry>=5){
            return false;
        }

        return true;
    }


    //increases the number of root.login try the user had and returns it
    private int loginTryHandler(HttpSession session){
        Integer loginTry;

        if (session.getAttribute("loginTry")==null){
            loginTry=1 ;
        }
        else {
         loginTry=(Integer) session.getAttribute("loginTry") +1;
        }


        session.setAttribute("loginTry",loginTry);
        return loginTry;
    }
}
