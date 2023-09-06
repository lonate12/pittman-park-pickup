package main.java.com.luisreneonate.pittmanparkpickup.exceptions;

public class GameNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5625735586348066809L;

    public GameNotFoundException() {
        super();
    }

    public GameNotFoundException(String message) {
        super(message);
    }

    public GameNotFoundException(Throwable cause) {
        super(cause);
    }

    public GameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
