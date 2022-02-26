package be.kul.scriptExecutor.Service.SubService;

import be.kul.scriptExecutor.Utils.AnonymizedFunctions.Functions.CountNumberOfRows;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.AnonymizedFunctionException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
