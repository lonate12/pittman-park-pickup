package main.java.com.luisreneonate.pittmanparkpickup.exceptions;

public class GameAlreadyPlayedException extends RuntimeException {

    private static final long serialVersionUID = 4883813722943449452L;

    public GameAlreadyPlayedException() {
    }

    public GameAlreadyPlayedException(String message) {
        super(message);
    }

    public GameAlreadyPlayedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameAlreadyPlayedException(Throwable cause) {
        super(cause);
    }
}
