package be.kul.scriptExecutor.Utils.Exceptions;

public class DataSetQueryExecutionException extends RuntimeException {
    public DataSetQueryExecutionException(String message) {
        super("Couldn't execute query on database: " + message);
    }

    public DataSetQueryExecutionException() {
        super("Couldn't execute query on database: Something went wrong");
    }
}
