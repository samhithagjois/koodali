package service;

public class PasswordService {
    /*① PasswordService (NEW, in service)

Handles:

generating temporary passwords

validating password strength

hashing passwords

comparing raw vs hashed passwords

This centralizes all password-related logic so it isn't scattered.

Expected methods:

String generateTemporaryPassword()

boolean isStrongPassword(String rawPassword)

String hashPassword(String rawPassword)

boolean matches(String rawPassword, String hashedPassword)
② PasswordValidator (OPTIONAL, in service or util)

If your rules get long or complex, separate them out.

Method:

boolean validate(String password)

(Not required — you can keep it inside PasswordService if you prefer.)
*/
}
