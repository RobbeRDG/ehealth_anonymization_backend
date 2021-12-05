package be.kul.useraccess.Utils.Exceptions.ExceptionClasses;

public class ScriptFileToStringConversionException extends RuntimeException{
    public ScriptFileToStringConversionException(String message) {
        super(message);
    }

    public ScriptFileToStringConversionException() {
        super("Couldn't extract the script text from the given file");
    }
}
