package koodali.model;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Person {


    private final List<AdminPermissions> permissions;

    public Administrator(String personID, String firstName, String lastName) {
        super(personID, firstName, lastName);
        permissions = new ArrayList<>();
    }

    /**
     * standard getter
     */
    public List<AdminPermissions> getPermissions() {
        return permissions;
    }

    /**
     * adds a permission to the permissions list.
     * Useful if you don't want to make a List beforehand consisting of less than two elements
     */
    public void addPermission(AdminPermissions permission) {
        permissions.add(permission);
    }
}
