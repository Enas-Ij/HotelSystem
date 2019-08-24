package root.Authentication;

import root.Exceptions.NotAuthenticatedException;
import root.permission.PermissionType;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CheckAuthentication {


    public boolean isAuthenticated(List<PermissionType> permissionTypeList,
                                   PermissionType permissionType){

        if (permissionTypeList.contains(permissionType)){
            return true;
        }

        else{
           try {
               throw new NotAuthenticatedException("401 not Authenticated");

           }
           catch (NotAuthenticatedException e){
               return false;
           }

        }
    }

    public boolean hasSession(HttpSession session){

        if (session==null){
            try {
                throw new NotAuthenticatedException("401 not Authenticated");

            }
            catch (NotAuthenticatedException e){
                return false;
            }
        }
        return true;
    }




}
