package root.restaurant.Costumer.service;

import root.actors.Employee;
import root.permission.PermissionType;
import root.reservation.dao.ReservationDAO;
import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import root.restaurant.menu.Menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class RestaurantService {

    @Autowired
    Menu menu;
    @Autowired
    ReservationDAO reservationDAO;

    public ModelAndView restaurant(HttpServletRequest request, ModelAndView modelAndView) {

        HttpSession session = request.getSession(false);

        if (!isAuthenticated(session)) {
            modelAndView.setViewName("Login");
            return modelAndView;
        }

        addMenu(modelAndView);

        Costumer costumer = (Costumer) session.getAttribute("costumer");
        addCurrentReservations(modelAndView, costumer.getId(), costumer.getReservationIdRoomMap());

        modelAndView.setViewName("Restaurant");
        return modelAndView;

    }


    //check if the user is Authenticated to view her/his reserved rooms (has the following permissions: HAS_A_RESERVATION
    // &LOGGED_IN_COSTUMER)
    private boolean isAuthenticated(HttpSession session) {

        if (session==null){
            return false;
        }

        Costumer costumer = (Costumer) session.getAttribute("costumer");

        if (costumer != null) {

            if (costumer.getPermissions().contains(PermissionType.HAS_A_RESERVATION) &&
                    costumer.getPermissions().contains(PermissionType.LOGGED_IN_COSTUMER)) {

                return true;
            }
        }


        return false;

    }


    //Add the list that contains item names,
    // HashMap itemSection< Key:itemName, value:itemSection>, and
    // HashMap kindPriceMap< Key:RoomKind, value:RoomPrice> to the model
    private void addMenu(ModelAndView modelAndView) {

        menu.initializeMenu();

        modelAndView.addObject("itemName", menu.getItemName());
        modelAndView.addObject("itemPrice", menu.getItemPrice());
        modelAndView.addObject("itemSection", menu.getItemSection());
    }

    //list of current reservations
    private void addCurrentReservations(ModelAndView modelAndView, Integer costumerId,
                                        Map<Integer, String> reservationIdRoomMap) {

        modelAndView.addObject("currentReservations",
                reservationDAO.selectCurrentReservationId(costumerId));
        modelAndView.addObject("reservationRoomMap", reservationIdRoomMap);

    }


}
