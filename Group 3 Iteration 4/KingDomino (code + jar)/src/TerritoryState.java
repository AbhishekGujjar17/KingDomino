import java.io.Serializable;
import java.util.*;

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

class TilePlacement implements Serializable{
    public int x;
    public int y;
    public int orientation;
    public int tileNumber;
    public TilePlacement(int x, int y, int orientation, int tileNumber){
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.tileNumber = tileNumber;
    }
}

class Position implements Serializable {
    private int x;
    private int y;
    public Position (int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}
}

public class TerritoryState implements Serializable {
    public Position castlePlacement;
    public List<TilePlacement> tilePlacements = new ArrayList<>();
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

    public int getScore()
    {
        int[][] territoryTerrains = new int[9][9];
        int score = 0;
        for ( int x = 0; x < 9; x ++) {for (int y = 0; y < 9; y++) {territoryTerrains[x][y] = territory[x][y].getTerrain();}}
        for ( int x = 0; x < 9; x ++)
        {
            for (int y = 0; y < 9; y++) {
                List<Position> block = new ArrayList<>();
                if (territory[x][y].getTerrain() !=0){
                    crawl(x, y, block, territoryTerrains);
                    score += calculateScore(block);
                }
            }

        }
        return score;
    }

    public void crawl(int x, int y, List<Position> block, int[][] territory)
    {
        block.add(new Position(x, y));
        int current = territory[x][y];
        if (current == 0){return;}
        territory[x][y] = 0;
        if (x-1 >=0) {if (territory[x-1][y] == current){crawl(x-1, y, block, territory);}}
        if (x+1 <=8) {if (territory[x+1][y] == current){crawl(x+1, y, block, territory);}}
        if (y-1 >=0) {if (territory[x][y-1] == current){crawl(x, y-1, block, territory);}}
        if (y+1 <=8) {if (territory[x][y+1] == current){crawl(x, y+1, block, territory);}}

    }

    public int calculateScore(List<Position> block){
        int score = 0;
        int crowns = 0;
        for (Position position: block){
            SquareState square = this.getSquare(position.getX(), position.getY());
            crowns += square.getCrowns();
            score += 1;
        }
        return score * crowns;
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
class SquareState implements Serializable{
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