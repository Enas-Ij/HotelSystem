package Checkers;

import Exceptions.NoSuchPermission;

public class PermissionStringMapper {

    /*transfer the permissionType to String to be used in functions such as storing the permission in db*/
    public String PremissionToString(PermissionType permissionType){

        if (permissionType==PermissionType.LOGGED_IN_COSTUMER){
            return "LOGGED_IN_COSTUMER";
        }

        if (permissionType==PermissionType.HAS_A_RESERVATION){
            return "HAS_A_RESERVATION";
        }


        throw new NoSuchPermission("No such permission as"+permissionType);

    }


    /*transfer the String to permissionType */
    public PermissionType StringToPremission(String permission){

        if (permission.equals("LOGGED_IN_COSTUMER")){
            return PermissionType.LOGGED_IN_COSTUMER;
        }
        if (permission.equals("HAS_A_RESERVATION")){
            return PermissionType.HAS_A_RESERVATION;
        }

            throw new NoSuchPermission("No such permission as"+permission);

    }
}
