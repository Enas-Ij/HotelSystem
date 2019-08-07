package Controllers;

import Controllers.ControlClasses.ViewReservedRoomsControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewReservedRoomsController {

    @Autowired
    private ViewReservedRoomsControl viewReservedRoomsControl;


    @RequestMapping(value = "viewReservedRooms", method = RequestMethod.GET)
    public ModelAndView viewReservedRooms(ModelAndView modelAndView,
                                          HttpServletRequest request){

        modelAndView= viewReservedRoomsControl.viewReservedRooms(request,modelAndView);

        return modelAndView;
    }
}
