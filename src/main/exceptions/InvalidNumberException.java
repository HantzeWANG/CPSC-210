package exceptions;

public class InvalidNumberException extends Exception {
    public InvalidNumberException() {}

    public InvalidNumberException(String msg) {
        super(msg);
    }

}
