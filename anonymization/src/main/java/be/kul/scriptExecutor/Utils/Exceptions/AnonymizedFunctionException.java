package be.kul.scriptExecutor.Utils.Exceptions;

public class AnonymizedFunctionException extends RuntimeException {
    public AnonymizedFunctionException(String message) {
        super("Couldn't execute anonymized function: " + message);
    }

    public AnonymizedFunctionException() {
        super("Couldn't execute anonymized function: Something went wrong");
    }
}
