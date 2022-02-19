package be.kul.useraccess.Entity;


import be.kul.useraccess.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@JsonTypeName("script_anonymization_result")
@Document("script_anonymization_results")
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
