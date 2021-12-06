package be.kul.useraccess.Utils.Components.ScriptParser.Data;

public class BooleanData extends Data {
    private boolean booleanValue;

    public BooleanData(boolean booleanValue) {
        super();
        this.booleanValue = booleanValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
}