package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.AnonymizationInformation;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.AnonymizationInformation.AnonymizationParameters.AnonymizationParameters;
import be.kul.scriptExecutor.Utils.enums.AnonymizationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonTypeName("anonymization_information")
public class AnonymizationInformation {
    @JsonProperty("status")
    private AnonymizationStatus status;
    @JsonProperty("parameters")
    private AnonymizationParameters parameters;


    public AnonymizationInformation(AnonymizationStatus status, AnonymizationParameters parameters) {
        this.status = status;
        this.parameters = parameters;
    }

    public AnonymizationStatus getStatus() {
        return status;
    }

    public void setStatus(AnonymizationStatus status) {
        this.status = status;
    }

    public AnonymizationParameters getParameters() {
        return parameters;
    }

    public void setParameters(AnonymizationParameters parameters) {
        this.parameters = parameters;
    }
}
