/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;
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
        FileInputStream fstream = new FileInputStream("keywords.txt");
        BufferedReader br = new BufferedReader(new InputStreamREader(fstream));
        
        String strLine;
        
        while((strLine = br.readLine()) != null){
            keyWordsTree.insert(strLine);
        }
        
        br.close();
    }
    
}
