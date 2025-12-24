package koodali.repository;

import koodali.model.Administrator;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends PersonRepository<Administrator> {

/*
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
    }*/


}
