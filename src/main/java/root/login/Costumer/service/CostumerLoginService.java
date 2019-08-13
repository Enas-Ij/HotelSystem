package root.login.Costumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import root.actors.Costumer;
import root.login.Costumer.dao.CostumerDAO;
import root.permission.dao.PermissionCostumerDAO;
import root.reservation.dao.ReservationDAO;

public class CostumerLoginService {

    @Autowired
    CostumerDAO costumerDAO;
    @Autowired
    PermissionCostumerDAO permissionCostumerDAO;
    @Autowired
    ReservationDAO reservationDAO;


    public Costumer login(String email, String password){


        //Bring the costumer Info from the dataBase
        Costumer costumer=costumerDAO.selectByEmail(email);
        costumer.setPermissions(permissionCostumerDAO.selectList(costumer.getId()));

        return costumer;
    }


}
