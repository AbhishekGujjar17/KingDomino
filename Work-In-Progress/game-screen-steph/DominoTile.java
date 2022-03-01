import javax.swing.*;
import java.awt.*;
import java.util.Objects;

// A representation of the tiles data consisting of the tile types and crowns within it.
class TileData{
    int tileNumber;
    int crowns;
    // t1 and t2 are the terrain types with integers to represent them.
    // 1 = yellow, 2 = forest, 3 = sea, 4 = grass, 5 = dessert, 6 = rocky
    int t1;
    int t2;
    public TileData(int tileNumber, int crowns, int t1, int t2){
        this.tileNumber = tileNumber;
        this.crowns = crowns;
        this.t1 = t1;
        this.t2 = t2;
    }
}

public class DominoTile{
    // This shows all the data for the tiles and when a new object is created it uses this to set its values
    private static TileData[] tileMappings = {
            new TileData(1, 0, 1, 1),
            new TileData(2, 0, 1, 1),
            new TileData(3, 0, 2, 2),
            new TileData(4, 0, 2, 2),
            new TileData(5, 0, 2, 2),
            new TileData(6, 0, 2, 2),
            new TileData(7, 0, 3, 3),
            new TileData(8, 0, 3, 3),
            new TileData(9, 0, 3, 3),
            new TileData(10, 0, 4, 4),
            new TileData(11, 0, 4, 4),
            new TileData(12, 0, 5, 5),
            new TileData(13, 0, 1, 2),
            new TileData(14, 0, 1, 3),
            new TileData(15, 0, 1, 4),
            new TileData(16, 0, 1, 5),
            new TileData(17, 0, 2, 3),
            new TileData(18, 0, 2, 4),
            new TileData(19, 1, 1, 2),
            new TileData(20, 1, 1, 3),
            new TileData(21, 1, 1, 4),
            new TileData(22, 1, 1, 5),
            new TileData(23, 1, 1, 6),
            new TileData(24, 1, 2, 1),
            new TileData(25, 1, 2, 1),
            new TileData(26, 1, 2, 1),
            new TileData(27, 1, 2, 1),
            new TileData(28, 1, 2, 3),
            new TileData(29, 1, 2, 4),
            new TileData(30, 1, 3, 1),
            new TileData(31, 1, 3, 1),
            new TileData(32, 1, 3, 2),
            new TileData(33, 1, 3, 2),
            new TileData(34, 1, 3, 2),
            new TileData(35, 1, 3, 2),
            new TileData(36, 1, 1, 4),
            new TileData(37, 1, 3, 4),
            new TileData(38, 1, 1, 5),
            new TileData(39, 1, 4, 5),
            new TileData(40, 1, 5, 1),
            new TileData(41, 2, 1, 4),
            new TileData(42, 2, 3, 4),
            new TileData(43, 2, 1, 5),
            new TileData(44, 2, 4, 5),
            new TileData(45, 2, 6, 1),
            new TileData(46, 2, 5, 6),
            new TileData(47, 2, 5, 6),
            new TileData(48, 3, 1, 6)
    };
    public JLabel front, back; // The two sides of the tile.
    private String frontDir, backDir;  // location of the pictures of the tiles front and back in the directory.

    // The number on the tile, size of the tile, the number of crowns and the orientation of the tile.
    // The size will determine how large the picture is, there will be two sizes for larger and smaller screens.
    // The orientation ranges from 1 to 4 with a 90-degree rotation increasing from 1 to 4.
    private int number, t1, t2, size, crowns, orientation;

    public DominoTile(int tileNumber)
    {
        // Check the tile mappings for the tile trying to be created and set the data accordingly
        for (int i = 0; i < tileMappings.length; i++) {
            if (tileMappings[i].tileNumber == tileNumber){
                number = tileNumber;
                crowns = tileMappings[i].crowns;
                t1 = tileMappings[i].t1;
                t2 = tileMappings[i].t2;
                break;
            }
        }

        frontDir = "d" + tileNumber + ".png";
        backDir = "" + tileNumber + ".png";

        // Load the pictures of the front and back.
        front = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource(frontDir))));
        front.setPreferredSize(new Dimension(25,25));
        back = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource(backDir))));
        back.setPreferredSize(new Dimension(25,25));
    }

    public void setOrientation(int newOrientation)  { orientation = newOrientation; }
    public void setSize(int newSize)                { orientation = newSize; }
    public JLabel getFront()                        { return front; }
    public JLabel getBack()                         { return back; }
    public int getCrowns()                          { return crowns; }
    public int getTerrain1()                        { return t1; }
    public int getTerrain2()                        { return t2; }
    public int getOrientation()                     { return orientation; }
    public int getSize()                            { return size; }
}