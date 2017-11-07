/*
 * Did not have tree hold the data for the line numbers, couldn't get it to compile when 
 * implementing it.  Without it my program has a run time of O(n) where n is the number 
 * of words in the given java source file.  I got this by adding the time it takes to create
 * the AVL tree that contains the keywords which will be O(n), not including the rotations to keep the 
 * tree balanced.  I will not include in the calculation.  Plus the time it takes to add the words from the
 * java source which will be O(n), where n is the number of words in the file, + O(logn), the time it takes to search for the words in the keywords tree.
 * If we include the time it takes to add the line number it will take O(logn) to check if the lineNumber exists already for the checked word.
 * Then to print will take O(n) or O(n * n) if we include the lineNumber, as we have to traverse through each 
 * element in the tree + each element in the tree of each node.  Threrfore my program runs at
 * O(n).  If we include the traversal of the lineNumber Tree it would be O(n^2).
 */
package assignment2;
import java.util.Scanner;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Justin Espiritu
 * 
 */
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyTreeSet<String> keyWordsTree = new MyTreeSet();
        MyTreeSet<String> identifierTree = new MyTreeSet();
        Scanner scan;
        try{
            FileInputStream fstream = new FileInputStream("../keywords.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            
            String strLine;
        
            while((strLine = br.readLine()) != null){
                strLine = strLine.trim();
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Source Code", "java");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try{
                scan = new Scanner(chooser.getSelectedFile());
                while(scan.hasNext()){
                    String temp = scan.next();
                    if(!keyWordsTree.contains(temp.toLowerCase()) && !identifierTree.contains(temp.toLowerCase())){
                        identifierTree.insert(temp);
                    }
                }
            }
            catch(FileNotFoundException ex){
                ex.printStackTrace();
            }
            identifierTree.inorder();
        }
    }   
}
