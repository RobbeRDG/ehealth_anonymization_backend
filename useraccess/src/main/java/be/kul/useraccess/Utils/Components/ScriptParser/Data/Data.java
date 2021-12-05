package be.kul.useraccess.Utils.Components.ScriptParser.Data;

import be.kul.useraccess.Utils.Components.ScriptParser.TreeExpressionNodes.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
        include=JsonTypeInfo.As.PROPERTY,
        property="name")
@JsonSubTypes({
        @JsonSubTypes.Type(value= DataSetData.class, name="dataSetData"),
        @JsonSubTypes.Type(value= IntegerData.class, name="integerData"),
        @JsonSubTypes.Type(value= StringData.class, name="stringData"),
        @JsonSubTypes.Type(value= VariableData.class, name="variableData"),
})
public class Data {
}
