package model;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Person{


    public List<AdminPermissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<AdminPermissions> permissions) {
        this.permissions = permissions;
    }

    private List<AdminPermissions> permissions;
    public Administrator(String personID, String firstName, String lastName) {
        super(personID, firstName, lastName);
        permissions = new ArrayList<>();
    }
}
