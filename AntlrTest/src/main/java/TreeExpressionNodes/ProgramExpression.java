package TreeExpressionNodes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ProgramExpression extends Expression {
    private ArrayList<Expression> childExpressions;

    public ProgramExpression() {
        this.childExpressions = new ArrayList<>();
    }

    public ProgramExpression(ArrayList<Expression> childExpressions) {
        this.childExpressions = childExpressions;
    }
}
