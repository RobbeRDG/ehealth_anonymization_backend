package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationParameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("dataset_anonymization_parameters")
public class DataSetAnonymizationParameters extends AnonymizationParameters{
    @JsonProperty("average_journalist_risk")
    private double averageJournalistRisk;
    @JsonProperty("highest_journalist_risk")
    private double highestJournalistRisk;
    @JsonProperty("suppression_count")
    private int suppressionCount;

    public double getAverageJournalistRisk() {
        return averageJournalistRisk;
    }

    public void setAverageJournalistRisk(double averageJournalistRisk) {
        this.averageJournalistRisk = averageJournalistRisk;
    }

    public double getHighestJournalistRisk() {
        return highestJournalistRisk;
    }

    public void setHighestJournalistRisk(double highestJournalistRisk) {
        this.highestJournalistRisk = highestJournalistRisk;
    }

    public int getSuppressionCount() {
        return suppressionCount;
    }

    public void setSuppressionCount(int suppressionCount) {
        this.suppressionCount = suppressionCount;
    }
}
