package be.kul.useraccess.Utils.ScriptSummaryComponents.ContainedData.DataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@JsonTypeName("dataset_data")
public class DataSetData extends Data {
    @JsonProperty("dataset")
    private List<HashMap<String, String>> dataset;

    public DataSetData(List<HashMap<String,String>> dataset) {
        super();
        this.dataset = dataset;
    }

    public List<HashMap<String, String>> getDataset() {
        return dataset;
    }

    public void setDataset(List<HashMap<String, String>> dataset) {
        this.dataset = dataset;
    }
}
