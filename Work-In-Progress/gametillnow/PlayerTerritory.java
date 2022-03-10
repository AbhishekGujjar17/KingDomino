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

/**
 * Write a description of class PlayerMap here.
 *
 * 
 */
public class PlayerTerritory extends JPanel implements ActionListener, MouseListener
{
    private JPanel playerMap;
    private JLabel playerLabel,picLabel;
    private int rows, columns;
    private boolean flag;
    private String playerName;
    private KingdomLayout [][] gridSquares;    // squares to appear in the kingdom panel
    
    private ImageIcon saveIcon;
    private JLabel bgLabel;
    private Color startingTileColour;
    


    public PlayerTerritory(String playerName, Color playerColour, boolean computerPlayers)
    {
        this.rows = 5;
        this.columns = 5;
        this.setSize(600,600);
        this.playerName = playerName;
        flag = true;
        
        
        
        
        
        //saveIcon = new ImageIcon(getClass().getResource("images/save.png"));
        
        
        this.setBackground(Color.yellow);
        this.setBorder(BorderFactory. createLineBorder(Color. black));
        this.setLayout(new BorderLayout());
        
        this.setBorder(BorderFactory. createLineBorder(Color. black));
        JLabel playerLabel= new JLabel(playerName + " Kingdom"); //the label at the top of each playermap
        playerLabel.setBackground(Color.yellow);
        playerLabel.setForeground(playerColour);
        this.add(playerLabel,BorderLayout.NORTH);
        
        JPanel playerMap = new JPanel();
        playerMap.setBorder(BorderFactory. createLineBorder(Color. black));
        playerMap.setLayout(new GridLayout(rows, columns));
        playerMap.setSize(500,500);
        this.add(playerMap,BorderLayout.CENTER);
        
        //Color startingTileColour = new Color(255, 255, 153);
        
        //making the grid
        gridSquares = new KingdomLayout[rows][columns];
        Border blackline= BorderFactory.createLineBorder(Color.black,3);
        bgLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource("images/castle1s.png"));
        bgLabel.setIcon(bgIcon);
        
        if (computerPlayers == true){
            
            
            for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gridSquares[x][y] = new KingdomLayout(x, y);
                gridSquares[x][y].setSize(20, 20);
                gridSquares[x][y].setBorder(blackline); //setting border
                gridSquares[x][y].setColor();
                //gridSquares[x][y].setImage();
                //gridSquares[x][y].addMouseListener(this); 
                playerMap.add(gridSquares[x][y]);
            }
        }
            //gridSquares[2][2].setBackground(Color.cyan);
            gridSquares[2][2].add(bgLabel);
            
            
            
        }
        
        else if (computerPlayers == false){
        for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gridSquares[x][y] = new KingdomLayout(x, y);
                gridSquares[x][y].setSize(20, 20);
                gridSquares[x][y].setBorder(blackline); //setting border
                gridSquares[x][y].setColor();
                //gridSquares[x][y].setImage();
                gridSquares[x][y].addMouseListener(this); 
                playerMap.add(gridSquares[x][y]);
            }
        }
    }
    
    
    
    }
    
    
    
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        
        //Object selected = mevt.getSource();
        
        // if a gridsquare is selected then switch its color
       
        
        
        /**
        if (selected.equals(newGame)){
            
            this.dispose();
            NumberOfPlayers playerSelectionDisplay = new NumberOfPlayers();
            
            
        }
        
         if (selected.equals(saveGame)){
            
            this.dispose();
            SavedGame savedGameDisplay = new SavedGame();
            
            
        }
        
        if (selected.equals(quit)){
            
            System.exit(0);
            
            
        }**/
        
        
    }
    
    public void mouseClicked(MouseEvent mevt)
    
    
    
    {
        
          //try{  
             
             Object selected = mevt.getSource();
             if (selected instanceof KingdomLayout && flag == true)
             {   
            
               KingdomLayout square = (KingdomLayout) selected;
               //square.add(bgLabel);
                //playerMap.add(square);
               /**BufferedImage myPicture = ImageIO.read(new File("images/save.png"));
               picLabel = new JLabel(new ImageIcon(myPicture));**/
               //square.add(picLabel);
               //playerMap.add(square);
               
               /**try{
               BufferedImage myPicture = ImageIO.read(new File("images/save.png"));
               picLabel = new JLabel(new ImageIcon(myPicture));
               }
               catch(IOException ex){System.out.println("hello");}**/
               square.setBackground(new Color(255, 255, 157));
               //square.setText("C");
               flag = false;
               
               JOptionPane.showMessageDialog(null,playerName + " you have successfully selected your starting tile","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
             }
            //}
          /**catch(IOException ex){System.out.println("hello");}**/
            //KingdomLayout square = (KingdomLayout) selected;
            
            //square.setBackground(Color.red);
            //square.add(picLabel);
            //playerMap.add(square);
            
    }        
        
    
    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}

}