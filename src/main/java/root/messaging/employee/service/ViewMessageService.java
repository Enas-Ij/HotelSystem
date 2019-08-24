package root.messaging.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import root.actors.Costumer;
import root.login.Costumer.dao.CostumerDAO;
import root.messaging.Message;
import root.messaging.dao.MessageDAO;
import root.messaging.employee.CostumerServiceAuthentication;
import root.reservation.dao.ReservationDAO;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

public class ViewMessageService {

    @Autowired
    private MessageDAO messageDAO;
    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private CostumerDAO costumerDAO;
    @Autowired
    private CostumerServiceAuthentication costumerServiceAuthentication;

    public ModelAndView viewMessage(HttpSession session, ModelAndView modelAndView){

        if (!costumerServiceAuthentication.isAuthenticated(session)){

            modelAndView.setViewName("NotAuthenticated");
            return modelAndView;
        }

        modelAndView.addObject("messageList", getMessages());
        modelAndView.setViewName("ViewMessagesForEmployee");

        return modelAndView;
    }




    //return a list of messages that status are sent and seen
    private List<Message> getMessages(){

       List<Integer> idList= messageDAO.selectMessageIdByStatus("sent");

       List<Message> messageList= new LinkedList<>();

        for (Integer messageId: idList ) {

            Message message= new Message();

            message.setMessageId(messageId);
            message.setMessageContent(messageDAO.selectMessageContent(messageId));
            message.setSubmittingTime(messageDAO.selectSubmittingTime(messageId));
            message.setReservationId(messageDAO.selectReservationId(messageId));
            message.setCostumer( getCostumer(message.getReservationId()) );
            message.setRoomNumber( getRoomNumber(message.getReservationId()) );

            messageList.add(message);
        }

        return messageList;
    }


    //returns the costumer that sent the message
    private Costumer getCostumer(Integer reservationId){

        int id=reservationDAO.selectCostumerId(reservationId);
        Costumer costumer= costumerDAO.select(id);
        costumer.setPassword("");
        return costumer;
    }


    //returns the room number of the reservation
    private String getRoomNumber(Integer reservationId){

        return reservationDAO.selectReservationRoomNum(reservationId);
    }
}
