package root.messaging.costumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import root.Authentication.CheckCostumerAuthentication;
import root.actors.Costumer;
import root.reservation.dao.ReservationDAO;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class MessagingSystemService {

    @Autowired
    ReservationDAO reservationDAO;

    @Autowired
    CheckCostumerAuthentication checkCostumerAuthentication;


    public ModelAndView getMessagingSystem(ModelAndView modelAndView, HttpSession session){

        if (!checkCostumerAuthentication.isReserved(session)){
            modelAndView.setViewName("Login");
            return modelAndView;
        }

        Costumer costumer= (Costumer) session.getAttribute("costumer");
        addCurrentReservations(modelAndView, costumer.getId(), costumer.getReservationIdRoomMap());


        modelAndView.setViewName("ContactCostumerService");
        return modelAndView;
    }



    //list of current reservations
    private void addCurrentReservations(ModelAndView modelAndView, Integer costumerId,
                                        Map<Integer, String> reservationIdRoomMap) {

        modelAndView.addObject("currentReservations",
                reservationDAO.selectCurrentReservationId(costumerId));
        modelAndView.addObject("reservationRoomMap", reservationIdRoomMap);

    }
}
