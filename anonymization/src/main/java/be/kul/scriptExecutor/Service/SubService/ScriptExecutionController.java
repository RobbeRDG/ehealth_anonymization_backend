package be.kul.scriptExecutor.Service.SubService;


import be.kul.scriptExecutor.Entity.AnonymizedPersonInformation;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import be.kul.scriptExecutor.Utils.ScriptAnonymizationResult.ScriptAnonymizationResult;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.ExceptionData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ScriptSummary;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes.ProgramExpression;
import be.kul.scriptExecutor.Utils.TreeVisitor.TreeVisitor;
import be.kul.scriptExecutor.Service.ScriptExecutorService;
import be.kul.scriptExecutor.Utils.Enums.FunctionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class ScriptExecutionController {
    @Autowired
    private ScriptExecutorService scriptExecutorService;
    @Autowired
    private AnonymizationController anonymizationController;

    public ScriptAnonymizationResult executeSummary(ScriptSummary scriptSummary) {
        //Get the script id
        long scriptId = scriptSummary.getScriptId();

        //Extract the program tree from the summary
        ProgramExpression programTree = scriptSummary.getProgramTree();

        //Create a new tree visitor
        TreeVisitor treeVisitor = new TreeVisitor(this);

        //Execute the tree using the visitor
        treeVisitor.visit(programTree);

        //Get the variables
        HashMap<String, DataContainer> variables = treeVisitor.getVariables();

        //Get the output variable names
        Set<String> outputVariableNames = scriptSummary.getOutputVariableNames();

        //Generate the output
        ScriptAnonymizationResult scriptAnonymizationResult = generateAnonymizationResult(scriptId, outputVariableNames, variables);

        return scriptAnonymizationResult;
    }

    private ScriptAnonymizationResult generateAnonymizationResult(long scriptId, Set<String> outputVariableNames, HashMap<String, DataContainer> variables) {
        ScriptAnonymizationResult scriptAnonymizationResult = new ScriptAnonymizationResult();
        scriptAnonymizationResult.setScriptId(scriptId);

        //Add the anonyimsed values to the result object
        for (String outputVariableName : outputVariableNames) {
            //Get the variable data
            DataContainer dataContainer = variables.get(outputVariableName);

            //If the output variable is a dataset it needs to be anonymised
            if (dataContainer.getAssignedData() instanceof DataSetData) {
                try {
                    //Anonymise the dataset
                    dataContainer = anonymizationController.anonymizeDataSet(dataContainer);
                } catch (DataSetAnonymizationException e) {
                    dataContainer = generateExceptionDataContainer(e);
                }
            }

            scriptAnonymizationResult.addResultValue(outputVariableName, dataContainer);
        }

        return scriptAnonymizationResult;
    }

    private DataContainer generateExceptionDataContainer(DataSetAnonymizationException e) {
        //create a datacontainer object
        DataContainer dataContainer = new DataContainer();

        //Create the assigned exception data object
        ExceptionData exceptionData = new ExceptionData(
                "DataSetAnonymizationException",
                e.getMessage()
        );

        //add the assigned data and anonymization information to the container
        dataContainer.setAssignedData(exceptionData);
        dataContainer.setAnonymizationInformation(null);

        return dataContainer;
    }

    public DataContainer getDataSet(String query) {
        //TODO add sql parser here to check query
        return scriptExecutorService.getMedicalDataSet(query);
    }

    public DataContainer executeAnonymizedFunction(FunctionId functionId, List<DataContainer> arguments) {
        return anonymizationController.executeAnonymizedFunction(functionId, arguments);
    }


    public String testDPresenceAnonymizationStats(String sqlQuery, double deltaStart, double deltaStop, double deltaStep) {
        //Get the research dataset
        DataContainer researchDataContainer = getDataSet(sqlQuery);

        return anonymizationController.testDPresenceAnonymizationStats(researchDataContainer, deltaStart, deltaStop, deltaStep);
    }

    public DataContainer testDPresenceAnonymization(String sqlQuery, double delta) {
        //Get the research dataset
        DataContainer researchDataContainer = getDataSet(sqlQuery);

        return anonymizationController.testDPresenceAnonymization(researchDataContainer, delta);
    }
}
