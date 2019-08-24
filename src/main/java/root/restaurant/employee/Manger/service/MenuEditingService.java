package root.restaurant.employee.Manger.service;

import org.springframework.beans.factory.annotation.Autowired;
import root.permission.PermissionType;
import root.restaurant.menu.Menu;
import root.restaurant.menu.dao.MenuDAO;

import java.util.List;

public class MenuEditingService {

    @Autowired
    Menu menu;
    @Autowired
    MenuDAO menuDAO;

    public void addItem(String itemName, Float  itemPrice,
                        String section, List<PermissionType> permissionTypeList){

        menuDAO.insertItem(itemName, itemPrice, section);
        menu.initializeMenu(permissionTypeList);
    }


    public void changeItemPrice(String itemName, Float itemPrice,
                                List<PermissionType> permissionTypeList) {


        menuDAO.updatePrice(itemName, itemPrice);
        menu.initializeMenu(permissionTypeList);
    }

    public void removeItem( String itemName, List<PermissionType> permissionTypeList){


        menuDAO.updateSection(itemName,"removed");
        menu.initializeMenu(permissionTypeList);
    }
    }

