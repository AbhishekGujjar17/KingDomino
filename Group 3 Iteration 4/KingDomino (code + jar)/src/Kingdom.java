import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.Desktop;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/*
 *  The main GameWindow of the gui.
 *  
 */
public class Kingdom extends JFrame implements Serializable, ActionListener, MouseListener
{
    private JMenuBar menu; //the menu
    private JMenuItem newGame;
    private JMenuItem saveGame;
    private JMenuItem loadGame;
    private int playersNumber;
    private JMenuItem help;
    private JMenuItem quit; //menu items for file menu

    private JPanel bottomPanel;
    public TileStack futureStack, currentStack, swapStack;
    
    private JLabel instructionLabel;        // textlabel at the very bottom of the window telling suer what to do next

    private int gameStep = 1;
    public List<TileStack.StackSlot> turnQueue;
    public List<String> playerQueue;

    private Set<Integer> availableTiles = new HashSet<>();
    private final List<String> playerNames = new ArrayList<>();
    private Set<PlayerTerritory> kingdoms;
    private final Set<PlayerTerritory> humanKingdoms = new HashSet<>();
    private final Set<PlayerTerritory> computerKingdoms = new HashSet<>();
    private String colorMode;
    private GameScreenState state;
    int cards;
    
    /*
     *  constructor method takes as input how many rows and columns of gridsquares to create
     *  it then creates the panels, their subcomponents and puts them all together in the main frame
     *  it makes sure that action listeners are added to selectable items
     *  it makes sure that the gui will be visible
     */
    public Kingdom(int playerMode, int humanPlayers, String colorMode, boolean botPresent, String playerOne, String playerTwo, String playerThree, String playerFour, boolean fromMemory)
        {
        int computerPlayers = botPresent ? playerMode - humanPlayers : 0;
        this.colorMode = colorMode;
        this.playersNumber = playerMode;

        cards = (playerMode == 2) ? 24 : 48;
        for (int i = 1; i <= cards; i++) {availableTiles.add(i);}

        playerNames.add(playerOne);
        playerNames.add(playerTwo);
        if (playerMode == 4){
        playerNames.add(playerThree);
        playerNames.add(playerFour);}

        playerQueue = new ArrayList<>(playerNames);

        // Give the current stack a queue with random arrangement of players
        List<Integer> tempKings = new ArrayList<>();
        if (playerMode == 2) {
            for (int i = 1; i <= playerMode; i++) {tempKings.add(i);}
            for (int i = 1; i <= playerMode; i++) {tempKings.add(i);}
        }
        else {
            for (int i = 1; i <= playerMode; i++) {tempKings.add(i);}
        }

        currentStack = new TileStack(this, "Current");
        currentStack.setKings(tempKings);
        futureStack = new TileStack(this, "Future");

        state = new GameScreenState(gameStep, playerMode, humanPlayers, colorMode, botPresent, playerOne, playerTwo, playerThree, playerFour, playerQueue, futureStack.isEmpty, availableTiles, currentStack.getkingQueue(), futureStack.getkingQueue());

            // Disable the future domino tiles stack
        for (TileStack.StackSlot slot: futureStack.getSlotList()) {slot.setActive(false);}

        // Set an initial stack of domino tiles to work with
        List<Integer> sequence = generateRandom(1, cards);
        currentStack.setSlotTile(sequence.get(0), 1);
        currentStack.setSlotTile(sequence.get(1), 2);
        currentStack.setSlotTile(sequence.get(2), 3);
        currentStack.setSlotTile(sequence.get(3), 4);
        for (TileStack.StackSlot slot: currentStack.getSlotList()) {slot.setActive(false);}

        state.currentKings = currentStack.getkingQueue();

        //first creating the menu
        menu = new JMenuBar();
            //menus
            JMenu options = new JMenu("Options");
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
            JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory. createLineBorder(Color. black));
            if (this.colorMode.equals("deficiencyMode")) {

                topPanel.setBackground(Color.gray);
            }

