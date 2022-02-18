package be.kul.scriptExecutor.Utils.AnonymizedFunctions.Functions;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.IntegerData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationInformation;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Enums.AnonymizationStatus;

import java.util.HashMap;
import java.util.List;

public class CountNumberOfRows extends AnonymizedFunction{
    private List<HashMap<String,String>> dataSet;

    public CountNumberOfRows(List<HashMap<String,String>> dataSet) {
        super();
        this.dataSet = dataSet;
    }
    @Override
    public DataContainer solve() {
        //Get the number of rows
        int numberOfRows = dataSet.size();

        //Set the dataobject
        IntegerData data = new IntegerData(numberOfRows);

        //Set the anonymization information
        AnonymizationInformation anonymizationInformation = new AnonymizationInformation(AnonymizationStatus.ANONYMIZED_FUNCTION, null);

        //Place the result in a container
        DataContainer result = new DataContainer();
        result.setAssignedData(data);
        result.setAnonymizationInformation(anonymizationInformation);

        return result;
    }
}
