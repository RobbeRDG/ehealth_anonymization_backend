package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;


@JsonTypeName("data_output_expression")
public class DataOutputExpression extends Expression {
    @JsonProperty("output_variables")
    private ArrayList<AtomExpression> outputVariables;

    public DataOutputExpression(ArrayList<AtomExpression> outputVariables) {
        this.outputVariables = outputVariables;
    }

    public DataOutputExpression() {
    }

    public ArrayList<AtomExpression> getOutputVariables() {
        return outputVariables;
    }

    public void setOutputVariables(ArrayList<AtomExpression> outputVariables) {
        this.outputVariables = outputVariables;
    }
}
