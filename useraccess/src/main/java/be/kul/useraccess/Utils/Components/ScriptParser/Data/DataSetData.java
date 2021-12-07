package be.kul.useraccess.Utils.Components.ScriptParser.Data;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.List;

@JsonTypeName("dataSetData")
public class DataSetData extends Data {
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
