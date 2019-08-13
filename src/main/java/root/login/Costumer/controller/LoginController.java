package root.login.Costumer.controller;

import root.checkers.EmailChecker;
import root.checkers.LoginTryTimesChecker;
import root.checkers.PasswordChecker;
import root.login.Costumer.service.CostumerLoginService;
import root.permission.PermissionType;
import root.login.Costumer.dao.CostumerDAO;
import root.reservation.dao.ReservationDAO;
import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
    ReservationDAO reservationDAO;
    @Autowired
    CostumerLoginService costumerLoginService;


        @RequestMapping(value = "login", method = RequestMethod.POST)
        public String login(ModelMap modelMap, @RequestParam String email,
                            @RequestParam String password, HttpServletRequest request){

            HttpSession session=request.getSession(true);

            //lock user out after 5 false root.login try
            if (!loginTryTimesChecker.isAllowedToTry(session)){
                modelMap.addAttribute("error",
                        " You're locked out, try again later.");
                return "Login";
            }

            //check if email exist and  passwords match
            if (!emailChecker.isExistsCostumer(email) ||!passwordChecker.passwordCheckCostumer(email,password)){

                modelMap.addAttribute("error"," Your root.login details don't seem right." +
                        " Check that your caps lock is off and try again. \n" +
                        "Careful! After five tries, you'll get locked out.");
                return "Login";
            }


            //Bring the costumer Info from the dataBase
            Costumer costumer=costumerLoginService.login(email, password);
            session.setAttribute("costumer",costumer);

            //Check if the costumer has a reservation
            if (costumer.getPermissions().contains(PermissionType.HAS_A_RESERVATION)){

                costumer.setReservationIdRoomMap(reservationDAO.selectReservedRooms(costumer.getId()));
                return "WelcomeHotelGuest";
            }

            return "WelcomeLoggedinCostumer";
        }

    }
