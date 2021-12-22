package be.kul.scriptExecutor.Utils.Components.ScriptExecution;


import be.kul.scriptExecutor.Service.ScriptExecutorService;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.AnonymizationController;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.AnonymizedDataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.Data;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeStructure.ScriptSummary.ScriptSummary;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeStructure.TreeExpressionNodes.ProgramExpression;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeStructure.TreeVisitor.TreeVisitor;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.enums.FunctionId;
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

    public ResponseEntity<HashMap<String, DataContainer>> executeSummary(ScriptSummary scriptSummary) {
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
        HashMap<String, DataContainer> outputVariables = generateOutputVariables(outputVariableNames, variables);

        return new ResponseEntity<>(
                outputVariables,
                HttpStatus.OK
        );
    }

    private HashMap<String, DataContainer> generateOutputVariables(Set<String> outputVariableNames, HashMap<String, DataContainer> variables) {
        HashMap<String, DataContainer> outputVariables = new HashMap<>();
        for (String outputVariableName : outputVariableNames) {
            //Get the variable data
            DataContainer dataContainer = variables.get(outputVariableName);

            //If the output variable is a dataset it needs to be anonymised
            if (dataContainer.getAssignedData() instanceof DataSetData) {
                //Anonymise the dataset
                DataSetData dataSetData = (DataSetData) dataContainer.getAssignedData();
                dataContainer = anonymizationController.anonymizeDataSet(dataContainer);
            }

            outputVariables.put(outputVariableName, dataContainer);
        }

        return outputVariables;
    }

    public DataContainer getDataSet(String query) {
        return scriptExecutorService.getDataSet(query);
    }

    public DataContainer executeAnonymizedFunction(FunctionId functionId, List<DataContainer> arguments) {
        return anonymizationController.executeAnonymizedFunction(functionId, arguments);
    }
}
