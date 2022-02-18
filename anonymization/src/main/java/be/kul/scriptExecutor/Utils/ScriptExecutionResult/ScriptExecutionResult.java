package be.kul.scriptExecutor.Utils.ScriptExecutionResult;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;

import java.util.HashMap;

public class ScriptExecutionResult {
    private HashMap<String, DataContainer> results;

    public ScriptExecutionResult() {
        results = new HashMap<>();
    }

    public void addResultValue(String dataName, DataContainer dataContainer) {
        results.put(dataName, dataContainer);
    }

}
