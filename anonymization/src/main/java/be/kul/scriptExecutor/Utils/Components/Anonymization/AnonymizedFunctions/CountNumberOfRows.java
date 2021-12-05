package be.kul.scriptExecutor.Utils.Components.Anonymization.AnonymizedFunctions;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.Data;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.IntegerData;

import java.util.HashMap;
import java.util.List;

public class CountNumberOfRows extends AnonymizedFunction{
    private List<HashMap<String,String>> dataSet;

    public CountNumberOfRows(List<HashMap<String,String>> dataSet) {
        super();
        this.dataSet = dataSet;
    }
    @Override
    public Data solve() {
        //Get the number of rows
        int numberOfRows = dataSet.size();

        //Place the result in an integer data object
        return new IntegerData(numberOfRows);
    }
}
