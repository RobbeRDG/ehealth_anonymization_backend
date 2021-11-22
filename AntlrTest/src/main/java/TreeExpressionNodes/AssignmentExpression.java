package TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonTypeName("assignmentExpression")
public class AssignmentExpression extends Expression{
    private AtomExpression variable;
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
