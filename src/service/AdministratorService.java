package service;

import model.Administrator;
import repository.AdminRepository;

public class AdministratorService {
    //createAdmin
    //checkCredentials

    private final AdminRepository adminRepo = new AdminRepository();

    public AdministratorService(){

    }

    public Administrator removeAdmin(String adminID){
       return adminRepo.getAdmins().remove(adminID);
    }
}
