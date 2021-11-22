package SupportedFunctions;

import enums.FunctionId;

import java.util.ArrayList;

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
