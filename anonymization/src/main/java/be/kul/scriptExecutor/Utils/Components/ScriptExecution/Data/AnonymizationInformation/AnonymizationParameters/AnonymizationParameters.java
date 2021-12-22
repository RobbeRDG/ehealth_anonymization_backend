package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.AnonymizationInformation.AnonymizationParameters;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
        include=JsonTypeInfo.As.PROPERTY,
        property="name")
@JsonSubTypes({
        @JsonSubTypes.Type(value= DataSetAnonymizationParameters.class, name="dataset_anonymization_parameters"),
})
public abstract class AnonymizationParameters {
}
