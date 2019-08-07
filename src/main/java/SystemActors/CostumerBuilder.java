package SystemActors;

import Checkers.PermissionType;

import java.util.List;

public class CostumerBuilder extends PersonBuilder{


    private Costumer costumer;

    public CostumerBuilder() {
        costumer = new Costumer();
    }

    public CostumerBuilder setFirstName(String firstName) {
        this.costumer.setFirstName(firstName);
        return this;
    }


    public CostumerBuilder setLastName(String lastName) {
        this.costumer.setLastName(lastName);
        return this;
    }



    public CostumerBuilder setPhoneNum(String phoneNum) {
        this.costumer.setPhoneNum(phoneNum);
        return this;
    }



    public CostumerBuilder setEmail(String email) {
        this.costumer.setEmail(email);
        return this;
    }




    public CostumerBuilder setPermissions(List<PermissionType> permissions) {
        this.costumer.setPermissions(permissions);
        return this;
    }


    @Override
    public PersonBuilder setPassword(String password) {
        this.costumer.setPassword(password);
        return this;
    }

    public Costumer build() {
        return this.costumer;
    }
}
