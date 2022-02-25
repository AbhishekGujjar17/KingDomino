import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Random;
/*
 *  The main GameWindow of the gui.
 *  @author Apurva Acharya
 */
public class GameWindow extends JFrame 
{
    // gui components that are contained in this frame:
    private Color lightBlue; 
    
    private JMenuBar menu; //the menu
    private JMenu file, help, save; //menus
    private JMenuItem load, quit; //menu items for file menu
    
    private JPanel topPanel, bottomPanel;    // top and bottom panels in the main GameWindow
    private JPanel dominoTiles, playerTurn, scores; //panels in the top of the window
    private JPanel kingdom, currentTiles;//panels in the bottom of the window
    private JPanel kingdomMap;//panel that has the kingdom
    private JPanel player1_curr,player2_curr,player3_curr,player4_curr; 
    private JLabel kingdomLabel,currentTileLabel; //labels in the top of kingdom and currentTiles
    
    
    
    private JLabel instructionLabel;        // textlabel at the very bottom of the window telling suer what to do next
    
    

    private GridSquare [][] gridSquares;    // squares to appear in the kingdom panel
    private int rows,columns;  // the size of the grid in kingdom
    
    private JLabel l1,l2,l3,l4,l5; //Just for displaying right now

    private int playerNum; // variable stores which player's turn it is
    /*
     *  constructor method takes as input how many rows and columns of gridsquares to create
     *  it then creates the panels, their subcomponents and puts them all together in the main frame
     *  it makes sure that action listeners are added to selectable items
     *  it makes sure that the gui will be visible
     */
    public GameWindow()
        {
        

        this.rows = 5;
        this.columns = 5;
        this.setSize(600,600);
        
        //first creating the menu
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu save = new JMenu("Save");
        menu.add(file);
        menu.add(help);
        menu.add(save);
        JMenuItem load= new JMenuItem("Load");
        JMenuItem quit= new JMenuItem("Quit");
        file.add(load);
        file.add(quit);
        
        
        Color lightBlue= new Color(0,0,182,155);
        // create the top panel
        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory. createLineBorder(Color. black));
        topPanel.setBackground(lightBlue);
        topPanel.setLayout(new FlowLayout());
        //panels for the top panel
        JPanel dominoTiles= new JPanel();
        dominoTiles.setBorder(BorderFactory. createLineBorder(Color. black));//set Panel Border later
        dominoTiles.setBackground(Color.yellow);
        JPanel playerTurn= new JPanel();
        playerTurn.setBorder(BorderFactory. createLineBorder(Color. black));
        playerTurn.setBackground(Color.yellow);
        JPanel scores= new JPanel();
        scores.setBorder(BorderFactory. createLineBorder(Color. black));
        scores.setBackground(Color.yellow);
        topPanel.add(dominoTiles);
        topPanel.add(playerTurn);
        topPanel.add(scores);
        //remove these labels later
        JLabel l1= new JLabel("Domino Tiles Stack");
        JLabel l2= new JLabel("Player's Turn");
        JLabel l3= new JLabel("Scores");
        dominoTiles.add(l1);
        playerTurn.add(l2);
        scores.add(l3);
        
        
        //working on the bottom panel now
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory. createLineBorder(Color. black));
        bottomPanel.setBackground(lightBlue);
        bottomPanel.setLayout(new BorderLayout());
        //the kingdom panel
        JPanel kingdom = new JPanel();
        kingdom.setBackground(lightBlue);
        kingdom.setBorder(BorderFactory. createLineBorder(Color. black));
        kingdom.setLayout(new BorderLayout());
        JLabel kingdomLabel= new JLabel("Player _'s Kingdom");
        kingdomLabel.setForeground(Color.white);
        kingdom.add(kingdomLabel,BorderLayout.NORTH);
        JPanel kingdomMap = new JPanel();
        kingdomMap.setBorder(BorderFactory. createLineBorder(Color. black));
        kingdomMap.setLayout(new GridLayout(rows, columns));
        kingdomMap.setSize(500,500);
        kingdom.add(kingdomMap,BorderLayout.CENTER);
        //the current tile panel
        JLabel currentTileLabel= new JLabel("Current Round Tiles");
        currentTileLabel.setForeground(Color.white);
        JPanel currentTiles= new JPanel();
        currentTiles.setBorder(BorderFactory. createLineBorder(Color. black));
        currentTiles.setBackground(lightBlue);
        currentTiles.setLayout(new GridLayout(5,1));
        JPanel player1_curr= new JPanel();
        player1_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player1_curr.setBackground(Color.yellow);
        JPanel player2_curr= new JPanel();
        player2_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player2_curr.setBackground(Color.yellow);
        JPanel player3_curr= new JPanel();
        player3_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player3_curr.setBackground(Color.yellow);
        JPanel player4_curr= new JPanel();
        player4_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player4_curr.setBackground(Color.yellow);
        currentTiles.add(currentTileLabel);
        currentTiles.add(player1_curr);
        currentTiles.add(player2_curr);
        currentTiles.add(player3_curr);
        currentTiles.add(player4_curr);
        //adding to the bottom panel
        bottomPanel.add(kingdom,BorderLayout.CENTER);
        bottomPanel.add(currentTiles,BorderLayout.EAST);
        //bottom of the screen
        JLabel instructionLabel= new JLabel("Instructions what to do next");
 
        // create the squares and add them to the grid
        gridSquares = new GridSquare[rows][columns];
        Border blackline= BorderFactory.createLineBorder(Color.black,3);
        for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gridSquares[x][y] = new GridSquare(x, y);
                gridSquares[x][y].setSize(20, 20);
                gridSquares[x][y].setBorder(blackline); //setting border
                gridSquares[x][y].setColor();
                //gridSquares[x][y].addMouseListener(this); use this to use the mouse later        
                kingdomMap.add(gridSquares[x][y]);
            }
        }
        
        // add the top and bottom panels to the main frame
        //I could not add the menu
        //getJMenuBar();
        //getJMenuBar().add(menu);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.CENTER); 
        getContentPane().add(instructionLabel, BorderLayout.SOUTH); 
        
        // housekeeping : behaviour
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    //WE IMPLEMENT AS PROF DID IN THE SAMPLE CODE TO MAKE THE LAYOUT FUNCTIONAl
    
   
}
