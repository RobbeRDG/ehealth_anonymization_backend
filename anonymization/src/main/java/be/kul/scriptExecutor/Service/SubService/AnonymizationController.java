package be.kul.scriptExecutor.Service.SubService;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.AnonymizedFunctionException;
import be.kul.scriptExecutor.Utils.Enums.FunctionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnonymizationController {
    //TODO make this just a utility bean and inject at start
    private AnonymisedFunctionsController anonymisedFunctionsController;
    private DataSetAnonymizationController dataSetAnonymizationController;

    @Autowired
    public AnonymizationController(AnonymisedFunctionsController anonymisedFunctionsController, DataSetAnonymizationController dataSetAnonymizationController) {
        this.anonymisedFunctionsController = anonymisedFunctionsController;
        this.dataSetAnonymizationController = dataSetAnonymizationController;
    }


    public DataContainer executeAnonymizedFunction(FunctionId functionId, List<DataContainer> argumentsList) {
        switch (functionId) {
            case nrow:
                return anonymisedFunctionsController.executeCountNumberOfRows(argumentsList);
        }
        throw new AnonymizedFunctionException(
                functionId.name() + " is not a supported function"
        );
    }

    public DataContainer anonymizeDataSet(DataContainer researchDataContainer) {
        return dataSetAnonymizationController.anonymizeDataSet(researchDataContainer);
    }

    public String testDPresenceAnonymizationStats(DataContainer researchDataContainer, double deltaStart, double deltaStop, double deltaStep) {
        return dataSetAnonymizationController.testDPresenceAnonymizationStats(researchDataContainer, deltaStart, deltaStop, deltaStep);
    }

    public DataContainer testDPresenceAnonymization(DataContainer researchDataContainer, double delta) {
        return dataSetAnonymizationController.testDPresenceAnonymization(researchDataContainer, delta);
    }
}
