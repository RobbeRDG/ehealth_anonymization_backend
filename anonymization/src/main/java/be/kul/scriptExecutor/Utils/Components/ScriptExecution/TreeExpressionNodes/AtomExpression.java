package be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeExpressionNodes;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.Data;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.enums.AtomExpressionType;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("atomExpression")
public class AtomExpression extends Expression {
    private AtomExpressionType atomExpressionType;
    private String dataStringOfAtom;
    private Data dataOfAtom;

    public AtomExpression(AtomExpressionType atomExpressionType, String dataStringOfAtom, Data dataOfAtom) {
        this.atomExpressionType = atomExpressionType;
        this.dataStringOfAtom = dataStringOfAtom;
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

    public String getDataStringOfAtom() {
        return dataStringOfAtom;
    }

    public void setDataStringOfAtom(String dataStringOfAtom) {
        this.dataStringOfAtom = dataStringOfAtom;
    }

    public Data getDataOfAtom() {
        return dataOfAtom;
    }
}
