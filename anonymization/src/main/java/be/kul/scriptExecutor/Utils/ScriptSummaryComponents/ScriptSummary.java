package be.kul.scriptExecutor.Utils.ScriptSummaryComponents;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes.ProgramExpression;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Set;

public class ScriptSummary {
    @Id
    @JsonProperty("script_id")
    private long scriptId;

    @JsonProperty("input_string")
    private String inputString;

    @JsonProperty("program_tree")
    private ProgramExpression programTree;

    @JsonProperty("variable_names")
    private Set<String> variableNames;

    @JsonProperty("output_variable_names")
    private Set<String> outputVariableNames;

    public ScriptSummary(long scriptId, String inputString, ProgramExpression programTree, Set<String> variableNames, Set<String> outputVariableNames) {
        this.scriptId = scriptId;
        this.inputString = inputString;
        this.programTree = programTree;
        this.variableNames = variableNames;
        this.outputVariableNames = outputVariableNames;
    }

    public long getScriptId() {
        return scriptId;
    }

    public void setScriptId(long scriptId) {
        this.scriptId = scriptId;
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public ProgramExpression getProgramTree() {
        return programTree;
    }

    public void setProgramTree(ProgramExpression programTree) {
        this.programTree = programTree;
    }

    public Set<String> getVariableNames() {
        return variableNames;
    }

    public void setVariableNames(Set<String> variableNames) {
        this.variableNames = variableNames;
    }

    public Set<String> getOutputVariableNames() {
        return outputVariableNames;
    }

    public void setOutputVariableNames(Set<String> outputVariableNames) {
        this.outputVariableNames = outputVariableNames;
    }
}
