package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.AnonymizedFunctions.AnonymisedFunctionsController;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.DataSetAnonymizationController;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects.ArxDataSetGenerator;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects.DataSetAnonymizationResult;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.Data;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.AnonymizedFunctionException;
import be.kul.scriptExecutor.Utils.enums.FunctionId;
import org.deidentifier.arx.ARXResult;
import org.deidentifier.arx.Data.DefaultData;
import org.deidentifier.arx.DataHandle;
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
