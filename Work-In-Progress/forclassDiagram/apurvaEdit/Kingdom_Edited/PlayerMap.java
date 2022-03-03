import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
/**
 * Write a description of class PlayerMap here.
 *
 * @author Apurva Acharya
 * @version
 */
public class PlayerMap extends JPanel
{
    private JLabel playerLabel;
    private int rows, columns;
    private KingdomLayout [][] gridSquares;    // squares to appear in the kingdom panel

    public PlayerMap()
    {
        this.rows = 5;
        this.columns = 5;
        this.setSize(600,600);
        
        this.setBackground(Color.yellow);
        this.setBorder(BorderFactory. createLineBorder(Color. black));
        this.setLayout(new BorderLayout());
        
        this.setBorder(BorderFactory. createLineBorder(Color. black));
        JLabel playerLabel= new JLabel("Player _'s Kingdom"); //the label at the top of each playermap
        playerLabel.setBackground(Color.yellow);
        playerLabel.setForeground(Color.black);
        this.add(playerLabel,BorderLayout.NORTH);
        
        JPanel playerMap = new JPanel();
        playerMap.setBorder(BorderFactory. createLineBorder(Color. black));
        playerMap.setLayout(new GridLayout(rows, columns));
        playerMap.setSize(500,500);
        this.add(playerMap,BorderLayout.CENTER);
        
        //making the grid
        gridSquares = new KingdomLayout[rows][columns];
        Border blackline= BorderFactory.createLineBorder(Color.black,3);
        for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gridSquares[x][y] = new KingdomLayout(x, y);
                gridSquares[x][y].setSize(20, 20);
                gridSquares[x][y].setBorder(blackline); //setting border
                gridSquares[x][y].setColor();
                //gridSquares[x][y].addMouseListener(this); use this to use the mouse later        
                playerMap.add(gridSquares[x][y]);
            }
        }
        
        
        
    }

}