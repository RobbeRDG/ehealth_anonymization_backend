package be.kul.scriptExecutor.Service.SubService;


import be.kul.scriptExecutor.Utils.ScriptAnonymizationResult.ScriptAnonymizationResult;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ScriptSummary;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes.ProgramExpression;
import be.kul.scriptExecutor.Utils.TreeVisitor.TreeVisitor;
import be.kul.scriptExecutor.Service.ScriptExecutorService;
import be.kul.scriptExecutor.Utils.Enums.FunctionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ScriptAnonymizationResult scriptAnonymizationResult = generateAnonymizationResult(outputVariableNames, variables);

        return scriptAnonymizationResult;
    }

    private ScriptAnonymizationResult generateAnonymizationResult(Set<String> outputVariableNames, HashMap<String, DataContainer> variables) {
        ScriptAnonymizationResult scriptAnonymizationResult = new ScriptAnonymizationResult();

        //Add the anonyimsed values to the result object
        for (String outputVariableName : outputVariableNames) {
            //Get the variable data
            DataContainer dataContainer = variables.get(outputVariableName);

            //If the output variable is a dataset it needs to be anonymised
            if (dataContainer.getAssignedData() instanceof DataSetData) {
                //Anonymise the dataset
                dataContainer = anonymizationController.anonymizeDataSet(dataContainer);
            }

            scriptAnonymizationResult.addResultValue(outputVariableName, dataContainer);
        }

        return scriptAnonymizationResult;
    }

    public DataContainer getDataSet(String query) {
        return scriptExecutorService.getDataSet(query);
    }

    public DataContainer executeAnonymizedFunction(FunctionId functionId, List<DataContainer> arguments) {
        return anonymizationController.executeAnonymizedFunction(functionId, arguments);
    }
}
