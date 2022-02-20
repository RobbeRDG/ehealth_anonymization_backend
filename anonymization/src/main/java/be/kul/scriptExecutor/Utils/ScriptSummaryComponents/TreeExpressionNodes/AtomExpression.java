package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes;

import be.kul.scriptExecutor.Utils.Enums.AtomExpressionType;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("atom_expression")
public class AtomExpression extends Expression {
    @JsonProperty("atom_expression_type")
    private AtomExpressionType atomExpressionType;

    @JsonProperty("data_of_atom")
    private DataContainer dataOfAtom;

    public AtomExpression(AtomExpressionType atomExpressionType, DataContainer dataOfAtom) {
        this.atomExpressionType = atomExpressionType;
        this.dataOfAtom = dataOfAtom;
    }

    public AtomExpression() {
    }

    public AtomExpressionType getAtomExpressionType() {
        return atomExpressionType;
    }

    public void setAtomExpressionType(AtomExpressionType atomExpressionType) {
        this.atomExpressionType = atomExpressionType;
    }

    public DataContainer getDataOfAtom() {
        return dataOfAtom;
    }
}