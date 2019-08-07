package Controllers.ControlClasses;

import DAO.ReservationDAO;
import Room.AvailableRooms;
import Room.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Set;


public class FindRoomControl {

    @Autowired
    private  AvailableRooms availableRooms;
    @Autowired
    private  ReservationDAO reservationDAO;
    @Autowired
    private  Rooms rooms;


    public  ModelAndView findRoom( Date toDate, Date fromDate
            , HttpServletRequest request, ModelAndView modelAndView){


        HttpSession session=request.getSession(true);

        modelAndView=findModelAndView(modelAndView,fromDate,toDate);

        session.setAttribute("fromDate",fromDate);
        session.setAttribute("toDate",toDate);

        return modelAndView;
    }


    //Checks if fromDate before ToDate
    private boolean isCorrectDateOrder(Date fromDate,Date toDate){

        if (fromDate.compareTo(toDate)!=-1){
            return false;
        }

        return true;
    }



    //generates the radio button dynamically for each available room kind
    private  String generateHtmlRadiobutton( Set<String> availableRoomKinds){

        String htmlAvblRoomRadio="";
        for (String roomKind:availableRoomKinds) {

            htmlAvblRoomRadio+="<br> <input type=\"radio\" name=\"roomKind\" value=\""
                    +roomKind+"\">Room Kind: "
                    +roomKind+", details: "+ rooms.getKindDetailsMap().get(roomKind)
                    +", price: "+rooms.getKindPriceMap().get(roomKind)+"<br>";
        }
        return htmlAvblRoomRadio;
    }

    private ModelAndView findModelAndView(ModelAndView modelAndView,Date from,Date to){

        if (!isCorrectDateOrder(from,to)){
            modelAndView.addObject("DateMessage","make sure you pick the correct " +
        "dates on the correct order ");
            modelAndView.setViewName("FindRoom");
        return modelAndView ; }

        //Find Available room kinds on the dates that the costumer choose
        Set<String> availableRoomKinds= availableRooms.getAvailableRoomKinds(from,to);
        String htmlAvblRoomRadio=generateHtmlRadiobutton(availableRoomKinds);

        modelAndView.addObject("htmlAvblRoomRadio",htmlAvblRoomRadio);
        modelAndView.setViewName("ReserveARoom");
        return modelAndView;
        }

}
