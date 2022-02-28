import java.awt.*;
import javax.swing.*;


/*
 *  A GUI component
 *
 *  
 *
 *  @author Apurva Acharya
 */
public class KingdomLayout extends JPanel
{
    private int xcoord, ycoord;  // location in the grid
    // constructor takes the x and y coordinates of this square
    public KingdomLayout(int xcoord, int ycoord)
    {
    super();
    this.setSize(50,50);
    this.xcoord = xcoord;
    this.ycoord = ycoord;
    }
    
    //changes the color of the panel to white
    public void setColor()
    {
        this.setBackground(Color.white);
    }
    

    // simple setters and getters for the x and y coordinates
    public void setXcoord(int value)    { xcoord = value; }
    public void setYcoord(int value)    { ycoord = value; }
    public int getXcoord()              { return xcoord; }
    public int getYcoord()              { return ycoord; }
}