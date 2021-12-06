package be.kul.useraccess.Utils.Components.ScriptParser.Data;

import be.kul.useraccess.Utils.Components.ScriptParser.Antlr.RScriptParser;

public class FloatData extends Data {
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
