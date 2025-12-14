package koodali.repository;

import koodali.model.Administrator;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AdminRepository extends PersonRepository<Administrator> {


    private HashMap<String, Administrator> admins;


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
