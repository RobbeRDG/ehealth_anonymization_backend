package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
