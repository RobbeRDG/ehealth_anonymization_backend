package be.kul.scriptExecutor.Utils.ScriptSummaryComponents;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes.ProgramExpression;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
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
}
