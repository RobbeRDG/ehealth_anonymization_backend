package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonTypeName("integer_data")
public class IntegerData extends Data {
    @JsonProperty("integer_value")
    private int integerValue;

    public IntegerData(int integerValue) {
        super();
        this.integerValue = integerValue;
    }

    public int getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(int integerValue) {
        this.integerValue = integerValue;
    }
}
