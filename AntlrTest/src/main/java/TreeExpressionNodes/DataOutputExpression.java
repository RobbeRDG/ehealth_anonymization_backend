package TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@JsonTypeName("dataOutputExpression")
public class DataOutputExpression extends Expression {
    private ArrayList<AtomExpression> outputVariables;

    public DataOutputExpression(ArrayList<AtomExpression> outputVariables) {
        this.outputVariables = outputVariables;
    }

    public DataOutputExpression() {
    }

    public ArrayList<AtomExpression> getOutputVariables() {
        return outputVariables;
    }

    public void setOutputVariables(ArrayList<AtomExpression> outputVariables) {
        this.outputVariables = outputVariables;
    }
}
