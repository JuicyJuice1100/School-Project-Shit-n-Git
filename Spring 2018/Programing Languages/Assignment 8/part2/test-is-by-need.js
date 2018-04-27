var is = require('./is-by-need');

var ints = is.from(1);
console.log(ints);
console.log(is.take(ints,10));
console.log(ints);
console.log(is.take(ints,5));
console.log(ints);
console.log(is.take(ints,10));
console.log(ints);
console.log(is.take(ints,12));
console.log(ints);

// Sieve of Eratosthenes
var primes = function () {
    var sift = function (p,seq) { 
	             return is.filter(function (n) { return n % p !== 0; }, 
				      seq); };
    var helper = function (seq) {
	return is.cons(is.hd(seq), 
		       function () { 
			   return helper(sift(is.hd(seq), is.tl(seq))); } );
    };
    return helper(is.from(2));
}();
console.log(primes);
console.log(is.take(primes,5));
console.log(primes);
console.log(is.take(primes,3));
console.log(primes);
console.log(is.take(primes,10));
console.log(primes);

