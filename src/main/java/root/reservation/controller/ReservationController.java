package root.reservation.controller;

import root.reservation.service.FindRoomService;
import root.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class ReservationController {

    @Autowired
    FindRoomService findRoomControl;
    @Autowired
    ReservationService reservationControl;


    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView findRoom(
            @RequestParam(value = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate
            , @RequestParam(value = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate
            , HttpServletRequest request, ModelAndView modelAndView) {

        // session
        HttpSession session= request.getSession(true);
        modelAndView = findRoomControl.findRoom(toDate,fromDate,request,modelAndView);


        return modelAndView;
    }



    @RequestMapping(value = "/reserve",method = RequestMethod.GET)
    public  ModelAndView reserve(
            @RequestParam String roomKind
            , HttpServletRequest request, ModelAndView modelAndView){
        HttpSession httpSession=request.getSession(true);
        modelAndView = reservationControl.reserve(roomKind,request,modelAndView);

        return modelAndView;
    }
}
