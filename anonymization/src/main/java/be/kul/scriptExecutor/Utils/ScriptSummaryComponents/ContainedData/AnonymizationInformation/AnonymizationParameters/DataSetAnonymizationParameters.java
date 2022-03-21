package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationParameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("dataset_anonymization_parameters")
public class DataSetAnonymizationParameters extends AnonymizationParameters{
    @JsonProperty("average_reidentification_risk")
    private double reIdentificationRisk;
    @JsonProperty("lowest_risk")
    private double lowestRisk;
    @JsonProperty("number_of_affected_by_lowest_risk")
    private double numberOfAffectedByLowestRisk;
    @JsonProperty("highest_risk")
    private double highestRisk;
    @JsonProperty("number_of_affected_by_highest_risk")
    private double numberOfAffectedByHighestRisk;
    @JsonProperty("suppression_count")
    private double suppressionCount;

    public DataSetAnonymizationParameters() {
        super();
    }

    public double getReIdentificationRisk() {
        return reIdentificationRisk;
    }

    public void setReIdentificationRisk(double reIdentificationRisk) {
        this.reIdentificationRisk = reIdentificationRisk;
    }

    public double getLowestRisk() {
        return lowestRisk;
    }

    public void setLowestRisk(double lowestRisk) {
        this.lowestRisk = lowestRisk;
    }

    public double getNumberOfAffectedByLowestRisk() {
        return numberOfAffectedByLowestRisk;
    }

    public void setNumberOfAffectedByLowestRisk(double numberOfAffectedByLowestRisk) {
        this.numberOfAffectedByLowestRisk = numberOfAffectedByLowestRisk;
    }

    public double getHighestRisk() {
        return highestRisk;
    }

    public void setHighestRisk(double highestRisk) {
        this.highestRisk = highestRisk;
    }

    public double getNumberOfAffectedByHighestRisk() {
        return numberOfAffectedByHighestRisk;
    }

    public void setNumberOfAffectedByHighestRisk(double numberOfAffectedByHighestRisk) {
        this.numberOfAffectedByHighestRisk = numberOfAffectedByHighestRisk;
    }

    public double getSuppressionCount() {
        return suppressionCount;
    }

    public void setSuppressionCount(double suppressionCount) {
        this.suppressionCount = suppressionCount;
    }
}
