package service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Teacher not found")  // 404
public class TeacherNotFoundException extends Exception {

    public TeacherNotFoundException() {
        super("The teacher was not found.\n" +
                "please check the id and if they are registered");
    }
}
