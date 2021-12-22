package be.kul.useraccess.Utils.Components.ScriptParser.Data.DataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("float_data")
public class FloatData extends Data {
    @JsonProperty("float_value")
    private float floatValue;

    public FloatData(float floatValue) {
        super();
        this.floatValue = floatValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }
}
