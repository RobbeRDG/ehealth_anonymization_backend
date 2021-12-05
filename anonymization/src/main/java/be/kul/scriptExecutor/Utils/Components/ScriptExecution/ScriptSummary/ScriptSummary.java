package be.kul.scriptExecutor.Utils.Components.ScriptExecution.ScriptSummary;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeExpressionNodes.ProgramExpression;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ScriptSummary {
    private long summaryId;
    private ProgramExpression programTree;
    private Set<String> variableNames;
    private Set<String> outputVariableNames;
}
