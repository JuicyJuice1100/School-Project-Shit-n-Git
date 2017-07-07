
/**
 * Write a description of class GetTime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.* ;

public class GetTime extends Thread
{
    private static final int MILISECONDS_TO_SECONDS = 1000 ;
    private static final int HOURS_TO_SECONDS = 3600 ;
    
    private Date currentDate ;
    private long totalSeconds ;
      
    public GetTime()
    {
        currentDate = new Date() ;
    }
    
    
    @Override
    public void run()
    {
        
        while(true)
        {
            currentDate = new Date() ;
           // long totalSeconds ;
            
            totalSeconds = currentDate.getTime() / MILISECONDS_TO_SECONDS + 6 * HOURS_TO_SECONDS ;
            
            WorldTime.setTotalSeconds( totalSeconds ) ;
        
            try {
        
                  Thread.sleep(MILISECONDS_TO_SECONDS);
        
              } catch (InterruptedException ex) {}

        }
        
    } // end run

}
