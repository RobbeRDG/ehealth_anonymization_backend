package be.kul.scriptExecutor.Utils.ScriptAnonymizationResult;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;

import java.util.HashMap;

public class ScriptAnonymizationResult {
    private HashMap<String, DataContainer> results;

    public ScriptAnonymizationResult() {
        results = new HashMap<>();
    }

    public void addResultValue(String dataName, DataContainer dataContainer) {
        results.put(dataName, dataContainer);
    }

}
