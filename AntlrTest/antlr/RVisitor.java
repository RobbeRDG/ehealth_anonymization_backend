// Generated from /home/robbe/Documents/School/2021-2022/Masterproef/ScriptAnonymizationBackend/AntlrTest/antlr/R.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(RParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(RParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RParser#functionDeclarationArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclarationArguments(RParser.FunctionDeclarationArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RParser#functionDeclarationArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclarationArgument(RParser.FunctionDeclarationArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(RParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RParser#subList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubList(RParser.SubListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RParser#sub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(RParser.SubContext ctx);
}