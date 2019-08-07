package SystemActors;

import Checkers.PermissionType;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;
    private String password;
    private List<PermissionType> permissions=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PermissionType> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionType> permissions) {

        this.permissions = permissions;
    }

    public void addPermission(PermissionType permissionType){
        permissions.add(permissionType);
    }
}
