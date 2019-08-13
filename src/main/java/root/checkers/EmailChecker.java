package root.checkers;

import root.login.Costumer.dao.CostumerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.login.Employee.dao.EmployeeDao;

@Component
public class EmailChecker {


    @Autowired
     private CostumerDAO costumerDAO;
    @Autowired
    private EmployeeDao employeeDao;


    /*Checks if the email exists in  the database*/
    public boolean isExistsCostumer(String email){
        final String e=email;
        int id=costumerDAO.selectIdByEmail(e);

        if (id!=-1){
            return true;
        }
        else {
            return false;
        }
    }

    /*Checks if the email exists in  the database*/
    public boolean isExistsEmployee(String email){
        final String e=email;
        int id=employeeDao.selectIdByEmail(e);

        if (id!=-1){
            return true;
        }
        else {
            return false;
        }
    }
}
