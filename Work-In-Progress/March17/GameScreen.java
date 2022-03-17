import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.Desktop;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/*
 *  The main GameWindow of the gui.
 *  
 */
public class GameScreen extends JFrame implements ActionListener, MouseListener
{
    private final JMenuBar menu; //the menu
    private final JMenu options; //menus
    private final JMenuItem newGame;
    private final JMenuItem saveGame;
    private final JMenuItem loadGame;
    private final JMenuItem help;
    private final JMenuItem quit; //menu items for file menu

    private final JPanel topPanel;
    private final JPanel bottomPanel;
    public TileStack futureStack, currentStack, swapStack;
    
    private final JLabel instructionLabel;        // textlabel at the very bottom of the window telling suer what to do next

    private int gameStep = 0;
    public List<TileStack.StackSlot> turnQueue;

    private final Set<Integer> availableTiles = new HashSet<>();
    private final List<String> playerNames = new ArrayList<>();
    private final Set<PlayerTerritory> kingdoms;
    private final Set<PlayerTerritory> humanKingdoms = new HashSet<>();
    private final Set<PlayerTerritory> computerKingdoms = new HashSet<>();
    
    /*
     *  constructor method takes as input how many rows and columns of gridsquares to create
     *  it then creates the panels, their subcomponents and puts them all together in the main frame
     *  it makes sure that action listeners are added to selectable items
     *  it makes sure that the gui will be visible
     */
    public GameScreen(int playerMode, int humanPlayers, int computerPlayers, String playerOne, String playerTwo, String playerThree, String playerFour)
        {
        for (int i = 1; i <= 48; i++) {availableTiles.add(i);}

        playerNames.add(playerOne);
        playerNames.add(playerTwo);
        playerNames.add(playerThree);
        playerNames.add(playerFour);

        // Give the current stack a queue with random arrangement of players
        List<Integer> tempKings = new ArrayList<>();
        if (playerMode == 2) {
            for (int i = 1; i <= playerMode; i++) {tempKings.add(i);}
            for (int i = 1; i <= playerMode; i++) {tempKings.add(i);}
        }
        else {
            for (int i = 1; i <= playerMode; i++) {tempKings.add(i);}
        }

        currentStack = new TileStack(this);
        currentStack.setKings(tempKings);
        futureStack = new TileStack(this);

        // Disable the future domino tiles stack
        for (TileStack.StackSlot slot: futureStack.getSlotList()) {slot.setActive(false);}

        // Set an initial stack of domino tiles to work with
        currentStack.setSlotTile(generateRandom(1, 48), 1);
        currentStack.setSlotTile(generateRandom(1, 48), 2);
        currentStack.setSlotTile(generateRandom(1, 48), 3);
        currentStack.setSlotTile(generateRandom(1, 48), 4);

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

        //bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBorder(BorderFactory. createLineBorder(Color. black));
        bottomPanel.setBackground(Color.green);

        // A panel to house all player territories.
        JPanel maps = new JPanel();
        maps.setLayout(new GridBagLayout());
        maps.setBackground(Color.white);

        // Make kingdoms for the players provided for each mode
        for (int player = 0; player < humanPlayers; player++){
            PlayerTerritory kingdom = new PlayerTerritory(playerNames.get(player), Color.red,false, this);
            humanKingdoms.add(kingdom);
            maps.add(kingdom);
        }
        for (int player = humanPlayers; player <= computerPlayers; player++){
            PlayerTerritory kingdom = new PlayerTerritory(playerNames.get(player), Color.red,true, this);
            computerKingdoms.add(kingdom);
            maps.add(kingdom);
        }
        kingdoms = new HashSet<>(){ { addAll(humanKingdoms); addAll(computerKingdoms); } };

        //Sets up the bottom panel which contains the domino tiles and the player territories.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottomPanel.add(currentStack, gbc);
        gbc.gridx = 1;
        gbc.weightx =2;
        bottomPanel.add(new JPanel().add(maps), gbc);
        gbc.gridx = 2;
        gbc.weightx = 1;
        bottomPanel.add(futureStack, gbc);

        //bottom of the screen
        instructionLabel= new JLabel("Instructions what to do next");

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);
        add(instructionLabel, BorderLayout.SOUTH);
        this.setJMenuBar(menu);
        
