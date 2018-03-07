var fp = require('./fp');
var util = require('util');

if (!exports)
    var exports = [];



// Your solution for problem 1 must appear between this and matching
// end comment below

var minMax = function (lol) {
    var min = function (l) {
        return fp.reduce(fp.min, fp.tl(l), fp.hd(l));
    }
    var max = function (l) {
        return fp.reduce(fp.max, fp.tl(l), fp.hd(l));
    }

    var minMax = function (l) {
        return fp.makeList(min(l), max(l));
    }

    var reducer = function (x, y) {
        return fp.cons(x, y);
    }

    return fp.reduceRight(
        reducer,
        fp.map(minMax, lol),
        []
    )
};


////////// End of code for problem 1 ////////////////////


// Your solution for problem 2 must appear between this and matching
// end comment below

var deepMap = function (f, ns) {
    if (fp.isNull(ns)) {
        return ns;
    } else if (fp.isList(fp.hd(ns))) {
        return fp.cons(deepMap(f, fp.hd(ns)), deepMap(f, fp.tl(ns)));
    } else {
        return fp.cons(f(fp.hd(ns)), deepMap(f, fp.tl(ns)));
    }
};


////////// End of code for problem 2 ////////////////////

// Your solution for problem 3 must appear between this and matching
// end comment below


var countOccurrences = function (ns) {
    // var counter = function(l){
    //     return fp.reduce(reducer, fp.filter(fp.curry(fp.isEq)(l), ns), 0);
    // }

    var mapper = function (l) {
        return fp.append(fp.makeList(fp.hd(fp.filter(fp.curry(fp.isEq)(l), ns))), fp.makeList(fp.reduce(reducer, fp.filter(fp.curry(fp.isEq)(l), ns), 0)));
    }

    var reducer = function (x, y) {
        return fp.add(x, 1);
    }

    return fp.map(mapper, ns);
};


////////// End of code for problem 3 ////////////////////


// Your solution for problem 4 must appear between this and matching
// end comment below


var isInRect = function (t, l, b, r, point) {
    if (fp.isLT(fp.hd(fp.tl(point)), t)) {
        if (fp.isGT(fp.hd(fp.tl(point)), b)) {
            if (fp.isGT(fp.hd(point), l)) {
                if (fp.isLT(fp.hd(point), r)) {
                    return true;
                }// point is to the left of r
            }// point is to the right of l
        }// point is above b
    }// point is below t
    return false;
};// isInRect function

var topEdge = 1000000;
var leftEdge = -1000000;
var bottomEdge = -1000000;
var rightEdge = 1000000;

// curry5 curries a function of five parameters
var curry5 = function (f) {
    return function (g) {
        return function (h) {
            return function (i) {
                return function (j) {
                    return function (k) {
                        return f(g, h, i, j, k);
                    }
                }
            }
        }
    }
};

// Use curry5 to define each of the five functions specified in the
// assignment
var isInFirstQuadrant = function (x) { return curry5(isInRect)(topEdge)(0)(0)(rightEdge)(x); };
var isInSecondQuadrant = function (x) { return curry5(isInRect)(topEdge)(leftEdge)(0)(0)(x); };
var isInThirdQuadrant = function (x) { return curry5(isInRect)(0)(leftEdge)(bottomEdge)(0)(x); };
var isInFourthQuadrant = function (x) { return curry5(isInRect)(0)(0)(bottomEdge)(rightEdge)(x); };
var isInUnitSquare = function (x) { return curry5(isInRect)(1)(-1)(-1)(1)(x); };



////////// End of code for problem 4 ////////////////////

// Your solution for problem 5 must appear between this and matching
// end comment below


var makeCompositeMFRFunc = function (mf, ff, rf, acc) {
    return 0;
};


////////// End of code for problem 5 ////////////////////

// Your solution for problem 6 must appear between this and matching
// end comment below


var howManyWithLargerGTSmaller = function (list) {
    var mapper = function(x){
        return fp.reduce(fp.isLT, fp.tl(x), fp.hd(x));
    }

    var reducer = function(x, y){
        return fp.add(x, 1);
    }

    return fp.reduce(
        reducer,
        fp.filter(fp.curry(fp.isEq)(true), fp.map(mapper, minMax(list))),
        0
    )
};


////////// End of code for problem 6 ////////////////////

////////// All test cases you add must be below this comment line

////////// Everything below this line will be stripped away when your
////////// submission is evaluated

console.log("Testing Problem 1");
console.log(minMax([[1, 2, 3], [5, 4], [6], [7, 10, 9, 9, 10]]));

console.log("Testing Problem 2");
console.log(deepMap(function (x) { return x + 1; }, [1, 2, [3, 4, 5], 6]));
console.log(deepMap(function (x) { return 2 * x; }, [[1, 2, 3], [5, 4], [6], [7, 10, [9, 9], 10]]));

console.log("Testing Problem 3");
console.log(countOccurrences([5]));
console.log(countOccurrences([1, 2, 3]));
console.log(countOccurrences([1, 2, 3, 2, 3, 1, 4, 5, 1]));

console.log("Testing Problem 4");

console.log(isInFirstQuadrant([1, 1]));
console.log(isInFirstQuadrant([-1, 1]));
console.log(isInFirstQuadrant([-1, -1]));
console.log(isInFirstQuadrant([1, -1]));
console.log(isInSecondQuadrant([1, 1]));
console.log(isInSecondQuadrant([-1, 1]));
console.log(isInSecondQuadrant([-1, -1]));
console.log(isInSecondQuadrant([1, -1]));
console.log(isInThirdQuadrant([1, 1]));
console.log(isInThirdQuadrant([-1, 1]));
console.log(isInThirdQuadrant([-1, -1]));
console.log(isInThirdQuadrant([1, -1]));
console.log(isInFourthQuadrant([1, 1]));
console.log(isInFourthQuadrant([-1, 1]));
console.log(isInFourthQuadrant([-1, -1]));
console.log(isInFourthQuadrant([1, -1]));
console.log(isInUnitSquare([0, 0]));


console.log("Testing Problem 5");
// Uncomment the lines below once you are ready to test makeCompositeMFRFunc
console.log(
    makeCompositeMFRFunc(
        function (x) { return x + 1; },
        function (x) { return x > 0; },
        fp.add,
        0)
        ([0, 0, -1, -1, 4, 3])
);


console.log(
    makeCompositeMFRFunc(
        function (x) { return x + 3; },
        function (x) { return fp.isZero(x % 2); },
        function (a, n) { return fp.cons(n, a); },
        [])
        ([11, 15, 12, 4, 3])
);


console.log("Testing Problem 6");
console.log(howManyWithLargerGTSmaller([]));
console.log(howManyWithLargerGTSmaller([[1, 2, 3]]));
console.log(howManyWithLargerGTSmaller([[1, 1], [2]]));
console.log(howManyWithLargerGTSmaller([[1, 2, 3], [2, 2], [1, -1]]));

