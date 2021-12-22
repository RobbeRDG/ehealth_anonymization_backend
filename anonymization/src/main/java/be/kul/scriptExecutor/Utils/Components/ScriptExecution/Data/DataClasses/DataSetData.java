package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@JsonTypeName("dataset_data")
public class DataSetData extends Data {
    @JsonProperty("unanonymized_dataset")
    private List<HashMap<String, String>> unanonymizedDataSet;

    public DataSetData(List<HashMap<String, String>> unanonymizedDataSet) {
        this.unanonymizedDataSet = unanonymizedDataSet;
    }

    public List<HashMap<String, String>> getUnanonymizedDataSet() {
        return unanonymizedDataSet;
    }

    public void setUnanonymizedDataSet(List<HashMap<String, String>> unanonymizedDataSet) {
        this.unanonymizedDataSet = unanonymizedDataSet;
    }
}
