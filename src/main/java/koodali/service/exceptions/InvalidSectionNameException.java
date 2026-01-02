package koodali.service.exceptions;

public class InvalidSectionNameException extends RuntimeException{
    public InvalidSectionNameException() {
        super("The name you entered for the section is not valid or not Formatted properly " +
                "Please consult Samhitha");
    }
}
