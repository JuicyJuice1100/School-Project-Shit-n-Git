
/**
 * Write a description of class NimGame here.
 * 
 * Creates a Nim Game object.
 * 
 * CS 262
 * Section 01
 * Assignment 01
 * 
 * @author 
 * @version (2/7/17)
 */

import java.util.Scanner ;
import javax.swing.* ;

public class NimGame
{
    //Declararions
    public static final int HUMAN = 1 ;
    public static final int COMPUTER = 0 ;
    public static final int RANDOM = 0 ;
    public static final int SMART = 1 ;
    
    private static int firstMove = 0  ;
    
    private int gameMode ;

    private Sticks sticks ;
    private int winner ;
    private int turn = 0 ;
  
    //Constructors
    
    //default constructor
    public NimGame()
        {
            gameMode = SMART ;
            sticks = new Sticks(15) ;
            winner = 0 ;
            
          }//default constructor
    
    //game mode constructor
    public NimGame( int gameMode )
        {
            this.gameMode = gameMode ;
            sticks = new Sticks(15) ;
            winner = 0 ;
           
        }//gamemode constructor
        
    //game mode and player count constructor
    public NimGame( int gameMode , int firstMove )
        {
            this.gameMode = gameMode ;
            NimGame.firstMove = firstMove ;
           sticks = new Sticks(15) ;
            winner = 0 ;
            
       }
    
    //Class Methods
    
    public static void setFirstMove(int firstMove)
    {
        NimGame.firstMove = firstMove ;
    }
    
    public static int getFirstMove()
    {
        return firstMove ; 
    }
    
    public void setMode(int gameMode)
    {
        this.gameMode = gameMode ;
    }
    
    public int getMode()
    {
        return gameMode ;
    }    
    
    public int getTurn()
    {
        return turn ;
    }
    
    public void setTurn(int turn)
    {
        this.turn = turn ;
    }
    
    //method to get the winner of the game object
    public int getWinner() 
        {
            return winner ;
        }//get winner
          
    
    //method for the player's move
    public void humanMove(  )
        {
            Scanner scanner = new Scanner(System.in) ;
            int sticksRemoved = 0 ;
            
            System.out.println("Your turn player " + turn) ;
            System.out.println("Input a number 1-3") ;
            sticksRemoved = scanner.nextInt() ;
            
            //makes sure a valid number of sticks is removed
            while ( sticksRemoved > Math.min( 3 , sticks.getSticks() ) || sticksRemoved < 1 )
                {
                    System.out.println("Invalid Move. Enter a number 1-3");
                    sticksRemoved = scanner.nextInt() ;
                }//while
            
            sticks.removeSticks( sticksRemoved );        
        }//human move
    
    //method for the computer's move
    public void computerMove()
        {
            int sticksRemoved ;
            
            //generates a random number of sticks for the computer to remove.
            sticksRemoved = (int)( Math.random() * Math.min( 3 ,  sticks.getSticks() ) + 1 ) ;
            
            System.out.println( "Computer removes: " + sticksRemoved + " stick(s)" );
            sticks.removeSticks( sticksRemoved );
        }//computer move
    
    //method for an "intelligent" computer for the harder setting.
    public void computerMoveWin()
        {
            int sticksRemoved ;
            sticksRemoved = (sticks.getSticks() - 1) % 4 ;

            if ( sticksRemoved == 0 )
                {
                    computerMove() ;
                }//if
            else
                {
                    System.out.println( "Computer removes: " + sticksRemoved + " stick(s)" );
                    sticks.removeSticks( sticksRemoved ); 
                }//if          
        }//computer move win
    

    //method for playing the game.
    public void playGame( )
        {

            turn = firstMove ;
            firstMove = (firstMove + 1) % 2 ;
            
           
            //starting the turn at the value of first move.
            
            //printing the initial display
            System.out.println( sticks.getSticks() + " Sticks Remain" ) ;
            System.out.println( sticks) ;
                
            while ( sticks.getSticks()  > 0 )
                {
                    if ( turn == HUMAN )
                        {
                             humanMove() ;
                        }//if
                    else if( gameMode == RANDOM )
                        {
                                computerMove() ;                                    
                                                                      
                        }//if
                    else
                        {
                                computerMoveWin() ; 
                        }//else
                    
                    turn = (turn + 1) % 2 ;
   
                    System.out.println( sticks.getSticks()  + " Sticks Remain" ) ;
                    //loop to print the graphic.

                    System.out.println(sticks) ;    
                }//while loop

                if( turn == COMPUTER )
                {
                    System.out.println("Computer Wins!") ;
                    winner = COMPUTER ;
                }//if
            else
                {
                    System.out.println("Player " + turn + " Wins!") ;
                    winner = turn ;
                }//else
         }//play game
         
         private static class Sticks
         {
             private int sticks ;
             
             public Sticks()
             {
                 sticks = 15 ;
              }
             public Sticks(int sticks)
                {
                    this.sticks = sticks ;
                }
             
             public int getSticks()
             {
                 return sticks ;
                }
             
             public void removeSticks(int sticks)
             {
                 this.sticks -= sticks ;
                }
             
                @Override
                public String toString()
                {
                    String string = "" ;
                    int i ;
                    
                    
                        for ( i = 0 ; i < sticks ; i++ )
                        {
                            string += "| " ;
                        }//for loop
                        
                   return string ;
                }
            }

}//nim game
