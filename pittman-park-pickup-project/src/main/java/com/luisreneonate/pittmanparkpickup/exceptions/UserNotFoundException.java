package main.java.com.luisreneonate.pittmanparkpickup.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1628494652054011319L;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
