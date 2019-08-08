package root.checkers;

import root.login.dao.CostumerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordChecker {

    @Autowired
    private CostumerDAO costumerDAO;

    /*Checks if the password entered to root.login form matches the password that exists in  the database*/
    public boolean passwordCheck(String email,String password){

        String costumerPassword=costumerDAO.selectPasswordByEmail(email);

        if (password.equals(costumerPassword)){
            return true;
        }
        else {
            return false;
        }
    }

}
