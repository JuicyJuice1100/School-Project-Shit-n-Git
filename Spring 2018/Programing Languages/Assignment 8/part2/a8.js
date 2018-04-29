var is = require("./is");

if ( ! exports )
   var exports = [ ];

// This code just constructs two sequences used in testing

var primes = function () {
    var sift = function (p,s) { return is.filter(function (n) { return n % p !== 0; }, s); };
    var helper = function (s) {
	return is.cons(is.hd(s), function () { return helper(sift(is.hd(s), is.tl(s))); } );
    };
    return helper(is.from(2));
}();

var seq = is.iterates(function (n) { return n % 3 === 0 ? n+2 : n+1; },1);


///////////////////////////////////////////////////////////////////////////
//                              Problem 1
///////////////////////////////////////////////////////////////////////////
// Your solution for problem 1 must appear between this and matching
// end comment below

var findTwins = (n) => is.hd(n) + 2 === is.hd(is.tl(n)) ? is.cons([is.hd(n), is.hd(is.tl(n))], () => findTwins(is.tl(n))) : findTwins(is.tl(n));


////////// End of code for problem 1 ////////////////////


///////////////////////////////////////////////////////////////////////////
//                              Problem 2
///////////////////////////////////////////////////////////////////////////
// Your solution for problem 2 must appear between this and matching
// end comment below


// Part a
const E = "east", S = "south", NE = "northEast", SW = "southWest";

var points2Dhelper = function(x, y, dir){
    if(dir === E){
        return is.cons([x, y], () => points2Dhelper(x + 1, y, SW));
    } else if(dir === S){
        return is.cons([x, y], () => points2Dhelper(x, y + 1, NE));
    } else if (dir === SW){
        return is.cons([x, y], (x - 1 === 0) ? () => points2Dhelper(x - 1, y + 1, S) : () => points2Dhelper(x - 1, y + 1, SW));
    } else {
        return is.cons([x, y], (y - 1 === 0) ? () => points2Dhelper(x + 1, y - 1, E) : () => points2Dhelper(x + 1, y - 1, NE));
    }
}

var points2D = points2Dhelper(0, 0, E);

// Part b

var triangle = is.filter((x) => x[0] > x[1], points2D);

// Part c

// var isOutsideCircle = function (x,y,cx,cy,r) {

// };

var isOutsideCircle = (x, y, cx, cy, r) => x*x + y*y  > (cx + r) * (cx + r);

var hole = is.filter((x) => isOutsideCircle(x[0], x[1] ,0 ,0 ,3), points2D);


////////// End of code for problem 2 ////////////////////


//// All test cases you add must be below this comment.  Everything
//// below this line will be stripped away to accomodate our more
//// extensive set of test cases when your submission is evaluated

console.log(is.take(seq,20));
// Should be [1,2,3,5,6,8,9,11,12,14,15,17,18,20,21,23,24,26,27,29]
var twinSeq = findTwins(seq);
console.log(is.take(twinSeq,10));
// Should be [[3,5],[6,8],[9,11],[12,14],[15,17],[18,20],[21,23],[24,26],[27,29],[30,32]]
var twinPrimes = findTwins(primes);
console.log(is.take(twinPrimes,10));
// Should be [[3,5],[5,7],[11,13],[17,19],[29,31],[41,43],[59,61],[71,73],[101,103],[107,109]]

console.log(is.take(points2D,20));
// Should be [[0,0],[1,0],[0,1],[0,2],[1,1],[2,0],[3,0],[2,1],[1,2],[0,3],[0,4],[1,3],
// [2,2],[3,1],[4,0],[5,0],[4,1],[3,2],[2,3],[1,4]]

console.log(is.take(triangle,15));
// Should be [[1,0],[2,0],[3,0],[2,1],[3,1],[4,0],[5,0],[4,1],[3,2],[4,2],[5,1],[6,0],[7,0],[6,1],[5,2]]

console.log(is.take(hole,15));
// Should be [[0,4],[1,3],[3,1],[4,0],[5,0],[4,1],[3,2],[2,3],[1,4],[0,5],[0,6],[1,5],[2,4],[3,3],[4,2]]
