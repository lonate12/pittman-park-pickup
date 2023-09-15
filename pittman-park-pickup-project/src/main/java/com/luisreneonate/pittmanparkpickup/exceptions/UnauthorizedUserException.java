package main.java.com.luisreneonate.pittmanparkpickup.exceptions;

public class UnauthorizedUserException extends RuntimeException {
    private static final long serialVersionUID = 5089278492406130350L;

    public UnauthorizedUserException() {
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }

    public UnauthorizedUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedUserException(Throwable cause) {
        super(cause);
    }
}
