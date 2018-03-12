// Justin Espiritu & Robbert Freiberg
// Bonus points for #2 using reduce
var fp = require('./fp');
var util = require('util');

if ( ! exports )
   var exports = [ ];

// Your solution for problem 1 must appear between this and matching
// end comment below

var searchList = function(ns, target) {
    var cps_search = function (ns, k){
        if(fp.isNull(ns)){
            return [];
        } else if(fp.isEq(fp.hd(ns), target)){
            return k(fp.makeList(target));
        } else{
            return cps_search(fp.tl(ns),
                function(x) {
                    return k(fp.cons(fp.hd(ns), x));
                })
            }
    };
    return cps_search(ns, function(x){return x;});
};    


////////// End of code for problem 1 ////////////////////


// Your solution for problem 2 must appear between this and matching
// end comment below

var b2d = function (b) {
    var mapper = function(x) {
        return x;
    }

    var reducer = function(x, y){
        return fp.add(x, fp.mul(y, 2));
    }

    return fp.reduceRight(
        reducer,
        fp.map(mapper, b),
        0
    )
};



// ////////// End of code for problem 2 ////////////////////
// 
// Your solution for problem 3 must appear between this and matching
// end comment below

var b2d_cps = function (b) {
    var cps = function(ns, k){
        if(fp.isZero(fp.hd(fp.reverse(ns)))){
            return "Leading zeros are not allowed";
        } else if(fp.isNull(fp.tl(ns))){
            return k(fp.hd(ns));
        } 
        else {
            return cps(fp.tl(ns), function(x){
                return k(fp.add(fp.hd(ns), fp.mul(x, 2)));
            })
        }
    }
    return cps(b, function(x){return x});
};


// ////////// End of code for problem 3 ////////////////////
// 

////////// All test cases you add must be below this comment line

////////// Everything below this line will be stripped away when your
////////// submission is evaluated

console.log("Testing Problem 1");
console.log(searchList([1,2,3,4,5], 4, []));
console.log(searchList([1,2,3,4,5], 6, []));


console.log("Testing Problem 2");
console.log( b2d([0]) );
console.log( b2d([1]) );
console.log( b2d([0,1]) );
console.log( b2d([1,1]) );
console.log( b2d([0,0,1,1]) );
console.log( b2d([1,0,1,0,0,1,1]) );
console.log( b2d([1,0,1,0,0,0,0]) );

console.log("Testing Problem 3");
console.log( b2d_cps([0]) );
console.log( b2d_cps([0,0]) );
console.log( b2d_cps([0,0,0,0]) );
console.log( b2d_cps([1]) );
console.log( b2d_cps([1,0]) );
console.log( b2d_cps([0,1]) );
console.log( b2d_cps([1,1]) );
console.log( b2d_cps([0,0,1,1]) );
console.log( b2d_cps([1,0,1,0,0,1,1]) );
console.log( b2d_cps([1,0,1,0,0,0,0]) );
