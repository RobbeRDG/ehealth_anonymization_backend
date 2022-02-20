package be.kul.useraccess.Utils.Exceptions.ExceptionClasses;

public class NotSupportedFunctionException extends RuntimeException {
    public NotSupportedFunctionException(String message) {
        super("The given function is not supported by the system: " + message);
    }

    public NotSupportedFunctionException() {
        super("The given function is not supported by the system");
    }
}
