package Room;

import DAO.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AvailableRooms {

    @Autowired
     Rooms rooms;
    @Autowired
    ReservationDAO reservationDAO;

    //Find Available room numbers on the dates that the costumer choose
    public List<String> getAvailableRoomNum(Date fromDate, Date toDate) {

        rooms.initializeRooms();
        List<String> availableRoomNum=reservationDAO.selectAvailableRooms(fromDate,toDate);
        return availableRoomNum;
    }


    //Find Available room kinds on the dates that the costumer choose
    public Set<String> getAvailableRoomKinds(Date fromDate, Date toDate) {

        rooms.initializeRooms();
        Set<String> availableRoomKinds= new HashSet<>();
        List<String> availableRoomNum=getAvailableRoomNum(fromDate,toDate);

        for ( String roomNum: availableRoomNum) {

            String roomKind=rooms.getNumberKindMap().get(roomNum);
            availableRoomKinds.add(roomKind);
        }
        return availableRoomKinds;
    }
}
