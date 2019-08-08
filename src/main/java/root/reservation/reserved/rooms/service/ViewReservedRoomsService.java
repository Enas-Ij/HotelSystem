package root.reservation.reserved.rooms.service;

import root.permission.PermissionType;
import root.reservation.dao.ReservationDAO;
import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ViewReservedRoomsService {

    @Autowired
    private ReservationDAO reservationDAO;


    //return the model of the user reserved  rooms if the user is isAuthenticated
    public ModelAndView viewReservedRooms( HttpServletRequest request, ModelAndView modelAndView){

        HttpSession session= request.getSession(true);

        if (!isAuthenticated(session)) {
            modelAndView.setViewName("Login");
            return modelAndView;
        }

        addRoomsToModel(session,modelAndView);
        addDates(session, modelAndView);
        modelAndView.setViewName("ViewCostumerReservations");

        return modelAndView;
    }


    //check if the user is Authenticated to view her/his reserved rooms (has the following permissions: HAS_A_RESERVATION
    // &LOGGED_IN_COSTUMER)
    private boolean isAuthenticated( HttpSession session){

        Costumer costumer = (Costumer) session.getAttribute("costumer");

        if (costumer == null) {
            return false;
        }

        if (costumer.getPermissions().contains(PermissionType.HAS_A_RESERVATION) &&
        costumer.getPermissions().contains(PermissionType.LOGGED_IN_COSTUMER)) {

            return true;
        }

        return false;
    }


    //add the Map of the costumer current/future reservations to the ModelAndView object
    private void addRoomsToModel(HttpSession session, ModelAndView modelAndView){

        Costumer costumer=(Costumer)session.getAttribute("costumer");

        modelAndView.addObject("reservationIdRoomMap",costumer.getReservationIdRoomMap());
    }


    //add root.reservation dates Map of the costumer current/future reservations to the ModelAndView object
    //
    private  void addDates(HttpSession session, ModelAndView modelAndView){

        Costumer costumer=(Costumer)session.getAttribute("costumer");

        Map<Integer, Date> reservationToDateMap= new HashMap<>();
        Map<Integer, Date> reservationFromDateMap= new HashMap<>();

        for (int reservation : costumer.getReservationIdRoomMap().keySet()) {

            Date toDate=reservationDAO.selectToDate(reservation);
            Date fromDate= reservationDAO.selectFromDate(reservation);

            reservationFromDateMap.put(reservation, fromDate);
            reservationToDateMap.put(reservation, toDate);
        }

        modelAndView.addObject("reservationFromDateMap", reservationFromDateMap);
        modelAndView.addObject("reservationToDateMap", reservationToDateMap);

    }

}
