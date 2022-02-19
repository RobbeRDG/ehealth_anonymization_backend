package be.kul.useraccess.Entity;


import be.kul.useraccess.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document("script_anonymization_results")
public class ScriptAnonymizationResult {
    @Transient
    public static final String SEQUENCE_NAME = "script_anonymization_results";

    @Id
    private Long scriptId;
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
}