            else {

                topPanel.setBackground(Color.green);
            }
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        Position placement = new Position();
        // Make kingdoms for the players provided for each mode
        for (int player = 0; player < humanPlayers; player++){
            gbc.gridx = placement.getX();
            gbc.gridy = placement.getY();
            PlayerTerritory kingdom = new PlayerTerritory(playerNames.get(player), Color.red,false, this);
            humanKingdoms.add(kingdom);
            maps.add(kingdom, gbc);
            placement.increase();
        }
        for (int player = humanPlayers; player < computerPlayers+humanPlayers; player++){
            gbc.gridx = placement.getX();
            gbc.gridy = placement.getY();
            PlayerTerritory kingdom = new PlayerTerritory(playerNames.get(player), Color.red,true, this);
            computerKingdoms.add(kingdom);
            maps.add(kingdom, gbc);
            placement.increase();
        }
        kingdoms = new HashSet<>(){ { addAll(humanKingdoms); addAll(computerKingdoms); } };

        //Sets up the bottom panel which contains the domino tiles and the player territories.
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottomPanel.add(currentStack, gbc);
        gbc.gridx = 1;
        gbc.weightx =2;
        JPanel ace = new JPanel();
        ace.add(maps);
        bottomPanel.add(ace, gbc);
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
        setGameStep();
        if (!fromMemory){
            JOptionPane.showMessageDialog(this,"Each Player must select a grid where they want to place their starting tile","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);
            JOptionPane.showMessageDialog(null,"Starting tile for a Robot Player is already selected","WELCOME TO GAME",JOptionPane.PLAIN_MESSAGE);}
        }

    public void loadGame(List<TileStack.StackSlot> currentStackState, List<TileStack.StackSlot> futureStackState, List<TerritoryState> territoryStates, GameScreenState gameScreenState){
        availableTiles = gameScreenState.availableTiles;
        playerQueue = gameScreenState.playerQueue;
        gameStep = gameScreenState.gameStep;
        currentStack.setKings(gameScreenState.currentKings);
        futureStack.setKings(gameScreenState.futureKings);
        futureStack.isEmpty = gameScreenState.futureIsEmpty;
        turnQueue = currentStack.getSlotList();

        currentStack.parent = this;
        futureStack.parent = this;
        currentStack.restore(currentStackState);
        futureStack.restore(futureStackState);

        int i=0;
        Iterator<PlayerTerritory> kds = kingdoms.iterator();
        for (TerritoryState territoryState: territoryStates){
            PlayerTerritory kingdom = kds.next();
            kingdom.setTerritoryState(territoryState);
            kingdom.loadSavedTiles();
            i++;
        }
        placeTile();
    }

