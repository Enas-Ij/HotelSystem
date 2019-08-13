package root.signup.controller;

import root.checkers.EmailChecker;
import root.permission.PermissionType;
import root.login.Costumer.dao.CostumerDAO;
import root.permission.dao.PermissionCostumerDAO;
import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignUpController {

    @Autowired
    CostumerDAO costumerDAO;
    @Autowired
    EmailChecker emailChecker;
    @Autowired
    PermissionCostumerDAO permissionCostumerDAO;

    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String signUp(ModelMap modelMap, @ModelAttribute Costumer costumer,
                         HttpServletRequest request){


        //checks if there is another account with the same email if so go to SomeThingIsWrong and show the Massege
        if (emailChecker.isExistsCostumer(costumer.getEmail())){
            String htmlMassage="<h3>Your email is already used in another Account<h3/>"
                    +"<a href=\"views/Login.jsp\"> Login </a><br>"
                    +"<a href=\"views/SignUp.jsp\"> SignUp </a><br>";
            modelMap.addAttribute("message",htmlMassage);

            return "SomethingIsWrong";
        }

        //Insert the account info to the database,give the user LOGGED_IN_COSTUMER root.permission
        //the transfer to WelcomeCostumer page
        costumerDAO.insert(costumer);
        costumer.setId(costumerDAO.selectIdByEmail(costumer.getEmail()));
        costumer.addPermission(PermissionType.LOGGED_IN_COSTUMER);
        permissionCostumerDAO.insert(costumer.getId(),PermissionType.LOGGED_IN_COSTUMER);

        //start a session
        HttpSession session=request.getSession(true);
        session.setAttribute("costumer",costumer);



        return "WelcomeLoggedinCostumer";
    }

    

}
