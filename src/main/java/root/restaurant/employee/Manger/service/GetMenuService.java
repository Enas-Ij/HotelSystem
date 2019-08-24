package root.restaurant.employee.Manger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import root.restaurant.employee.Manger.RestaurantManagementAuthentication;
import root.restaurant.menu.Menu;

import javax.servlet.http.HttpSession;

public class GetMenuService {

    @Autowired
    RestaurantManagementAuthentication restaurantManagementAuthentication;
    @Autowired
    Menu menu;

    public ModelAndView getMenu(ModelAndView modelAndView, HttpSession session){


        if (!restaurantManagementAuthentication.isAuthenticated(session)){
            modelAndView.setViewName("NotAuthenticated");
            return modelAndView;
        }

        menu.initializeMenu();
        modelAndView.addObject("itemName", menu.getItemName());
        modelAndView.addObject("itemPrice", menu.getItemPrice());
        modelAndView.addObject("itemSection", menu.getItemSection());

        modelAndView.setViewName("MenuEditing");

        return modelAndView;
    }




}
