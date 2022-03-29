import java.util.Arrays;

// A grid of numbers representing the various territory tile states
/* 0: Empty
   1: Yellow
   2: Forest
   3: Sea
   4: Grass
   5: Desert
   6: Rocky
   10: Occupied by a castle
 */
public class TerritoryState {
    private final SquareState [][] territory = new SquareState [9][9];
    public TerritoryState()
    {
        for ( int x = 0; x < 9; x ++)
        {
            for ( int y = 0; y < 9; y ++)
            {
                this.territory[x][y] = new SquareState(0, 0);
            }
        }
    }

    public SquareState getSquare(int x, int y)
    {
        return territory[x][y];
    }

    public boolean isPlayPossible(int tileNumber)
    {
        int terrain1 = 10;
        int terrain2 = 10;

        // Select the domino tile data corresponding to the tile number provided
        for (TileData tileMapping : DominoTile.tileMappings)
        {
            if (tileMapping.tileNumber == tileNumber){terrain1 = tileMapping.t1; terrain2 = tileMapping.t2; break;}
        }
        for ( int x = 0; x < 9; x ++)
        {
            for (int y = 0; y < 9; y++)
            {
                int terrain = territory[x][y].getTerrain();
                if (terrain != 0){continue;}
                if (x - 1 >= 0) {if (territory[x - 1][y].getTerrain() == terrain1 || territory[x - 1][y].getTerrain() == terrain2 || territory[x - 1][y].getTerrain() == 10) {return true;}}
                if (x + 1 <= 8) {if (territory[x + 1][y].getTerrain() == terrain1 || territory[x + 1][y].getTerrain() == terrain2 || territory[x + 1][y].getTerrain() == 10) {return true;}}
                if (y - 1 >= 0) {if (  territory[x][y-1].getTerrain() == terrain1 || territory[x][y-1].getTerrain() == terrain2   || territory[x][y-1].getTerrain() == 10) {return true;}}
                if (y + 1 <= 8) {if (  territory[x][y+1].getTerrain() == terrain1 || territory[x][y+1].getTerrain() == terrain2   || territory[x][y+1].getTerrain() == 10) {return true;}}
            }
        }
        return false;
    }

    public int[] getBlockBounds()
    {
        int[] bounds = new int[4];
        int minX = 8, minY = 8, maxX = 0, maxY = 0, xGap, yGap;
        for ( int x = 0; x < 9; x ++)
        {
            for ( int y = 0; y < 9; y ++)
            {
                if (this.territory[x][y].getTerrain() == 0){continue;}
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }
        }

        xGap = 4 - (maxX - minX);
        yGap = 4 - (maxY - minY);

        bounds[0] = Math.max(minX - xGap, 0);
        bounds[1] = Math.max(minY - yGap, 0);
        bounds[2] = Math.min(maxX + xGap, 8);
        bounds[3] = Math.min(maxY + yGap, 8);
        return bounds;
    }
}

// A structure to represent each grid square
class SquareState{
    private int crowns;
    private int terrain;

    public SquareState( int terrain, int crowns)
    {
        this.terrain = terrain;
        this.crowns = crowns;
    }

    public int getTerrain() {return terrain;}
    public int getCrowns()  {return crowns;}
    public void setState(int newTerrain, int newCrowns)  {terrain = newTerrain; crowns = newCrowns;}
}