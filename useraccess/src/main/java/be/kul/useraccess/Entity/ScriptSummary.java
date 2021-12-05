package be.kul.useraccess.Entity;

import be.kul.useraccess.Utils.Components.ScriptParser.TreeExpressionNodes.ProgramExpression;
import be.kul.useraccess.Utils.Components.ScriptParser.enums.FunctionId;
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
