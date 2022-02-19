package be.kul.scriptExecutor.Utils.ScriptAnonymizationResult;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;

@JsonTypeName("script_anonymization_result")
public class ScriptAnonymizationResult {
    @JsonProperty("script_id")
    private Long scriptId;

    @JsonProperty("results")
    private HashMap<String, DataContainer> results;

    public ScriptAnonymizationResult() {
        results = new HashMap<>();
    }

    public void addResultValue(String dataName, DataContainer dataContainer) {
        results.put(dataName, dataContainer);
    }

    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    public Long getScriptId() {
        return scriptId;
    }

    public HashMap<String, DataContainer> getResults() {
        return results;
    }

    public void setResults(HashMap<String, DataContainer> results) {
        this.results = results;
    }
}
