package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data;

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
}
