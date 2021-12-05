package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("stringData")
public class StringData extends Data {
    private String stringValue;

    public StringData(String stringValue) {
        super();
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
