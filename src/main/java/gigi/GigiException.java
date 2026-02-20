package gigi;

/**
 * Represents custom runtime exceptions specific to the Gigi application.
 * Used for handling logical errors such as invalid date formats or command syntax.
 */
public class GigiException extends RuntimeException {
    public GigiException(String message) {
        super(message);
    }
}
