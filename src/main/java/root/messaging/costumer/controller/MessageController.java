package root.messaging.costumer.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import root.messaging.costumer.service.MessagingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import root.messaging.costumer.service.SubmitMessageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MessageController {

    @Autowired
    MessagingSystemService messagingSystemService;
    @Autowired
    SubmitMessageService submitMessageService;

    @RequestMapping("contact-service")
    public ModelAndView goToMessagingView(ModelAndView modelAndView, HttpServletRequest request){

        HttpSession session= request.getSession(false);
        modelAndView= messagingSystemService.getMessagingSystem(modelAndView, session);

        return modelAndView;
    }



    @RequestMapping (value = "submitMessage", method = RequestMethod.POST)
    public ModelAndView submitMessage(ModelAndView modelAndView, HttpServletRequest request,
                                      @RequestParam String costumerMessage,
                                      @RequestParam Integer reservationId){

        HttpSession session= request.getSession(false);
        modelAndView= submitMessageService.submit(modelAndView, session, costumerMessage, reservationId);
        return modelAndView;
    }
}

