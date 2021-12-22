package be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeStructure.TreeVisitor;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.AnonymizationInformation.AnonymizationInformation;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.*;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.ScriptExecutionController;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeStructure.TreeExpressionNodes.*;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeStructure.enums.AtomExpressionType;
import be.kul.scriptExecutor.Utils.enums.AnonymizationStatus;
import be.kul.scriptExecutor.Utils.enums.FunctionId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeVisitor extends AbstractTreeVisitor<Expression> {
    private ScriptExecutionController scriptExecutionController;
    private HashMap<String, DataContainer> variables;


    public TreeVisitor(ScriptExecutionController scriptExecutionController) {
        super();
        this.scriptExecutionController = scriptExecutionController;
        variables = new HashMap<>();
    }

    public HashMap<String, DataContainer> getVariables() {
        return variables;
    }

    @Override
    public Expression visit(AssignmentExpression expression) {
        //Get the name of the variable the data is going to be assigned to
        VariableData variableData = (VariableData) expression.getVariable().getDataOfAtom().getAssignedData();
        String variableName = variableData.getVariableName();

        //Visit the data expression
        AtomExpression atomExpression = (AtomExpression) visit(expression.getData());

        //Get the variable data from the atom expression
        DataContainer dataContainer;
        switch (atomExpression.getAtomExpressionType()) {
            /*
            case ID:
                dataContainer = followVariableToAssignedData(atomExpression.getDataOfAtom());
                break;

             */
            case STRING:
            case INT:
            case FLOAT:
            case BOOLEAN:
            case DATASET:
                dataContainer = atomExpression.getDataOfAtom();
                break;
            default:
                throw new RuntimeException("Atom expression has no valid type: this shouldn't happen");
        }

        //Put the new variable in the map
        variables.put(variableName, dataContainer);

        //Return atom expression instead
        return new AtomExpression(atomExpression.getAtomExpressionType(), dataContainer);
    }

    @Override
    public Expression visit(AtomExpression expression) {
        return expression;
    }

    @Override
    public Expression visit(DataInputExpression expression) {
        //Extract the sql query
        StringData queryData = (StringData) expression.getSqlCommand().getDataOfAtom().getAssignedData();
        String query = queryData.getStringValue();

        //Get the requested data
        DataContainer unanonymizedDataSetContainer = scriptExecutionController.getDataSet(query);


        //Create a new Dataset atom expression
        AtomExpression atomExpression = new AtomExpression(AtomExpressionType.DATASET, unanonymizedDataSetContainer);

        return atomExpression;
    }

    @Override
    public Expression visit(DataOutputExpression expression) {
        return expression;
    }

    @Override
    public Expression visit(FunctionArgumentsExpression expression) {
        return expression;
    }

    @Override
    public Expression visit(FunctionCallExpression expression) {
        //Get the argument expressions
        List<AtomExpression> argumentExpressions = expression.getArgumentExpressions();

        //Build the argument list
        List<DataContainer> argumentData = new ArrayList<>();
        for (AtomExpression atomExpression : argumentExpressions) {
            switch (atomExpression.getAtomExpressionType()) {
                case ID:
                    argumentData.add(followVariableToAssignedData(atomExpression.getDataOfAtom()));
                    break;
                case STRING:
                case BOOLEAN:
                case INT:
                case FLOAT:
                case DATASET:
                    argumentData.add(atomExpression.getDataOfAtom());
                    break;
                default:
                    throw new RuntimeException("Atom expression has no valid type: this shouldn't happen");
            }
        }

        //Get the functionId
        FunctionId functionId = expression.getFunctionId();

        //Execute the function
        DataContainer functionResult = scriptExecutionController.executeAnonymizedFunction(functionId, argumentData);

        //Build a new atomexpression
        AtomExpression atomExpression;
        if (functionResult.getAssignedData() instanceof BooleanData) {
            atomExpression = new AtomExpression(AtomExpressionType.BOOLEAN, functionResult);
        } else if (functionResult.getAssignedData() instanceof DataSetData) {
            atomExpression = new AtomExpression(AtomExpressionType.DATASET, functionResult);
        } else if (functionResult.getAssignedData() instanceof FloatData) {
            atomExpression = new AtomExpression(AtomExpressionType.FLOAT, functionResult);
        } else if (functionResult.getAssignedData() instanceof IntegerData) {
            atomExpression = new AtomExpression(AtomExpressionType.INT, functionResult);
        } else if (functionResult.getAssignedData() instanceof StringData) {
            atomExpression = new AtomExpression(AtomExpressionType.STRING, functionResult);
        } else {
            throw new RuntimeException("function result data has no valid type: this shouldn't happen");
        }

        return atomExpression;
    }


    @Override
    public Expression visit(ProgramExpression expression) {
        for (Expression childExpression : expression.getChildExpressions()) {
            //If it's an output expression, ignore
            //else visit the child expressions
            if (!(childExpression instanceof DataOutputExpression)) {
                visit(childExpression);
            }
        }
        return expression;
    }

    private DataContainer followVariableToAssignedData(DataContainer dataContainer) {
        //Get the data from the container
        VariableData variableData = (VariableData) dataContainer.getAssignedData();

        //Get the variable name
        String variableName = variableData.getVariableName();

        //Get the variable from the variables map
        DataContainer pointedDataContainer = variables.get(variableName);

        //Get the assigned data
        Data assignedData = pointedDataContainer.getAssignedData();

        //If the assigned data is a variable, recursively find the next pointed variable
        if (assignedData instanceof VariableData) {
            return followVariableToAssignedData(pointedDataContainer);
        }

        //If the data is not a variable, return the data
        return pointedDataContainer;
        /*
        if (assignedData instanceof BooleanData) {
            return (BooleanData) assignedData;
        } else if (assignedData instanceof DataSetData) {
            return (DataSetData) assignedData;
        } else if (assignedData instanceof FloatData) {
            return (FloatData) assignedData;
        } else if (assignedData instanceof IntegerData) {
            return (IntegerData) assignedData;
        } else {
            return (StringData) assignedData;
        }
         */
    }
}
