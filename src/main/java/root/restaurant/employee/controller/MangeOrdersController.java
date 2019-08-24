package root.restaurant.employee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import root.restaurant.employee.Common.OrderManagementAuthentication;
import root.restaurant.employee.service.OrderDeliveredService;
import root.restaurant.employee.service.ViewOrdersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MangeOrdersController {

    @Autowired
    ViewOrdersService viewOrdersService;
    @Autowired
    OrderDeliveredService orderDeliveredService;
    @Autowired
    OrderManagementAuthentication orderManagementAuthentication;


    @RequestMapping("/viewOrders")
    public ModelAndView viewOrders(HttpServletRequest request, ModelAndView modelAndView){

        HttpSession session= request.getSession(false);
        modelAndView= viewOrdersService.viewOrders(session,modelAndView);

        return modelAndView;
    }



    @RequestMapping(value = "orderDelivered", method = RequestMethod.GET)
    public String OrderDelivered(@RequestParam int orderId, HttpServletRequest request,
                                 HttpServletResponse response){

        HttpSession session= request.getSession(false);
        if (!orderManagementAuthentication.authentication(session)){
            return  "NotAuthenticated";
        }

        orderDeliveredService.orderDelivered(orderId);

        try(PrintWriter printWriter= response.getWriter()){

            printWriter.write("ready");

        }
        catch (IOException e){

            System.out.println(e.getMessage());
        }
        return "";
    }
}
