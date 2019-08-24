package root.permission;

import root.Exceptions.NoSuchPermission;

public class PermissionStringMapper {

    /*transfer the permissionType to String to be used in functions such as storing the root.permission in db*/
    public String PermissionToString(PermissionType permissionType){

        if (permissionType==PermissionType.LOGGED_IN_COSTUMER){
            return "LOGGED_IN_COSTUMER";
        }

        if (permissionType==PermissionType.HAS_A_RESERVATION){
            return "HAS_A_RESERVATION";
        }

        if (permissionType== PermissionType.ORDER_MANAGEMENT){
            return "ORDER_MANAGEMENT";
        }

        if (permissionType== PermissionType.RESTAURANT_MANAGEMENT){
            return "RESTAURANT_MANAGEMENT";
        }

        if (permissionType== PermissionType.COSTUMER_SERVICE){
            return "COSTUMER_SERVICE";
        }

        throw new NoSuchPermission("No such root.permission as"+permissionType);

    }


    /*transfer the String to permissionType */
    public PermissionType StringToPermission(String permission){

        if (permission.equals("LOGGED_IN_COSTUMER")){
            return PermissionType.LOGGED_IN_COSTUMER;
        }
        if (permission.equals("HAS_A_RESERVATION")){
            return PermissionType.HAS_A_RESERVATION;
        }
        if (permission.equals("ORDER_MANAGEMENT")){
            return PermissionType.ORDER_MANAGEMENT;
        }
        if (permission.equals("RESTAURANT_MANAGEMENT")){
            return PermissionType.RESTAURANT_MANAGEMENT;
        }
        if (permission.equals("COSTUMER_SERVICE")){
            return PermissionType.COSTUMER_SERVICE;
        }



        throw new NoSuchPermission("No such permission as"+permission);

    }
}
