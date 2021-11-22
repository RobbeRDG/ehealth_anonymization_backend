package TreeExpressionNodes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignmentExpression extends Expression{
    private AtomExpression variable;
    private Expression data;

    public AssignmentExpression(AtomExpression variable, Expression data) {
        this.variable = variable;
        this.data = data;
    }
}
