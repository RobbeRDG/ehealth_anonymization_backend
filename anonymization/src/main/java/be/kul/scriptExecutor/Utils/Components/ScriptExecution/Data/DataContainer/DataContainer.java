package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.AnonymizationInformation.AnonymizationInformation;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("data_container")
public class DataContainer {
    @JsonProperty("assigned_data")
    private Data assignedData;
    @JsonProperty("anonymization_information")
    private AnonymizationInformation anonymizationInformation;

    public DataContainer() {
    }

    public Data getAssignedData() {
        return assignedData;
    }

    public void setAssignedData(Data assignedData) {
        this.assignedData = assignedData;
    }

    public AnonymizationInformation getAnonymizationInformation() {
        return anonymizationInformation;
    }

    public void setAnonymizationInformation(AnonymizationInformation anonymizationInformation) {
        this.anonymizationInformation = anonymizationInformation;
    }
}
