package be.kul.scriptExecutor.Utils.ScriptSummaryComponents.TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
        include=JsonTypeInfo.As.PROPERTY,
        property="name")
@JsonSubTypes({
        @JsonSubTypes.Type(value=AssignmentExpression.class, name="assignment_expression"),
        @JsonSubTypes.Type(value=AtomExpression.class, name="atom_expression"),
        @JsonSubTypes.Type(value=DataInputExpression.class, name="data_input_expression"),
        @JsonSubTypes.Type(value=DataOutputExpression.class, name="data_output_expression"),
        @JsonSubTypes.Type(value=FunctionArgumentsExpression.class, name="function_arguments_expression"),
        @JsonSubTypes.Type(value=FunctionCallExpression.class, name="function_call_expression"),
        @JsonSubTypes.Type(value=ProgramExpression.class, name="program_expression"),
})
public class Expression {
    public Expression() {
    }
}
