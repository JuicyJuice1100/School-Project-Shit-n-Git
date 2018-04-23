/* Justin Espiritu & Robert Freiberg*/
/* global SLang : true */

(function (){

"use strict";

var exports = {};

function createProgram(e) {
    return ["Program", e]; 
}
function isProgram(e) { 
    return e[0] === "Program"; 
}
function getProgramExp(e) { 
    if (isProgram(e)) {
	return e[1];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getProgramExp is not a program.");
    }
}				       
function createVarExp(v) { 
    return ["VarExp", v]; 
}
function isVarExp(e) { 
    return e[0] === "VarExp"; 
}
function getVarExpId(e) { 
    if (isVarExp(e)) {
	return e[1];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getVarExpId is not a VarExp.");
    }
}
function createIntExp(n) {
    return ["IntExp", parseInt(n)];
}
function isIntExp(e) { 
    return e[0] === "IntExp"; 
}
function getIntExpValue(e) { 
    if (isIntExp(e)) {
	return e[1];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getIntExpValue is not an IntExp.");
    }
}
function createFnExp(params,body) {
    return ["FnExp",params,body];
}
function isFnExp(e) { 
    return e[0] === "FnExp"; 
}
function getFnExpParams(e) { 
    if (isFnExp(e)) {
	return e[1];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getFnExpParams is not an FnExp.");
    }
}
function getFnExpBody(e) { 
    if (isFnExp(e)) {
	return e[2];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getFnExpBody is not an FnExp.");
    }
}
function createAppExp(fn,args) {
    return ["AppExp",fn,args];
}
function isAppExp(e) { 
    return e[0] === "AppExp"; 
}
function getAppExpFn(e) { 
    if (isAppExp(e)) {
	return e[1];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getAppExpFn is not an AppExp.");
    }
}
function getAppExpArgs(e) { 
    if (isAppExp(e)) {
	return e[2].slice(1); // eliminate the first element (i.e., "args")
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getAppExpArgs is not an AppExp.");
    }
}
function createPrim1AppExp(prim,arg) {
    return ["Prim1AppExp",prim,arg];
}
function isPrim1AppExp(e) { 
    return e[0] === "Prim1AppExp"; 
}
function getPrim1AppExpPrim(e) { 
    if (isPrim1AppExp(e)) {
	return e[1];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getPrim1AppExpPrim is not a Prim1AppExp.");
    }
}
function getPrim1AppExpArg(e) { 
    if (isPrim1AppExp(e)) {
	return e[2];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getPrim1AppExpArg is not a Prim1AppExp.");
    }
}
function createPrim2AppExp(prim,arg1,arg2) {
    return ["Prim2AppExp",prim,arg1,arg2];
}
function isPrim2AppExp(e) { 
    return e[0] === "Prim2AppExp"; 
}
function getPrim2AppExpPrim(e) { 
    if (isPrim2AppExp(e)) {
	return e[1];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getPrim2AppExpPrim is not a Prim2AppExp.");
    }
}
function getPrim2AppExpArg1(e) { 
    if (isPrim2AppExp(e)) {
	return e[2];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getPrim2AppExpArg is not a Prim2AppExp.");
    }
}
function getPrim2AppExpArg2(e) { 
    if (isPrim2AppExp(e)) {
	return e[3];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getPrim2AppExpArg is not a Prim2AppExp.");
    }
}

function createListExp(list) {
    return ["ListExp",list];
}
function isListExp(e) { 
    return e[0] === "ListExp"; 
}
function getListExpList(e) { 
    if (isListExp(e)) {
	return e[1];
    } else {
	throw new Error("Interpreter error: "  +
			"The argument of getListExp is not a ListExp.");
    }
}

function createIfElse(x, y, z){
    return ["IfElse", x, y, z]
}
function isIfElse(e){
    return e[0] === "IfElse";
}
function getIfElseArg1(e){
    if(isIfElse(e)){
        return e[1]
    } else {
        throw new Error ("Interpreter error: " + 
                        "the argument of getIfElseArg1 is not a IfElseExp.")
    }
}

function getIfElseArg2(e){
    if(isIfElse(e)){
        return e[2]
    } else {
        throw new Error ("Interpreter error: " + 
                        "the argument of getIfElseArg2 is not a IfElseExp.")
    }
}

function getIfElseArg3(e){
    if(isIfElse(e)){
        return e[3]
    } else {
        throw new Error ("Interpreter error: " + 
                        "the argument of getIfElseArg3 is not a IfElseExp.")
    }
}

exports.createProgram = createProgram;
exports.isProgram = isProgram;
exports.getProgramExp = getProgramExp;
exports.createVarExp = createVarExp;
exports.isVarExp = isVarExp;
exports.getVarExpId = getVarExpId;
exports.createIntExp = createIntExp;
exports.isIntExp = isIntExp;
exports.getIntExpValue = getIntExpValue;
exports.createFnExp = createFnExp;
exports.isFnExp = isFnExp;
exports.getFnExpParams = getFnExpParams;
exports.getFnExpBody = getFnExpBody;
exports.createAppExp = createAppExp;
exports.isAppExp = isAppExp;
exports.getAppExpFn = getAppExpFn;
exports.getAppExpArgs = getAppExpArgs;
exports.createPrim1AppExp = createPrim1AppExp;
exports.createPrim2AppExp = createPrim2AppExp;
exports.isPrim1AppExp = isPrim1AppExp;
exports.isPrim2AppExp = isPrim2AppExp;
exports.getPrim1AppExpPrim = getPrim1AppExpPrim;
exports.getPrim2AppExpPrim = getPrim2AppExpPrim;
exports.getPrim1AppExpArg = getPrim1AppExpArg;
exports.getPrim2AppExpArg1 = getPrim2AppExpArg1;
exports.getPrim2AppExpArg2 = getPrim2AppExpArg2;
exports.createListExp = createListExp;
exports.isListExp = isListExp;
exports.getListExpList = getListExpList;
exports.createIfElse = createIfElse;
exports.isIfElse = isIfElse;
exports.getIfElseArg1 = getIfElseArg1;
exports.getIfElseArg2 = getIfElseArg2;
exports.getIfElseArg3 = getIfElseArg3;

window.SLang.absyn = exports;
}());
