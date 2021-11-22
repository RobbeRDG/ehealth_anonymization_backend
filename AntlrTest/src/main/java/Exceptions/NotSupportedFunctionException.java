package Exceptions;

public class NotSupportedFunctionException extends Exception {
    public NotSupportedFunctionException(String message) {
        super(message);
    }

    public NotSupportedFunctionException() {
        super();
    }
}
