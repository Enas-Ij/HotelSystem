package root.restaurant.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import root.restaurant.order.dao.OrderDAO;

import java.util.Date;

public class OrderDeliveredService {

    @Autowired
    OrderDAO orderDAO;


    //update order status to "delivered", and add the delivery time
    public void orderDelivered (Integer orderId){



        Date now= new Date();
        orderDAO.updateStatus(orderId, "delivered", now);
    }

}
