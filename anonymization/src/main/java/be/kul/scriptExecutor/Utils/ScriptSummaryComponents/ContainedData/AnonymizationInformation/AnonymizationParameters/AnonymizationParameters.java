package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationParameters;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
        include=JsonTypeInfo.As.PROPERTY,
        property="name")
@JsonSubTypes({
        @JsonSubTypes.Type(value= DataSetAnonymizationParameters.class, name="dataset_anonymization_parameters"),
})
public abstract class AnonymizationParameters {
    public AnonymizationParameters() {
    }
}
