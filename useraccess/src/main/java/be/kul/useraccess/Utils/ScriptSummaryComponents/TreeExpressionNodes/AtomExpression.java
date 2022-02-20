package be.kul.useraccess.Utils.ScriptSummaryComponents.TreeExpressionNodes;

import be.kul.useraccess.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.useraccess.Utils.Enums.AtomExpressionType;
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