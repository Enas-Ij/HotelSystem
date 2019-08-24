package root.checkout.service;

import org.springframework.beans.factory.annotation.Autowired;
import root.reservation.dao.ReservationDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BringReservationsService {

    @Autowired
    ReservationDAO reservationDAO;

    public Map<Integer, String> getReservations(int costumerId){


        List<Integer> reservations=reservationDAO.selectCurrentReservationId(costumerId);
        Map<Integer,String> reservationRoomMap= new HashMap<>();

        for (Integer reservation : reservations) {

            String roomNum= reservationDAO.selectReservationRoomNum(reservation);
            reservationRoomMap.put(reservation,roomNum);

        }

        return reservationRoomMap;
    }
}
