package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
        include=JsonTypeInfo.As.PROPERTY,
        property="name")
@JsonSubTypes({
        @JsonSubTypes.Type(value= DataSetData.class, name="dataset_data"),
        @JsonSubTypes.Type(value= IntegerData.class, name="integer_data"),
        @JsonSubTypes.Type(value= StringData.class, name="string_data"),
        @JsonSubTypes.Type(value= VariableData.class, name="variable_data"),
        @JsonSubTypes.Type(value= BooleanData.class, name="boolean_data"),
        @JsonSubTypes.Type(value= FloatData.class, name="float_data"),
        @JsonSubTypes.Type(value= AnonymizedDataSetData.class, name="anonymized_dataset_data"),
        @JsonSubTypes.Type(value= ExceptionData.class, name="exception_data"),
})
public class Data {
}
