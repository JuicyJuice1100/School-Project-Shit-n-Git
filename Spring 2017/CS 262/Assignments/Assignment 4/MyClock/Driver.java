import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;

public class Driver
{
    public static void main(String[] args)
    {
        JFrame setApp = new JFrame("Clock Demo") ;
        ClockDemo clockDemo = new ClockDemo() ;
        
        setApp.add(clockDemo, BorderLayout.CENTER) ;
        
        setApp.setSize(1200, 600) ;
        setApp.setVisible(true);
        
        setApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        
    }
}