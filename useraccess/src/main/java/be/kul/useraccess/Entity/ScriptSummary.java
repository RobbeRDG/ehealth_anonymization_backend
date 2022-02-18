package be.kul.useraccess.Entity;

import be.kul.useraccess.Utils.ScriptSummaryComponents.TreeExpressionNodes.ProgramExpression;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("script_summaries")
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

    public ScriptSummary(String inputString, ProgramExpression programTree, Set<String> variableNames, Set<String> outputVariableNames) {
        this.inputString = inputString;
        this.programTree = programTree;
        this.variableNames = variableNames;
        this.outputVariableNames = outputVariableNames;
    }

    public long getScriptId() {
        return scriptId;
    }
}
