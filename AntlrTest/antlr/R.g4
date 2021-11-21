/*
 [The "BSD licence"]
 Copyright (c) 2013 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/


grammar R;

//This is the root node of the tree
prog
    :   (expr (';'|NL)*| NL )* EOF
    ;

//The main building blocks of the tree
expr
    :   expr ('*'|'/') expr
    |   expr ('+'|'-') expr
    |   ('-'|'+') expr
    |   expr ':' expr
    |   expr ('>'|'>='|'<'|'<='|'=='|'!=') expr
    |   '!' expr
    |   expr ('&'|'&&') expr
    |   expr ('|'|'||') expr
    |   'if' '(' expr ')' expr
    |   'if' '(' expr ')' expr 'else' expr
    |   'for' '(' ID 'in' expr ')' expr
    |   'while' '(' expr ')' expr
    |   'repeat' expr
    |   'next'
    |   'break'
    |   ID
    |   STRING
    |   HEX
    |   INT
    |   FLOAT
    |   COMPLEX
    |   'NULL'
    |   'NA'
    |   'Inf'
    |   'NaN'
    |   'TRUE'
    |   'FALSE'
    |   expr ('<-') expr
    |   ID '(' subList ')'
    |   ID '<-' 'function' '(' functionDeclarationArguments? ')' '{' exprList '}'
    ;


//Function declaration
functionDeclarationArguments
    : functionDeclarationArgument (',' functionDeclarationArgument)*
    ;
functionDeclarationArgument
    :   ID
    |   ID '=' expr
    |   '...'
    |   '.'
    ;

//Needed for function body in curly braces
exprList
    :   expr ((';'|NL) (expr)?)*
    ;

//Pass the function arguments
subList
    : sub (',' sub)*
    ;
sub :   expr
    |   ID '='
    |   ID '=' expr
    |   STRING '='
    |   STRING '=' expr
    |   'NULL' '='
    |   'NULL' '=' expr
    |   '...'
    |   '.'
    |
    ;






//////////////////////////////////////////////////////////////////////////////////////////////////////////
//Lexor
//////////////////////////////////////////////////////////////////////////////////////////////////////////


HEX :   '0' ('x'|'X') HEXDIGIT+ [Ll]? ;

INT :   DIGIT+ [Ll]? ;

fragment
HEXDIGIT
    : ('0'..'9'|'a'..'f'|'A'..'F')
    ;

FLOAT
    :  DIGIT+ '.' DIGIT* EXP? [Ll]?
    |   DIGIT+ EXP? [Ll]?
    |   '.' DIGIT+ EXP? [Ll]?
    ;

fragment
DIGIT
    :  '0'..'9'
    ;

fragment
EXP
    :   ('E' | 'e') ('+' | '-')? INT
    ;

COMPLEX
    :   INT 'i'
    |   FLOAT 'i'
    ;

STRING
    :   '"' ( ESC | ~[\\"] )*? '"'
    |   '\'' ( ESC | ~[\\'] )*? '\''
    |   '`' ( ESC | ~[\\'] )*? '`'
    ;

fragment
ESC
    :   '\\' [abtnfrv"'\\]
    |   UNICODE_ESCAPE
    |   HEX_ESCAPE
    |   OCTAL_ESCAPE
    ;

fragment
UNICODE_ESCAPE
    :   '\\' 'u' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT
    |   '\\' 'u' '{' HEXDIGIT HEXDIGIT HEXDIGIT HEXDIGIT '}'
    ;

fragment
OCTAL_ESCAPE
    :   '\\' [0-3] [0-7] [0-7]
    |   '\\' [0-7] [0-7]
    |   '\\' [0-7]
    ;

fragment
HEX_ESCAPE
    :   '\\' HEXDIGIT HEXDIGIT?
    ;

ID
    :   '.' (LETTER|'_'|'.') (LETTER|DIGIT|'_'|'.')*
    |   LETTER (LETTER|DIGIT|'_'|'.')*
    ;

fragment
LETTER
    :   [a-zA-Z]
    ;

USER_OP
    :   '%' .*? '%'
    ;

COMMENT
    :   '#' .*? '\r'? '\n' -> skip
    ;

// Match both UNIX and Windows newlines
NL
    :   '\r'? '\n' -> skip
    ;

WS
    :   [ \t\u000C]+ -> skip
    ;