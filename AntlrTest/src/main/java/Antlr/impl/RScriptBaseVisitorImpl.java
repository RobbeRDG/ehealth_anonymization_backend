package Antlr.impl;


import Antlr.RScriptBaseVisitor;
import Antlr.RScriptParser;
import Exceptions.NotSupportedFunctionException;
import SupportedFunctions.FunctionFingerPrint;
import SupportedFunctions.FunctionFingerPrintScanner;
import TreeExpressionNodes.*;
import enums.FunctionId;
import enums.atomExpressionType;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

public class RScriptBaseVisitorImpl extends RScriptBaseVisitor<Expression> {
    private Set<String> variableNames;
    private Set<FunctionId> usedFunctionIds;

    public RScriptBaseVisitorImpl() {
        variableNames = new HashSet<>();
        usedFunctionIds = new HashSet<>();
    }

    public Set<FunctionId> getUsedFunctionIds() {
        return usedFunctionIds;
    }

    public Set<String> getVariableNames() {
        return variableNames;
    }

    @Override
    public Expression visitProg(RScriptParser.ProgContext ctx) {
        ArrayList<Expression> childExpressions = new ArrayList<>();

        //Get all the child expressions
        List<ParseTree> children = ctx.children;

        //Remove the EOF expression at the end
        children.remove(children.size()-1);

        //Get all the child expressions
        for (ParseTree child : ctx.children) {
            childExpressions.add(visit(child));
        }

        //The child expressions should contain 1 and only 1 output expression
        int outputExpressionCount = 0;
        for (Expression childExpression : childExpressions) {
            if (childExpression instanceof DataOutputExpression) outputExpressionCount++;
        }
        if (outputExpressionCount != 1) throw new ParseCancellationException(
                "The script should only contain one output expression"
        );

        return new ProgramExpression(childExpressions);
    }

    @Override
    public Expression visitAssignmentExpr(RScriptParser.AssignmentExprContext ctx) {
        //Get the variable expression
        String variableName = ctx.getChild(0).getText();
        AtomExpression variable = new AtomExpression(atomExpressionType.ID, variableName);

        //Store the variable in the used variables
        variableNames.add(variableName);

        //Get the expression to assign to the variable
        Expression expression = visit(ctx.getChild(2));

        return new AssignmentExpression(variable, expression);
    }


    @Override
    public Expression visitFuncCallExpr(RScriptParser.FuncCallExprContext ctx) {
        //Get the function name
        String functionName = ctx.getChild(0).getText();

        //Get the arguments expression
        FunctionArgumentsExpression argumentsExpression = (FunctionArgumentsExpression) visit(ctx.getChild(2));

        //Extract the arguments from the expression
        ArrayList<AtomExpression> argumentExpressions = argumentsExpression.getFunctionArguments();


        //Get the number of arguments
        int numberOfArguments = argumentExpressions.size();
        argumentsExpression.getFunctionArguments();


        //Use the function name and number of arguments to see if the called function is supported by the anonymizer
        FunctionId functionId;
        FunctionFingerPrint functionFingerPrint = new FunctionFingerPrint(functionName, numberOfArguments);
        try {
            functionId = FunctionFingerPrintScanner.getSupportedFunctionId(functionFingerPrint);
        } catch (NotSupportedFunctionException e) {
            throw new ParseCancellationException(e.getMessage());
        }

        //Add the function id to the used functions
        usedFunctionIds.add(functionId);


        //Return the new function call expression
        return new FunctionCallExpression(functionId, argumentExpressions);
    }

    @Override
    public Expression visitDataInputExpr(RScriptParser.DataInputExprContext ctx) {
        //Get the input argument
        AtomExpression argumentExpression;
        try {
            argumentExpression = (AtomExpression) visit(ctx.getChild(2));
        } catch (ClassCastException e) {
            throw new ParseCancellationException(
                    "The dataset input only expects a single argument"
            );
        }

        //The argument should be a string
        if (argumentExpression.getAtomExpressionType() != atomExpressionType.STRING) throw new ParseCancellationException(
                "The dataset input query can only be a string"
        );

        return new DataInputExpression(argumentExpression);
    }

    @Override
    public Expression visitDataOutputExpr(RScriptParser.DataOutputExprContext ctx) {
        //Get the argument expressions
        FunctionArgumentsExpression argumentsExpression = (FunctionArgumentsExpression) visit(ctx.getChild(2));

        //Extract the arguments from the expression
        ArrayList<AtomExpression> argumentExpressions = argumentsExpression.getFunctionArguments();

        //See if the arguments only contain variables
        for (AtomExpression atomExpression : argumentExpressions) {
            if (atomExpression.getAtomExpressionType() != atomExpressionType.ID) throw new ParseCancellationException(
                    "The output of a script can only contain variables"
            );
        }


        return new DataOutputExpression(argumentExpressions);
    }

