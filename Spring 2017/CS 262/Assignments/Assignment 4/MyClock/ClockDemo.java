
/**
 * Write a description of class ClockDemo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;

public class ClockDemo extends JPanel
{
    private ClockGUI clockGUI1 ;
    private ClockGUI clockGUI2 ;
    private ClockGUI clockGUI3 ;
    private GetTime getTime ;
    private Thread clock1 ;
    private Thread clock2 ;
    private Thread clock3 ;
    
    public ClockDemo()
    {
        super() ;
        setLayout(new BorderLayout()) ;
        setLayout(new GridLayout(0, 3)) ;
        //setLayout(new FlowLayout());
        getTime = new GetTime() ;
        getTime.start() ;
        
        clockGUI1 = new ClockGUI() ;
        //add(clockGUI1, BorderLayout.CENTER) ;
        add(clockGUI1) ;
        clock1 = new Thread(clockGUI1) ;
        clock1.start() ;
        
        clockGUI2 = new ClockGUI() ;
        //add(clockGUI2, BorderLayout.EAST) ;
        add(clockGUI2) ;
        clock2 = new Thread(clockGUI2) ;
        clock2.start() ;
        
        
        clockGUI3 = new ClockGUI() ;
        //add(clockGUI3, BorderLayout.WEST) ;
        add(clockGUI3) ;
        clock3 = new Thread(clockGUI3) ;
        clock3.start() ;
        
        
        
    }
}
