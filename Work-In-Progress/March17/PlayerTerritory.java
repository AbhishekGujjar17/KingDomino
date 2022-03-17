import java.awt.*;
import java.awt.event.*;
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
    private boolean turn;
    private final GameScreen parent;
    private final JPanel playerMap;
    private final int rows;
    private final int columns;
    private String playerName;
    private final GridSquare [][] gridSquares;    // squares to appear in the kingdom panel
    private int currentSlot;
    private int currentSlotOrientation;
    


    public PlayerTerritory(String playerName, Color playerColour, boolean computerPlayers, GameScreen parent)
    {
        this.parent = parent;
        this.rows = 9;
        this.columns = 9;
        this.playerName = playerName;
        this.turn = false;

        //saveIcon = new ImageIcon(getClass().getResource("images/save.png"));
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
        this.setPreferredSize(new Dimension(460, 460));
        this.add(playerMap, BorderLayout.CENTER);

        //making the grid
        gridSquares = new GridSquare[rows][columns];
        Border blackline= BorderFactory.createLineBorder(Color.black,3);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        if (computerPlayers){


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

    // Places a Domino tile in the player territory
    public void addTile(int x, int y){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        switch(currentSlotOrientation){
            case 1 -> {
                if (x+1>8){System.out.println("Too far bruh");return;}
                playerMap.remove(gridSquares[x][y]);
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = 2;
                gbc.gridheight = 1;
                playerMap.remove(gridSquares[x+1][y]);
            }
            case 2 -> {
                if (y+1>8){System.out.println("Too far bruh");return;}
                playerMap.remove(gridSquares[x][y]);
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = 1;
                gbc.gridheight = 2;
                playerMap.remove(gridSquares[x][y+1]);
            }
            case 3 -> {
                if (x-1>8){System.out.println("Too far bruh"); return;}
                playerMap.remove(gridSquares[x][y]);
                gbc.gridx = x-1;
                gbc.gridy = y;
                gbc.gridwidth = 2;
                gbc.gridheight = 1;
                playerMap.remove(gridSquares[x-1][y]);
            }
            case 4 -> {
                if (y-1>8){System.out.println("Too far bruh"); return;}
                playerMap.remove(gridSquares[x][y]);
                gbc.gridx = x;
                gbc.gridy = y-1;
                gbc.gridwidth = 1;
                gbc.gridheight = 2;
                playerMap.remove(gridSquares[x][y-1]);
            }
        }
        ((GameScreen) SwingUtilities.getWindowAncestor(this)).setGameStep();

        playerMap.add(new DominoTile(this.currentSlot, this.currentSlotOrientation).front, gbc);
        revalidate();

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
}