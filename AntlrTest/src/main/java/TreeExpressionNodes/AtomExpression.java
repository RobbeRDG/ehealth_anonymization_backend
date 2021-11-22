package TreeExpressionNodes;

import enums.atomExpressionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtomExpression extends Expression {
    private enums.atomExpressionType atomExpressionType;
    private String stringValueOfAtom;

    public AtomExpression(atomExpressionType atomExpressionType, String stringValueOfAtom) {
        this.atomExpressionType = atomExpressionType;
        this.stringValueOfAtom = stringValueOfAtom;
    }

    public enums.atomExpressionType getAtomExpressionType() {
        return atomExpressionType;
    }
}
