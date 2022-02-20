package be.kul.useraccess.Utils.ScriptSummaryComponents.TreeExpressionNodes;

import be.kul.useraccess.Utils.Enums.FunctionId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("function_call_expression")
public class FunctionCallExpression extends Expression{
    @JsonProperty("function_id")
    private FunctionId functionId;

    @JsonProperty("argument_expressions")
    private ArrayList<AtomExpression> argumentExpressions;

    public FunctionCallExpression(FunctionId functionId, ArrayList<AtomExpression> argumentExpressions) {
        this.functionId = functionId;
        this.argumentExpressions = argumentExpressions;
    }

    public FunctionId getFunctionId() {
        return functionId;
    }

    public void setFunctionId(FunctionId functionId) {
        this.functionId = functionId;
    }

    public FunctionCallExpression() {
    }

    public ArrayList<AtomExpression> getArgumentExpressions() {
        return argumentExpressions;
    }

    public void setArgumentExpressions(ArrayList<AtomExpression> argumentExpressions) {
        this.argumentExpressions = argumentExpressions;
    }
}
