package root.signup.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import root.actors.Employee;
import root.checkers.EmailChecker;
import root.login.Employee.dao.EmployeeDao;
import root.permission.PermissionType;
import root.permission.dao.PermissionEmployeeDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmployeeRegistration {


    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    EmailChecker emailChecker;
    @Autowired
    PermissionEmployeeDao permissionEmployeeDao;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String signUp(ModelMap modelMap, @ModelAttribute Employee employee,
                         HttpServletRequest request){


        //checks if there is another account with the same email if so go to SomeThingIsWrong and show the Massege
        if (emailChecker.isExistsEmployee(employee.getEmail())){
            String htmlMassage="<h3>Your email is already used in another Account<h3/>"
                    +"<a href=\"views/Login.jsp\"> Login </a><br>"
                    +"<a href=\"views/SignUp.jsp\"> SignUp </a><br>";
            modelMap.addAttribute("message",htmlMassage);

            return "SomethingIsWrong";
        }

        //Insert the account info to the database,give the employee permission assigned for his role
        //the transfer to WelcomeCostumer page
        employeeDao.insert(employee);
        employee.setId(employeeDao.selectIdByEmail(employee.getEmail()));

        List<PermissionType> permissions= permissionEmployeeDao.selectPermissions(employee.getRole());


        modelMap.addAttribute("employee", employee);
        return "EmployeeIsRegistered";
    }
}
