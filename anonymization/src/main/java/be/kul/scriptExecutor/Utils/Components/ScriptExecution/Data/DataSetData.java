package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("dataSetData")
public class DataSetData extends Data {
    private List<HashMap<String, String>> unanonymizedDataSet;

    public List<HashMap<String, String>> getUnanonymizedDataSet() {
        return unanonymizedDataSet;
    }

    public void setUnanonymizedDataSet(List<HashMap<String, String>> unanonymizedDataSet) {
        this.unanonymizedDataSet = unanonymizedDataSet;
    }
}
