package be.kul.useraccess.Utils.Exceptions.ExceptionClasses;

public class ScriptParsingException extends RuntimeException{
    public ScriptParsingException(String message) {
        super("Something went wrong parsing the script: " + message);
    }

    public ScriptParsingException() {
        super("Something went wrong parsing the script: Something went wrong");
    }
}
