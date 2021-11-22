package TreeExpressionNodes;

import enums.FunctionId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class FunctionCallExpression extends Expression{
    private FunctionId functionId;
    private ArrayList<AtomExpression> argumentExpressions;

    public FunctionCallExpression(FunctionId functionId, ArrayList<AtomExpression> argumentExpressions) {
        this.functionId = functionId;
        this.argumentExpressions = argumentExpressions;
    }
}
