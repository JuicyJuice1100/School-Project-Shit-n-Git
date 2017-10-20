/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.HashMap;

/**
 * 
 * @author Justin Espiritu
 * @date 10/18/2017
 * @class VariableInput
 * 
 * Creates a Hashmap that assigns a user inputed variable, key, and assigns the user inputed value as, value
 */
public class VariableInput{
    HashMap<String, Double> variables;
    
    /**
     * Constructor
     */
    public VariableInput(){
        variables = new HashMap();
    }
    
    /**
     * 
     * @param variable user inputed string
     * Takes user input and assigns key and value for the hashmap
     */
    public void variableInput(String variable){
        String[] input = variable.split("\\s*=\\s*", 2);
        variables.put(input[0], Double.parseDouble(input[1]));
    }
    
    /**
     * 
     * @return variables
     * standard get() function
     */
    public HashMap getVariables(){
        return variables;
    }
}
