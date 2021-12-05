package be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeVisitor;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeExpressionNodes.*;

abstract class AbstractTreeVisitor<T> {
    public abstract T visit(AssignmentExpression expression);

    public abstract T visit(AtomExpression expression);

    public abstract T visit(DataInputExpression expression);

    public abstract T visit(DataOutputExpression expression);

    public abstract T visit(FunctionArgumentsExpression expression);

    public abstract T visit(FunctionCallExpression expression);

    public abstract T visit(ProgramExpression expression);

    public T visit(Expression expression) {
        if (expression instanceof AssignmentExpression) {
            return visit((AssignmentExpression) expression);
        } else if (expression instanceof AtomExpression) {
            return visit((AtomExpression) expression);
        } else if (expression instanceof DataInputExpression) {
            return visit((DataInputExpression) expression);
        } else if (expression instanceof DataOutputExpression) {
            return visit((DataOutputExpression) expression);
        } else if (expression instanceof FunctionArgumentsExpression) {
            return visit((FunctionArgumentsExpression) expression);
        } else if (expression instanceof FunctionCallExpression) {
            return visit((FunctionCallExpression) expression);
        } else {
            return visit((ProgramExpression) expression);
        }
    }
}
