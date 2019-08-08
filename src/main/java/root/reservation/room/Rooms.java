package room;

import root.reservation.room.dao.RoomDetailsDAO;
import root.reservation.room.dao.RoomsDAO;
import root.Exceptions.NoSuchRoomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;


public class Rooms {

    @Autowired
    private RoomsDAO roomsDAO;
    @Autowired
    private RoomDetailsDAO roomDetailsDAO;
    private  List<String> roomKinds;
    private  List<String> roomNums;
    private int initCount=0;
    //HashMap Key:RoomNumber, value:RoomKind
    private  HashMap<String, String> numberKindMap = new HashMap<>();

    //HashMap Key:RoomKind, value:RoomPrice
    private  HashMap<String, Float> kindPriceMap = new HashMap<>();

    //HashMap Key:RoomKind, value:RoomDetails
    private  HashMap<String, String> kindDetailsMap = new HashMap<>();



    //Checks if rooms bean has been initialized before
    public void initializeRooms(){
     if (initCount==0){
         initCount++;
         roomsInitializer();
     }
     return;
    }

    //Initialize the maps values from the DB
    private void roomsInitializer() {

        roomKinds = roomDetailsDAO.selectRoomKinds();
        roomNums = roomsDAO.selectRoomsNumbers();
        fillRoomPrice();
        fillRoomDetails();
        fillRoomNumber();
    }


    //Fill the HashMap numberKindMap< Key:RoomNumber, value:RoomKind> from the DB
    private void fillRoomNumber() {

        for (String num : roomNums) {

            String kind = roomsDAO.selectKind(num);
            numberKindMap.put(num, kind);

        }

    }


    //Fill the HashMap kindPriceMap< Key:RoomKind, value:RoomPrice> from the DB
    private void fillRoomPrice() {


        for (String roomKind : roomKinds) {
            Float price = roomDetailsDAO.selectPrice(roomKind);
            kindPriceMap.put(roomKind, price);
        }

    }


    //Fill the HashMap kindDetailsMap< Key:RoomKind, value:RoomDetails> from the DB
    private void fillRoomDetails() {

        for (String roomKind : roomKinds) {

            String detail = roomDetailsDAO.selectDetail(roomKind);
            kindDetailsMap.put(roomKind, detail);
        }
    }


    public  List<String> getRoomKinds()
    {
        return roomKinds;
    }



    public  List<String> getRoomNums() {
        return roomNums;
    }


    public  HashMap<String, String> getNumberKindMap() {
        return numberKindMap;
    }


    public  HashMap<String, Float> getKindPriceMap() {
        return kindPriceMap;
    }


    public  HashMap<String, String> getKindDetailsMap() {
        return kindDetailsMap;
    }


    public Float getPrice(String roomNum) {

        String roomKind = numberKindMap.get(roomNum);

        if(roomKind==null){
            throw new NoSuchRoomNumber("No room number= "+roomNum);
        }

        return kindPriceMap.get(roomKind);
    }



    public String getDetail(String roomNum) {

        String roomKind = numberKindMap.get(roomNum);

        if(roomKind==null){
            throw new NoSuchRoomNumber("No room number= "+roomNum);
        }

        return kindDetailsMap.get(roomKind);
    }

}