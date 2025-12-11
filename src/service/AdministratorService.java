package service;

import model.AdminPermissions;
import model.Administrator;
import repository.AdminRepository;
import service.exceptions.AdminNotFoundException;

import java.util.Optional;

public class AdministratorService extends PersonService<Administrator>{
    //createAdmin
    //checkCredentials

    private final AdminRepository adminRepo;

    public AdministratorService(AdminRepository adminRepo) {
        super(adminRepo);
        this.adminRepo = adminRepo;
    }

    public Administrator findByID(String adminID){
        return adminRepo
                .findByID(adminID)
                .orElseThrow(AdminNotFoundException::new);
    }

    public Administrator addAdminRights(Administrator admin, AdminPermissions permission){
        if(!admin.getPermissions().contains(permission)){
            admin.addPermission(permission);
            return admin;
        }
        return admin;
    }

    public Administrator removeAdminRights(Administrator admin, AdminPermissions permission){
        if(admin.getPermissions().contains(permission)){
            admin.getPermissions().remove(permission);
            return admin;
        }
        return admin;
    }

}
