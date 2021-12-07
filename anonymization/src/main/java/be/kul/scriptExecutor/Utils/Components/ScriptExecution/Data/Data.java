package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
        include=JsonTypeInfo.As.PROPERTY,
        property="name")
@JsonSubTypes({
        @JsonSubTypes.Type(value= DataSetData.class, name="dataSetData"),
        @JsonSubTypes.Type(value= IntegerData.class, name="integerData"),
        @JsonSubTypes.Type(value= StringData.class, name="stringData"),
        @JsonSubTypes.Type(value= VariableData.class, name="variableData"),
        @JsonSubTypes.Type(value= BooleanData.class, name="booleanData"),
        @JsonSubTypes.Type(value= FloatData.class, name="floatData"),
})
public class Data {
}
