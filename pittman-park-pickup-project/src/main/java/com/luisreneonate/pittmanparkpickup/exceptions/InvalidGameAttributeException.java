package main.java.com.luisreneonate.pittmanparkpickup.exceptions;

public class InvalidGameAttributeException extends RuntimeException {

    private static final long serialVersionUID = 715951940473215587L;

    public InvalidGameAttributeException() {
    }

    public InvalidGameAttributeException(String message) {
        super(message);
    }

    public InvalidGameAttributeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGameAttributeException(Throwable cause) {
        super(cause);
    }
}
