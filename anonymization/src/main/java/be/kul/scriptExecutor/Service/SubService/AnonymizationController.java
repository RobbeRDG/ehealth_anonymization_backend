package be.kul.scriptExecutor.Service.SubService;

import be.kul.scriptExecutor.Utils.AnonymizedFunctions.AnonymisedFunctionsController;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.DataSetAnonymizationController;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.AnonymizedFunctionException;
import be.kul.scriptExecutor.Utils.Enums.FunctionId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnonymizationController {
    private static final AnonymisedFunctionsController anonymisedFunctionsController = new AnonymisedFunctionsController();
    private static final DataSetAnonymizationController dataSetAnonymizationController = new DataSetAnonymizationController();


    public DataContainer executeAnonymizedFunction(FunctionId functionId, List<DataContainer> argumentsList) {
        switch (functionId) {
            case nrow:
                return anonymisedFunctionsController.executeCountNumberOfRows(argumentsList);
        }
        throw new AnonymizedFunctionException(
                functionId.name() + " is not a supported function"
        );
    }

    public DataContainer anonymizeDataSet(DataContainer dataSetContainer) {
        return dataSetAnonymizationController.anonymizeDataSet(dataSetContainer);
    }
}
