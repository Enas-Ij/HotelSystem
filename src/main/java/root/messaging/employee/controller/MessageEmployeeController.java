package root.messaging.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import root.messaging.employee.CostumerServiceAuthentication;
import root.messaging.employee.service.UpdateMessageStatusService;
import root.messaging.employee.service.ViewMessageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MessageEmployeeController {

    @Autowired
    ViewMessageService viewMessageService;
    @Autowired
    CostumerServiceAuthentication costumerServiceAuthentication;
    @Autowired
    UpdateMessageStatusService updateMessageStatusService;

    @RequestMapping("viewMessages")
    public ModelAndView viewMessages(HttpServletRequest request, ModelAndView modelAndView){

        HttpSession session= request.getSession(false);

        modelAndView= viewMessageService.viewMessage(session, modelAndView);

        return modelAndView;

    }

    @RequestMapping(value = "messageStatus", method = RequestMethod.GET)
    public String OrderDelivered(@RequestParam int messageId, @RequestParam String messageStatus,
                                 HttpServletRequest request, HttpServletResponse response){

        HttpSession session= request.getSession(false);
        if (!costumerServiceAuthentication.isAuthenticated(session)){
            return  "NotAuthenticated";
        }

        updateMessageStatusService.updateStatus(messageId, messageStatus);

        try(PrintWriter printWriter= response.getWriter()){

            printWriter.write("ready");

        }
        catch (IOException e){

            System.out.println(e.getMessage());
        }
        return "";
    }

}
