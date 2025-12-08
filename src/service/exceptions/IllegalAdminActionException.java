package service.exceptions;
// @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Illegal Admin Activity")  // 404
public class IllegalAdminActionException extends Exception{


    public IllegalAdminActionException() {
        super("You cannot complete this action as you are not authorized for this section." +
                "If you believe you have the permissions needed, please contact Samhitha");
    }
}
