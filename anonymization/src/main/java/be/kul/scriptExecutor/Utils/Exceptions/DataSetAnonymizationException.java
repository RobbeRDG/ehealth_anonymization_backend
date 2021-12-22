package be.kul.scriptExecutor.Utils.Exceptions;

public class DataSetAnonymizationException extends RuntimeException {
    public DataSetAnonymizationException(String message) {
        super("Couldn't execute the dataset anonymization: " + message);
    }

    public DataSetAnonymizationException() {
        super("Couldn't execute the dataset anonymization: Something went wrong");
    }
}

