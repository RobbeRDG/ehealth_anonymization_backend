package be.kul.useraccess.Utils.Components.ScriptParser.SupportedFunctions;

import be.kul.useraccess.Utils.Components.ScriptParser.enums.FunctionId;
import be.kul.useraccess.Utils.Components.ScriptParser.Exceptions.NotSupportedFunctionException;

import java.util.HashMap;

public class FunctionFingerPrintScanner {
    private static HashMap<String, SupportedFunctionFingerPrint> supportedFunctionFingerPrints;

    public static void addSupportedFunctionFingerPrint(String functionName, SupportedFunctionFingerPrint supportedFunctionFingerPrint) {
        if (supportedFunctionFingerPrints == null) supportedFunctionFingerPrints = new HashMap<>();

        supportedFunctionFingerPrints.put(supportedFunctionFingerPrint.getFunctionName(), supportedFunctionFingerPrint);
    }

    public static FunctionId getSupportedFunctionId(FunctionFingerPrint functionFingerPrint) throws NotSupportedFunctionException {
        String functionName = functionFingerPrint.getFunctionName();

        //Test if the function is supported
        if (supportedFunctionFingerPrints.containsKey(functionName)) {
            SupportedFunctionFingerPrint supportedFunctionFingerPrint = supportedFunctionFingerPrints.get(functionName);
            if (!supportedFunctionFingerPrint.matchesWith(functionFingerPrint)) throw new NotSupportedFunctionException(
                    "The function: " + functionName + " is not supported by the anonymizer"
            );
            return supportedFunctionFingerPrint.getFunctionId();
        }
        throw new NotSupportedFunctionException(
                "The function: " + functionName + " is not supported by the anonymizer"
        );
    }
}
