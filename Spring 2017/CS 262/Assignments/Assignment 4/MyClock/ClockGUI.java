
/**
 * Write a description of class ClockGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;


public class ClockGUI extends JPanel implements Runnable
{
   private static final int MILISECONDS_TO_SECONDS = 1000 ;
    
   private WorldTime worldTime ;
   private JTextField txtTime ;
   private JPanel panelTimeZone ;
   private JButton btnSetTimeZone ;
   private JTextField txtSetTimeZone ;
   private AnalogClock analogPanel ; 
   
   public ClockGUI()
   {
       super() ;
       setLayout(new BorderLayout()) ;
       
       worldTime = new WorldTime() ;
       
       txtTime = new JTextField(20) ;
       txtTime.setEditable(false) ;
       add(txtTime, BorderLayout.NORTH) ;
       
       panelTimeZone = new JPanel() ;
       btnSetTimeZone = new JButton("Set Time Zone") ;
       txtSetTimeZone = new JTextField(6) ;
       panelTimeZone.add(btnSetTimeZone) ;
       panelTimeZone.add(txtSetTimeZone) ;
       add(panelTimeZone, BorderLayout.SOUTH) ;
       
       analogPanel = new AnalogClock(worldTime) ;
       analogPanel.setSize(50, 50) ;
       add(analogPanel, BorderLayout.CENTER) ;
       
       
       
       EventHandler handler = new EventHandler() ;
       btnSetTimeZone.addActionListener(handler) ;
    }
    
    
    
    
    @Override
    public void run()
    {
        while(true)
        {
            txtTime.setText(worldTime.toString()) ;
            
            repaint() ;
        
            try {
        
                  Thread.sleep(MILISECONDS_TO_SECONDS);
        
              } catch (InterruptedException ex) {}
        
        }
 
    }
    
    private class EventHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int timeZone = 0;;

            boolean valid = true ;
            
            if(e.getSource() == btnSetTimeZone)
            {
                try
                {
                    timeZone = Integer.parseInt(txtSetTimeZone.getText());
                }
                catch (Exception ex)
                {
                    valid = false ;
                }
                
                if(valid)
                {
                    worldTime.setTimeZone(timeZone)  ;
                }
            }
          
        }
    } // end EventHandler

    
}
