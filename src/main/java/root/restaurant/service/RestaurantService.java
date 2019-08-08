package root.restaurant.service;

import root.permission.PermissionType;
import root.restaurant.Menu;
import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RestaurantService {

    @Autowired
    Menu menu;

    public ModelAndView restaurant(HttpServletRequest request, ModelAndView modelAndView){

        HttpSession session=request.getSession(true);

        if (!isAuthenticated(session)) {
            modelAndView.setViewName("Login");
            return modelAndView;
        }

        modelAndView=addModel(modelAndView);
        modelAndView.setViewName("Restaurant");
        return modelAndView;

    }




    //check if the user is Authenticated to view her/his reserved rooms (has the following permissions: HAS_A_RESERVATION
    // &LOGGED_IN_COSTUMER)
    private boolean isAuthenticated(HttpSession session){

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



    //Add the list that contains item names,
    // HashMap itemSection< Key:itemName, value:itemSection>, and
    // HashMap kindPriceMap< Key:RoomKind, value:RoomPrice> to the model
    private ModelAndView addModel(ModelAndView modelAndView){

        menu.initializeMenu();

        modelAndView.addObject("itemName",menu.getItemName());
        modelAndView.addObject("itemPrice",menu.getItemPrice());
        modelAndView.addObject("itemSection",menu.getItemSection());

        return modelAndView;
    }


}
