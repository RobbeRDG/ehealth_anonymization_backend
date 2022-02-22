package be.kul.scriptExecutor.Utils.AnonymizedPersonInformationObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnonymizedValue {
    @JsonProperty("anonymization_level_identifier")
    private String anonymizationLevelIdentifier;

    @JsonProperty("anonymized_value")
    private String anonymizedValue;

    public AnonymizedValue(String anonymizationLevelIdentifier, String anonymizedValue) {
        this.anonymizationLevelIdentifier = anonymizationLevelIdentifier;
        this.anonymizedValue = anonymizedValue;
    }

    public String getAnonymizationLevelIdentifier() {
        return anonymizationLevelIdentifier;
    }

    public void setAnonymizationLevelIdentifier(String anonymizationLevelIdentifier) {
        this.anonymizationLevelIdentifier = anonymizationLevelIdentifier;
    }

    public String getAnonymizedValue() {
        return anonymizedValue;
    }

    public void setAnonymizedValue(String anonymizedValue) {
        this.anonymizedValue = anonymizedValue;
    }
}
