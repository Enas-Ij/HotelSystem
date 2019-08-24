package root.bill.bills;

import org.springframework.beans.factory.annotation.Autowired;
import root.reservation.dao.ReservationDAO;
import root.reservation.room.Rooms;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RoomBill {

    @Autowired
    ReservationDAO reservationDAO;
    @Autowired
    Rooms rooms;


    //Find the total price of the room= price of one night* number of nights
    public Float findRoomTotalPrice(Integer reservationId){

        String roomNum= reservationDAO.selectReservationRoomNum(reservationId);

        rooms.initializeRooms();
        Float roomPriceBerNight= rooms.getPrice(roomNum);
        Long numOfNights= findReservationDays(reservationId);
        Float total= numOfNights* roomPriceBerNight;

        return total;
    }



    //find the number of days/nights between the beginning of the reservation to the end of it
    public Long findReservationDays(Integer reservationId){

        Date fromDate= reservationDAO.selectFromDate(reservationId);
        Date toDate= reservationDAO.selectToDate(reservationId);

        long diffInMilli = Math.abs(toDate.getTime() - fromDate.getTime());
        long diffDays = TimeUnit.DAYS.convert(diffInMilli, TimeUnit.MILLISECONDS);

        return diffDays;
    }
}
