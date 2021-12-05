package be.kul.useraccess.Utils.Components.ScriptParser.TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;


@JsonTypeName("programExpression")
public class ProgramExpression extends Expression {
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
