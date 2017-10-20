/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author Justin Espiritu
 * @date 10/18/2017
 * @class Assignment1
 * 
 * Driver for VariableInput and EquationInputOutput Class
 * Takes user inputs and either saves user input to a hashmap as variables
 * or evaluates an expression
 * Assumptions:
 * User input as a variable contains one "=" with variable to the left of "="
 * and its value to the right
 * Expression do not contain variables that were not defined by the user, no 
 * "=" in expressions
 */
public class Assignment1{

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        VariableInput variableInput = new VariableInput();
        EquationInputOutput equationInputOutput;
        ArrayList arrayListEquation = new ArrayList();
        String command = scan.nextLine();
        
        //Checks to if user puts a valid expression or inputing a variable.  If neither exit program.
        while(command.contains("=") || command.contains("+") || command.contains("-") || command.contains("/") || command.contains("*")){
            //Variable
            if(command.contains("=")){
                variableInput.variableInput(command);
            }
            //Expression
            else{
                command = command.replaceAll("\\s*","");
                /*In order to accept any expression with any ammount of spaces I created a String Tokenizer then inputed
                * the tokens into an ArrayList for easier access and evaluation
                */
                StringTokenizer token = new StringTokenizer(command, "+-*/()", true);
                ArrayList<String> equation = new ArrayList<>();
                while(token.hasMoreTokens()){
                    equation.add(token.nextToken());
                }
                
                equationInputOutput = new EquationInputOutput(variableInput.getVariables());
                arrayListEquation = equationInputOutput.ConvertInfixToPostfix(equation);
                //So user can see postfix expression
                System.out.println(arrayListEquation.toString());
                //Answer as double rounded to 2 decimal places
                System.out.printf("\033[31m%.2f \n", equationInputOutput.EvaluateEquation(arrayListEquation));
            }
            command = scan.nextLine();
        }
        
    }
}

