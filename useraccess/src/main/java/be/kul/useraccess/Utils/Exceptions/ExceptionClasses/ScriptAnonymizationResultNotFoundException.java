package be.kul.useraccess.Utils.Exceptions.ExceptionClasses;

public class ScriptAnonymizationResultNotFoundException extends RuntimeException{
    public ScriptAnonymizationResultNotFoundException() {
        super("The requested script anonymization result could not be found: Something went wrong");
    }

    public ScriptAnonymizationResultNotFoundException(String message) {
        super("The requested script anonymization result could not be found: " + message);
    }
}
