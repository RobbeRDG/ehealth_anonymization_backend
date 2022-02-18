package be.kul.useraccess.Utils.ScriptSummaryComponents.ContainedData.DataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.List;

@JsonTypeName("dataset_data")
public class DataSetData extends Data {
    @JsonProperty("unanonymized_dataset")
    private List<HashMap<String, String>> unanonymizedDataSet;

    public DataSetData(List<HashMap<String,String>> unanonymizedDataSet) {
        super();
        this.unanonymizedDataSet = unanonymizedDataSet;
    }

    public List<HashMap<String, String>> getUnanonymizedDataSet() {
        return unanonymizedDataSet;
    }

    public void setUnanonymizedDataSet(List<HashMap<String, String>> unanonymizedDataSet) {
        this.unanonymizedDataSet = unanonymizedDataSet;
    }
}
