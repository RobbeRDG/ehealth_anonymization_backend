package be.kul.useraccess.Utils.Components.ScriptParser.TreeExpressionNodes;

import be.kul.useraccess.Utils.Components.ScriptParser.enums.FunctionId;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("functionCallExpression")
public class FunctionCallExpression extends Expression{
    private FunctionId functionId;
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
