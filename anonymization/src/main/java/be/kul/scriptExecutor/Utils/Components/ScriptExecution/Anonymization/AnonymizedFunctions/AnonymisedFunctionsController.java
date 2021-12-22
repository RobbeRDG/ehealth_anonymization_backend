package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.AnonymizedFunctions;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.AnonymizedFunctions.Functions.CountNumberOfRows;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.Data;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.AnonymizedFunctionException;

import java.util.List;

public class AnonymisedFunctionsController {

    public DataContainer executeCountNumberOfRows(List<DataContainer> argumentsList) {
        //Get the dataset
        DataSetData dataSetData;
        try {
            dataSetData = (DataSetData) argumentsList.get(0).getAssignedData();
        } catch (Exception e) {
            throw new AnonymizedFunctionException(
                    "The passed argument to the count number of rows function is not a dataset"
            );
        }

        //Make a new function
        CountNumberOfRows countNumberOfRows = new CountNumberOfRows(dataSetData.getUnanonymizedDataSet());

        //Execute function
        return countNumberOfRows.solve();
    }
}
