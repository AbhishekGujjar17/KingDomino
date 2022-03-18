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
import java.util.ArrayList;
/*
 *  The main GameWindow of the gui.
 *  
 */
public class KingdomColorBlind extends JFrame implements ActionListener, MouseListener
{
    // gui components that are contained in this frame:
    private Color lightBlue; 
    
    private JMenuBar menu; //the menu
    private JMenu options; //menus
    private JMenuItem newGame,saveGame , loadGame, help, quit; //menu items for file menu
    
    private JPanel topPanel, bottomPanel;    // top and bottom panels in the main GameWindow
    private JPanel playerTurn; // scores; //panels in the top of the window
    //private JPanel dominoTiles;
    private JPanel kingdom, currentTiles;//panels in the bottom of the window
    private JPanel kingdomMap;//panel that has the kingdom
    private JPanel player1_curr,player2_curr,player3_curr,player4_curr;
    private JPanel player1_fut,player2_fut,player3_fut,player4_fut;
    private JLabel kingdomLabel,currentTileLabel; //labels in the top of kingdom and currentTiles
    
    private JLabel instructionLabel;        // textlabel at the very bottom of the window telling suer what to do next
    
    private JPanel player1_curr1,player2_curr1,player3_curr1,player4_curr1;
    private JPanel player1_fut1,player2_fut1,player3_fut1,player4_fut1;
    private JPanel player1_curr2,player2_curr2,player3_curr2,player4_curr2;
    private JPanel player1_fut2,player2_fut2,player3_fut2,player4_fut2;
    //private JButton but1;
    
    
    
    private ArrayList<Integer> generatedNumbers  = new ArrayList<Integer>();
    
    
    private JPanel maps;
    private PlayerTerritory map1, map2, map3, map4;
    
    int min = 0;
    int max = 7;
    
    private Color  grass = new Color(102,255,102);
    private Color  sea = new Color(51,204,255);
    private Color forest = new Color(0,102,0);
    private Color sand = new Color(153,102,0);
    
    
    
    private int[] Crowns = {0,1,0,3,1};
    private Color[] colorarray = {grass,sea,forest,Color.yellow,sand, Color.black,grass,sea,forest,Color.yellow,sand, Color.black};
    private int[] tileNumber = {1,2,3,4,5};
    
    

    private KingdomLayout [][] gridSquares;    // squares to appear in the kingdom panel
    private int rows,columns;  // the size of the grid in kingdom
    
    private JLabel l1,l2,l3,l4,l5; //Just for displaying right now
    
    private int random_int1,random_int2,random_int3,random_int4,random_int5,random_int6,random_int7,random_int8;

    private int playerNum; // variable stores which player's turn it is
    
    private String playerOne, playerTwo, playerThree, playerFour;
    private int playerMode;
    private int playerTurnNumber;
    
    private JLabel mipple_1, mipple_2, mipple_3, mipple_4,mipple_1_2,mipple_2_2,mipple_3_2,mipple_4_2;
    
    
    private boolean mipple_Selected1;
    private boolean mipple_Selected2;
    private boolean mipple_Selected3;
    private boolean mipple_Selected4;
    private boolean visionDeficiency;
    static boolean selectingCurrentDominoTiles;
    
