/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author Justin Espiritu
 */

/**
 * 
 * Class that creates a Hashtable to store variables
 */
public class VariableInput{
    Hashtable<String, Double> variables;
    Scanner scan;
    
    public VariableInput(){
        variables = new Hashtable();
        scan = new Scanner(System.in);
    }
    
    public void variableInput(){
        System.out.println("Enter a variable (Ex. A = 148)");
        String[] input = scan.nextLine().split("\\s*=\\s*", 2);
        scan.reset();
        variables.put(input[0], Double.parseDouble(input[1]));
    }
    
    public void clear(){
        variables.clear();
    }
}
