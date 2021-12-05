package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data;

public class BooleanData extends Data {
    private boolean booleanValue;

    public BooleanData(boolean booleanValue) {
        super();
        this.booleanValue = booleanValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }
}
