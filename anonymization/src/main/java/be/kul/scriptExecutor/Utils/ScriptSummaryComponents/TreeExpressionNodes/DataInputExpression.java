package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("data_input_expression")
public class DataInputExpression extends Expression {
    @JsonProperty("sql_command")
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
