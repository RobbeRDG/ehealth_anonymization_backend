package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;


@JsonTypeName("function_arguments_expression")
public class FunctionArgumentsExpression extends Expression {
    @JsonProperty("function_arguments")
    private ArrayList<AtomExpression> functionArguments;

    public FunctionArgumentsExpression() {
    }

    public FunctionArgumentsExpression(ArrayList<AtomExpression> functionArguments) {
        this.functionArguments = functionArguments;
    }

    public int getNumberOfFunctionArguments() {
        return functionArguments.size();
    }

    public ArrayList<AtomExpression> getFunctionArguments() {
        return functionArguments;
    }

    public void setFunctionArguments(ArrayList<AtomExpression> functionArguments) {
        this.functionArguments = functionArguments;
    }
}
