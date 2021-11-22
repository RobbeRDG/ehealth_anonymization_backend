import Antlr.*;
import Antlr.impl.RScriptBaseVisitorImpl;
import SupportedFunctions.FunctionFingerPrintScanner;
import SupportedFunctions.SupportedFunctionFingerPrint;
import TreeExpressionNodes.ProgramExpression;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.FunctionId;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Collections;


public class Main {
    private static ProgramExpression test;

    public static void main(String[] args) {
        //Set the input string
        String testInput = "data <- input(\"Select * from test\") \n" +
                "count <- nrow(data) \n" +
                "output(count)\n";

        //Parse the input into a program tree
        RScriptLexer lexer = new RScriptLexer(CharStreams.fromString(testInput));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RScriptParser parser = new RScriptParser(tokens);
        RScriptParser.ProgContext programTree = parser.prog();

        //Set the supported list of functions
        FunctionFingerPrintScanner.addSupportedFunctionFingerPrint("nrow", new SupportedFunctionFingerPrint(FunctionId.nrow, "nrow", true, Collections.singletonList(1)));


        //Visit the program tree
        RScriptBaseVisitorImpl visitor = new RScriptBaseVisitorImpl();
        ProgramExpression programExpression = new ProgramExpression();
        try {
            programExpression = (ProgramExpression) visitor.visitProg(programTree);
        } catch (ParseCancellationException e) {
            e.printStackTrace();
        }


        //Test if the output variables are defined in the script
        //TODO

        //Generate the anonimisation information for each function
        //TODO


        //Export the tree and anonimisation information
        //TODO


        System.out.println(programExpression);

    }
}
