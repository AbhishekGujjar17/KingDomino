import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;

/**
 * Write a description of class PlayerMap here.
 *
 * 
 */
public class PlayerTerritory extends JPanel implements ActionListener, MouseListener
{
    private final GameScreen parent;
    private final JPanel playerMap;
    private final int rows;
    private final int columns;
    private String playerName;
    private final GridSquare [][] gridSquares;    // squares to appear in the kingdom panel
    private int currentSlot;
    private int currentSlotOrientation;
    private boolean computerPlayer;
    private TerritoryState territoryState;

    public PlayerTerritory(String playerName, Color playerColour, boolean computerPlayer, GameScreen parent)
    {
        this.territoryState = new TerritoryState();
        this.computerPlayer = computerPlayer;
        this.parent = parent;
        this.rows = 9;
        this.columns = 9;
        this.playerName = playerName;

        this.setBackground(Color.yellow);
        this.setBorder(BorderFactory. createLineBorder(Color. black));
        this.setLayout(new BorderLayout());

        this.setBorder(BorderFactory. createLineBorder(Color. black));
        JLabel playerLabel= new JLabel(playerName + " Kingdom"); //the label at the top of each playermap
        playerLabel.setBackground(Color.yellow);
        playerLabel.setForeground(playerColour);
        this.add(playerLabel,BorderLayout.NORTH);

        playerMap = new JPanel();
        playerMap.setBorder(BorderFactory. createLineBorder(Color. black));
        playerMap.setLayout(new GridBagLayout());
        if (parent.getPlayerNumber() == 2){this.setPreferredSize(new Dimension(460, 460));}
        else {this.setPreferredSize(new Dimension(380, 380));}
        this.add(playerMap, BorderLayout.CENTER);

        //making the grid
        gridSquares = new GridSquare[rows][columns];
        Border blackline= BorderFactory.createLineBorder(Color.black,3);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        if (computerPlayer){


            for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gbc.gridx = x;
                gbc.gridy = y;
                gridSquares[x][y] = new GridSquare(x, y, parent);
                gridSquares[x][y].setActive(false);
                gridSquares[x][y].setBorder(blackline);
                playerMap.add(gridSquares[x][y], gbc);
            }
        }

        }

        else {
        for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gbc.gridx = x;
                gbc.gridy = y;
                gridSquares[x][y] = new GridSquare(x, y, parent);
                gridSquares[x][y].setBorder(blackline); //setting border
                gridSquares[x][y].setActive(false);
                gridSquares[x][y].addMouseListener(this);
                playerMap.add(gridSquares[x][y], gbc);
            }
        }
    }
    }

    public void setSquares(int tileNumber, int x1, int y1, int x2, int y2)
    {
        for (TileData tileMapping : DominoTile.tileMappings)
        {
            if (tileMapping.tileNumber == tileNumber)
            {
                territoryState.getSquare(x1, y1).setState(tileMapping.t1, tileMapping.c1);
                territoryState.getSquare(x2, y2).setState(tileMapping.t2, tileMapping.c2);
            }
        }

    }

    public boolean isPlayPossible(int tileNumber) {return territoryState.isPlayPossible(tileNumber);}

    // To tell the territory if it should respond to clicks.
    public void setActive(boolean active){
        for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gridSquares[x][y].setActive(active);
            }
        }
        revalidate();
    }

    public boolean isWithinBlock()
    {
        return false;
    }

    public boolean validateTilePlacement(int tileNumber, int orientation, int x1, int y1, int x2, int y2)
    {
        int[] bounds = territoryState.getBlockBounds();
        int terrain1 = 10;
        int terrain2 = 10;

        // Check if the squares are occupied
        if (territoryState.getSquare(x1, y1).getTerrain() != 0) {return false;}
        if (territoryState.getSquare(x2, y2).getTerrain() != 0) {return false;}

        // Select the domino tile data corresponding to the tile number provided
        for (TileData tileMapping : DominoTile.tileMappings)
        {
            if (tileMapping.tileNumber == tileNumber){terrain1 = tileMapping.t1; terrain2 = tileMapping.t2; break;}
        }

        // Check if there are mutual terrains in neighbouring tiles
        switch (orientation) {
            case 1 -> {
                // Check the left terrain
                if (x1 - 1 >= bounds[0]) {
                    if (territoryState.getSquare(x1 - 1, y1).getTerrain() == terrain1 || territoryState.getSquare(x1 - 1, y1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y1 - 1 >= bounds[1]) {
                    if (territoryState.getSquare(x1, y1 - 1).getTerrain() == terrain1 || territoryState.getSquare(x1, y1 - 1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y1 + 1 <= bounds[3]) {
                    if (territoryState.getSquare(x1, y1 + 1).getTerrain() == terrain1 || territoryState.getSquare(x1, y1+1).getTerrain() == 10) {
                        return true;
                    }
                }

                // Check the right terrain
                if (x2 + 1 <= bounds[2]) {
                    if (territoryState.getSquare(x2 + 1, y2).getTerrain() == terrain2 || territoryState.getSquare(x2 + 1, y2).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y2 - 1 >= bounds[1]) {
                    if (territoryState.getSquare(x2, y2 - 1).getTerrain() == terrain2 || territoryState.getSquare(x2, y2-1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y2 + 1 <= bounds[3]) {
                    if (territoryState.getSquare(x2, y2 + 1).getTerrain() == terrain2 || territoryState.getSquare(x2, y2+1).getTerrain() == 10) {
                        return true;
                    }
                }
            }
            case 2 -> {
                // Check the left terrain
                if (x1 - 1 >= bounds[0]) {
                    if (territoryState.getSquare(x1 - 1, y1).getTerrain() == terrain1 || territoryState.getSquare(x1 - 1, y1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y1 - 1 >= bounds[1]) {
                    if (territoryState.getSquare(x1, y1 - 1).getTerrain() == terrain1 || territoryState.getSquare(x1, y1 - 1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (x1 + 1 <= bounds[2]) {
                    if (territoryState.getSquare(x1 + 1, y1).getTerrain() == terrain1 || territoryState.getSquare(x1 + 1, y1).getTerrain() == 10) {
                        return true;
                    }
                }

                // Check the right terrain
                if (x2 + 1 <= bounds[2]) {
                    if (territoryState.getSquare(x2 + 1, y2).getTerrain() == terrain2 || territoryState.getSquare(x2 + 1, y2).getTerrain() == 10) {
                        return true;
                    }
                }
                if (x2 - 1 >= bounds[0]) {
                    if (territoryState.getSquare(x2 - 1, y2).getTerrain() == terrain2 || territoryState.getSquare(x2 - 1, y2).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y2 + 1 <= bounds[3]) {
                    if (territoryState.getSquare(x2, y2 + 1).getTerrain() == terrain2 || territoryState.getSquare(x2, y2+1).getTerrain() == 10) {
                        return true;
                    }
                }
            }
            case 3 -> {
                // Check the left terrain
                if (x1 + 1 <= bounds[2]) {
                    if (territoryState.getSquare(x1 + 1, y1).getTerrain() == terrain1 || territoryState.getSquare(x1 + 1, y1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y1 - 1 >= bounds[1]) {
                    if (territoryState.getSquare(x1, y1 - 1).getTerrain() == terrain1 || territoryState.getSquare(x1, y1 - 1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y1 + 1 <= bounds[3]) {
                    if (territoryState.getSquare(x1, y1 + 1).getTerrain() == terrain1 || territoryState.getSquare(x1, y1 + 1).getTerrain() == 10) {
                        return true;
                    }
                }

                // Check the right terrain
                if (x2 - 1 >= bounds[0]) {
                    if (territoryState.getSquare(x2 - 1, y2).getTerrain() == terrain2 || territoryState.getSquare(x2 - 1, y2).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y2 - 1 >= bounds[1]) {
                    if (territoryState.getSquare(x2, y2 - 1).getTerrain() == terrain2 || territoryState.getSquare(x2, y2 - 1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y2 + 1 <= bounds[3]) {
                    if (territoryState.getSquare(x2, y2 + 1).getTerrain() == terrain2 || territoryState.getSquare(x2, y2 + 1).getTerrain() == 10) {
                        return true;
                    }
                }
            }
            case 4 -> {
                // Check the left terrain
                if (x1 + 1 <= bounds[2]) {
                    if (territoryState.getSquare(x1 + 1, y1).getTerrain() == terrain1 || territoryState.getSquare(x1 + 1, y1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (x1 - 1 >= bounds[0]) {
                    if (territoryState.getSquare(x1 - 1, y1).getTerrain() == terrain1 || territoryState.getSquare(x1 - 1, y1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y1 + 1 <= bounds[3]) {
                    if (territoryState.getSquare(x1, y1 + 1).getTerrain() == terrain1 || territoryState.getSquare(x1, y1 + 1).getTerrain() == 10) {
                        return true;
                    }
                }

                // Check the right terrain
                if (x2 - 1 >= bounds[0]) {
                    if (territoryState.getSquare(x2 - 1, y2).getTerrain() == terrain2 || territoryState.getSquare(x2 - 1, y2).getTerrain() == 10) {
                        return true;
                    }
                }
                if (y2 - 1 >= bounds[1]) {
                    if (territoryState.getSquare(x2, y2 - 1).getTerrain() == terrain2 || territoryState.getSquare(x2, y2 - 1).getTerrain() == 10) {
                        return true;
                    }
                }
                if (x2 + 1 <= bounds[2]) {
                        if (territoryState.getSquare(x2 + 1, y2).getTerrain() == terrain2 || territoryState.getSquare(x2 + 1, y2).getTerrain() == 10) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Places a Domino tile in the player territory
    public void addTile(int x, int y)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        int x2, y2;
        x2 = x; y2 = y;
        switch(currentSlotOrientation){
            case 1 -> {
                x2 = x+1;
                if (!validateTilePlacement(currentSlot, currentSlotOrientation, x, y, x2, y2)) {return;};
                if (x+1>8){System.out.println("Too far bruh");return;}
                playerMap.remove(gridSquares[x][y]);
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = 2;
                gbc.gridheight = 1;
                playerMap.remove(gridSquares[x2][y2]);
            }
            case 2 -> {
                y2 = y+1;
                if (!validateTilePlacement(currentSlot, currentSlotOrientation, x, y, x2, y2)) {return;};
                if (y+1>8){System.out.println("Too far bruh");return;}
                playerMap.remove(gridSquares[x][y]);
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = 1;
                gbc.gridheight = 2;
                playerMap.remove(gridSquares[x2][y2]);
            }
            case 3 -> {
                x2 = x-1;
                if (!validateTilePlacement(currentSlot, currentSlotOrientation, x, y, x2, y2)) {return;};
                if (x-1>8){System.out.println("Too far bruh"); return;}
                playerMap.remove(gridSquares[x][y]);
                gbc.gridx = x-1;
                gbc.gridy = y;
                gbc.gridwidth = 2;
                gbc.gridheight = 1;
                playerMap.remove(gridSquares[x2][y2]);
            }
            case 4 -> {
                y2 = y-1;
                if (!validateTilePlacement(currentSlot, currentSlotOrientation, x, y, x2, y2)) {return;};
                if (y-1>8){System.out.println("Too far bruh"); return;}
                playerMap.remove(gridSquares[x][y]);
                gbc.gridx = x;
                gbc.gridy = y-1;
                gbc.gridwidth = 1;
                gbc.gridheight = 2;
                playerMap.remove(gridSquares[x2][y2]);
            }

        }

        setSquares(this.currentSlot, x, y, x2, y2);
        playerMap.add(new DominoTile(this.currentSlot, this.currentSlotOrientation, parent.getColorMode()).front, gbc);

        ((GameScreen) SwingUtilities.getWindowAncestor(this)).setGameStep();

        revalidate();
    }

    // Add a castle to the territory
    public void addCastle(int x, int y){
        JLabel castle = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("images/"+parent.getColorMode()+"/castles/castle.png"))));
        castle.setPreferredSize(new Dimension(15,15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        territoryState.getSquare(x, y).setState(10, 0);

        playerMap.remove(gridSquares[x][y]);
        playerMap.add(castle, gbc);
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
    {}

    public void setCurrentSlot(int slot, int orientation){this.currentSlot = slot; this.currentSlotOrientation = orientation;}

    
    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isComputerPlayer() {
        return computerPlayer;
    }
}