package TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonTypeName;
import enums.atomExpressionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonTypeName("atomExpression")
public class AtomExpression extends Expression {
    private enums.atomExpressionType atomExpressionType;
    private String stringValueOfAtom;

    public AtomExpression(atomExpressionType atomExpressionType, String stringValueOfAtom) {
        this.atomExpressionType = atomExpressionType;
        this.stringValueOfAtom = stringValueOfAtom;
    }

    public AtomExpression() {
    }

    public enums.atomExpressionType getAtomExpressionType() {
        return atomExpressionType;
    }

    public void setAtomExpressionType(enums.atomExpressionType atomExpressionType) {
        this.atomExpressionType = atomExpressionType;
    }

    public String getStringValueOfAtom() {
        return stringValueOfAtom;
    }

    public void setStringValueOfAtom(String stringValueOfAtom) {
        this.stringValueOfAtom = stringValueOfAtom;
    }
}
