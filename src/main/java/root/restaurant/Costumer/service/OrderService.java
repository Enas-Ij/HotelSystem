package root.restaurant.Costumer.service;

import root.permission.PermissionType;
import root.restaurant.menu.Menu;
import root.restaurant.order.dao.OrderDAO;
import root.restaurant.order.dao.OrderItemsDAO;
import root.restaurant.order.Order;
import root.restaurant.order.OrderPriceCalculator;
import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class OrderService {

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

        Order order= createOrder(request,
                (Costumer) session.getAttribute("costumer"));

        addOrderToModel(modelAndView, order);

        modelAndView.setViewName("ViewOrderBill");

        return modelAndView;

    }


    // Creates order and call methods to store it the DB
    private Order createOrder(HttpServletRequest request, Costumer costumer){


        Order order= new Order();

        order.setCostumerId(costumer.getId());

        for (String itemName: menu.getItemName()  ) {

            int itemQuantity= Integer.parseInt(request.getParameter(itemName));
            order.addToItemQuantityMap(itemName, itemQuantity);
        }

      order.setReservationId(Integer.parseInt( request.getParameter("reservationId")));

        order.setTotalWithoutTax(
                orderPriceCalculator.calculateTotalWithoutTax
                        (order.getItemQuantityMap()));
        order.setTotalWithTax(
                orderPriceCalculator.calculateTotalWithTax
                        (order.getItemQuantityMap()));

        storeOrder(order, costumer.getId());
        order.setOrderId(getOrderId( order, costumer.getId()));
        storeOrderItems(order);


        return order;
    }



    //return model and view object, add the item price map and the order to the model
    private void addOrderToModel( ModelAndView modelAndView, Order order ){

        modelAndView.addObject("order", order);
        modelAndView.addObject("itemPriceMap", menu.getItemPrice());

    }




    //Store the order in the DB (costumerId| totalPrice)
    private int storeOrder(Order order, Integer costumerId){


        return orderDAO.insert(costumerId ,order.getReservationId(),
                order.getTotalWithTax(), new Date(),"waiting");

    }


    // get orderId from DB
    private int getOrderId(Order order, Integer costumerId){

        return orderDAO.selectOrderId(costumerId, order.getTotalWithTax());
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
