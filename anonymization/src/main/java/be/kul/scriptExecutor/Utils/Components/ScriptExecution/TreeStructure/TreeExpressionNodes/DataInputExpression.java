package be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeStructure.TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("dataInputExpression")
public class DataInputExpression extends Expression {
    private AtomExpression sqlCommand;

    public DataInputExpression(AtomExpression sqlCommand) {
        this.sqlCommand = sqlCommand;
    }

    public DataInputExpression() {
    }

    public AtomExpression getSqlCommand() {
        return sqlCommand;
    }

    public void setSqlCommand(AtomExpression sqlCommand) {
        this.sqlCommand = sqlCommand;
    }
}
