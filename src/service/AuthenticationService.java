package service;

public class AuthenticationService {
    /*
    * ④ AuthenticationService (NEW, in service)

Uses repositories + PasswordService to authenticate users.

Methods:

Person login(String username, String rawPassword)

void resetPassword(String userId)

void changePassword(String userId, String newPassword)

This prevents your StudentService/TeacherService/AdminService from being polluted with login logic.
    * */
}
