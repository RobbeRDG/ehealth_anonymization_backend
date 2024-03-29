package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationParameters.AnonymizationParameters;
import be.kul.scriptExecutor.Utils.Enums.AnonymizationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

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
