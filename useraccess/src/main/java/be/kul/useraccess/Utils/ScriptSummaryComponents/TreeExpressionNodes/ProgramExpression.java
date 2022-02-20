package be.kul.useraccess.Utils.ScriptSummaryComponents.TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;


@JsonTypeName("program_expression")
public class ProgramExpression extends Expression {
    @JsonProperty("child_expressions")
    private ArrayList<Expression> childExpressions;

    public ProgramExpression() {
        this.childExpressions = new ArrayList<>();
    }

    public ProgramExpression(ArrayList<Expression> childExpressions) {
        this.childExpressions = childExpressions;
    }

    public ArrayList<Expression> getChildExpressions() {
        return childExpressions;
    }

    public void setChildExpressions(ArrayList<Expression> childExpressions) {
        this.childExpressions = childExpressions;
    }
}
