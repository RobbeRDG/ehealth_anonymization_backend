package be.kul.useraccess.Utils.Components.ScriptParser.Data;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
import java.util.Map;

@JsonTypeName("dataSetData")
public class DataSetData extends Data {
    private List<Map<String, Object>> dataSetValue;

    public DataSetData(List<Map<String, Object>> dataSetValue) {
        super();
        this.dataSetValue = dataSetValue;
    }
}
