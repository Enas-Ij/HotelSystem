package Controllers;

import Controllers.ControlClasses.OrderControl;
import Controllers.ControlClasses.OrderPriceControl;
import Controllers.ControlClasses.RestaurantControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller

public class RestaurantController {
    @Autowired
    RestaurantControl restaurantControl;
    @Autowired
    OrderPriceControl orderPriceControl;
    @Autowired
    OrderControl orderControl;

    //viewing the restaurant and menu item and prices
    @RequestMapping("/ViewRestaurant")
    public ModelAndView restaurant(HttpServletRequest request,
                                   ModelAndView modelAndView) {

        restaurantControl.restaurant(request,modelAndView);

        return modelAndView;
    }

    // dynamically calculate the order price when receive ajax request and send
    // the response to ajax
    @RequestMapping(value = "/price", method = RequestMethod.GET)
    public void order(HttpServletRequest request, HttpServletResponse response) {

       String responseString= orderPriceControl.orderPrice(request);
        try(PrintWriter printWriter= response.getWriter()){

            printWriter.write(responseString);

        }
        catch (IOException e){

            System.out.println(e.getMessage());
        }
    }



    //Creates order and store it,
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView order(HttpServletRequest request,
                              ModelAndView modelAndView){

        modelAndView= orderControl.order(request, modelAndView);

        return modelAndView;
    }




}
