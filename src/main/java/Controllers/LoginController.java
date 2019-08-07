package Controllers;

import Checkers.EmailChecker;
import Checkers.LoginTryTimesChecker;
import Checkers.PasswordChecker;
import Checkers.PermissionType;
import DAO.CostumerDAO;
import DAO.CostumerLoginDAO;
import DAO.PermissionCostumerDAO;
import DAO.ReservationDAO;
import SystemActors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
    public class LoginController {

    @Autowired
    CostumerDAO costumerDAO;
    @Autowired
    EmailChecker emailChecker;
    @Autowired
    PasswordChecker passwordChecker;
    @Autowired
    LoginTryTimesChecker loginTryTimesChecker;
    @Autowired
    PermissionCostumerDAO permissionCostumerDAO;
    @Autowired
    CostumerLoginDAO costumerLoginDAO;
    @Autowired
    ReservationDAO reservationDAO;


        @RequestMapping(value = "/login", method = RequestMethod.POST)
        public String login(ModelMap modelMap, @RequestParam String email,
                            @RequestParam String password, HttpServletRequest request){

            HttpSession session=request.getSession(true);

            //lock user out after 5 false login try
            if (!loginTryTimesChecker.isAllowedToTry(session)){
                modelMap.addAttribute("error",
                        " You're locked out, try again later.");
                return "Login";
            }

            //check if email exist
            if (!emailChecker.isExists(email)){

                modelMap.addAttribute("error"," Your login details don't seem right." +
                        " Check that your caps lock is off and try again. \n" +
                        "Careful! After five tries, you'll get locked out.");
                return "Login";
            }

            //check if passwords match
            if (!passwordChecker.passwordCheck(email,password)){
                modelMap.addAttribute("error","Your login details don't seem right." +
                        " Check that your caps lock is off and try again. \n" +
                        "Careful! After five tries, you'll get locked out.");
                return "Login";
            }

            //Bring the costumer Info from the dataBase
            Costumer costumer=costumerDAO.selectByEmail(email);
            costumer.setPermissions(permissionCostumerDAO.selectList(costumer.getId()));
            session.setAttribute("costumer",costumer);
            costumerLoginDAO.insert(costumer,new Date());

            //Check if the costumer has a reservation
            if (costumer.getPermissions().contains(PermissionType.HAS_A_RESERVATION)){

                costumer.setReservationIdRoomMap(reservationDAO.selectReservedRooms(costumer));
                return "WelcomeHotelGuest";
            }

            return "WelcomeLoggedinCostumer";
        }

    }
