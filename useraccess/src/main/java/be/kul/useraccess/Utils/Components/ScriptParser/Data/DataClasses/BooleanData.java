package be.kul.useraccess.Utils.Components.ScriptParser.Data.DataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("boolean_data")
public class BooleanData extends Data {
    @JsonProperty("boolean_value")
    private boolean booleanValue;

    public BooleanData(boolean booleanValue) {
        super();
        this.booleanValue = booleanValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
}
