package service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student not found")  // 404
public class StudentNotFoundException extends Exception {

    public StudentNotFoundException() {
        super("The student was not found.\n" +
                "please check the student id or if said student is enrolled");
    }
}
