package Checkers;

import root.login.dao.CostumerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailChecker {


    @Autowired
     private CostumerDAO costumerDAO;

    /*Checks if the email exists in  the database*/
    public boolean isExists(String email){
        final String e=email;
        int DoesntExist=999999999;
        int id=costumerDAO.selectIdByEmail(e);

        if (id!=DoesntExist){
            return true;
        }
        else {
            return false;
        }
    }
}
