import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class Main {
    public static void main(String[] args) {
        String inpupt = "myString <- \"Hello, World!\"\n" +
                "print ( myString)";
        RLexer lexer = new RLexer(CharStreams.fromString(inpupt));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RParser parser = new RParser(tokens);

        RParser.ExprContext tset = parser.expr();
        System.out.println();

    }
}
