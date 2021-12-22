package be.kul.useraccess.Utils.Components.ScriptParser.TreeExpressionNodes;

import be.kul.useraccess.Utils.Components.ScriptParser.Data.DataClasses.Data;
import be.kul.useraccess.Utils.Components.ScriptParser.Data.DataContainer.DataContainer;
import be.kul.useraccess.Utils.Components.ScriptParser.enums.AtomExpressionType;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("atomExpression")
public class AtomExpression extends Expression {
    private AtomExpressionType atomExpressionType;
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