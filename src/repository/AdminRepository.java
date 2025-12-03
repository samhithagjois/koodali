package repository;

import model.Administrator;

import java.util.HashMap;

public class AdminRepository extends PersonRepository<Administrator> {


    private HashMap<String,Administrator> admins;


    public AdminRepository() {
        super();
        admins = new HashMap<>();
    }

    public HashMap<String, Administrator> getAdmins() {
        return admins;
    }

    public void setAdmins(HashMap<String, Administrator> admins) {
        this.admins = admins;
    }

}
