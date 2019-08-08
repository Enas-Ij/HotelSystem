package root.actors;

import java.util.HashMap;
import java.util.Map;

public class Costumer extends Person {

    private Map<Integer,String> reservationIdRoomMap= new HashMap<>();

    public Map<Integer, String> getReservationIdRoomMap() {
        return reservationIdRoomMap;
    }

    public void setReservationIdRoomMap(Map<Integer, String> reservationIdRoomMap) {
        this.reservationIdRoomMap = reservationIdRoomMap;
    }
}
