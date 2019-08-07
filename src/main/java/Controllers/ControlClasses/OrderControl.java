package Controllers.ControlClasses;

import Checkers.PermissionType;
import DAO.OrderDAO;
import DAO.OrderItemsDAO;
import Restaurant.Menu;
import Restaurant.Order;
import Restaurant.OrderPriceCalculator;
import SystemActors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class OrderControl {

    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderItemsDAO orderItemsDAO;
    @Autowired
    OrderPriceCalculator orderPriceCalculator;
    @Autowired
    Menu menu;

    public ModelAndView order(HttpServletRequest request, ModelAndView modelAndView){

        HttpSession session = request.getSession(true);



        if (!isAuthenticated(session)) {
            modelAndView.setViewName("Login");
            return modelAndView;
        }

        modelAndView= createOrder(request, modelAndView);
        modelAndView.setViewName("ViewOrderBill");

        return modelAndView;

    }


    //Creates order and call methods to store it the DB
    private ModelAndView createOrder(HttpServletRequest request,
                             ModelAndView modelAndView){

        Map<String,String> parameters= request.getParameterMap();
        HttpSession session = request.getSession(false);

        Costumer costumer= (Costumer) session.getAttribute("costumer");
        Order order= new Order();

        order.setCostumerId(costumer.getId());

        for (String itemName:  parameters.keySet()  ) {

            int itemQuantity= Integer.parseInt(request.getParameter(itemName));
            order.addToItemQuantityMap(itemName, itemQuantity);
        }

        order.setTotalWithoutTax(
                orderPriceCalculator.calculateTotalWithoutTax
                        (order.getItemQuantityMap()));
        order.setTotalWithTax(
                orderPriceCalculator.calculateTotalWithTax
                        (order.getItemQuantityMap()));

        storeOrder(order, costumer);
        order.setOrderId(getOrderId( order, costumer));
        storeOrderItems(order);

        modelAndView.addObject("order", order);
        modelAndView.addObject("itemPriceMap", menu.getItemPrice());
        return modelAndView;
    }


    //Store the order in the DB (costumerId| totalPrice)
    private int storeOrder(Order order, Costumer costumer){

        Integer reservationId=0;
        for (Integer reservation: costumer.getReservationIdRoomMap().keySet()  ) {
            reservationId=reservation;
            break;
        }

        return orderDAO.insert(costumer ,reservationId, order.getTotalWithTax(), "waiting");

    }


    // get orderId from DB
    private int getOrderId(Order order, Costumer costumer){

        return orderDAO.selectOrderId(costumer, order.getTotalWithTax());
    }


    //Store the order in the DB (orderId| Item | quantity)

    private  int storeOrderItems(Order order){

      return   orderItemsDAO.insert(order.getOrderId(), order.getItemQuantityMap());
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




}
