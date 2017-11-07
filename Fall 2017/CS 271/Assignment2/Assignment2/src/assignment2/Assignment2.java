/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Justin Espiritu
 */
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyTreeSet<String> keyWordsTree = new MyTreeSet();
        try{
            FileInputStream fstream = new FileInputStream("../keywords.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            
            String strLine;
        
            while((strLine = br.readLine()) != null){
                strLine.replaceAll("\\s+","");
                keyWordsTree.insert(strLine);
            }

            br.close();
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        
        keyWordsTree.levelTraverse();
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java File", "java");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getName());
        }
    }   
}
