import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import java.util.*;
/*
 *  The main GameWindow of the gui.
 *  
 */
public class Kingdom extends JFrame implements ActionListener
{
    // gui components that are contained in this frame:
    private Color lightBlue; 
    
    private JMenuBar menu; //the menu
    private JMenu options; //menus
    private JMenuItem newGame,saveGame , loadGame, help, quit; //menu items for file menu
    
    private JPanel topPanel, bottomPanel;    // top and bottom panels in the main GameWindow
    private JPanel dominoTiles, playerTurn, scores; //panels in the top of the window
    private JPanel kingdom, currentTiles;//panels in the bottom of the window
    private JPanel kingdomMap;//panel that has the kingdom
    private JPanel player1_curr,player2_curr,player3_curr,player4_curr; 
    private JLabel kingdomLabel,currentTileLabel; //labels in the top of kingdom and currentTiles
    
    private JLabel instructionLabel;        // textlabel at the very bottom of the window telling suer what to do next
    
    
    private JPanel maps;
    private PlayerTerritory map1, map2, map3, map4;
    
    

    private KingdomLayout [][] gridSquares;    // squares to appear in the kingdom panel
    private int rows,columns;  // the size of the grid in kingdom
    
    private JLabel l1,l2,l3,l4,l5; //Just for displaying right now

    private int playerNum; // variable stores which player's turn it is
    
    private String playerOne, playerTwo, playerThree, playerFour;
    /*
     *  constructor method takes as input how many rows and columns of gridsquares to create
     *  it then creates the panels, their subcomponents and puts them all together in the main frame
     *  it makes sure that action listeners are added to selectable items
     *  it makes sure that the gui will be visible
     */
    public Kingdom(int playerMode,int humanPlayers, boolean computerPlayers, String playerOne, String playerTwo, String playerThree, String playerFour)
        {
        
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerThree = playerThree;
        this.playerFour = playerFour;
        
        
        this.rows = 5;
        this.columns = 5;
        this.setSize(1000,600);
        
        //first creating the menu
        menu = new JMenuBar();
        
        options = new JMenu("Options");
        
        
        newGame = new JMenuItem("New Game");
        saveGame = new JMenuItem("Save Game");
        loadGame = new JMenuItem("Load Game");
        help = new JMenuItem("Help");
        quit = new JMenuItem("Quit");
        
        
        
        options.add(newGame);
        options.add(saveGame);
        options.add(loadGame);
        options.add(help);
        options.add(quit);
        menu.add(options);
        
        
        newGame.addActionListener(this);
        saveGame.addActionListener(this);
        loadGame.addActionListener(this);
        help.addActionListener(this);
        quit.addActionListener(this);
        
        
        //Color lightBlue= new Color(0,0,182,155);
        // create the top panel
        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory. createLineBorder(Color. black));
        topPanel.setBackground(Color.green);
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
        
        //bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory. createLineBorder(Color. black));
        bottomPanel.setBackground(Color.green);
        bottomPanel.setLayout(new BorderLayout());
       
        //the current tile panel
        JLabel currentTileLabel= new JLabel("Current Round Tiles");
        currentTileLabel.setForeground(Color.black);
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
        
        //future
        
        JLabel futureTileLabel= new JLabel("Future Round Tiles");
        futureTileLabel.setForeground(Color.black);
        JPanel futureTiles= new JPanel();
        futureTiles.setBorder(BorderFactory. createLineBorder(Color. black));
        futureTiles.setBackground(lightBlue);
        futureTiles.setLayout(new GridLayout(5,1));
        JPanel player1_fut= new JPanel();
        player1_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        player1_fut.setBackground(Color.yellow);
        player1_fut.setBounds(0,0,1000,1000);
        JPanel player2_fut= new JPanel();
        player2_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        player2_fut.setBackground(Color.yellow);
        JPanel player3_fut= new JPanel();
        player3_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        player3_fut.setBackground(Color.yellow);
        JPanel player4_fut= new JPanel();
        player4_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        player4_fut.setBackground(Color.yellow);
        futureTiles.add(futureTileLabel);
        futureTiles.add(player1_fut);
        futureTiles.add(player2_fut);
        futureTiles.add(player3_fut);
        futureTiles.add(player4_fut);
        
