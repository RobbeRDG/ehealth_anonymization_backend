// Generated from /home/robbe/Documents/School/2021-2022/Masterproef/ScriptAnonymizationBackend/AntlrTest/antlr/R.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, HEX=42, INT=43, FLOAT=44, COMPLEX=45, STRING=46, 
		ID=47, USER_OP=48, COMMENT=49, NL=50, WS=51;
	public static final int
		RULE_prog = 0, RULE_expr = 1, RULE_functionDeclarationArguments = 2, RULE_functionDeclarationArgument = 3, 
		RULE_exprList = 4, RULE_subList = 5, RULE_sub = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "expr", "functionDeclarationArguments", "functionDeclarationArgument", 
			"exprList", "subList", "sub"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'*'", "'/'", "'+'", "'-'", "':'", "'>'", "'>='", "'<'", 
			"'<='", "'=='", "'!='", "'!'", "'&'", "'&&'", "'|'", "'||'", "'if'", 
			"'('", "')'", "'else'", "'for'", "'in'", "'while'", "'repeat'", "'next'", 
			"'break'", "'NULL'", "'NA'", "'Inf'", "'NaN'", "'TRUE'", "'FALSE'", "'<-'", 
			"'function'", "'{'", "'}'", "','", "'='", "'...'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "HEX", "INT", "FLOAT", "COMPLEX", 
			"STRING", "ID", "USER_OP", "COMMENT", "NL", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "R.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(RParser.EOF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(RParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(RParser.NL, i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RVisitor ) return ((RVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__12) | (1L << T__17) | (1L << T__21) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << HEX) | (1L << INT) | (1L << FLOAT) | (1L << COMPLEX) | (1L << STRING) | (1L << ID) | (1L << NL))) != 0)) {
				{
				setState(22);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
				case T__4:
				case T__12:
				case T__17:
				case T__21:
				case T__23:
				case T__24:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__29:
				case T__30:
				case T__31:
				case T__32:
				case HEX:
				case INT:
				case FLOAT:
				case COMPLEX:
				case STRING:
				case ID:
					{
					setState(14);
					expr(0);
					setState(18);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(15);
							_la = _input.LA(1);
							if ( !(_la==T__0 || _la==NL) ) {
							_errHandler.recoverInline(this);
							}
							else {
								if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
								_errHandler.reportMatch(this);
								consume();
							}
							}
							} 
						}
						setState(20);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
					}
					}
					break;
				case NL:
					{
					setState(21);
					match(NL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ID() { return getToken(RParser.ID, 0); }
		public TerminalNode STRING() { return getToken(RParser.STRING, 0); }
		public TerminalNode HEX() { return getToken(RParser.HEX, 0); }
		public TerminalNode INT() { return getToken(RParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(RParser.FLOAT, 0); }
		public TerminalNode COMPLEX() { return getToken(RParser.COMPLEX, 0); }
		public SubListContext subList() {
			return getRuleContext(SubListContext.class,0);
		}
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FunctionDeclarationArgumentsContext functionDeclarationArguments() {
			return getRuleContext(FunctionDeclarationArgumentsContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RVisitor ) return ((RVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(30);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(31);
				expr(28);
				}
				break;
			case 2:
				{
				setState(32);
				match(T__12);
				setState(33);
				expr(25);
				}
				break;
			case 3:
				{
				setState(34);
				match(T__17);
				setState(35);
				match(T__18);
				setState(36);
				expr(0);
				setState(37);
				match(T__19);
				setState(38);
				expr(22);
				}
				break;
			case 4:
				{
				setState(40);
				match(T__17);
				setState(41);
				match(T__18);
				setState(42);
				expr(0);
				setState(43);
				match(T__19);
				setState(44);
				expr(0);
				setState(45);
				match(T__20);
				setState(46);
				expr(21);
				}
				break;
			case 5:
				{
				setState(48);
				match(T__21);
				setState(49);
				match(T__18);
				setState(50);
				match(ID);
				setState(51);
				match(T__22);
				setState(52);
				expr(0);
				setState(53);
				match(T__19);
				setState(54);
				expr(20);
				}
				break;
			case 6:
				{
				setState(56);
				match(T__23);
				setState(57);
				match(T__18);
				setState(58);
				expr(0);
				setState(59);
				match(T__19);
				setState(60);
				expr(19);
				}
				break;
			case 7:
				{
				setState(62);
				match(T__24);
				setState(63);
				expr(18);
				}
				break;
			case 8:
				{
				setState(64);
				match(T__25);
				}
				break;
			case 9:
				{
				setState(65);
				match(T__26);
				}
				break;
			case 10:
				{
				setState(66);
				match(ID);
				}
				break;
			case 11:
				{
				setState(67);
				match(STRING);
				}
				break;
			case 12:
				{
				setState(68);
				match(HEX);
				}
				break;
			case 13:
				{
				setState(69);
				match(INT);
				}
				break;
			case 14:
				{
				setState(70);
				match(FLOAT);
				}
				break;
			case 15:
				{
				setState(71);
				match(COMPLEX);
				}
				break;
			case 16:
				{
				setState(72);
				match(T__27);
				}
				break;
			case 17:
				{
				setState(73);
				match(T__28);
				}
				break;
			case 18:
				{
				setState(74);
				match(T__29);
				}
				break;
			case 19:
				{
				setState(75);
				match(T__30);
				}
				break;
			case 20:
				{
				setState(76);
				match(T__31);
				}
				break;
			case 21:
				{
				setState(77);
				match(T__32);
				}
				break;
			case 22:
				{
				setState(78);
				match(ID);
				setState(79);
				match(T__18);
				setState(80);
				subList();
				setState(81);
				match(T__19);
				}
				break;
			case 23:
				{
				setState(83);
				match(ID);
				setState(84);
				match(T__33);
				setState(85);
				match(T__34);
				setState(86);
				match(T__18);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__39) | (1L << T__40) | (1L << ID))) != 0)) {
					{
					setState(87);
					functionDeclarationArguments();
					}
				}

				setState(90);
				match(T__19);
				setState(91);
				match(T__35);
				setState(92);
				exprList();
				setState(93);
				match(T__36);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(120);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(118);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(97);
						if (!(precpred(_ctx, 30))) throw new FailedPredicateException(this, "precpred(_ctx, 30)");
						setState(98);
						_la = _input.LA(1);
						if ( !(_la==T__1 || _la==T__2) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(99);
						expr(31);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(100);
						if (!(precpred(_ctx, 29))) throw new FailedPredicateException(this, "precpred(_ctx, 29)");
						setState(101);
						_la = _input.LA(1);
						if ( !(_la==T__3 || _la==T__4) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(102);
						expr(30);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(103);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(104);
						match(T__5);
						setState(105);
						expr(28);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(106);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(107);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(108);
						expr(27);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(109);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(110);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==T__14) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(111);
						expr(25);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(112);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(113);
						_la = _input.LA(1);
						if ( !(_la==T__15 || _la==T__16) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(114);
						expr(24);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(115);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						{
						setState(116);
						match(T__33);
						}
						setState(117);
						expr(4);
						}
						break;
					}
					} 
				}
				setState(122);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FunctionDeclarationArgumentsContext extends ParserRuleContext {
		public List<FunctionDeclarationArgumentContext> functionDeclarationArgument() {
			return getRuleContexts(FunctionDeclarationArgumentContext.class);
		}
		public FunctionDeclarationArgumentContext functionDeclarationArgument(int i) {
			return getRuleContext(FunctionDeclarationArgumentContext.class,i);
		}
		public FunctionDeclarationArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclarationArguments; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RVisitor ) return ((RVisitor<? extends T>)visitor).visitFunctionDeclarationArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationArgumentsContext functionDeclarationArguments() throws RecognitionException {
		FunctionDeclarationArgumentsContext _localctx = new FunctionDeclarationArgumentsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDeclarationArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			functionDeclarationArgument();
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__37) {
				{
				{
				setState(124);
				match(T__37);
				setState(125);
				functionDeclarationArgument();
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationArgumentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FunctionDeclarationArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclarationArgument; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RVisitor ) return ((RVisitor<? extends T>)visitor).visitFunctionDeclarationArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationArgumentContext functionDeclarationArgument() throws RecognitionException {
		FunctionDeclarationArgumentContext _localctx = new FunctionDeclarationArgumentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_functionDeclarationArgument);
		try {
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				match(ID);
				setState(133);
				match(T__38);
				setState(134);
				expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(135);
				match(T__39);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
				match(T__40);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(RParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(RParser.NL, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RVisitor ) return ((RVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			expr(0);
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==NL) {
				{
				{
				setState(140);
				_la = _input.LA(1);
				if ( !(_la==T__0 || _la==NL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__12) | (1L << T__17) | (1L << T__21) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << HEX) | (1L << INT) | (1L << FLOAT) | (1L << COMPLEX) | (1L << STRING) | (1L << ID))) != 0)) {
					{
					setState(141);
					expr(0);
					}
				}

				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubListContext extends ParserRuleContext {
		public List<SubContext> sub() {
			return getRuleContexts(SubContext.class);
		}
		public SubContext sub(int i) {
			return getRuleContext(SubContext.class,i);
		}
		public SubListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RVisitor ) return ((RVisitor<? extends T>)visitor).visitSubList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubListContext subList() throws RecognitionException {
		SubListContext _localctx = new SubListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_subList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			sub();
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__37) {
				{
				{
				setState(150);
				match(T__37);
				setState(151);
				sub();
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(RParser.ID, 0); }
		public TerminalNode STRING() { return getToken(RParser.STRING, 0); }
		public SubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sub; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RVisitor ) return ((RVisitor<? extends T>)visitor).visitSub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubContext sub() throws RecognitionException {
		SubContext _localctx = new SubContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sub);
		try {
			setState(176);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				match(ID);
				setState(159);
				match(T__38);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				match(ID);
				setState(161);
				match(T__38);
				setState(162);
				expr(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				match(STRING);
				setState(164);
				match(T__38);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(165);
				match(STRING);
				setState(166);
				match(T__38);
				setState(167);
				expr(0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(168);
				match(T__27);
				setState(169);
				match(T__38);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(170);
				match(T__27);
				setState(171);
				match(T__38);
				setState(172);
				expr(0);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(173);
				match(T__39);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(174);
				match(T__40);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 30);
		case 1:
			return precpred(_ctx, 29);
		case 2:
			return precpred(_ctx, 27);
		case 3:
			return precpred(_ctx, 26);
		case 4:
			return precpred(_ctx, 24);
		case 5:
			return precpred(_ctx, 23);
		case 6:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\65\u00b5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\7\2\23\n\2\f"+
		"\2\16\2\26\13\2\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3[\n\3\3\3\3\3\3\3\3\3\3\3\5\3b\n\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3y"+
		"\n\3\f\3\16\3|\13\3\3\4\3\4\3\4\7\4\u0081\n\4\f\4\16\4\u0084\13\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\5\5\u008c\n\5\3\6\3\6\3\6\5\6\u0091\n\6\7\6\u0093"+
		"\n\6\f\6\16\6\u0096\13\6\3\7\3\7\3\7\7\7\u009b\n\7\f\7\16\7\u009e\13\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\5\b\u00b3\n\b\3\b\2\3\4\t\2\4\6\b\n\f\16\2\b\4\2\3\3\64\64\3\2"+
		"\6\7\3\2\4\5\3\2\t\16\3\2\20\21\3\2\22\23\2\u00de\2\32\3\2\2\2\4a\3\2"+
		"\2\2\6}\3\2\2\2\b\u008b\3\2\2\2\n\u008d\3\2\2\2\f\u0097\3\2\2\2\16\u00b2"+
		"\3\2\2\2\20\24\5\4\3\2\21\23\t\2\2\2\22\21\3\2\2\2\23\26\3\2\2\2\24\22"+
		"\3\2\2\2\24\25\3\2\2\2\25\31\3\2\2\2\26\24\3\2\2\2\27\31\7\64\2\2\30\20"+
		"\3\2\2\2\30\27\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\35"+
		"\3\2\2\2\34\32\3\2\2\2\35\36\7\2\2\3\36\3\3\2\2\2\37 \b\3\1\2 !\t\3\2"+
		"\2!b\5\4\3\36\"#\7\17\2\2#b\5\4\3\33$%\7\24\2\2%&\7\25\2\2&\'\5\4\3\2"+
		"\'(\7\26\2\2()\5\4\3\30)b\3\2\2\2*+\7\24\2\2+,\7\25\2\2,-\5\4\3\2-.\7"+
		"\26\2\2./\5\4\3\2/\60\7\27\2\2\60\61\5\4\3\27\61b\3\2\2\2\62\63\7\30\2"+
		"\2\63\64\7\25\2\2\64\65\7\61\2\2\65\66\7\31\2\2\66\67\5\4\3\2\678\7\26"+
		"\2\289\5\4\3\269b\3\2\2\2:;\7\32\2\2;<\7\25\2\2<=\5\4\3\2=>\7\26\2\2>"+
		"?\5\4\3\25?b\3\2\2\2@A\7\33\2\2Ab\5\4\3\24Bb\7\34\2\2Cb\7\35\2\2Db\7\61"+
		"\2\2Eb\7\60\2\2Fb\7,\2\2Gb\7-\2\2Hb\7.\2\2Ib\7/\2\2Jb\7\36\2\2Kb\7\37"+
		"\2\2Lb\7 \2\2Mb\7!\2\2Nb\7\"\2\2Ob\7#\2\2PQ\7\61\2\2QR\7\25\2\2RS\5\f"+
		"\7\2ST\7\26\2\2Tb\3\2\2\2UV\7\61\2\2VW\7$\2\2WX\7%\2\2XZ\7\25\2\2Y[\5"+
		"\6\4\2ZY\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\]\7\26\2\2]^\7&\2\2^_\5\n\6\2_"+
		"`\7\'\2\2`b\3\2\2\2a\37\3\2\2\2a\"\3\2\2\2a$\3\2\2\2a*\3\2\2\2a\62\3\2"+
		"\2\2a:\3\2\2\2a@\3\2\2\2aB\3\2\2\2aC\3\2\2\2aD\3\2\2\2aE\3\2\2\2aF\3\2"+
		"\2\2aG\3\2\2\2aH\3\2\2\2aI\3\2\2\2aJ\3\2\2\2aK\3\2\2\2aL\3\2\2\2aM\3\2"+
		"\2\2aN\3\2\2\2aO\3\2\2\2aP\3\2\2\2aU\3\2\2\2bz\3\2\2\2cd\f \2\2de\t\4"+
		"\2\2ey\5\4\3!fg\f\37\2\2gh\t\3\2\2hy\5\4\3 ij\f\35\2\2jk\7\b\2\2ky\5\4"+
		"\3\36lm\f\34\2\2mn\t\5\2\2ny\5\4\3\35op\f\32\2\2pq\t\6\2\2qy\5\4\3\33"+
		"rs\f\31\2\2st\t\7\2\2ty\5\4\3\32uv\f\5\2\2vw\7$\2\2wy\5\4\3\6xc\3\2\2"+
		"\2xf\3\2\2\2xi\3\2\2\2xl\3\2\2\2xo\3\2\2\2xr\3\2\2\2xu\3\2\2\2y|\3\2\2"+
		"\2zx\3\2\2\2z{\3\2\2\2{\5\3\2\2\2|z\3\2\2\2}\u0082\5\b\5\2~\177\7(\2\2"+
		"\177\u0081\5\b\5\2\u0080~\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2"+
		"\2\2\u0082\u0083\3\2\2\2\u0083\7\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u008c"+
		"\7\61\2\2\u0086\u0087\7\61\2\2\u0087\u0088\7)\2\2\u0088\u008c\5\4\3\2"+
		"\u0089\u008c\7*\2\2\u008a\u008c\7+\2\2\u008b\u0085\3\2\2\2\u008b\u0086"+
		"\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008a\3\2\2\2\u008c\t\3\2\2\2\u008d"+
		"\u0094\5\4\3\2\u008e\u0090\t\2\2\2\u008f\u0091\5\4\3\2\u0090\u008f\3\2"+
		"\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u008e\3\2\2\2\u0093"+
		"\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\13\3\2\2"+
		"\2\u0096\u0094\3\2\2\2\u0097\u009c\5\16\b\2\u0098\u0099\7(\2\2\u0099\u009b"+
		"\5\16\b\2\u009a\u0098\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2"+
		"\u009c\u009d\3\2\2\2\u009d\r\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00b3\5"+
		"\4\3\2\u00a0\u00a1\7\61\2\2\u00a1\u00b3\7)\2\2\u00a2\u00a3\7\61\2\2\u00a3"+
		"\u00a4\7)\2\2\u00a4\u00b3\5\4\3\2\u00a5\u00a6\7\60\2\2\u00a6\u00b3\7)"+
		"\2\2\u00a7\u00a8\7\60\2\2\u00a8\u00a9\7)\2\2\u00a9\u00b3\5\4\3\2\u00aa"+
		"\u00ab\7\36\2\2\u00ab\u00b3\7)\2\2\u00ac\u00ad\7\36\2\2\u00ad\u00ae\7"+
		")\2\2\u00ae\u00b3\5\4\3\2\u00af\u00b3\7*\2\2\u00b0\u00b3\7+\2\2\u00b1"+
		"\u00b3\3\2\2\2\u00b2\u009f\3\2\2\2\u00b2\u00a0\3\2\2\2\u00b2\u00a2\3\2"+
		"\2\2\u00b2\u00a5\3\2\2\2\u00b2\u00a7\3\2\2\2\u00b2\u00aa\3\2\2\2\u00b2"+
		"\u00ac\3\2\2\2\u00b2\u00af\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b1\3\2"+
		"\2\2\u00b3\17\3\2\2\2\17\24\30\32Zaxz\u0082\u008b\u0090\u0094\u009c\u00b2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}