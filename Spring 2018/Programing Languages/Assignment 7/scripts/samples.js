/* global SLang : true */

(function () {

    "use strict";


    var samples = [

	"",
	[ "COBOL subtract warm-up",
	  "(fn (x) => subtract 5 from x 20)",
	  '["Num",15]' ],
	[ "sumlist warm-up",
	  "(fn (x) => sumlist(x) [1,2,3])",
	  '["Num",6]' ],
	[ "New prim. op. + infix syntax",
	  "(fn (n,p,q) => (((~(n)+20)-p) / (q % 3)) 10 2 11)",
	  '["Num",4]' ],
	[ "Boolean ops 1", "(1 === ( (100 / 4) % 3))",
	  '["Bool",true]' ],
	[ "Boolean ops 2", "not( ((11 / 4) > (30 - (25 % 13))) )",
	  '["Bool",true]'],
	[ "If expression 1" , 
	  "(fn (n,p,q) => if n then (p + q) else (p * q) (6 < 1) 2 3)",
	  '["Num",6]' ],
	[ "If expression 2" , 
	  "(fn (n,p,q) => if n then (p + q) else (p * q) (6 > 1) 2 3)",
	  '["Num",5]' ],
	[ "If expression 3" , 
	  "(fn (n,p,q) => if n then (p + q) else (q / 0) 1 2 3)",
	  'No output [Runtime error]' ],
	[ "If expression 4" , 
	  "(fn (n,p,q) => if n then (p + q) else (q / 0) (6 > 1) 2 3)",
	  '["Num",5]' ],
	[ "Lists 1", "[]", '["List",[]]' ],
	[ "Lists 2", "[1]", '["List",[1]]' ],
	[ "Lists 3", "[1,2,3,4,5]", '["List",[1,2,3,4,5]]' ],
	[ "Lists 4", "hd([1,2])", '["Num",1]' ],
	[ "Lists 5", "tl([1,2])", '["List",[2]]' ],
	[ "Lists 6", "tl([1])", '["List",[]]' ],
	[ "Lists 7", "(1 :: [])", '["List",[1]]' ],
	[ "Lists 8", "(1 :: [2,3])", '["List",[1,2,3]]' ],
	[ "Lists 9", "isNull( [] )", '["Bool",true]' ],
	[ "Lists 9", "isNull( [1,2,3] )", '["Bool",false]' ],
	["Let 1",
	 " let x = 1 y = 2 in (x + y) end",
	 '["Num",3]'],
	["Let 2",
	 "let x = 1 in let f = fn(y) => (y + x) in let x = 2 in (f 3) end end end ",
	 '["Num",4]'],
	["Let 3",
	 "let x = 1 sqr = fn (x) => (x * x) in let f = fn(y) => (y + x) in let x = 2 in (x + (sqr (f x))) end end end",
	 '["Num",11]']
    ];

    window.SLang.samples = samples;
    console.log("done loading samples");
})();