    public void saveGame(int en) throws IOException {
        File theDir = new File(""+en);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(""+en+"/Kingdoms.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        List <TerritoryState> st = new ArrayList<>();
        for (PlayerTerritory territory: kingdoms ){
            st.add(territory.getTerritoryState());
        }

        oos.writeObject(st);
        oos.close();

        fos = new FileOutputStream(""+en + "/CurrentStack.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(currentStack.getSlotList());
        oos.close();

        fos = new FileOutputStream(""+en + "/FutureStack.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(futureStack.getSlotList());
        oos.close();

        fos = new FileOutputStream(""+ en + "/Game.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(state);
        oos.close();

        System.out.println("Saved");
    }

    public List<String> getPlayerNames(){return playerNames;}

    public int getGameStep(){
        return gameStep;
    }

    // Move to the next game step e.g. pass a turn, place a tile.
    public void setGameStep(){
        state.gameStep = gameStep;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 0;

        switch (gameStep) {
            // This game step runs through each player placing their castles
            case 1 -> {
                if (playerQueue.isEmpty()){gameStep = 2;
                    state.gameStep = gameStep; setGameStep(); return;}
                for (PlayerTerritory territory: kingdoms) {
                    if (playerQueue.get(0).equals(territory.getPlayerName())){
                        if (territory.isComputerPlayer()){
                            territory.addCastle(4,4);
                            playerQueue.remove(0);
                            state.playerQueue = playerQueue;
                            setGameStep();
                            state.gameStep = gameStep;
                            return;}
                        territory.setActive(true);
                    }
                    else {{territory.setActive(false);}}
                }
                playerQueue.remove(0);
                state.playerQueue = playerQueue;
            }
            // This game stage is only used in the beginning to load up a stack of cards
            case 2 -> {
                selectCurrentTile();
                if (!currentStack.isKingPresent()) {break;}
                this.gameStep = 3;
                state.gameStep = gameStep;
            }
            // This game step Populates the future stack when all castles are placed
            case 3 -> {
                this.gameStep = 4;
                state.gameStep = gameStep;
                futureStack.setKings(currentStack.getkingQueue());
                List<Integer> sequence = generateRandom(1, cards);
                futureStack.setSlotTile(sequence.get(0), 1);
                futureStack.setSlotTile(sequence.get(1), 2);
                futureStack.setSlotTile(sequence.get(2), 3);
                futureStack.setSlotTile(sequence.get(3), 4);
                state.futureKings = futureStack.getkingQueue();
                turnQueue = new ArrayList<>(currentStack.getSlotList());
                state.futureIsEmpty = futureStack.isEmpty;
                revalidate();
                setGameStep();
            }
            case 4 -> {
                if (futureStack.isEmpty && turnQueue.isEmpty()){declareWinner();}
                this.gameStep = 5;
                state.gameStep = gameStep;
                System.out.println(turnQueue.get(0).getOwner());
                System.out.println("\n");
                for (PlayerTerritory territory: kingdoms) {
                    System.out.println(territory.getPlayerName());
                    System.out.println("-");
                    if (territory.getPlayerName().equals(turnQueue.get(0).getOwner()))
                    {
                        if (territory.isPlayPossible(turnQueue.get(0).getTileNumber())){placeTile();}
                        else {setGameStep(); break;}
                    }
                }
            }
            case 5 -> {
                this.gameStep = 4;
                state.gameStep = gameStep;
                currentStack.removeSelection();
                state.currentKings = currentStack.getkingQueue();
                state.futureIsEmpty = futureStack.isEmpty;
                if (futureStack.isEmpty){setGameStep();
                    state.gameStep = gameStep; return;}
                selectFutureTile();
                if (turnQueue.isEmpty()){this.gameStep = 6;
                    state.gameStep = gameStep;}
                revalidate();
            }
            // This game stage switches the future and current domino tiles
            case 6 -> {
                gbc.gridx = 0;
                swapStack = currentStack;
                bottomPanel.remove(currentStack);
                currentStack = futureStack;
                bottomPanel.remove(futureStack);
                futureStack = swapStack;
                futureStack.isEmpty = true;
                bottomPanel.add(currentStack, gbc);
                gbc.gridx = 2;
                bottomPanel.add(futureStack, gbc);
                turnQueue = new ArrayList<>(currentStack.getSlotList());
                state.futureIsEmpty = futureStack.isEmpty;
                futureStack.setKings(currentStack.getkingQueue());
                state.futureKings = futureStack.getkingQueue();
                state.currentKings = currentStack.getkingQueue();
                revalidate();
                gameStep = 7;
                state.gameStep = gameStep;
            }
            case 7 -> {
                System.out.println("shh");
                if (!currentStack.isKingPresent()) {break;}
                System.out.println("shh");
                this.gameStep = 1;
                state.gameStep = gameStep;
                if (!availableTiles.isEmpty()){
                    List<Integer> sequence = generateRandom(1, cards);
                    futureStack.setSlotTile(sequence.get(0), 1);
                    futureStack.setSlotTile(sequence.get(1), 2);
                    futureStack.setSlotTile(sequence.get(2), 3);
                    futureStack.setSlotTile(sequence.get(3), 4);}
                for (TileStack.StackSlot slot: futureStack.getSlotList()) {slot.setChosen(!slot.isChosen());}
                state.futureKings = futureStack.getkingQueue();
                turnQueue = new ArrayList<>(currentStack.getSlotList());
                state.futureIsEmpty = futureStack.isEmpty;
                revalidate();
                gameStep = 4;
                setGameStep();
            }
        }

    }

    public void selectCurrentTile(){
        for (TileStack.StackSlot slot: futureStack.getSlotList()) {slot.setActive(false);}
        for (PlayerTerritory territory: kingdoms) {territory.setActive(false);}
        for (TileStack.StackSlot slot: currentStack.getSlotList()) {slot.setActive(!slot.isChosen());}
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
    }

    public void setTerritoryTiles(){
        System.out.println("jjj\n");
        for (PlayerTerritory territory: kingdoms) {
            if (territory.getPlayerName().equals(turnQueue.get(0).getOwner())) {
                System.out.println("jjj\n");
                territory.setActive(true);
                territory.setCurrentSlot(turnQueue.get(0).tile.getNumber(), turnQueue.get(0).tile.getOrientation());
            }
            else {territory.setActive(false);}
        }
    }

    public void declareWinner(){
        List<String> winners = new ArrayList<>();
        int score = 0;
        for (PlayerTerritory territory: kingdoms){
            int tScore = territory.getTerritoryState().getScore();
            if (score < tScore){score = tScore; winners.clear(); winners.add(territory.getPlayerName()); }
            else if (score == tScore){winners.add(territory.getPlayerName());}
        }
        if (winners.toArray().length > 1){
        System.out.println("Our winners are: \n");}
        else {
            System.out.println("Our winner is: ");}
        for (String winner: winners){
            System.out.println("King "+winner+"\n");
        }
        System.out.println("With a score of "+score);
        System.exit(0);
    }

    public int getPlayerNumber(){
        return this.playersNumber;
    }

    public String getColorMode(){return colorMode;}

    // Generate a random number from the available numbers for tiles.
    // After generating a number it removes it to prevent duplicates.
    public List<Integer> generateRandom(int min, int max){
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            int tileNumber = 0;
            while(!availableTiles.contains(tileNumber)){
                tileNumber =  ThreadLocalRandom.current().nextInt(min, max + 1);
            }
            sequence.add(tileNumber);
            availableTiles.remove(tileNumber);
            state.availableTiles = availableTiles;

        }
        Collections.sort(sequence);
        return sequence;
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
            new NewGame();
            
            
        }
        
        if (selected.equals(loadGame)){
            this.dispose();
            new SavedGame();
            
            
            
        }
        
        if (selected.equals(saveGame)){
            try {
                saveGame(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
        }
        
        if (selected.equals(quit)){
            
            System.exit(0);
            
            
        }
        
        
    }
    static final class Position {
        private int x = 0;
        private int y = 0;

        public Position(){

        }

        public void increase(){
            if (x==0){
            x += 1;}
            else {x = 0; y+=1;}
        }

        public int getX(){return x;}
        public int getY(){return y;}
    }


    public void mouseClicked(MouseEvent mevt){}
    
    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}

}

class GameScreenState implements Serializable{
    int playerMode;
    int gameStep;
    int humanPlayers;
    String colorMode;
    boolean botPresent;
    String playerOne;
    String playerTwo;
    String playerThree;
    String playerFour;
    List<String> playerQueue;
    boolean futureIsEmpty;
    Set<Integer> availableTiles;
    List<Integer> currentKings;
    List<Integer> futureKings;

    public GameScreenState(int gameStep, int playerMode, int humanPlayers, String colorMode, boolean botPresent, String playerOne, String playerTwo, String playerThree, String playerFour, List<String> playerQueue, boolean futureIsEmpty, Set<Integer> availableTiles, List<Integer> currentKings, List<Integer> futureKings){
        this.currentKings = currentKings;
        this.futureKings = futureKings;
        this.gameStep = gameStep;
        this.availableTiles = availableTiles;
        this.futureIsEmpty = futureIsEmpty;
        this.playerQueue = playerQueue;
        this.playerMode = playerMode;
        this.humanPlayers = humanPlayers;
        this.colorMode = colorMode;
        this.botPresent = botPresent;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerThree = playerThree;
        this.playerFour = playerFour;
    }
}