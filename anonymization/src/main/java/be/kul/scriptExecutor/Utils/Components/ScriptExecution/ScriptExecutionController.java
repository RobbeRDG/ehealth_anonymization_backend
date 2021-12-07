package be.kul.scriptExecutor.Utils.Components.ScriptExecution;


import be.kul.scriptExecutor.Service.ScriptExecutorService;
import be.kul.scriptExecutor.Utils.Components.Anonymization.AnonymizationController;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.Data;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.ScriptSummary.ScriptSummary;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeExpressionNodes.ProgramExpression;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeVisitor.TreeVisitor;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Variable.VariableContainer;
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

    public ResponseEntity<HashMap<String, VariableContainer>> executeSummary(ScriptSummary scriptSummary) {
        //Extract the program tree from the summary
        ProgramExpression programTree = scriptSummary.getProgramTree();

        //Create a new tree visitor
        TreeVisitor treeVisitor = new TreeVisitor(this);

        //Execute the tree using the visitor
        treeVisitor.visit(programTree);

        //Get the variables
        HashMap<String, VariableContainer> variables = treeVisitor.getVariables();

        //Get the output variable names
        Set<String> outputVariableNames = scriptSummary.getOutputVariableNames();

        //Generate the output
        HashMap<String, VariableContainer> outputVariables = new HashMap<>();
        for (String outputVariableName : outputVariableNames) {
            outputVariables.put(outputVariableName, variables.get(outputVariableName));
        }

        return new ResponseEntity<>(
                outputVariables,
                HttpStatus.OK
        );
    }

    public List<HashMap<String,String>> getDataSet(String query) {
        return scriptExecutorService.getDataSet(query);
    }

    public Data executeAnonymizedFunction(FunctionId functionId, List<Data> arguments) {
        return anonymizationController.executeAnonymizedFunction(functionId, arguments);
    }
}
