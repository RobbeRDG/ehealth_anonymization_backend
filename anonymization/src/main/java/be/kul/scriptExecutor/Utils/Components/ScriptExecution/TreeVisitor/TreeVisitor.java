package be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeVisitor;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.*;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.ScriptExecutionController;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeExpressionNodes.*;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Variable.VariableContainer;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.enums.AtomExpressionType;
import be.kul.scriptExecutor.Utils.enums.FunctionId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeVisitor extends AbstractTreeVisitor<Expression> {
    private ScriptExecutionController scriptExecutionController;
    private HashMap<String, VariableContainer> variables;

    public TreeVisitor(ScriptExecutionController scriptExecutionController) {
        super();
        this.scriptExecutionController = scriptExecutionController;
        variables = new HashMap<>();
    }

    @Override
    public Expression visit(AssignmentExpression expression) {
        //Get the name of the variable the data is going to be assigned to
        StringData stringData = (StringData) expression.getVariable().getDataOfAtom();
        String variableName = stringData.getStringValue();

        //Create a new Variable container object
        VariableContainer variableContainer = new VariableContainer(variableName);

        //Visit the data expression
        AtomExpression atomExpression = (AtomExpression) visit(expression.getData());

        //Get the variable data from the atom expression
        switch (atomExpression.getAtomExpressionType()) {
            case ID:
                variableContainer.setAssignedData(followVariableToAssignedData((VariableData) atomExpression.getDataOfAtom()));
                break;
            case STRING:
            case INT:
            case FLOAT:
            case BOOLEAN:
            case DATASET:
                variableContainer.setAssignedData( atomExpression.getDataOfAtom());
                break;
        }

        //Put the new variable in the map
        variables.put(variableName, variableContainer);

        return expression;
    }

    @Override
    public Expression visit(AtomExpression expression) {
        return expression;
    }

    @Override
    public Expression visit(DataInputExpression expression) {
        //Extract the sql query
        StringData queryData = (StringData) expression.getSqlCommand().getDataOfAtom();
        String query = queryData.getStringValue();

        //Get the requested data
        List<HashMap<String,String>> unanonymizedDataSet = scriptExecutionController.getDataSet(query);

        //Create a dataset data object
        DataSetData dataSetData = new DataSetData(unanonymizedDataSet);

        //Create a new Dataset atom expression
        AtomExpression atomExpression = new AtomExpression(AtomExpressionType.DATASET, "", dataSetData);

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
        List<Data> argumentData = new ArrayList<>();
        for (AtomExpression atomExpression : argumentExpressions) {
            switch (atomExpression.getAtomExpressionType()) {
                case ID:
                    argumentData.add(followVariableToAssignedData((VariableData) atomExpression.getDataOfAtom()));
                    break;
                case STRING:
                case BOOLEAN:
                case INT:
                case FLOAT:
                case DATASET:
                    argumentData.add(atomExpression.getDataOfAtom());
                    break;
            }
        }

        //Get the functionId
        FunctionId functionId = expression.getFunctionId();

        //Execute the function
        Data functionResult = scriptExecutionController.executeAnonymizedFunction(functionId, argumentData);

        //Build a new atomexpression
        AtomExpression atomExpression;
        if (functionResult instanceof BooleanData) {
            BooleanData booleanData = (BooleanData) functionResult;
            atomExpression = new AtomExpression(AtomExpressionType.BOOLEAN, String.valueOf(booleanData.getBooleanValue()), booleanData);
        } else if (functionResult instanceof DataSetData) {
            DataSetData dataSetData = (DataSetData) functionResult;
            atomExpression = new AtomExpression(AtomExpressionType.DATASET, "", dataSetData);
        } else if (functionResult instanceof FloatData) {
            FloatData floatData = (FloatData) functionResult;
            atomExpression = new AtomExpression(AtomExpressionType.FLOAT, String.valueOf(floatData.getFloatValue()), floatData);
        } else if (functionResult instanceof IntegerData) {
            IntegerData integerData = (IntegerData) functionResult;
            atomExpression = new AtomExpression(AtomExpressionType.INT, String.valueOf(integerData.getIntegerValue()), integerData);
        } else {
            StringData stringData = (StringData) functionResult;
            atomExpression = new AtomExpression(AtomExpressionType.STRING, String.valueOf(stringData.getStringValue()), stringData);
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

    private Data followVariableToAssignedData(VariableData dataOfAtom) {
        //Get the variable name
        String variableName = dataOfAtom.getVariableName();

        //Get the variable from the variables map
        VariableContainer variableContainer = variables.get(variableName);

        //Get the assigned data
        Data assignedData = variableContainer.getAssignedData();

        //If the assigned data is a variable, recursively find the next variable
        if (assignedData instanceof VariableData) {
            return followVariableToAssignedData((VariableData) assignedData);
        }

        //If the data is not a variable, return the data
        return assignedData;
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
