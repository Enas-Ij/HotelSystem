package root.restaurant.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import root.reservation.dao.ReservationDAO;
import root.restaurant.employee.Common.OrderManagementAuthentication;
import root.restaurant.order.Order;
import root.restaurant.order.dao.OrderDAO;
import root.restaurant.order.dao.OrderItemsDAO;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

public class ViewOrdersService {


    @Autowired
    OrderManagementAuthentication orderManagementAuthentication;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderItemsDAO orderItemsDAO;
    @Autowired
    ReservationDAO reservationDAO;

    public ModelAndView viewOrders(HttpSession session, ModelAndView modelAndView){


        if (!orderManagementAuthentication.authentication(session)){
            modelAndView.setViewName("NotAuthenticated");
            return modelAndView;
        }

        List<Order> waitingOrders =getWaitingOrders();
        modelAndView.addObject("waitingOrders", waitingOrders);
        modelAndView.setViewName("ViewOrdersForEmployee");

        return modelAndView;
    }



    //get a List of Waiting Orders Ids
    private List<Integer> getWaitingOrdersId(){

       return orderDAO.selectOrderIdByStatus("waiting");
    }



    // return a list of the waiting orders, each order contains the following info:
    //  orderId, ReservationId, RoomNumber, ItemQuantityMap
    private List<Order> getWaitingOrders(){

        List<Integer> waitingOrdersId =getWaitingOrdersId();
        List<Order> waitingOrders = new LinkedList<>();

        for (Integer waitingOrderId: waitingOrdersId){

            //create order
           Order order= new Order();

           order.setOrderId(waitingOrderId);
           //getting item quantity map for the order from DB
           order.setItemQuantityMap(orderItemsDAO.selectOrderItemsQuantity(waitingOrderId));
           //getting reservationId and roomNumber for the order from DB
           order.setReservationId( orderDAO.selectReservationId(waitingOrderId));

           String roomNum=reservationDAO.selectReservationRoomNum(order.getReservationId());
           order.setRoomNumber(roomNum);
           waitingOrders.add(order);
        }

        return  waitingOrders ;
    }
}
