package root.restaurant.employee.Manger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import root.permission.PermissionType;
import root.restaurant.employee.Manger.RestaurantManagementAuthentication;
import root.restaurant.employee.Manger.service.GetMenuService;
import root.restaurant.employee.Manger.service.MenuEditingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ManageMenuController {

    @Autowired
    GetMenuService getMenuService;
    @Autowired
    RestaurantManagementAuthentication restaurantManagementAuthentication;
    @Autowired
    MenuEditingService menuEditingService;
    List<PermissionType> permissionTypes=new ArrayList<>();


    @RequestMapping(value = "viewMenu")
    public  ModelAndView viewMenu(HttpServletRequest request, ModelAndView modelAndView){

        HttpSession session = request.getSession(false);
        getMenuService.getMenu( modelAndView, session);

        return modelAndView;
    }


    @RequestMapping("addItem")
    public void addItem(HttpServletRequest request, HttpServletResponse response
    , @RequestParam String itemName,  @RequestParam Float  itemPrice,  @RequestParam String section){
        HttpSession session = request.getSession(false);

        if (!restaurantManagementAuthentication.isAuthenticated(session)){
            return;
        }

        if (!permissionTypes.contains(PermissionType.RESTAURANT_MANAGEMENT)){
            permissionTypes.add(PermissionType.RESTAURANT_MANAGEMENT);}

        menuEditingService.addItem(itemName, itemPrice, section, permissionTypes );

        try (PrintWriter writer= response.getWriter()){

            writer.write("done");
        }

        catch (IOException e){

        }
    }


    @RequestMapping("updatePrice")
    public void changeItemPrice(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam String itemName,@RequestParam Float itemPrice){

        HttpSession session = request.getSession(false);

        if (!restaurantManagementAuthentication.isAuthenticated(session)){
            return;
        }

        if (!permissionTypes.contains(PermissionType.RESTAURANT_MANAGEMENT)){
        permissionTypes.add(PermissionType.RESTAURANT_MANAGEMENT);}

        menuEditingService.changeItemPrice(itemName, itemPrice,permissionTypes);
        try (PrintWriter writer= response.getWriter()){

            writer.write("done");
        }

        catch (IOException e){

        }
    }


    @RequestMapping("removeItem")
    public void removeItem(HttpServletRequest request, HttpServletResponse response
    ,@RequestParam String itemName){

        HttpSession session = request.getSession(false);

        if (!restaurantManagementAuthentication.isAuthenticated(session)){
            return;
        }

        if (!permissionTypes.contains(PermissionType.RESTAURANT_MANAGEMENT)){
            permissionTypes.add(PermissionType.RESTAURANT_MANAGEMENT);}

        menuEditingService.removeItem(itemName,permissionTypes);

        try (PrintWriter writer= response.getWriter()){

            writer.write("done");
        }

        catch (IOException e){

        }
    }

}
