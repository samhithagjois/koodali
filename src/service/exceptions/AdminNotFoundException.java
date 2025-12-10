package service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Admin not found")  // 404
public class AdminNotFoundException extends Exception {

    public AdminNotFoundException() {
        super("This admin was not found. Please check if you are logged in LOL");
    }
}
