package be.kul.useraccess.Utils.Components.ScriptParser.Data;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("variableData")
public class VariableData extends Data {
    private String variableName;

    public VariableData(String variableName) {
        super();
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }
}