    /*
     *  constructor method takes as input how many rows and columns of gridsquares to create
     *  it then creates the panels, their subcomponents and puts them all together in the main frame
     *  it makes sure that action listeners are added to selectable items
     *  it makes sure that the gui will be visible
     */
    public KingdomColorBlind(int playerMode,int humanPlayers, boolean computerPlayers, String playerOne, String playerTwo, String playerThree, String playerFour)
        {
        
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerThree = playerThree;
        this.playerFour = playerFour;
        this.visionDeficiency = true;
        
        this.playerMode = playerMode;
        
        mipple_Selected1 = false;
        mipple_Selected2 = false;
        mipple_Selected3 = false;
        mipple_Selected4 = false;
        selectingCurrentDominoTiles = false;
        
        if (playerMode == 2){
            
            playerTurnNumber = (int)Math.floor(Math.random()*(2-1+1)+1);
        }
        
        if (playerMode == 4){
            
            playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
            
        }
        
        
        
        
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
        //topPanel.setBackground(Color.green);
        topPanel.setLayout(new FlowLayout());
        //panels for the top panel
        //JPanel dominoTiles= new JPanel();
        //dominoTiles.setBorder(BorderFactory. createLineBorder(Color. black));//set Panel Border later
        //dominoTiles.setBackground(Color.yellow);
        JPanel playerTurn= new JPanel();
        playerTurn.setBorder(BorderFactory. createLineBorder(Color. black));
        //playerTurn.setBackground(Color.yellow);
        //JPanel scores= new JPanel();
        //scores.setBorder(BorderFactory. createLineBorder(Color. black));
        //scores.setBackground(Color.yellow);
        //topPanel.add(dominoTiles);
        topPanel.add(playerTurn);
        //topPanel.add(scores);
        //remove these labels later
        //l1= new JLabel("Domino Tiles Stack");
        l2= new JLabel(playerTurnNumber + " Player Turn");
        //l3= new JLabel("Scores");
        //dominoTiles.add(l1);
        playerTurn.add(l2);
        //scores.add(l3);
        
        //trying to implement
        
        
        
        //bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory. createLineBorder(Color. black));
        //bottomPanel.setBackground(Color.green);
        bottomPanel.setLayout(new BorderLayout());
    
       
        //the current tile panel
        
        random_int1 = (int)Math.floor(Math.random()*(max-min+1)+min);
        
                
        while (generatedNumbers.contains(random_int1) ){
            
            random_int1 = (int)Math.floor(Math.random()*(max-min+1)+min);
        }
        generatedNumbers.add(random_int1);
        
        random_int2 = (int)Math.floor(Math.random()*(max-min+1)+min);
        while (generatedNumbers.contains(random_int2)){
            
            random_int2 = (int)Math.floor(Math.random()*(max-min+1)+min);
        }
        
        generatedNumbers.add(random_int2);
        
        random_int3 = (int)Math.floor(Math.random()*(max-min+1)+min);
        while (generatedNumbers.contains(random_int3) ){
            
            random_int3 = (int)Math.floor(Math.random()*(max-min+1)+min);
        }
        generatedNumbers.add(random_int3);
        
        random_int4 = (int)Math.floor(Math.random()*(max-min+1)+min);
        while (generatedNumbers.contains(random_int4)){
            
            random_int4 = (int)Math.floor(Math.random()*(max-min+1)+min);
        }
        generatedNumbers.add(random_int4);
        
        random_int5 = (int)Math.floor(Math.random()*(max-min+1)+min);
        while (generatedNumbers.contains(random_int5)){
            
            random_int5 = (int)Math.floor(Math.random()*(max-min+1)+min);
        }
        generatedNumbers.add(random_int5);
        
        random_int6= (int)Math.floor(Math.random()*(max-min+1)+min);
        while (generatedNumbers.contains(random_int6)){
            
            random_int6 = (int)Math.floor(Math.random()*(max-min+1)+min);
        }
        generatedNumbers.add(random_int6);
        
        random_int7= (int)Math.floor(Math.random()*(max-min+1)+min);
        while (generatedNumbers.contains(random_int7)){
            
            random_int7 = (int)Math.floor(Math.random()*(max-min+1)+min);
        }
        generatedNumbers.add(random_int7);
        
        random_int8= (int)Math.floor(Math.random()*(max-min+1)+min);
        while (generatedNumbers.contains(random_int8)){
            
            random_int8 = (int)Math.floor(Math.random()*(max-min+1)+min);
        }
        generatedNumbers.add(random_int8);
        

        
        
        JLabel currentTileLabel= new JLabel("Current Domino Tiles");
        currentTileLabel.setForeground(Color.black);
        JPanel currentTiles= new JPanel();
        currentTiles.setBorder(BorderFactory. createLineBorder(Color. black));
        //currentTiles.setBackground(lightBlue);
        currentTiles.setLayout(new GridLayout(5,1));
        /**
        but1 = new JButton();
        
        but1.setBorder(BorderFactory. createLineBorder(Color. black));
        but1.setBackground(colorarray[random_int1]);**/
        
        mipple_1 = new JLabel("");
        mipple_2 = new JLabel("");
        mipple_3 = new JLabel("");
        mipple_4 = new JLabel("");
        mipple_1_2 = new JLabel("");
        mipple_2_2 = new JLabel("");
        mipple_3_2 = new JLabel("");
        mipple_4_2 = new JLabel("");
        
        /*
        player1_curr= new JPanel();
        player1_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player1_curr.setBackground(colorarray[random_int1]);
        player1_curr.add(mipple_1);
        player1_curr.addMouseListener(this);
        
        
        
        player2_curr= new JPanel();
        player2_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player2_curr.setBackground(colorarray[random_int2]);
        player2_curr.add(mipple_2);
        player2_curr.addMouseListener(this);
        
        player3_curr= new JPanel();
        player3_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player3_curr.setBackground(colorarray[random_int3]);
        player3_curr.add(mipple_3);
        player3_curr.addMouseListener(this);
        
        
        player4_curr= new JPanel();
        player4_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player4_curr.setBackground(colorarray[random_int4]);
        player4_curr.add(mipple_4);
        player4_curr.addMouseListener(this);
        
        currentTiles.add(currentTileLabel);
        currentTiles.add(player1_curr);
        currentTiles.add(player2_curr);
        currentTiles.add(player3_curr);
        currentTiles.add(player4_curr);
        */
        player1_curr= new JPanel(new GridLayout(1,2));
        player1_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player1_curr1= new JPanel();
        player1_curr2= new JPanel();
        player1_curr1.setBackground(colorarray[random_int1]);
        player1_curr2.setBackground(colorarray[random_int2]);
        player1_curr.add(player1_curr1);
        player1_curr.add(player1_curr2);
        player1_curr1.add(mipple_1);
        player1_curr2.add(mipple_1_2);
        player1_curr.addMouseListener(this);
        
        
        player2_curr= new JPanel(new GridLayout(1,2));
        player2_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player2_curr1= new JPanel();
        player2_curr2= new JPanel();
        player2_curr1.setBackground(colorarray[random_int3]);
        player2_curr2.setBackground(colorarray[random_int4]);
        player2_curr.add(player2_curr1);
        player2_curr.add(player2_curr2);
        player2_curr1.add(mipple_2);
        player2_curr2.add(mipple_2_2);
        player2_curr.addMouseListener(this);
        
        player3_curr= new JPanel(new GridLayout(1,2));
        player3_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player3_curr1= new JPanel();
        player3_curr2= new JPanel();
        player3_curr1.setBackground(colorarray[random_int5]);
        player3_curr2.setBackground(colorarray[random_int6]);
        player3_curr.add(player3_curr1);
        player3_curr.add(player3_curr2);
        player3_curr1.add(mipple_3);
        player3_curr2.add(mipple_3_2);
        player3_curr.addMouseListener(this);
        
        player4_curr= new JPanel(new GridLayout(1,2));
        player4_curr.setBorder(BorderFactory. createLineBorder(Color. black));
        player4_curr1= new JPanel();
        player4_curr2= new JPanel();
        player4_curr1.setBackground(colorarray[random_int7]);
        player4_curr2.setBackground(colorarray[random_int8]);
        player4_curr.add(player4_curr1);
        player4_curr.add(player4_curr2);
        player4_curr1.add(mipple_4);
        player4_curr2.add(mipple_4_2);
        player4_curr.addMouseListener(this);
        
        currentTiles.add(currentTileLabel);
        currentTiles.add(player1_curr);
        currentTiles.add(player2_curr);
        currentTiles.add(player3_curr);
        currentTiles.add(player4_curr);
       

        /** old future
        JLabel futureTileLabel= new JLabel("Future Round Tiles");
        futureTileLabel.setForeground(Color.black);
        JPanel futureTiles= new JPanel();
        futureTiles.setBorder(BorderFactory. createLineBorder(Color. black));
        //futureTiles.setBackground(lightBlue);
        futureTiles.setLayout(new GridLayout(5,1));
        player1_fut= new JPanel();
        player1_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        //player1_fut.setBackground(Color.yellow);
        player1_fut.setBounds(0,0,1000,1000);
        player2_fut= new JPanel();
        player2_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        //player2_fut.setBackground(Color.yellow);
        player3_fut= new JPanel();
        player3_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        //player3_fut.setBackground(Color.yellow);
        player4_fut= new JPanel();
        player4_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        //player4_fut.setBackground(Color.yellow);
        futureTiles.add(futureTileLabel);
        futureTiles.add(player1_fut);
        futureTiles.add(player2_fut);
        futureTiles.add(player3_fut);
        futureTiles.add(player4_fut);
        **/
        JLabel futureTileLabel= new JLabel("Future Round Tiles");
        futureTileLabel.setForeground(Color.black);
        JPanel futureTiles= new JPanel();
        futureTiles.setBorder(BorderFactory. createLineBorder(Color. black));
        futureTiles.setBackground(lightBlue);
        futureTiles.setLayout(new GridLayout(5,1));
        
        player1_fut= new JPanel(new GridLayout(1,2));
        player1_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        player1_fut1= new JPanel();
        player1_fut2= new JPanel();
        player1_fut.add(player1_fut1);
        player1_fut.add(player1_fut2);
        
        player2_fut= new JPanel(new GridLayout(1,2));
        player2_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        player2_fut1= new JPanel();
        player2_fut2= new JPanel();
        player2_fut.add(player2_fut1);
        player2_fut.add(player2_fut2);
        
        player3_fut= new JPanel(new GridLayout(1,2));
        player3_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        player3_fut1= new JPanel();
        player3_fut2= new JPanel();
        player3_fut.add(player3_fut1);
        player3_fut.add(player3_fut2);
        
        player4_fut= new JPanel(new GridLayout(1,2));
        player4_fut.setBorder(BorderFactory. createLineBorder(Color. black));
        player4_fut1= new JPanel();
        player4_fut2= new JPanel();
        player4_fut.add(player4_fut1);
        player4_fut.add(player4_fut2);
        
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
            map1= new PlayerTerritory(playerOne, Color.red,false, 1, this.visionDeficiency );
            map2= new PlayerTerritory(playerTwo, Color.blue, true,0, this.visionDeficiency);
            maps.add(map1);
            maps.add(map2);
        }
        
