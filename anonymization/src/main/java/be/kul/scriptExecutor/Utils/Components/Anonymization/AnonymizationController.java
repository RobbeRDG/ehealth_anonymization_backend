package be.kul.scriptExecutor.Utils.Components.Anonymization;

import be.kul.scriptExecutor.Utils.Components.Anonymization.AnonymizedFunctions.CountNumberOfRows;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.Data;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.IntegerData;
import be.kul.scriptExecutor.Utils.Exceptions.AnonymizedFunctionException;
import be.kul.scriptExecutor.Utils.enums.FunctionId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnonymizationController {
    public Data executeAnonymizedFunction(FunctionId functionId, List<Data> argumentsList) {
        switch (functionId) {
            case nrow:
                return executeCountNumberOfRows(argumentsList);
        }
        throw new AnonymizedFunctionException(
                functionId.name() + " is not a supported function"
        );
    }

    private Data executeCountNumberOfRows(List<Data> argumentsList) {
        //Get the dataset
        DataSetData dataSetData;
        try {
            dataSetData = (DataSetData) argumentsList.get(0);
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
