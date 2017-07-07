
/**
 * Write a description of class Driver here.
 * 
 * A driver class to go with the NimMatch and NimGame classes.
 * 
 * @author 
 * @version (2/7/17)
 */

import javax.swing.* ;
import java.util.Scanner ;

public class Driver
{
    public static void main(String[] args)
        {
            //declarations
            /*MatchGame match ;
            match = new MatchGame() ;
            match.playMatch() ;
    	*/
            NimGameGUI nimGame = new NimGameGUI();
            nimGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            nimGame.setSize(100, 100);
            nimGame.setVisible(true);

        }//main
}//driver
