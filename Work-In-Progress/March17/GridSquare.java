import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


/*
 *  A GUI component
 *
 *  
 *
 *  @author Apurva Acharya
 */
public class GridSquare extends JPanel implements MouseListener
{
    private int xcoord, ycoord;  // location in the grid
    private boolean isActive = true;
    private final GameScreen parent;

    // constructor takes the x and y coordinates of this square
    public GridSquare(int xcoord, int ycoord, GameScreen parent)
    {
    super();
    this.parent = parent;
    this.xcoord = xcoord;
    this.ycoord = ycoord;
    addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isActive){return;}
        parent.setTerritoryTiles();
        ((PlayerTerritory) getParent().getParent()).addTile(xcoord, ycoord);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isActive){return;}
        setBackground(Color.lightGray);
        revalidate();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isActive){return;}
        setBackground(Color.white);
        revalidate();
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
