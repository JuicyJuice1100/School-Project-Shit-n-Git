// Justin Espiritu & Robbert Freiberg
var fp = require('./fp');
var util = require('util');

if ( ! exports )
   var exports = [ ];


// Sample problem 0.   Reverse from the slides for Section 2.4 (slides5)

// This just demonstrates how you develop your solutions and add test
// cases

var reverse = function (ns) {
    return reverse_helper(ns,[]);
}

var reverse_helper = function (input,acc) {
    if (fp.isNull(input))
	return acc;
    else 
	return reverse_helper(fp.tl(input),fp.cons(fp.hd(input),acc));
}

// Your solution for problem 1 must appear between this and matching
// end comment below

var addUpOccurrences= function (n,ns) {
    if(fp.isNull(ns)){
        return 0;
    } else if(fp.isEq(n, fp.hd(ns))){
        return fp.add(1, addUpOccurrences(n, fp.tl(ns)));
    } else{
        return addUpOccurrences(n, fp.tl(ns));
    }
};

////////// End of code for problem 1 ////////////////////


// Your solution for problem 2 must appear between this and matching
// end comment below

var intersperse = function (ms,ns) {
    if(fp.isNull(ms) && fp.isNull(ns)){
        return []
    }
    else if(fp.isNull(ms)){
        return ns;
    }
    else if(fp.isNull(ns)){
        return ms;
    }
    else {
        return fp.cons(fp.hd(ms), intersperse(ns, fp.tl(ms)));
    }
};


////////// End of code for problem 2 ////////////////////

// Your solution for problem 3 must appear between this and matching
// end comment below


// Problem 3
var flatten = function (l) {
    if(fp.isNull(l)){
        return [];
    } else if(fp.isList(fp.hd(l))){
        return fp.append(flatten(fp.hd(l)), flatten(fp.tl(l)));
    } else {
        return fp.cons(fp.hd(l), flatten(fp.tl(l)));
    }
};

////////// End of code for problem 3 ////////////////////


// Your solution for problem 4 must appear between this and matching
// end comment below

// Problem 4
var remove_every_nth_helper = function (n,ns,index) {
    if(fp.isNull(ns)){
        return []
    } else if(fp.isEq(index, n)){
        return remove_every_nth(n,fp.tl(ns),1);
    } else{
        return fp.cons(fp.hd(ns), remove_every_nth_helper(n,fp.tl(ns),fp.add(index, 1)))
    }

};

var remove_every_nth = function(n,ns){
    if(fp.isNull(ns) || fp.isLT(n, 2)){
        return [];
    } else {
        return remove_every_nth_helper(n,ns,1);
    }
}

////////// End of code for problem 4 ////////////////////

// Your solution for problem 5 must appear between this and matching
// end comment below

// left, right, and isLeaf are some conveniences for binary tree processing

// Return the left subtree
var left = function (bst) {
    return fp.hd(fp.tl(bst));
};

// Return the right subtree
var right = function (bst) {
    return fp.hd(fp.tl(fp.tl(bst)));
};

// Is this tree a leaf node
var isLeaf = function (tree) {
	return fp.isNull(left(tree)) && fp.isNull(right(tree));
};

var postorder = function (tree) {
    if(fp.isNull(tree)){
        return [];
    } else {
        return fp.append(fp.append(postorder(left(tree)), postorder(right(tree))), fp.cons(fp.hd(tree), []));
    }
};



////////// End of code for problem 5 ////////////////////

// Your solution for problem 6 must appear between this and matching
// end comment below

var removeLeafNodesLessThan = function (n,tree) {
    if(fp.isNull(tree)){
        return [];
    } else if(isLeaf(tree)){
        if(fp.isLT(fp.hd(tree), n)){
            return [];
        } else {
            return tree;
        }
    } else {
        return fp.cons(fp.hd(tree), fp.makeList(removeLeafNodesLessThan(n, left(tree)), removeLeafNodesLessThan(n, right(tree))));
    }
};

////////// End of code for problem 6 ////////////////////



////////// All test cases you add must be below this comment line

////////// Everything below this line will be stripped away when your
////////// submission is evaluated

console.log("Testing sample problem 0");
console.log(reverse( [1,2,3] ));
console.log(reverse( [0] ));
console.log(util.inspect(reverse( [1,2,3]), false ,null,true));

console.log("Testing Problem 1");
console.log(addUpOccurrences( 3, [1,2,3] ));
console.log(addUpOccurrences( 3, [1,2,3,3,4,3,2,1,3] ));
console.log(addUpOccurrences( 3, [1,2,4,5,6] ));
console.log(addUpOccurrences( 3, [ ] ));

console.log("Testing Problem 2");
console.log(intersperse( [1,3,5], [2,4,6] ));
console.log(intersperse( [1,3,5,7,8], [2,4,6] ));
console.log(intersperse( [1,3,5], [2,4,6,7,8] ));

console.log("Testing Problem 3");
console.log(flatten([1, 2, [3, 4, 5], 6]));

console.log("Testing Problem 4");
console.log(remove_every_nth(4, [3, 6, 8, 1, 10, 23, 22, 18, 45]));
console.log(remove_every_nth(2, [3, 6, 8, 1, 10, 23, 22, 18, 45]));

var tree1 = [ 20,
	      [ 10,
		[ 5, [], [] ],
		[ 15, [], [] ] ],
	      [ 30, [], [ 35, [], [] ] ] ];

var tree2 =     [12, [11, [], [] ],
                     [3, [4, [], [] ],
		         [4, [], [] ]]];

console.log("Testing Problem 5");
console.log(postorder(tree1));
console.log(postorder(tree2));


console.log("Testing Problem 6");

console.log(removeLeafNodesLessThan(10, tree1));
console.log(removeLeafNodesLessThan(4, tree2));
console.log(removeLeafNodesLessThan(5, tree2));			
	    

