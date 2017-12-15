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
 * Class for compressing in LZ78
 * Encode
 * @author Justin Espiritu
 */
public class Assignment5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JFileChooser chosen = new JFileChooser();
        int returnVal = chosen.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String input = "";
            String check = "";
            ArrayList<Integer> ascii = new ArrayList<>();
            HashMap<String, Integer> dictionary = new HashMap<>();
            StringBuilder output = new StringBuilder();
            Scanner file = new Scanner(chosen.getSelectedFile());
            while (file.hasNext()) {
                input += file.next();
            }
            for (char ch : input.toCharArray()) {
                ascii.add((int) ch);
            }
            String dic = "";
            int i = 0;
            while (i < ascii.size()) {
                check = ascii.get(i).toString();     
                if (dictionary.get(check) == null) {
                    output.append("(" + 0 + "," + check + ")");
                    dictionary.put(check, i + 1);
                } else {
                    dic = check + ascii.get(i+1).toString();
                    output.append("(" + dictionary.get(check) + ",");
                    check = ascii.get(i + 1).toString();  
                    output.append(check + ")");
                    dictionary.put(dic, i + 1);
                    i++;
                }
                i++;
            }
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
