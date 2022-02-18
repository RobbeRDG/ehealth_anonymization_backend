package be.kul.useraccess.Entity;

import be.kul.useraccess.Utils.Components.ScriptParser.TreeExpressionNodes.ProgramExpression;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ScriptSummary {
    @JsonProperty("summary_id")
    private long summaryId;

    @JsonProperty("program_tree")
    private ProgramExpression programTree;

    @JsonProperty("variable_names")
    private Set<String> variableNames;

    @JsonProperty("output_variable_names")
    private Set<String> outputVariableNames;
}
