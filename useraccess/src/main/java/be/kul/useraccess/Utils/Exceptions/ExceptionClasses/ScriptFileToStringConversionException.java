package be.kul.useraccess.Utils.Exceptions.ExceptionClasses;

public class ScriptFileToStringConversionException extends RuntimeException{
    public ScriptFileToStringConversionException(String message) {
        super("Couldn't convert specified script file to string: " + message);
    }

    public ScriptFileToStringConversionException() {
        super("Couldn't convert specified script file to string: Something went wrong");
    }
}
