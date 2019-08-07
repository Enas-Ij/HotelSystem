package Controllers;

import DAO.CostumerLoginDAO;
import DAO.ReservationDAO;
import DAO.RoomDetailsDAO;
import SystemActors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
public class testController {
    @Autowired
    RoomDetailsDAO roomDetailsDAO;
    @Autowired
    CostumerLoginDAO costumerLoginDAO;
    @Autowired
    ReservationDAO reservationDAO;


    @RequestMapping(value = "/test",method = RequestMethod.POST)
     public String test( HttpServletRequest request){
        HttpSession session=request.getSession(true);
        System.out.println("1 value="+session.getAttribute("numberOfTimes"));
        if (session.getAttribute("numberOfTimes")==null){
            session.setAttribute("numberOfTimes",(Integer) 1);
            System.out.println("2  value="+session.getAttribute("numberOfTimes"));
            return "test";
        }
        session.setAttribute
                ("numberOfTimes", (int)(session.getAttribute("numberOfTimes"))+(Integer)1);
        System.out.println("3  value="+session.getAttribute("numberOfTimes"));
        List<String> list=roomDetailsDAO.selectRoomKinds();
        System.out.println(list);
        return "test";
      }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test2(@RequestParam(value="TO") @DateTimeFormat(pattern="yyyy-MM-dd") Date To ,
                        @RequestParam(value="From") @DateTimeFormat(pattern="yyyy-MM-dd") Date From
            , HttpServletRequest request){
        System.out.println(To+" "+From);
        HttpSession session=request.getSession(true);
        return To+" "+From;
    }

    @RequestMapping("testDB")
    public  String test3(){
        Costumer costumer=new Costumer();
        costumer.setId(1);
        reservationDAO.insert(costumer,"101",new Date(),new Date());
        costumerLoginDAO.insert(costumer,new Date());
        return "test";
    }

    @RequestMapping("test4")
    public  void test4(HttpServletResponse response,HttpServletRequest request){

        Map<String,String> stringStringMap=request.getParameterMap();
        Float txt=0f;
        for (String par:stringStringMap.keySet() ) {
             txt=Float.parseFloat(request.getParameter(par));
        }

        float price= txt+txt*.20f;
        try(PrintWriter printWriter= response.getWriter()){
        printWriter.write(price+"");}
        catch (IOException e){

        }

    }
}
