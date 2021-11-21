import Antlr.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class Main {
    public static void main(String[] args) {
        //Set the input string
        /*
        String inpupt = "for(year in c(2010,2011,2012)) {\n" +
                "print(\"The year is\", year)\n" +
                "}\n" +
                "print(\"test\")\n" +
                "test <- ncount(1234)\n" +
                "test <- 1234\n" +
                "ncount(1234)\n" +
                "function_name <- function(arg_1, arg_2) {\n" +
                "print(test)\n" +
                "}";

         */
        String testInput =  "data <- sql(\"Select * from test\") \n" +
                "count <- nrows(data) \n" +
                "output <- c(count) \n";

        //Parse the input into a program tree
        RLexer lexer = new RLexer(CharStreams.fromString(testInput));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RParser parser = new RParser(tokens);
        RParser.ProgContext programTree = parser.prog();

        //Visit the program tree
        //RVisitor visitor = new RVisitorImpl();
        //visitor.visitProg(programTree);

        System.out.println();

    }
}
