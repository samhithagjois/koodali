package repository;

public class UserCredentialRepository {
    /*⑤ UserCredentialsRepository (NEW, in repository)

Where you'll store usernames + hashed passwords.

Even if you use in-memory maps early on, keep this separate.

Map:

Map<String, CredentialRecord> credentials;


Where CredentialRecord contains:

hashedPassword

userRole

“mustChangePassword” flag

(This flag enforces the first-login password change.)*/
}