    @Override
    public Expression visitFunctionCallArguments(RScriptParser.FunctionCallArgumentsContext ctx) {
        ArrayList<AtomExpression> argumentExpressions = new ArrayList<>();

        //Get the expression from each child
        for (ParseTree child : ctx.children) {
            try {
                argumentExpressions.add((AtomExpression) visit(child));
            } catch (ClassCastException e) {
                throw new ParseCancellationException(
                        "The anonymizer only supports variable names, integers, floats, Strings and booleans as  function call arguments, without assignments"
                );
            }
        }
        return new FunctionArgumentsExpression(argumentExpressions);
    }

    @Override
    public Expression visitIdAtomExpr(RScriptParser.IdAtomExprContext ctx) {
        //Get the variable name
        String variableName = ctx.getText();

        //Store the variable in the used variables
        variableNames.add(variableName);

        return new AtomExpression(atomExpressionType.ID, variableName);
    }


    @Override
    public Expression visitFloatAtomExpr(RScriptParser.FloatAtomExprContext ctx) {
        return new AtomExpression(atomExpressionType.FLOAT, ctx.getText());
    }

    @Override
    public Expression visitStringAtomExpr(RScriptParser.StringAtomExprContext ctx) {
        return new AtomExpression(atomExpressionType.STRING, ctx.getText().replace("\"", ""));
    }

    @Override
    public Expression visitIntAtomExpr(RScriptParser.IntAtomExprContext ctx) {
        return new AtomExpression(atomExpressionType.INT, ctx.getText());
    }

    @Override
    public Expression visitBoolAtomExpr(RScriptParser.BoolAtomExprContext ctx) {
        return new AtomExpression(atomExpressionType.BOOLEAN, ctx.getText());
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////// UNSUPPORTED EXPRESSIONS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Expression visitMathOpExpr(RScriptParser.MathOpExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support math operations yet");
    }


    @Override
    public Expression visitInfAtomExpr(RScriptParser.InfAtomExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support ininite arguments yet");
    }

    @Override
    public Expression visitUnariMathOpExpr(RScriptParser.UnariMathOpExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support unari math operations yet");
    }


    @Override
    public Expression visitRepeatLoopExpr(RScriptParser.RepeatLoopExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support repeate loop operations yet");
    }

    @Override
    public Expression visitCompExpr(RScriptParser.CompExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support comparison operations yet");
    }

    @Override
    public Expression visitLogicExpr(RScriptParser.LogicExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support logic operations yet");
    }

    @Override
    public Expression visitFuncDeclExpr(RScriptParser.FuncDeclExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support function declarations yet");
    }

    @Override
    public Expression visitIfExpr(RScriptParser.IfExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support if operations yet");
    }


    @Override
    public Expression visitComplAtomExpr(RScriptParser.ComplAtomExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support complex numbers yet");
    }

    @Override
    public Expression visitHexAtomExpr(RScriptParser.HexAtomExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support hex arguments yet");
    }


    @Override
    public Expression visitIfElseExpr(RScriptParser.IfElseExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support if else operations yet");
    }

    @Override
    public Expression visitNanAtomExpr(RScriptParser.NanAtomExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support NAN operations yet");
    }


    @Override
    public Expression visitForLoopExpr(RScriptParser.ForLoopExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support for loop operations yet");
    }

    @Override
    public Expression visitNullAtomExpr(RScriptParser.NullAtomExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support NULL operations yet");
    }

    @Override
    public Expression visitFlowExpr(RScriptParser.FlowExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support flow control operations yet");
    }

    @Override
    public Expression visitWhileLoopExpr(RScriptParser.WhileLoopExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support while loop operations yet");
    }

    @Override
    public Expression visitNegExpr(RScriptParser.NegExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support negations yet");
    }

    @Override
    public Expression visitNaAtomExpr(RScriptParser.NaAtomExprContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support NA operations yet");
    }

    @Override
    public Expression visitFunctionDeclarationArguments(RScriptParser.FunctionDeclarationArgumentsContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support function declarations yet");
    }

    @Override
    public Expression visitFunctionDeclarationArgument(RScriptParser.FunctionDeclarationArgumentContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support function declarations yet");
    }

    @Override
    public Expression visitExprList(RScriptParser.ExprListContext ctx) {
        throw new ParseCancellationException("The anonymizer doesn't support function declarations yet");
    }
}
