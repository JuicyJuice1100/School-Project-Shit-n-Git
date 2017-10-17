/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author Justin Espiritu
 */

import java.util.Scanner;

public class Assignment1 {

    /**
     * Outputs a small command prompt and takes in user inputs for commands
     * Loops until exited w/ 0
     * Only put integers for commands otherwise program will break w/ exception
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        VariableInput variableInput = new VariableInput();
        EquationInputOutput equationInputOutput;
        
        System.out.println("Enter a command (1 = enter new variable, "
                + "2 = enter equation, 3 = delete variables, 0 = exit)");
        int command = scan.nextInt();
        
        while(command != 0){
            
            switch(command){
                case 1:
                    variableInput.variableInput();
                    break;
                case 2:
                    System.out.println("Enter an equation (Ex. A + 158 * B)"
                            + "Only use variables that were inputed");
                    //removes the /n string.  Wouldn't scan without this
                    scan.nextLine();
                    String equation = scan.nextLine().replaceAll("\\s+", "");
                    equationInputOutput = new EquationInputOutput(variableInput, equation);
                    break;
                case 3:
                    variableInput.clear();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid command");
            }
            
            System.out.println("Enter a command (1 = enter new variable, "
                + "2 = enter equation, 0 = exit)");
            command = scan.nextInt();
        }
    }
    
}
