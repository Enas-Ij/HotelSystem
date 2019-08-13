package root.login.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import root.actors.Employee;
import root.checkers.EmailChecker;
import root.checkers.LoginTryTimesChecker;
import root.checkers.PasswordChecker;
import root.login.Employee.dao.EmployeeDao;
import root.login.Employee.service.EmployeeLoginService;
import root.permission.PermissionType;
import root.permission.dao.PermissionEmployeeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EmployeeLoginController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    EmailChecker emailChecker;
    @Autowired
    PasswordChecker passwordChecker;
    @Autowired
    LoginTryTimesChecker loginTryTimesChecker;
    @Autowired
    PermissionEmployeeDao permissionEmployeeDao;
    @Autowired
    EmployeeLoginService employeeLoginService;


    @RequestMapping(value = "loginEmployee", method = RequestMethod.POST)
    public String login(ModelMap modelMap, @RequestParam String email,
                        @RequestParam String password, HttpServletRequest request){

        HttpSession session=request.getSession(true);

        //lock user out after 5 false root.login try
        if (!loginTryTimesChecker.isAllowedToTry(session)){
            modelMap.addAttribute("error",
                    " You're locked out, try again later.");
            return "EmployeeLogin";
        }

        //check if email exist and  passwords match
        if (!emailChecker.isExistsEmployee(email) ||!passwordChecker.passwordCheckEmployee(email,password)){

            modelMap.addAttribute("error"," Your login details don't seem right." +
                    " Check that your caps lock is off and try again. \n" +
                    "Careful! After five tries, you'll get locked out.");
            return "EmployeeLogin";
        }


        //Bring the employee Info from the dataBase
        Employee employee= employeeLoginService.login(email, password);
        session.setAttribute("employee", employee);

        //******////***
        modelMap.addAttribute("employeeFirstName", employee.getFirstName());
        return "WelcomeLoggedInEmployee";
    }
}
