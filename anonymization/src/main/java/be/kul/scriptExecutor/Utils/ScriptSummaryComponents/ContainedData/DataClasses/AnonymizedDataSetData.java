package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.List;

@JsonTypeName("anonymized_dataset_data")
public class AnonymizedDataSetData extends Data {
    @JsonProperty("anonymized_dataset")
    private List<HashMap<String, String>> anonymizedDataSet;

    public AnonymizedDataSetData(List<HashMap<String, String>> anonymizedDataSet) {
        this.anonymizedDataSet = anonymizedDataSet;
    }

    public List<HashMap<String, String>> getAnonymizedDataSet() {
        return anonymizedDataSet;
    }

    public void setAnonymizedDataSet(List<HashMap<String, String>> anonymizedDataSet) {
        this.anonymizedDataSet = anonymizedDataSet;
    }
}
