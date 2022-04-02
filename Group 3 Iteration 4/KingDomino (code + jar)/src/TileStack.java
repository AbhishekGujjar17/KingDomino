import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

// A list of four slots for each domino
class TileStack extends JPanel implements MouseListener {
    public Kingdom parent;
    private StackSlot slot1;
    private StackSlot slot2;
    private StackSlot slot3;
    private StackSlot slot4;
    JPanel slots;
    private GridBagConstraints gbc = new GridBagConstraints();
    private List<Integer> kings;
    private List<StackSlot> slotList = new ArrayList<>();
    public boolean isEmpty = true;

    public TileStack(Kingdom parent) {
        this.parent = parent;
        gbc.fill = GridBagConstraints.BOTH;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel currentTileLabel= new JLabel("Current Domino Tiles");
        currentTileLabel.setForeground(Color.black);

        slots= new JPanel();
        slots.setBorder(BorderFactory. createLineBorder(Color. black));
        slots.setLayout(new GridLayout(4,1));

        slot1 = new StackSlot();
        slot1.addMouseListener(new TileListener(slot1));

        slot2 = new StackSlot();
        slot2.addMouseListener(new TileListener(slot2));

        slot3 = new StackSlot();
        slot3.addMouseListener(new TileListener(slot3));

        slot4 = new StackSlot();
        slot4.addMouseListener(new TileListener(slot4));

        slots.add(slot1);
        slots.add(slot2);
        slots.add(slot3);
        slots.add(slot4);

        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        this.add(currentTileLabel);
        this.add(slots);
    }

    public List<StackSlot> getSlotList(){return slotList;}

    // Gets a list containing the player numbers in order of their tile selections in this stack.
    public List<Integer> getkingQueue(){
        List<Integer> kingQueue = new ArrayList<>();
        for (TileStack.StackSlot slot: parent.currentStack.getSlotList()){
            kingQueue.add(slot.getOwnerNumber());
        }
        return kingQueue;
    }

    // Sets a specified slots Domino tile.
    public void setSlotTile(int tileNumber, int slotNumber){
        gbc.weightx = 1;
        gbc.weighty = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;

        switch (slotNumber) {
            case 1 -> {
                slot1.setTile(new DominoTile(tileNumber, 1, parent.getColorMode()));
            }
            case 2 -> {
                slot2.setTile(new DominoTile(tileNumber, 1, parent.getColorMode()));
            }
            case 3 -> {
                slot3.setTile(new DominoTile(tileNumber, 1, parent.getColorMode()));
            }
            case 4 -> {
                slot4.setTile(new DominoTile(tileNumber, 1, parent.getColorMode()));
            }
        }
    }

    public void setKings(List<Integer> kings){
        this.kings = kings;
    }

    // Removes the top most Domino tile from the stack
    public void removeSelection(){
        for (StackSlot slot: parent.turnQueue){
            if (slot.isChosen()){
                slot.reset(); break;}
        }
        parent.turnQueue.remove(0);
    }

    public void restore(List<StackSlot> restoreSlots){
        this.remove(slots);
        slots= new JPanel();
        slots.setBorder(BorderFactory. createLineBorder(Color. black));
        slots.setLayout(new GridLayout(4,1));
        slotList.clear();

        slot1 = restoreSlots.get(0);
        slot1.addMouseListener(new TileListener(slot1));
        slot2 = restoreSlots.get(1);
        slot2.addMouseListener(new TileListener(slot2));
        slot3 = restoreSlots.get(2);
        slot3.addMouseListener(new TileListener(slot3));
        slot4 = restoreSlots.get(3);
        slot4.addMouseListener(new TileListener(slot4));

        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slotList.add(slot4);

        slots.add(slot1);
        slots.add(slot2);
        slots.add(slot3);
        slots.add(slot4);

        this.add(slots);
        revalidate();
        repaint();
    }

    public boolean isKingPresent(){return kings.isEmpty();}

    // A single slot in a domino stack.
    class StackSlot extends JPanel implements Serializable {
        public DominoTile tile;
        private String owner;
        private int ownerNumber;
        private JPanel kingDisplay;
        private boolean chosen = false;
        private boolean isActive = true;
        private final JPanel tilePlaceHolder = new JPanel();
        private final JPanel meeplePlaceHolder = new JPanel();

        public StackSlot(){
            setLayout(new GridBagLayout());
            setBorder(BorderFactory. createLineBorder(Color. black));

            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 8;
            gbc.gridx = 0;
            gbc.gridy = 0;

            add(tilePlaceHolder, gbc);

            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;

            add(meeplePlaceHolder, gbc);

            gbc.gridy = 2;

            JButton rotateButton = new JButton();
            rotateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!isActive){return;}
                    tile.rotate();
                    revalidate();
                }
            });

            JPanel controls = new JPanel();
            controls.setBackground(new Color(1,1,1));
            controls.add(new JLabel("Rotate"));
            controls.add(rotateButton);
            add(controls, gbc);
            revalidate();
        }

        // Removes both the Domino tile and the selection made by a player to the slot.
        public void reset(){
            System.out.println(this.getComponents().length);
            remove(this.tile.front);
            remove(this.kingDisplay);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 8;
            gbc.gridx = 0;
            gbc.gridy = 0;

            add(tilePlaceHolder, gbc);

            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;

            add(meeplePlaceHolder, gbc);
            revalidate();
            repaint();
        }

        // Put a domino tile in a slot.
        public void setTile(DominoTile tile) {
            remove(tilePlaceHolder);
            if (this.tile != null){remove(this.tile.front);}
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridy = 0;
            gbc.weighty = 8;
            this.tile = tile;
            add(this.tile.front, gbc);
            revalidate();
            isEmpty = false;
        }

        public int getTileNumber(){return tile.getNumber();}

        public boolean isChosen(){return chosen;}
        public void setChosen(boolean newC){chosen = newC;}

        // Display the name and meeple of the king who owns the tile.
        private void addMeeple(){
            remove(meeplePlaceHolder);
            if (!kings.isEmpty()){
                int kingNumber = kings.get(0);
                String kingName = parent.getPlayerNames().get(kingNumber-1);
                setMeeple(kingName, kingNumber);

                this.ownerNumber = kingNumber;
                this.owner = kingName;

                kings.remove(0);
                if (kings.isEmpty()) {parent.setGameStep();}
            }}

        public void setMeeple(String kingName, int kingNumber){
            remove(meeplePlaceHolder);
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;

            kingDisplay = new JPanel();
            kingDisplay.setBackground(new Color(255,1,255));
            kingDisplay.add(new JLabel("King " + kingName));
            kingDisplay.add(new KingMeeple(kingNumber, kingName).king);
            add(kingDisplay, gbc);
            revalidate();
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String newOwner) {
            owner = newOwner;
        }

        public void setOwnerNumber(int newOwnerNumber) {
            ownerNumber = newOwnerNumber;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public int getOwnerNumber() {
            return ownerNumber;
        }
    }


    // A mouse listener for the tiles.
    private class TileListener implements MouseListener{
        StackSlot slot;
        public TileListener(StackSlot slot){
            this.slot = slot;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("hmsaa");
            if (!slot.isActive()){return;}
            System.out.println("hmsa");
            if (!slot.isChosen()){
                System.out.println("hms");
                slot.addMeeple();
                slot.setChosen(true);
                parent.setGameStep();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }



    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
