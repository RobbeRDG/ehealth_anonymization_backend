package be.kul.useraccess.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationParameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("dataset_anonymization_parameters")
public class DataSetAnonymizationParameters extends AnonymizationParameters{
    @JsonProperty("reidentification_risk")
    private double reIdentificationRisk;

    public DataSetAnonymizationParameters() {
        super();
    }

    public double getReIdentificationRisk() {
        return reIdentificationRisk;
    }

    public void setReIdentificationRisk(double reIdentificationRisk) {
        this.reIdentificationRisk = reIdentificationRisk;
    }
}
