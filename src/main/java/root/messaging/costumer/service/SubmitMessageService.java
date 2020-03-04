package root.messaging.costumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import root.Authentication.CheckCostumerAuthentication;
import root.messaging.dao.MessageDAO;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class SubmitMessageService {

    @Autowired
    MessageDAO messageDAO;
    @Autowired
    CheckCostumerAuthentication checkCostumerAuthentication;

    public ModelAndView submit(ModelAndView modelAndView, HttpSession session,
                               String costumerMessage,
                               Integer reservationId){

        if (!checkCostumerAuthentication.isReserved(session)){
            modelAndView.setViewName("Login");
            return modelAndView;
        }

        saveMessage(costumerMessage, reservationId);

        modelAndView.setViewName("MessageConfirmed");
        return modelAndView;
    }


    private void saveMessage(String costumerMessage, Integer reservationId){

        Date now= new Date();
        messageDAO.insert(reservationId, costumerMessage, now, "sent" );

    }
}
