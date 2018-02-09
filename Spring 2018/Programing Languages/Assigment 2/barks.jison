/* 
    description: Starting point for Problem 1
*/

// lexical part of the grammar 
// ===========================

%lex
%%
\s+                   /* no return statement, so skip whitespace */
"yip"                   return "YIP"
"yap"                   return "YAP"
<<EOF>>               return "EOF"
.                     return "INVALID"

/lex

%start program

// phrase-structure section of the grammar.  You complete this
// ===========================================================

%% /* language grammar*/

program
    : bark "EOF"
        {if($1 % 2 == 0){return "Bingley yipped " + $1 + " times";}
        else{return "Darcey yipped " + $1 + " times";}
        }
    ;

bark
    : "YIP" bark
        {$$ = 1 + $2;}
    | "YAP" bark
        {$$ = $2;}
    | /* empty */
        {$$ = 0;}
    ;
