package service.exceptions;

public class WrongPasswordException extends Exception{


    public WrongPasswordException() {
        super("Wrong password. Try again");
    }
}