        if (playerMode==2 && computerPlayers == false){
            maps.setLayout(new GridLayout(1,2));
            map1= new PlayerTerritory(playerOne, Color.red, false,2, this.visionDeficiency);
            map2= new PlayerTerritory(playerTwo, Color.blue, false,2, this.visionDeficiency);
            maps.add(map1);
            maps.add(map2);
        }
        else if (playerMode==4 && computerPlayers == true && humanPlayers == 1){
            maps.setLayout(new GridLayout(2,2));
            map1= new PlayerTerritory(playerOne, Color.red, false,1, this.visionDeficiency);
            map2= new PlayerTerritory(playerTwo, Color.blue, true,0, this.visionDeficiency);
            map3= new PlayerTerritory(playerThree, Color.darkGray, true,0, this.visionDeficiency);
            map4= new PlayerTerritory(playerFour, Color.magenta, true,0, this.visionDeficiency);
            maps.add(map1);
            maps.add(map2);
            maps.add(map3);
            maps.add(map4);
        }
        
        else if (playerMode==4 && computerPlayers == true && humanPlayers == 2){
            maps.setLayout(new GridLayout(2,2));
            map1= new PlayerTerritory(playerOne, Color.red, false,2, this.visionDeficiency);
            map2= new PlayerTerritory(playerTwo, Color.blue, false,2, this.visionDeficiency);
            map3= new PlayerTerritory(playerThree, Color.darkGray, true,0, this.visionDeficiency);
            map4= new PlayerTerritory(playerFour, Color.magenta, true,0, this.visionDeficiency);
            maps.add(map1);
            maps.add(map2);
            maps.add(map3);
            maps.add(map4);
        }
        
        
        else if (playerMode==4 && computerPlayers == true && humanPlayers == 3){
            maps.setLayout(new GridLayout(2,2));
            map1= new PlayerTerritory(playerOne, Color.red, false,3, this.visionDeficiency);
            map2= new PlayerTerritory(playerTwo, Color.blue, false,3, this.visionDeficiency);
            map3= new PlayerTerritory(playerThree, Color.darkGray, false,3, this.visionDeficiency);
            map4= new PlayerTerritory(playerFour, Color.magenta, true,0, this.visionDeficiency);
            maps.add(map1);
            maps.add(map2);
            maps.add(map3);
            maps.add(map4);
        }
        
