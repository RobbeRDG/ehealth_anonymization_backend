package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("assignment_expression")
public class AssignmentExpression extends Expression{
    @JsonProperty("variable")
    private AtomExpression variable;

    @JsonProperty("data")
    private Expression data;

    public AssignmentExpression(AtomExpression variable, Expression data) {
        this.variable = variable;
        this.data = data;
    }

    public AssignmentExpression() {
    }

    public AtomExpression getVariable() {
        return variable;
    }

    public void setVariable(AtomExpression variable) {
        this.variable = variable;
    }

    public Expression getData() {
        return data;
    }

    public void setData(Expression data) {
        this.data = data;
    }
}
