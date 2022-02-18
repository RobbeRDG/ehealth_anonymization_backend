package be.kul.useraccess.Utils.SupportedFunctions;

import be.kul.useraccess.Utils.Enums.FunctionId;

import java.util.List;

public class SupportedFunctionFingerPrint {
    private FunctionId functionId;
    private String functionName;
    private boolean hasSpecificNumberOfArguments;
    private List<Integer> ListOfPossibleNumberOfArguments;

    public SupportedFunctionFingerPrint(FunctionId functionId, String functionName, boolean hasSpecificNumberOfArguments, List<Integer> listOfPossibleNumberOfArguments) {
        this.functionId = functionId;
        this.functionName = functionName;
        this.hasSpecificNumberOfArguments = hasSpecificNumberOfArguments;
        this. ListOfPossibleNumberOfArguments = listOfPossibleNumberOfArguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public boolean matchesWith(FunctionFingerPrint functionFingerPrint) {
        //Matches name
        boolean matchesName = matchesName(functionFingerPrint);

        //Matches parameter amount if required
        boolean matchesNumberOfParameters = true;
        if (hasSpecificNumberOfArguments) matchesNumberOfParameters = matchesNumberOfParameters(functionFingerPrint);

        return (matchesName && matchesNumberOfParameters);
    }

    private boolean matchesNumberOfParameters(FunctionFingerPrint functionFingerPrint) {
        return ListOfPossibleNumberOfArguments.contains(functionFingerPrint.getNumberOfArguments());
    }

    private boolean matchesName(FunctionFingerPrint functionFingerPrint) {
        return functionName.equals(functionFingerPrint.getFunctionName());
    }

    public FunctionId getFunctionId() {
        return functionId;
    }
}
