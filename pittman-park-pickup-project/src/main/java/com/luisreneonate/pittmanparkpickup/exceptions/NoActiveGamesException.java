package main.java.com.luisreneonate.pittmanparkpickup.exceptions;

public class NoActiveGamesException extends RuntimeException {
    private static final long serialVersionUID = -6229092375206640571L;

    public NoActiveGamesException() {
    }

    public NoActiveGamesException(String message) {
        super(message);
    }

    public NoActiveGamesException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoActiveGamesException(Throwable cause) {
        super(cause);
    }
}
