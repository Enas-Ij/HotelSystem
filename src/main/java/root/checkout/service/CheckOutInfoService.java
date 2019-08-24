package root.checkout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import root.bill.bills.RestaurantTotalBill;
import root.bill.bills.RoomBill;
import root.bill.dao.BillDAO;
import root.permission.PermissionType;
import root.permission.dao.PermissionCostumerDAO;
import root.reservation.dao.ReservationDAO;

import java.util.Date;
import java.util.List;


public class CheckOutInfoService {


    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    BillDAO billDAO;
    @Autowired
    RoomBill roomBill;
    @Autowired
    RestaurantTotalBill restaurantTotalBill;
    @Autowired
    PermissionCostumerDAO permissionCostumerDAO;

    public ModelAndView checkOutInfo(Integer reservationId, ModelAndView modelAndView){

        checkOutNow(reservationId);
        checkIfHotelGuest(reservationId);
        saveBill(reservationId);

        modelAndView.addObject("reservationDuration", roomBill.findReservationDays(reservationId));
        modelAndView.addObject("roomBill", roomBill.findRoomTotalPrice(reservationId));
        modelAndView.addObject("restaurantBill", restaurantTotalBill.calculateTotalRestaurantBill(reservationId));
        modelAndView.addObject("hotelGuest",checkIfHotelGuest(reservationId));
        return modelAndView;

    }

    //Change the check out date (ToDate) in the DataBase to Today's date
    private void checkOutNow(Integer reservationId){

        reservationDAO.updateToDate(reservationId, new Date());


    }

    // remove the HAS_A_RESERVATION
    //permission if the costumer does not have other reservations
    private boolean checkIfHotelGuest(Integer reservationId){
        int costumerId=reservationDAO.selectCostumerId(reservationId);
        List<Integer> reservation=reservationDAO.selectReservationId(costumerId);
        if (reservation.size()==0|| (reservation.size()==1&& reservation.get(0)==reservationId)){

            permissionCostumerDAO.removePermission(costumerId, PermissionType.HAS_A_RESERVATION);
            return false;
        }
        return true;
    }

    //save the reservation bill in the data base
    private void saveBill(Integer reservationId){

        Float roomTotal= roomBill.findRoomTotalPrice(reservationId);
        Float restaurantTotal= restaurantTotalBill.calculateTotalRestaurantBill(reservationId);
        Float total= roomTotal+ restaurantTotal;

        billDAO.insert(reservationId, roomTotal , restaurantTotal , total );

    }


}
