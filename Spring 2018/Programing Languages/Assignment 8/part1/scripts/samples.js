/* global SLang : true */

(function () {

"use strict";

var samples = [

/* 0 */   "",
/* 1 */   [ "lets",
	    "lets\n" +
	    "    u = 1\n" +
	    "    v = (u + 1)\n" +
	    "in\n" +
	    "    print v\n" +
	    "end"
          ],
/* 2 */   [ "lets",
            "lets\n" +
	    "    u = 1\n" +
	    "    v = (u * 2)\n" +
	    "in\n" +
	    "    lets\n" +
            "        u = v\n" +
            "        v = (u * 3)\n" +
	    "    in\n" +
            "        print v\n" +
	    "    end\n" +
	    "end"
          ],
/* 3 */   [ "lets",
            "lets\n" +
	    "    u = 1\n" +
	    "    v = (u * 2)\n" +
	    "in\n" +
	    "    let\n" +
            "        u = v\n" +
            "        v = (u * 3)\n" +
	    "    in\n" +
            "        print v\n" +
	    "    end\n" +
	    "end"
          ],
/* 4 */   [ "for",
	    "let\n" +
	    "    i = 0\n" +
	    "    x = 99\n" +
	    "in\n" +
	    "    for( ; (i<10); set i = (i+2) ) {\n" +
            "        set x = (2 * i);\n" +
            "        print (x + i)\n" +
	    "    }\n" +
	    "end"
	  ],
/* 5 */   [ "letmr",
	    "let\n" +
	    "    false = 0\n" +
	    "    true = 1\n" +
	    "in\n" +
	    "    letmr\n" +
            "        f1 = fn (n) => if (n===0) then true else (f2 (n - 1))\n" +
            "        f2 = fn (n) => if (n===0) then false else (f1 (n - 1))\n" +
	    "    in\n" +
            "        print (f1 25);\n" +
            "        print (f1 26);\n" +
            "        print (f2 25);\n" +
            "        print (f2 26)\n" +
	    "    end\n" +
	    "end"
          ],
/* 6 */   [ "letmr",
	    "let\n" +
	    "    i = 0\n" +
	    "in\n" +
	    "    letmr\n" +
            "        f = fn (n) => if (n===0) then 1 else (n - (m (f (n-1))))\n" +
            "        m = fn (n) => if (n===0) then 0 else (n - (f (m (n-1))))\n" +
	    "    in\n" +
            "        for( ; (i < 5); set i = (i+1) ) {\n" +
            "           print (m i)\n" +
            "        }\n" +
	    "    end\n" +
	    "end"
          ]
];

SLang.samples = samples;

}());
