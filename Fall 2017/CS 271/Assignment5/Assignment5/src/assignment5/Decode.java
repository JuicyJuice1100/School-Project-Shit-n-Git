/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import java.io.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Justin
 */
public class Decode {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JFileChooser chosen = new JFileChooser();
        int returnVal = chosen.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Scanner file = new Scanner(chosen.getSelectedFile());
            StringBuilder output = new StringBuilder();
            /* 
                Just ran out of time to finish.
            */
            JFileChooser create = new JFileChooser();
            create.setCurrentDirectory(new File(""));
            int retrival = create.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fw = new FileWriter(create.getSelectedFile() + ".txt");
                    fw.write(output.toString());
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
