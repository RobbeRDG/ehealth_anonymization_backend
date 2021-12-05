package be.kul.useraccess.Utils.Components.ScriptParser.Data;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("integerData")
public class IntegerData extends Data {
    private int integerValue;

    public IntegerData(int integerValue) {
        super();
        this.integerValue = integerValue;
    }
}
