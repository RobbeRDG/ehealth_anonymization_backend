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
	 * Visit a parse tree produced by the {@code assignmentExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpr(RParser.AssignmentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathOpExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathOpExpr(RParser.MathOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdAtomExpr(RParser.IdAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcCallExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallExpr(RParser.FuncCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code infAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfAtomExpr(RParser.InfAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unariMathOpExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnariMathOpExpr(RParser.UnariMathOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatAtomExpr(RParser.FloatAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repeatLoopExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatLoopExpr(RParser.RepeatLoopExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(RParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicExpr(RParser.LogicExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcDeclExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclExpr(RParser.FuncDeclExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpr(RParser.IfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAtomExpr(RParser.StringAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntAtomExpr(RParser.IntAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code complAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplAtomExpr(RParser.ComplAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hexAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexAtomExpr(RParser.HexAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueAtomExpr(RParser.TrueAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifElseExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseExpr(RParser.IfElseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nanAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNanAtomExpr(RParser.NanAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseAtomExpr(RParser.FalseAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forLoopExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoopExpr(RParser.ForLoopExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullAtomExpr(RParser.NullAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code flowExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlowExpr(RParser.FlowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileLoopExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoopExpr(RParser.WhileLoopExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(RParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code naAtomExpr}
	 * labeled alternative in {@link RParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaAtomExpr(RParser.NaAtomExprContext ctx);
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