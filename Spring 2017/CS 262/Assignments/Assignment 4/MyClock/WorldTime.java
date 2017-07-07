
/**
 * DESCRIPTION: This class is used to manage clocks across different time zones.
 * CLASS: 
 * SECTION: 1
 * NAME: 
 * DATE: 
 * VERSION:
 */

import java.util.* ;

public class WorldTime
{
    //Constants
    private static final int HOURS_TO_SECONDS = 3600 ;
    private static final int MINUTES_TO_SECONDS = 60 ;
    private static final int HOURS_TO_MINUTES = 60 ;
    private static final int DAYS_TO_HOURS = 24 ;
    private static final int MILISECONDS_TO_SECONDS = 1000 ;
    
    //Data Members
    private static long totalSeconds ;
    
    private int timeZone ;
    
    //Constructors
    public WorldTime()  //Instantiates a clock object
    {
        timeZone = 0 ;
    }
    
    public WorldTime(int timeZone)  //Instantiates a clock object with the parameter timeZone
    {
        this.timeZone = timeZone ;
    }
    
    //Methods
    public synchronized void setTimeZone(int timeZone)  //Mutator method that sets the timeZone of a Clock object
    {
        this.timeZone = timeZone ;
    }
    
    
    public synchronized int getTimeZone()
    {
        return timeZone ;
    }
    
    public synchronized long getLocalTime()  //Accessor method that returns the total seconds of a Clock object
    {
        long totalLocalSeconds ;
        
        totalLocalSeconds = totalSeconds + timeZone * HOURS_TO_SECONDS ;
        
        return totalLocalSeconds ;
    }
    
    public synchronized int getHours()
    {
        long totalLocalSeconds ;
        int hours ;
        
        totalLocalSeconds = totalSeconds + timeZone * HOURS_TO_SECONDS ;
        
        hours = (int) (totalLocalSeconds / HOURS_TO_SECONDS + 19) % DAYS_TO_HOURS ;
        
        return hours ;

    }
    
    public synchronized int getMinutes()
    {
        long totalLocalSeconds ;
        int minutes ;
        
        totalLocalSeconds = totalSeconds + timeZone * HOURS_TO_SECONDS ;
        
        minutes = (int) (totalLocalSeconds % HOURS_TO_SECONDS) / HOURS_TO_MINUTES ;
        
        return minutes ;
    }
    
    public synchronized int getSeconds()
    {
        long totalLocalSeconds ;
        int seconds ;
        
        totalLocalSeconds = totalSeconds + timeZone * HOURS_TO_SECONDS ;
        
        seconds = (int) totalLocalSeconds % MINUTES_TO_SECONDS ;
        
        return seconds ;
    }
    
    @Override
    public synchronized String toString()  //Accessor method that returns time in a standard output
    {
         String str ;
         
         Date currentDate = new Date(this.getLocalTime() * MILISECONDS_TO_SECONDS) ;
        
        str = currentDate.toString() ;
        
        return str ;
    }
  
    public static synchronized void tick()  //Class method that moves the second of all Clock object by 1
    {
        Date currentDate = new Date() ;
        WorldTime.totalSeconds = currentDate.getTime() / MILISECONDS_TO_SECONDS + 6 * HOURS_TO_SECONDS ;
    }
    
    public static synchronized void setTotalSeconds(long totalSeconds)  //Class method that sets the total seconds of all Clock objects
    {
        WorldTime.totalSeconds = totalSeconds ;
               
    }
    
    public static synchronized long getTotalSeconds()
    {
        return WorldTime.totalSeconds ;
    }

}
