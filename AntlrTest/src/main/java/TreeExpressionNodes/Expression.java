package TreeExpressionNodes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
        include=JsonTypeInfo.As.PROPERTY,
        property="name")
@JsonSubTypes({
        @JsonSubTypes.Type(value=AssignmentExpression.class, name="assignmentExpression"),
        @JsonSubTypes.Type(value=AtomExpression.class, name="atomExpression"),
        @JsonSubTypes.Type(value=DataInputExpression.class, name="dataInputExpression"),
        @JsonSubTypes.Type(value=DataOutputExpression.class, name="dataOutputExpression"),
        @JsonSubTypes.Type(value=FunctionArgumentsExpression.class, name="functionArgumentsExpression"),
        @JsonSubTypes.Type(value=FunctionCallExpression.class, name="functionCallExpression"),
        @JsonSubTypes.Type(value=ProgramExpression.class, name="programExpression"),
})
public class Expression {
}