package be.kul.useraccess.Utils.Components.ScriptParser.SupportedFunctions;

public class FunctionFingerPrint {
    private String functionName;
    private int numberOfArguments;

    public FunctionFingerPrint(String functionName, int numberOfArguments) {
        this.functionName = functionName;
        this.numberOfArguments =numberOfArguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public int getNumberOfArguments() {
        return numberOfArguments;
    }
}
