package service.exceptions;

// @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Admin not found")  // 404
public class AdminNotFoundException extends Exception{

    public AdminNotFoundException() {
        super("This admin was not found. Please check if you are logged in LOL");
    }
}
