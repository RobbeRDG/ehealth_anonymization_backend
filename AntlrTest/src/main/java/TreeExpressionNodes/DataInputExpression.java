package TreeExpressionNodes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataInputExpression extends Expression {
    private AtomExpression sqlCommand;

    public DataInputExpression(AtomExpression sqlCommand) {
        this.sqlCommand = sqlCommand;
    }
}
