package main.java.com.luisreneonate.pittmanparkpickup.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID = -9006523290165377272L;

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
