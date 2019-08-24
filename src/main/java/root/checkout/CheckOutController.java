package root.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import root.Authentication.CheckCostumerAuthentication;
import root.actors.Costumer;
import root.checkout.service.BringReservationsService;
import root.checkout.service.CheckOutInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CheckOutController {

    @Autowired
    CheckCostumerAuthentication checkCostumerAuthentication;
    @Autowired
    CheckOutInfoService checkOutInfoService;
    @Autowired
    BringReservationsService bringReservationsService;

    @RequestMapping("checkOut")
    public ModelAndView checkOutInfo(HttpServletRequest request, ModelAndView modelAndView
                                     ,@RequestParam Integer reservationId) {

        HttpSession session= request.getSession(false);

        if (!checkCostumerAuthentication.isReserved(session)){

            if (!checkCostumerAuthentication.isLoggedIn(session)){
                modelAndView.setViewName("Login");
                return modelAndView;
            }

            modelAndView.setViewName("NotAuthenticated");
            return modelAndView;
        }

       modelAndView= checkOutInfoService.checkOutInfo(reservationId, modelAndView);
        modelAndView.setViewName("CheckOutBill");
        return modelAndView;

    }



    @RequestMapping("chooseReservation")
    public ModelAndView chooseReservation( HttpServletRequest request, ModelAndView modelAndView){

        HttpSession session= request.getSession(false);
        Costumer costumer;

        if (!checkCostumerAuthentication.isReserved(session)){

            if (!checkCostumerAuthentication.isLoggedIn(session)){
                modelAndView.setViewName("Login");
                return modelAndView;
            }

            modelAndView.setViewName("NotAuthenticated");
            return modelAndView;
        }

        costumer= (Costumer) session.getAttribute("costumer");

        Map reservationRoomMap= bringReservationsService.getReservations(costumer.getId());
        modelAndView.addObject("reservationRoomMap", reservationRoomMap);
        modelAndView.setViewName("CheckOutReservations");

        return modelAndView;
    }

}
