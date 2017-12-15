/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab12;

import java.io.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Justin Espiritu
 */
public class Lab12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        JFileChooser chosen = new JFileChooser();
        int returnVal = chosen.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Scanner file = new Scanner(chosen.getSelectedFile());
            while(file.hasNext()){
                /* did not have time to finish but wanted to make some type
                of program that compiled.  Got stuck on my method as Dijkstra 
                and Prim's method uses distances/weights but with the way that
                I implemented my program I did not know how to grab the weights
                from my edges when I am given only a vertex.
                */
            }
        }
    }
}
