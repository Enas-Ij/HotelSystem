package root.checkers;

import root.login.Costumer.dao.CostumerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.login.Employee.dao.EmployeeDao;

@Component
public class PasswordChecker {

    @Autowired
    private CostumerDAO costumerDAO;
    @Autowired
    private EmployeeDao employeeDao;


    /*Checks if the password entered to root.login form matches the password that exists in  the database*/
    public boolean passwordCheckCostumer(String email, String password){

        String costumerPassword=costumerDAO.selectPasswordByEmail(email);

        if (password.equals(costumerPassword)){
            return true;
        }
        else {
            return false;
        }
    }

    /*Checks if the password entered to root.login form matches the password that exists in  the database*/
    public boolean passwordCheckEmployee (String email, String password){

        String employeePassword=employeeDao.selectPasswordByEmail(email);

        if (password.equals(employeePassword)){
            return true;
        }
        else {
            return false;
        }
    }

}
