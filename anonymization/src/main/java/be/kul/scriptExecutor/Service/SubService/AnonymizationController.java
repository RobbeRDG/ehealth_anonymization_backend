package be.kul.scriptExecutor.Service.SubService;

import be.kul.scriptExecutor.Entity.AnonymizedPersonInformation;
import be.kul.scriptExecutor.Utils.AnonymizedFunctions.AnonymisedFunctionsController;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.AnonymizedFunctionException;
import be.kul.scriptExecutor.Utils.Enums.FunctionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnonymizationController {
    @Autowired
    private ScriptExecutionController scriptExecutionController;

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

    public DataContainer anonymizeDataSet(DataContainer dataSetContainer, String anonymizationLevelIdentifier) {
        return dataSetAnonymizationController.anonymizeDataSet(dataSetContainer,anonymizationLevelIdentifier);
    }

    public AnonymizedPersonInformation getAnonymizedPersonInformation(long personId) {
        return scriptExecutionController.getAnonymizedPersonInformation(personId);
    }
}
