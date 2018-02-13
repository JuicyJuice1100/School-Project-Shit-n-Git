/* 
    description: CS 331 - Spring 2017 - A2 - Problem 2
*/

// lexical section of the grammar 
// ==============================

%lex
%%
\s+                   /* no return statement, so skip whitespace */
[0-9]+                return "NUMBER"
[a-zA-Z][a-zA-Z0-9_]* return "ID"
"~"                   return "UNARYMINUS"
"+"                   return "PLUS"
"-"                   return "MINUS"
"*"                   return "TIMES"
"/"                   return "DIV"
"%"                   return "MOD"
"^"                   return "POW"
"="                   return "EQUAL"
"("                   return "LPAREN"
")"                   return "RPAREN"
<<EOF>>               return "EOF"
.                     return "INVALID"

/lex

%start program

// phrase-structure section of the grammar.  You must complete this
// ================================================================

%% 

program
    : declaration add "EOF"
        {return JSON.stringify([$1,$2])}
    ;

declaration
    : "ID" "EQUAL" declaration
        {$$ = $3 + " " + $1}
    | "ID" "EQUAL"
        {$$ = $1}
    ;


add
    : mult
        {$$ = $1}
    | add "PLUS" mult
        {$$ = $1 + $3}
    | add "MINUS" mult
        {$$ = $1 - $3}
    ;

mult
    : exponent
        {$$ = $1}
    | mult "TIMES" exponent
        {$$ = $1 * $3}
    | mult "DIV" exponent
        {$$ = Math.floor($1 / $3)}
    | mult "MOD" exponent
        {$$ = $1 % $3}
    ;

exponent
    : unary
        {$$ = $1}
    | unary "POW" exponent
        {$$ = Math.pow($1, $3)}
    ;

unary
    : parenthesis 
        {$$ = $1}
    | "UNARYMINUS" unary
        {$$ = -$2}
    ;

parenthesis
    : "NUMBER"
        {$$ = Number($1)}
    | "LPAREN" add "RPAREN"
        {$$ = $2}
    ;

 