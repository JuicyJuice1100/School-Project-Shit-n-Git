/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 
 * @author Justin Espiritu
 * @date 10/18/2017
 * @class EquationInputOutput
 * 
 * Evaluates expression that are pre-parsed as ArrayList
 * Contains a infix to postfix converter
 * and evaluator for postfix expressions
 * extends the stack to easily implement the pre-built java stack
 */
public class EquationInputOutput extends Stack{
    HashMap<String, Double> variables;
    
    /**
     * 
     * @param variables hashmap of variables
     * Constructor that assigns a Hashmap to a variable
     */
    public EquationInputOutput(HashMap variables){
        this.variables = variables;
    }
    
    /**
     * 
     * @param infix pre-parsed equation as an ArrayList
     * @return an ArrayList as a postfix equation
     * Converts a infix equation to a postfix equation and returns a parsed ArrayList
     */
    public ArrayList<String> ConvertInfixToPostfix(ArrayList<String> infix){
        ArrayList postfix = new ArrayList();
         for(String string: infix){
            switch (string) {
                case "(":
                    push(string);
                    break;
                case ")":
                    while(!peek().equals("(")){
                        postfix.add(pop());
                    }   pop();
                    break;
                case "+": case "-":
                    while(!empty() && !peek().equals("(")){
                        postfix.add(pop());
                    }   push(string);
                    break;
                case "*": case "/":
                    while(!empty() && !peek().equals("(") && !peek().equals("+")&& !peek().equals("-")){
                        postfix.add(pop());
                    }   push(string);
                    break;
                default:
                    postfix.add(string);
            }
        }
        while(!empty()){
            postfix.add(pop());
        }
        return postfix;
    }
    
    /**
     * 
     * @param equation each input of the equation as an element in ArrayList
     * @return a double, result of the postfix equation
     * Checks if there are any variables then Evaluates the postfixed equation
     */
    public double EvaluateEquation(ArrayList<String> equation){
        double result = 0.0;
        for(String string: equation){
            if(variables.containsKey(string)){
                string = Double.toString(variables.get(string));
            }
            if(string.equals("+") || string.equals("-") || string.equals("/") || string.equals("*")){
               //Had to make sure the expressions were being written currectly so made variables to make sure order was correct (specifically for - and /
               String variableX, variableY;
               variableY = pop().toString();
               variableX = pop().toString();
               push(EvaluateExpression((variableX + string + variableY)));
            }
            else{
                push(string);
            }
        }
        return Double.parseDouble(pop().toString());
    }
    
    /**
    * @param expression expression written as a string 
    * @return String, result from the written expression
    * Takes in an expression as a string and evaluates it using javascript and returns the result as a string
    */
    public String EvaluateExpression(String expression){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = null;
        try{
            result = engine.eval(expression);
        }catch(ScriptException ex){
            
        }
        return result.toString();
    }
}

