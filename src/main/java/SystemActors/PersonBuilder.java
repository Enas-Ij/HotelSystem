package SystemActors;

import Checkers.PermissionType;

import java.util.List;

public abstract class PersonBuilder {

    public abstract PersonBuilder setFirstName(String firstName) ;

    public abstract PersonBuilder setLastName(String lastName) ;

    public abstract PersonBuilder setPhoneNum(String phoneNum);

    public abstract PersonBuilder setEmail(String email) ;

    public abstract PersonBuilder setPermissions(List<PermissionType> permissions) ;

    public abstract PersonBuilder setPassword(String password) ;

    public abstract Person build();


}
