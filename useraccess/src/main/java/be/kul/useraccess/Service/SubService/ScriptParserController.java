package be.kul.useraccess.Service.SubService;

import be.kul.useraccess.Utils.Antlr.impl.RScriptBaseVisitorImpl;
import be.kul.useraccess.Utils.SupportedFunctions.FunctionFingerPrintScanner;
import be.kul.useraccess.Entity.ScriptSummary;
import be.kul.useraccess.Utils.Antlr.RScriptLexer;
import be.kul.useraccess.Utils.Antlr.RScriptParser;
import be.kul.useraccess.Utils.Exceptions.ExceptionClasses.ScriptParsingException;
import be.kul.useraccess.Utils.SupportedFunctions.SupportedFunctionFingerPrint;
import be.kul.useraccess.Utils.ScriptSummaryComponents.TreeExpressionNodes.ProgramExpression;
import be.kul.useraccess.Utils.Enums.FunctionId;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

@Component
public class ScriptParserController {
    public ScriptSummary generateScriptSummary(String scriptString) {
        //Split the script string into its different tokens
        RScriptLexer lexer = new RScriptLexer(CharStreams.fromString(scriptString));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //Parse the tokens to build a parse tree
        RScriptParser parser = new RScriptParser(tokens);
        RScriptParser.ProgContext parseTree = parser.prog();

        //Set the supported list of functions
        FunctionFingerPrintScanner.addSupportedFunctionFingerPrint("nrow", new SupportedFunctionFingerPrint(FunctionId.nrow, "nrow", true, Collections.singletonList(1)));

        //Visit the parse tree to build a programTree
        RScriptBaseVisitorImpl visitor = new RScriptBaseVisitorImpl();
        ProgramExpression programTree = new ProgramExpression();
        try {
            programTree = (ProgramExpression) visitor.visitProg(parseTree);
        } catch (ParseCancellationException e) {
            throw new ScriptParsingException(e.getLocalizedMessage());
        }

        //Get the extra visitor stats
        Set<String> variableNames = visitor.getVariableNames();
        Set<String> outputVariableNames = visitor.getOutputVariableNames();
        Set<FunctionId> usedFunctionIds = visitor.getUsedFunctionIds();
        Set<String> inputDataQueries = visitor.getInputDataQueries();

        //Validate if the output variables are correct
        validateOutputVariables(outputVariableNames, variableNames);

        //Validate the sql inputs
        validateInputDataQueries(inputDataQueries);

        //Generate an anonymizationSummary
        ScriptSummary scriptSummary = new ScriptSummary(
                scriptString,
                programTree,
                variableNames,
                outputVariableNames
        );

        return scriptSummary;
    }

    private void validateInputDataQueries(Set<String> inputDataQueries) {
        String blackList[] = new String[]{
                "INSERT",
                "UPDATE",
                "DELETE",
                "RENAME",
                "DROP",
                "CREATE",
                "TRUNCATE",
                "ALTER",
                "COMMIT",
                "ROLLBACK",
                "MERGE",
                "CALL",
                "EXPLAIN",
                "LOCK",
                "GRANT",
                "REVOKE",
                "SAVEPOINT",
                "TRANSACTION",
                "SET",
        };

        for (String inputDataQuery : inputDataQueries) {
            if (Arrays.stream(blackList).anyMatch(inputDataQuery::contains)) throw new ScriptParsingException(
                    "SQL query: " + inputDataQuery + " contains an illegal expression"
            );
        }
    }

    private void validateOutputVariables(Set<String> outputVariableNames, Set<String> variableNames) {
        for (String outputVariableName : outputVariableNames) {
            if (!variableNames.contains(outputVariableName)) throw new ScriptParsingException(outputVariableName + " is not declared in the script variables");
        }
    }
}
