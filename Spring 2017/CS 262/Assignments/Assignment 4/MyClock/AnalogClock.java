
/**
 * Write a description of class AnalogClock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;

public class AnalogClock extends JPanel 
{
   private WorldTime worldTime ;
    
   public AnalogClock(WorldTime worldTime)
   {
       this.worldTime = worldTime ;
    }
   
   
        @Override
     public void paintComponent(Graphics g) 
     {
        double minsecdeg = (double)Math.PI / 30;
        double hrdeg = (double)Math.PI / 6 ;
        int tx, ty ;
        int xpoints[] = new int[3];
        int ypoints[] = new int[3]; 
         
         
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.fillOval(5, 5,getWidth()-20, getWidth()-20);
        g.setColor(Color.BLACK);
        g.fillOval(10, 10, getWidth() - 30, getWidth()-30);
        g.setColor(Color.WHITE);
        g.fillOval((getWidth()-20)/2,(getWidth()-20)/2,15,15);
        g.setFont(g.getFont().deriveFont(Font.BOLD,32));

         //for(int i=1 ; i<=12 ; i++)
           //g.drawString(Integer.toString(i),((getWidth())/2)+(i/12)*11+(int)(((getWidth())/2)*Math.sin(i*Math.PI/6)),((getWidth())/2)-(int)(((getWidth())/2)*Math.cos(i*Math.PI/6)));


 
       //second hand
        tx = getWidth()/2 + (int)((getWidth()-30)/2 * Math.sin(worldTime.getSeconds() * minsecdeg));
        ty = getWidth()/2 - (int)((getWidth()-30)/2 * Math.cos(worldTime.getSeconds() * minsecdeg));
        g.drawLine(getWidth()/2, getWidth()/2 , tx, ty);

        //minute hand
        tx = getWidth()/2 + (int)((getWidth()-30)/2 * Math.sin(worldTime.getMinutes() * minsecdeg)) + 20;
        ty = getWidth()/2 - (int)((getWidth()-30)/2 * Math.cos(worldTime.getMinutes() * minsecdeg)) - 20;
        xpoints[0] = getWidth()/2;
        xpoints[1] = tx + 2 ;
        xpoints[2] = tx - 2 ;
        ypoints[0] = getWidth()/2;
        ypoints[1] = ty + 2 ;
        ypoints[2] = ty - 2 ;
        g.fillPolygon(xpoints, ypoints, 3) ;

        //hour hand
        tx = getWidth()/2 + (int)((getWidth()-30)/2 * Math.sin(worldTime.getHours() % 12 * hrdeg + worldTime.getMinutes() * Math.PI / 360)) + 50 ;
        ty = getWidth()/2 - (int)((getWidth()-30)/2 * Math.cos(worldTime.getHours() % 12 * hrdeg + worldTime.getMinutes() * Math.PI / 360)) - 50;
        xpoints[1] = tx + 8 ;
        xpoints[2] = tx - 8 ;
        ypoints[1] = ty - 8 ;
        ypoints[2] = ty + 8 ;
        g.fillPolygon(xpoints, ypoints, 3);

    }  // emd paintComponent()


    
}
