package Antlr;// Generated from /home/robbe/Documents/School/2021-2022/Masterproef/ScriptAnonymizationBackend/AntlrTest/antlr/RScript.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RScriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RScriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RScriptParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(RScriptParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignmentExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpr(RScriptParser.AssignmentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathOpExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathOpExpr(RScriptParser.MathOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdAtomExpr(RScriptParser.IdAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dataInputExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataInputExpr(RScriptParser.DataInputExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcCallExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallExpr(RScriptParser.FuncCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code infAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfAtomExpr(RScriptParser.InfAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unariMathOpExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnariMathOpExpr(RScriptParser.UnariMathOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatAtomExpr(RScriptParser.FloatAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repeatLoopExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatLoopExpr(RScriptParser.RepeatLoopExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(RScriptParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicExpr(RScriptParser.LogicExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcDeclExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclExpr(RScriptParser.FuncDeclExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpr(RScriptParser.IfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAtomExpr(RScriptParser.StringAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolAtomExpr(RScriptParser.BoolAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntAtomExpr(RScriptParser.IntAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code complAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplAtomExpr(RScriptParser.ComplAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hexAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexAtomExpr(RScriptParser.HexAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifElseExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseExpr(RScriptParser.IfElseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nanAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNanAtomExpr(RScriptParser.NanAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dataOutputExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataOutputExpr(RScriptParser.DataOutputExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forLoopExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoopExpr(RScriptParser.ForLoopExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullAtomExpr(RScriptParser.NullAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code flowExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlowExpr(RScriptParser.FlowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileLoopExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoopExpr(RScriptParser.WhileLoopExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(RScriptParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code naAtomExpr}
	 * labeled alternative in {@link RScriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaAtomExpr(RScriptParser.NaAtomExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RScriptParser#functionDeclarationArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclarationArguments(RScriptParser.FunctionDeclarationArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RScriptParser#functionDeclarationArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclarationArgument(RScriptParser.FunctionDeclarationArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RScriptParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(RScriptParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RScriptParser#functionCallArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallArguments(RScriptParser.FunctionCallArgumentsContext ctx);
}