        // housekeeping : behaviour
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400,900);
        setResizable(false);
        setVisible(true);
        
        //JOptionPane.showMessageDialog(this,"Each Player must select a grid where they want to place their starting tile","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
        //JOptionPane.showMessageDialog(null,"Starting tile for a Robot Player is already selected","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
        //JOptionPane.showMessageDialog(null,"After all the starting images.tiles are selected, Each Player must select a Domino from Current Domino Tiles where they want to place their King Meeple","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
    }

    public List<String> getPlayerNames(){return playerNames;}
    public List<TileStack.StackSlot> getTurnQueue(){return this.turnQueue;}

    // Move to the next game step e.g. pass a turn, place a tile.
    public void setGameStep(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 0;

        switch (gameStep) {
            // This game stage is only used in the beginning to load up a stack of cards
            case 0 -> {
                if (!currentStack.isKingPresent()) {break;}
                this.gameStep = 1;
                futureStack.setKings(currentStack.getkingQueue());
                futureStack.setSlotTile(generateRandom(1, 48), 1);
                futureStack.setSlotTile(generateRandom(1, 48), 2);
                futureStack.setSlotTile(generateRandom(1, 48), 3);
                futureStack.setSlotTile(generateRandom(1, 48), 4);
                turnQueue = new ArrayList<>(currentStack.getSlotList());
                revalidate();
            }
            case 1 -> {
                this.gameStep = 2;
                placeTile();
            }
            case 2 -> {
                this.gameStep = 1;
                currentStack.removeSelection();
                selectFutureTile();
                if (turnQueue.isEmpty()){this.gameStep = 3;}
                revalidate();
            }
            // This game stage switches the future and current domino tiles
            case 3 -> {
                gbc.gridx = 0;
                swapStack = currentStack;
                bottomPanel.remove(currentStack);
                currentStack = futureStack;
                bottomPanel.remove(futureStack);
                futureStack = swapStack;
                bottomPanel.add(currentStack, gbc);
                gbc.gridx = 2;
                bottomPanel.add(futureStack, gbc);
                turnQueue = new ArrayList<>(currentStack.getSlotList());
                placeTile();
                futureStack.setKings(currentStack.getkingQueue());
                revalidate();
                gameStep = 4;
            }
            case 4 -> {
                if (!currentStack.isKingPresent()) {break;}
                this.gameStep = 1;
                futureStack.setSlotTile(generateRandom(1, 48), 1);
                futureStack.setSlotTile(generateRandom(1, 48), 2);
                futureStack.setSlotTile(generateRandom(1, 48), 3);
                futureStack.setSlotTile(generateRandom(1, 48), 4);
                for (TileStack.StackSlot slot: futureStack.getSlotList()) {slot.setChosen();}
                turnQueue = new ArrayList<>(currentStack.getSlotList());
                placeTile();
                revalidate();
                gameStep = 2;
            }
        }

    }

    // Disables and enables respective components to allow a player place a tile on his territory.
    public void placeTile(){
        for (TileStack.StackSlot slot: turnQueue) {
            slot.setActive(slot.equals(turnQueue.get(0)));
        }
        for (TileStack.StackSlot slot: futureStack.getSlotList()) {slot.setActive(false);}
        setTerritoryTiles();
    }

    // Disables and enables respective components to allow a player select a future tile.
    public void selectFutureTile(){
        for (TileStack.StackSlot slot: currentStack.getSlotList()) {slot.setActive(false);}
        for (PlayerTerritory territory: kingdoms) {territory.setActive(false);}
        for (TileStack.StackSlot slot: futureStack.getSlotList()) {if (!slot.isChosen()) {slot.setActive(true);}}
        revalidate();
    }

    public void setTerritoryTiles(){
        for (PlayerTerritory territory: kingdoms) {
            if (territory.getPlayerName().equals(turnQueue.get(0).getOwner())) {
                territory.setActive(true);
                territory.setCurrentSlot(turnQueue.get(0).tile.getNumber(), turnQueue.get(0).tile.getOrientation());
            }
            else {territory.setActive(false);}
        }
    }

    // Generate a random number from the available numbers for tiles.
    // After generating a number it removes it to prevent duplicates.
    public int generateRandom(int min, int max){
        int tileNumber = 0;
        while(!availableTiles.contains(tileNumber)){
            tileNumber =  ThreadLocalRandom.current().nextInt(min, max + 1);
        }
        availableTiles.remove(tileNumber);
        return tileNumber;
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


    public void mouseClicked(MouseEvent mevt){}
    
    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}

}