        else if (playerMode==4 && computerPlayers == false){
            maps.setLayout(new GridLayout(2,2));
            map1= new PlayerTerritory(playerOne, Color.red, false, 4, this.visionDeficiency);
            map2= new PlayerTerritory(playerTwo, Color.blue, false, 4, this.visionDeficiency);
            map3= new PlayerTerritory(playerThree, Color.darkGray, false, 4, this.visionDeficiency);
            map4= new PlayerTerritory(playerFour, Color.magenta, false, 4, this.visionDeficiency);
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
        //JLabel instructionLabel= new JLabel("Instructions what to do next");
 
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
        //getContentPane().add(instructionLabel, BorderLayout.SOUTH);
        this.setJMenuBar(menu);
        
        // housekeeping : behaviour
        
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        JOptionPane.showMessageDialog(this,"Each Player must select a grid where they want to place their starting tile","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null,"Starting tile for a Robot Player is already selected","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
    
    
        
        //JOptionPane.showMessageDialog(null,"After all the starting tiles are selected, Each Player must select a Domino from Current Domino Tiles where they want to place their King Meeple","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
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
            PlayerTerritory.blindValue  = 0;
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
    
    /**
    public void mouseClicked(MouseEvent mevt)
    
    
    
    {
        
          //try{  
             
             Object selected = mevt.getSource();
             
             if (playerMode == 2){
               if (selected.equals(player1_curr) && mipple_Selected1 == false && selectingCurrentDominoTiles == true )
             {   
                 if (playerTurnNumber == 2){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else{
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2;
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 mipple_Selected1 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut.setBackground(colorarray[random_int3]);
                     player2_fut.setBackground(colorarray[random_int1]);
                     player3_fut.setBackground(colorarray[random_int4]);
                     player4_fut.setBackground(colorarray[random_int2]);
                    }
             }
             
             if (selected.equals(player2_curr) && mipple_Selected2 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1;
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else{
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2;
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 mipple_Selected2 = true;
                 if (mipple_Selected1 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut.setBackground(colorarray[random_int3]);
                     player2_fut.setBackground(colorarray[random_int1]);
                     player3_fut.setBackground(colorarray[random_int4]);
                     player4_fut.setBackground(colorarray[random_int2]);
                    }
             }
             
             if (selected.equals(player3_curr) && mipple_Selected3 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1;
                     l2.setText(playerTurnNumber + " Player Turn");

                 }
                 
                 else{
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2;
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 mipple_Selected3 = true;
                 if (mipple_Selected2 == true && mipple_Selected1 == true && mipple_Selected4 == true){
                     
                     player1_fut.setBackground(colorarray[random_int3]);
                     player2_fut.setBackground(colorarray[random_int1]);
                     player3_fut.setBackground(colorarray[random_int4]);
                     player4_fut.setBackground(colorarray[random_int2]);
                    }
                 
             }
             
             if (selected.equals(player4_curr) && mipple_Selected4 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1;
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else{
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2;
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                  
                 mipple_Selected4 = true;
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected1 == true){
                     
                     player1_fut.setBackground(colorarray[random_int3]);
                     player2_fut.setBackground(colorarray[random_int1]);
                     player3_fut.setBackground(colorarray[random_int4]);
                     player4_fut.setBackground(colorarray[random_int2]);
                    }
             }
            }
            
            if (playerMode == 4){
             if (selected.equals(player1_curr) && mipple_Selected1 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 //playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
                 mipple_Selected1 = true;
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut.setBackground(colorarray[random_int3]);
                     player2_fut.setBackground(colorarray[random_int1]);
                     player3_fut.setBackground(colorarray[random_int4]);
                     player4_fut.setBackground(colorarray[random_int2]);
                    }
               
             }
             
             if (selected.equals(player2_curr) && mipple_Selected2 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 //playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
                 mipple_Selected2 = true;
                 if (mipple_Selected1 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut.setBackground(colorarray[random_int3]);
                     player2_fut.setBackground(colorarray[random_int1]);
                     player3_fut.setBackground(colorarray[random_int4]);
                     player4_fut.setBackground(colorarray[random_int2]);
                    }
        
             }
             
             if (selected.equals(player3_curr) && mipple_Selected3 == false && selectingCurrentDominoTiles == true)
             {   
                 
                 if (playerTurnNumber == 2){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 //playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
                 mipple_Selected3 = true;
                 
                 if (mipple_Selected1 == true && mipple_Selected2 == true && mipple_Selected4 == true){
                     
                     player1_fut.setBackground(colorarray[random_int3]);
                     player2_fut.setBackground(colorarray[random_int1]);
                     player3_fut.setBackground(colorarray[random_int4]);
                     player4_fut.setBackground(colorarray[random_int2]);
                    }
             }
             
             if (selected.equals(player4_curr) && mipple_Selected4 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P- " + playerTurnNumber);l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                
                 }
                 
                 mipple_Selected4 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected1 == true){
                     
                     player1_fut.setBackground(colorarray[random_int3]);
                     player2_fut.setBackground(colorarray[random_int1]);
                     player3_fut.setBackground(colorarray[random_int4]);
                     player4_fut.setBackground(colorarray[random_int2]);
                    }
             }
            }
            //}
          catch(IOException ex){System.out.println("hello");}
            //KingdomLayout square = (KingdomLayout) selected;
            
            //square.setBackground(Color.red);
            //square.add(picLabel);
            //playerMap.add(square);
            
    }
    **/
    /**public void mouseClicked(MouseEvent mevt)
    
    
    
    {
        
          //try{  
             
             Object selected = mevt.getSource();
             
             if (playerMode == 2){
               if (selected.equals(player1_curr) && mipple_Selected1 == false && selectingCurrentDominoTiles == true )
             {   
                 if (playerTurnNumber == 2){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else{
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
            }
                 mipple_Selected1 = true;
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
             }
             
             if (selected.equals(player2_curr) && mipple_Selected2 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else{
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 mipple_Selected2 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
             }
             
             if (selected.equals(player3_curr) && mipple_Selected3 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");

                 }
                 
                 else{
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 mipple_Selected3 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
                 
             }
             
             if (selected.equals(player4_curr) && mipple_Selected4 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else{
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                  
                 mipple_Selected4 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
             }
   
            
             if (playerMode == 4)
             {
                 if (selected.equals(player1_curr) && mipple_Selected1 == false && selectingCurrentDominoTiles == true)
             {   
                 
                 if (playerTurnNumber == 2){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 //playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
                 mipple_Selected1 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
               
             }
             
             if (selected.equals(player2_curr) && mipple_Selected2 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 //playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
                 mipple_Selected2 = true;
                 
                 if (mipple_Selected1 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
        
             }
             
             if (selected.equals(player3_curr) && mipple_Selected3 == false && selectingCurrentDominoTiles == true)
             {   
                 
                 if (playerTurnNumber == 2){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 //playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
                 mipple_Selected3 = true;
                 if (mipple_Selected1 == true && mipple_Selected2 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
             }
             
             
             
             
             
             if (selected.equals(player4_curr) && mipple_Selected4 == false && selectingCurrentDominoTiles == true)
             {   
                 if (playerTurnNumber == 2){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");

                     
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                
                 }
                 
                 mipple_Selected4 = true;
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
             }
            }
            //}yo
          
            //KingdomLayout square = (KingdomLayout) selected;
            
            //square.setBackground(Color.red);
            //square.add(picLabel);
            //playerMap.add(square);
            
    } **/  
    public void mouseClicked(MouseEvent mevt)
    
    
    
     {
             Object selected = mevt.getSource();
             
             
            
             
             if (playerMode == 2){
               if (selected.equals(player1_curr)  && selectingCurrentDominoTiles == true )
             {   
                 if (mipple_Selected1 == true){
                    
                    JOptionPane.showMessageDialog(null,"This domino is already selected, please place your King Meeple on other Domino Tile!","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
                    
                }
                if (mipple_Selected1 == false){
                 if (playerTurnNumber == 2){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else{
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                }
            
                 mipple_Selected1 = true;
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
              }
             
             if (selected.equals(player2_curr)  && selectingCurrentDominoTiles == true)
             {  
                 if (mipple_Selected2 == true){
                    
                    JOptionPane.showMessageDialog(null,"This domino is already selected, please place your King Meeple on other Domino Tile!","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
                    
                }
                if (mipple_Selected2 == false){
                 if (playerTurnNumber == 2){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else{
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                }
                 mipple_Selected2 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
             }
             
             if (selected.equals(player3_curr) && selectingCurrentDominoTiles == true)
             {   
                 if (mipple_Selected3 == true){
                    
                    JOptionPane.showMessageDialog(null,"This domino is already selected, please place your King Meeple on other Domino Tile!","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
                    
                }
                if (mipple_Selected3 == false){
                 if (playerTurnNumber == 2){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");

                 }
                 
                 else{
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                }
                 
                 mipple_Selected3 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
                 
             }
             
             if (selected.equals(player4_curr)  && selectingCurrentDominoTiles == true)
             {   
                 if (mipple_Selected4 == true){
                    
                    JOptionPane.showMessageDialog(null,"This domino is already selected, please place your King Meeple on other Domino Tile!","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
                    
                }
                if (mipple_Selected4 == false){
                 if (playerTurnNumber == 2){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else{
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                }
                  
                 mipple_Selected4 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
             }
   
            }
            if (playerMode == 4)
             {
                 
                
                 if (selected.equals(player1_curr) && selectingCurrentDominoTiles == true)
             {   
                 if (mipple_Selected1 == true){
                     JOptionPane.showMessageDialog(null,"This domino is already selected, please place your King Meeple on other Domino Tile!","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
                 }
                 if (mipple_Selected1 == false){
                 if (playerTurnNumber == 2){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_1.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1.setForeground(Color.cyan);
                     mipple_1.setText("P-");
                     
                     mipple_1_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_1_2.setForeground(Color.cyan);
                     mipple_1_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                 }
                }
                 //playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
                 mipple_Selected1 = true;
                 
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
               
             }
              
             if (selected.equals(player2_curr) && selectingCurrentDominoTiles == true)
             { 
                  if (mipple_Selected2 == true){
                     JOptionPane.showMessageDialog(null,"This domino is already selected, please place your King Meeple on other Domino Tile!","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
                 }
                 if (mipple_Selected2 == false){
                 if (playerTurnNumber == 2){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2.setForeground(Color.cyan);
                     mipple_2.setText("P-");
                     
                     mipple_2_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_2_2.setForeground(Color.cyan);
                     mipple_2_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                
                }
                 
                 //playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
                 mipple_Selected2 = true;
                 
                 if (mipple_Selected1 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
        
             }
             
             if (selected.equals(player3_curr) && selectingCurrentDominoTiles == true)
             {   
                 if (mipple_Selected3 == true){
                     JOptionPane.showMessageDialog(null,"This domino is already selected, please place your King Meeple on other Domino Tile!","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
                 }
                 if (mipple_Selected3 == false){
                 if (playerTurnNumber == 2){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_3.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3.setForeground(Color.cyan);
                     mipple_3.setText("P-");
                     
                     mipple_3_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_3_2.setForeground(Color.cyan);
                     mipple_3_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                }
                 //playerTurnNumber = (int)Math.floor(Math.random()*(4-1+1)+1);
                 mipple_Selected3 = true;
                 if (mipple_Selected1 == true && mipple_Selected2 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
             }
             
             
             
             // you need to modify here
             
             
             
             
             
             if (selected.equals(player4_curr)  && selectingCurrentDominoTiles == true)
             {   
                 if (mipple_Selected4 == true){
                     JOptionPane.showMessageDialog(null,"This domino is already selected, please place your King Meeple on other Domino Tile!","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
                 }
                 if (mipple_Selected4 == false){
                 if (playerTurnNumber == 2){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 3; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 3){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 4; 
                     l2.setText(playerTurnNumber + " Player Turn");

                     
                 }
                 
                 else if (playerTurnNumber == 4){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 1; 
                     l2.setText(playerTurnNumber + " Player Turn");
                     
                 }
                 
                 else if (playerTurnNumber == 1){
                     mipple_4.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4.setForeground(Color.cyan);
                     mipple_4.setText("P-");
                     
                     mipple_4_2.setFont(new Font("Serif", Font.BOLD, 57));
                     mipple_4_2.setForeground(Color.cyan);
                     mipple_4_2.setText(""+playerTurnNumber);
                     
                     l2.setText(playerTurnNumber + " Player Turn");
                     playerTurnNumber = 2; 
                     l2.setText(playerTurnNumber + " Player Turn");
                
                 }
                }
                 mipple_Selected4 = true;
                 if (mipple_Selected2 == true && mipple_Selected3 == true && mipple_Selected4 == true){
                     
                     player1_fut1.setBackground(colorarray[random_int1]);
                     player2_fut1.setBackground(colorarray[random_int2]);
                     player3_fut1.setBackground(colorarray[random_int3]);
                     player4_fut1.setBackground(colorarray[random_int4]);
                     player1_fut2.setBackground(colorarray[random_int5]);
                     player2_fut2.setBackground(colorarray[random_int6]);
                     player3_fut2.setBackground(colorarray[random_int7]);
                     player4_fut2.setBackground(colorarray[random_int8]);
                    }
             }
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
    
    