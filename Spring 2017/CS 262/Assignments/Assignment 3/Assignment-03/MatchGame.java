
/**
 * Write a description of class NimMatch here.
 * 
 * Creates NimGame Objects
 * 
 * @author 
 * @version (2/7/17)
 */

import java.util.Scanner ;
import javax.swing.* ;

public class MatchGame
{
    //declarations
    private int gamesPlayed ;
    
    private int humanWon ;
    private int computerWon ;
    
    

    //constructors
    public MatchGame()
        {
            gamesPlayed = 0 ;
            humanWon = 0 ;
            computerWon = 0 ;
        }//default constructor
    
    //methods    
        
    public void playMatch()
        {
           int gameSelect ;
           int playMatch ;
           boolean newMatch = true ; 
            Scanner scanner = new Scanner(System.in) ;
            
            while(newMatch)
            {
            
            //processing
            System.out.println(" ***GAME LIST***" ) ;
            System.out.println( "1 - Game of Nim" ) ;
            
            System.out.println("Which game do you want to play?") ;
            gameSelect = scanner.nextInt() ;
            
            //switch for expanding the program
            switch ( gameSelect )
                {
                    case 1 :
                        playNim() ;
                        break ;
                    default :
                        newMatch = false ;
                        break ;
                }//switch block
                
           playMatch = JOptionPane.showConfirmDialog(null, "New match?", "choose one",  JOptionPane.YES_NO_OPTION);
           if(playMatch == JOptionPane.NO_OPTION)
           {
               newMatch = false ;
            }
            else
            {
                newMatch = true ;
            }
                
            } // end while    
                
        }//play match
    
    public void updateScores( NimGame game )
        {
            if(game.getWinner() == NimGame.HUMAN)
            {
                humanWon++ ;
            }
            else
            {
                computerWon++ ;
            }
         
            gamesPlayed ++ ;
        }//update scores
        
    public String reportScores()
        {
            int i = 1 ;
            
            String str ;
            str = gamesPlayed + " Games Played " + "\n" + computerWon + " Computer wins, " 
            + "\n" + humanWon +  " Player wins, " ;

            return str ;        
        }//report scores
        
        private void playNim()
        {
            int difficulty = 0 ;
            int playGame  ;
            boolean newGame = true ;
            Scanner scanner = new Scanner(System.in) ;
            
            //variables needed to update the scores properly
            int highScore ;
            int matchWinner ;
            
            NimGame test ;

            //loop to play multiple games
            while ( newGame )
                {
                    //creates a nim game object and plays it
                    NimGame game = new NimGame() ;
                    
                    game.playGame(  ) ;
                    
                    updateScores( game ) ;
                    System.out.println( reportScores() ) ;
                    
                    playGame = JOptionPane.showConfirmDialog(null, "New game?", "choose one", JOptionPane.YES_NO_OPTION);
           if(playGame == JOptionPane.NO_OPTION)
           {
               newGame = false ;
            }
            else
            {
                newGame = true ;
            }
                    
                  }//while block

        }
}//nim match
