package root.reservation.reserved.rooms.controller;

import root.reservation.reserved.rooms.service.ViewReservedRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewReservedRoomsController {

    @Autowired
    private ViewReservedRoomsService viewReservedRoomsControl;


    @RequestMapping(value = "viewReservedRooms", method = RequestMethod.GET)
    public ModelAndView viewReservedRooms(ModelAndView modelAndView,
                                          HttpServletRequest request){

        modelAndView= viewReservedRoomsControl.viewReservedRooms(request,modelAndView);


        return modelAndView;
    }
}
