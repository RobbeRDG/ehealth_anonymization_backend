package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Variable;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.Data;

public class VariableContainer {
    private String variableName;
    private Data assignedData;

    public VariableContainer(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Data getAssignedData() {
        return assignedData;
    }

    public void setAssignedData(Data assignedData) {
        this.assignedData = assignedData;
    }
}