        //JOptionPane.showMessageDialog(null,"Please Select a grid where you want to place your starting tile" ,JOptionPane.PLAIN_MESSAGE);
        //JOptionPane.showMessageDialog(null,"CLICK ON ACTION'S TO BEGIN ","WELCOME TO GAME ",JOptionPane.PLAIN_MESSAGE);
        //maps for the players
        maps= new JPanel();
        if (playerMode==2 && computerPlayers == true){
            maps.setLayout(new GridLayout(1,2));
            map1= new PlayerTerritory(playerOne, Color.red,false );
            map2= new PlayerTerritory(playerTwo, Color.blue, true);
            maps.add(map1);
            maps.add(map2);
        }
        
        if (playerMode==2 && computerPlayers == false){
            maps.setLayout(new GridLayout(1,2));
            map1= new PlayerTerritory(playerOne, Color.red, false);
            map2= new PlayerTerritory(playerTwo, Color.blue, false);
            maps.add(map1);
            maps.add(map2);
        }
        else if (playerMode==4 && computerPlayers == true && humanPlayers == 1){
            maps.setLayout(new GridLayout(2,2));
            map1= new PlayerTerritory(playerOne, Color.red, false);
            map2= new PlayerTerritory(playerTwo, Color.blue, true);
            map3= new PlayerTerritory(playerThree, Color.darkGray, true);
            map4= new PlayerTerritory(playerFour, Color.magenta, true);
            maps.add(map1);
            maps.add(map2);
            maps.add(map3);
            maps.add(map4);
        }
        
        else if (playerMode==4 && computerPlayers == true && humanPlayers == 2){
            maps.setLayout(new GridLayout(2,2));
            map1= new PlayerTerritory(playerOne, Color.red, false);
            map2= new PlayerTerritory(playerTwo, Color.blue, false);
            map3= new PlayerTerritory(playerThree, Color.darkGray, true);
            map4= new PlayerTerritory(playerFour, Color.magenta, true);
            maps.add(map1);
            maps.add(map2);
            maps.add(map3);
            maps.add(map4);
        }
        
        
        else if (playerMode==4 && computerPlayers == true && humanPlayers == 3){
            maps.setLayout(new GridLayout(2,2));
            map1= new PlayerTerritory(playerOne, Color.red, false);
            map2= new PlayerTerritory(playerTwo, Color.blue, false);
            map3= new PlayerTerritory(playerThree, Color.darkGray, false);
            map4= new PlayerTerritory(playerFour, Color.magenta, true);
            maps.add(map1);
            maps.add(map2);
            maps.add(map3);
            maps.add(map4);
        }
        
        else if (playerMode==4 && computerPlayers == false){
            maps.setLayout(new GridLayout(2,2));
            map1= new PlayerTerritory(playerOne, Color.red, false);
            map2= new PlayerTerritory(playerTwo, Color.blue, false);
            map3= new PlayerTerritory(playerThree, Color.darkGray, false);
            map4= new PlayerTerritory(playerFour, Color.magenta, false);
            maps.add(map1);
            maps.add(map2);
            maps.add(map3);
            maps.add(map4);
        }
        //adding to the bottom panel
        bottomPanel.add(maps,BorderLayout.CENTER);
        bottomPanel.add(currentTiles,BorderLayout.WEST);
        bottomPanel.add(futureTiles,BorderLayout.EAST);
        //bottom of the screen
        JLabel instructionLabel= new JLabel("Instructions what to do next");
 
        /*
        // create the squares and add them to the grid
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
                kingdomMap.add(gridSquares[x][y]);
            }
        }
        */
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.CENTER); 
        getContentPane().add(instructionLabel, BorderLayout.SOUTH);
        this.setJMenuBar(menu);
        
        // housekeeping : behaviour
        
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        JOptionPane.showMessageDialog(this,"Each Player must select a grid where they want to place their starting tile","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null,"Starting tile for a Robot Player is already selected","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
    }
    
    
    
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        if (selected.equals(help))
        {
            
            
            try {
           File pdf = new File("rules.pdf");
           if (pdf.exists()) {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdf);
            } else {
                System.out.println("Desktop is not supported");
            }
        } else {
            System.out.println("File does not exists");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
        }
    
        if (selected.equals(newGame)){
            
            this.dispose();
            NewGame startNewGame = new NewGame();
            
            
        }
        
        if (selected.equals(loadGame)){
            this.dispose();
            SavedGame startSaveGame = new SavedGame();
            
            
            
        }
        
        if (selected.equals(saveGame)){
            
            
            //this.dispose();
            
            
        }
        
        if (selected.equals(quit)){
            
            System.exit(0);
            
            
        }
        
        
    }
    
    
   
}