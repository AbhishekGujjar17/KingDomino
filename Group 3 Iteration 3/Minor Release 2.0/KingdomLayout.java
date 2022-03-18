import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.UIManager;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;



/*
 *  A GUI component
 *
 *  
 *
 *  
 */
public class KingdomLayout extends JPanel
{
    private int xcoord, ycoord;
    
    private JLabel picLabel;// location in the grid
    // constructor takes the x and y coordinates of this square
    public KingdomLayout(int xcoord, int ycoord)
    {
    super();
    this.setSize(25,25);
    this.xcoord = xcoord;
    this.ycoord = ycoord;
    
    try{
        BufferedImage myPicture = ImageIO.read(new File("images/save.png"));
        picLabel = new JLabel(new ImageIcon(myPicture));
        }
        catch(IOException ex){};
    }
    
    //changes the color of the panel to white
    public void setColor()
    {
        this.setBackground(Color.white);
        
    }
    
    
    
    public void setImage(){
        
        this.add(picLabel);
    }
    

    // simple setters and getters for the x and y coordinates
    public void setXcoord(int value)    { xcoord = value; }
    public void setYcoord(int value)    { ycoord = value; }
    public int getXcoord()              { return xcoord; }
    public int getYcoord()              { return ycoord; }
}