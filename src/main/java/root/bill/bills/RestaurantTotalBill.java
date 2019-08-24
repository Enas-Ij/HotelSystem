package root.bill.bills;

import org.springframework.beans.factory.annotation.Autowired;
import root.restaurant.order.dao.OrderDAO;

import java.util.List;

public class RestaurantTotalBill {

    @Autowired
    OrderDAO orderDAO;


    // return total price for all orders of the reservation
    public Float calculateTotalRestaurantBill(Integer reservationId){

       List<Float>  priceList= orderDAO.selectOrderPrice(reservationId);
       Float total= 0f;

        for (Float price : priceList) {
            total+= price;
        }

        return total;
    }
}
