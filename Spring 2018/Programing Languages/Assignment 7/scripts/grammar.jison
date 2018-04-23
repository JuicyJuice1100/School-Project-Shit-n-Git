/* Justin Espiritu & Robert Freiberg*/
/* description: Grammar for SLang 1 */

/* lexical grammar */
%lex

DIGIT		      [0-9]
LETTER		      [a-zA-Z]

%%

\s+                                   { /* skip whitespace */ }
"fn"				      { return 'FN'; }
"hd"                                {return 'HD';}
"tl"                                {return 'TL';}
"::"                                {return 'CONS';}
"isNull"                            {return 'ISNULL';}
"("                   		      { return 'LPAREN'; }
")"                   		      { return 'RPAREN'; }
"+"                   		      { return 'PLUS'; }
"subtract"                         { return 'SUBTRACT'; }
"from"                              {return 'FROM'; }
"sumlist"                           {return 'SUMLIST';}
"==="                               {return 'EQUAL';}
"<"                                 {return 'LESSTHAN';}
">"                                 {return 'GREATERTHAN';}
"not"                               {return 'NOT';}
"-"                                 {return 'MINUS';}
"/"                                 {return 'DIVIDE';}
"%"                                 {return 'MOD';}
"if"                                {return 'IF';}
"then"                              {return 'THEN';}
"else"                              {return 'ELSE';}
"*"                   		      { return 'TIMES'; }
"add1"                                { return 'ADD1'; }
"let"                               {return 'LET';}
"in"                                {return 'IN';}
"end"                               {return 'END';}
","                   		      { return 'COMMA'; }
"~"                               { return 'NEGATE';}
"=>"                   		      { return 'THATRETURNS'; }
"="                                 {return 'EQ'; }
"["                                   { return 'LBRACKET'; }
"]"                                   { return 'RBRACKET'; }
<<EOF>>               		      { return 'EOF'; }
{LETTER}({LETTER}|{DIGIT}|_)*  	      { return 'VAR'; }
{DIGIT}+                              { return 'INT'; }
.                     		      { return 'INVALID'; }

/lex

%start program

%% /* language grammar */

program
    : exp EOF
        { return SLang.absyn.createProgram($1); }
    ;

exp
    : var_exp       { $$ = $1; }
    | intlit_exp    { $$ = $1; }
    | fn_exp        { $$ = $1; }
    | app_exp       { $$ = $1; }    
    | prim1_app_exp { $$ = $1; }
    | prim2_app_exp { $$ = $1; }
    | list_exp      { $$ = $1; }
    | ifElse_exp    {$$ = $1;}
    | let_exp       {$$ = $1;}
    ;

let_exp
    : LET bindings IN exp END
        {   
            $2[1].unshift("args");
            $$ = SLang.absyn.createAppExp(SLang.absyn.createFnExp($2[0], $4), $2[1]);
        }
    ;

bindings
    : var_exp EQ exp
        {$$ = [[$1[1]],[$3]];}
    | var_exp EQ exp bindings
        { 
            $4[0].unshift($1[1]);
            $4[1].unshift($3);
            $$ = $4;
        }
    ;

var_exp
    : VAR  { $$ = SLang.absyn.createVarExp( $1 ); }
    ;

intlit_exp
    : INT  { $$ =SLang.absyn.createIntExp( $1 ); }
    ;

fn_exp
    : FN LPAREN formals RPAREN THATRETURNS exp
           { $$ = SLang.absyn.createFnExp($3,$6); }
    ;

ifElse_exp
    : IF exp THEN exp ELSE exp
            {$$ = SLang.absyn.createIfElse($2, $4, $6);}
    ;

formals
    : /* empty */ { $$ = [ ]; }
    | VAR moreformals 
        { var result;
          if ($2 === [ ])
	     result = [ $1 ];
          else {
             $2.unshift($1);
             result = $2;
          }
          $$ = result;
        }
    ;

moreformals
    : /* empty */ { $$ = [ ] }
    | COMMA VAR moreformals 
       { $3.unshift($2); 
         $$ = $3; }
    ;

app_exp
    : LPAREN exp args RPAREN
       {  $3.unshift("args");
          $$ = SLang.absyn.createAppExp($2,$3); }
    ;

prim1_app_exp
    : prim1_op LPAREN exp RPAREN
       { $$ = SLang.absyn.createPrim1AppExp($1,$3); }
    ;

prim2_app_exp
/*  : prim2_op LPAREN exp COMMA exp RPAREN
       { $$ = SLang.absyn.createPrim2AppExp($1,$3,$5); } */
    : prim2_op exp FROM exp
       {$$ = SLang.absyn.createPrim2AppExp("-", $4, $2); }
    | LPAREN exp prim2_op exp RPAREN
    {$$ = SLang.absyn.createPrim2AppExp($3, $2, $4); }
    ;

prim1_op
    : HD {$$ = $1;}
    | TL {$$ = $1;}
    | ISNULL {$$ = $1;}
    |  ADD1     { $$ = $1; }
    |  SUMLIST  { $$ = $1; }
    | NEGATE {$$ = $1;}
    | NOT {$$ = $1; }

    ;

prim2_op
    :  PLUS     { $$ = $1; }
    |  TIMES    { $$ = $1; }
    |  DIVIDE {$$ = $1;}
    |  MOD {$$ = $1;}
    | SUBTRACT {$$ = $1;}
    | MINUS {$$ = $1;}
    | LESSTHAN {$$ = $1;}
    | GREATERTHAN {$$ = $1;}
    | EQUAL {$$ = $1; }
    | CONS {$$ = $1; }
    ;

args
    : /* empty */ { $$ = [ ]; }
    | exp args
        { var result;
          if ($2 === [ ])
	     result = [ $1 ];
          else {
             $2.unshift($1);
             result = $2;
          }
          $$ = result;
        }
    ;

prim_args
    :  /* empty */ { $$ = [ ]; }
    |  exp more_prim_args    { $2.unshift($1); $$ = $2; }
    ;

more_prim_args
    : /* empty */ { $$ = [ ] }
    | COMMA exp more_prim_args { $3.unshift($2); $$ = $3; }
    ;

list_exp
    : LBRACKET int_list RBRACKET { $$ = SLang.absyn.createListExp($2); } 
    ;

int_list
    : /* empty */        { $$ = []; }
    | INT more_ints      { $2.unshift(parseInt($1)); $$ = $2; }
    ;

more_ints
    : /* empty */         { $$ = []; }
    | COMMA INT more_ints { $3.unshift(parseInt($2)); $$ = $3;}
    ;
%%

