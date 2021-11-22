package TreeExpressionNodes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class DataOutputExpression extends Expression {
    private ArrayList<AtomExpression> outputVariables;

    public DataOutputExpression(ArrayList<AtomExpression> outputVariables) {
        this.outputVariables = outputVariables;
    }
}
