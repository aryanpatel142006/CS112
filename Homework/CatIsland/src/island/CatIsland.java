package island;

import island.constants.*;

/**
 * Welcome to Cat Island! A mini-world made using OOP!
 * Run the driver to see the islands, then solve each one in its
 * corresponding method below.
 * 
 * You should use the cat.moveRight(), cat.moveLeft(), cat.moveUp(),
 * and cat.moveDown() methods to traverse the islands and complete
 * their unique objectives
 * 
 * @author Aryan Patel
 */
public class CatIsland {

    /**
     * To complete this island, your cat should collect all the yarn and return
     * to the starting position (1,1)
     * 
     * @param cat
     * @throws CatInWaterException
     */
    public static void solveIsland1(Cat cat) throws CatInWaterException {
        cat.moveRight();
        cat.moveLeft();
        cat.moveDown();
        cat.moveUp();
    }

    /**
     * To complete this island, your cat visit every single grass square
     * on this island. It does not have to return to its starting position
     * 
     * @param cat
     * @throws CatInWaterException
     */
    public static void solveIsland2(Cat cat) throws CatInWaterException {
        for(int i = 0; i<3; i++){
        cat.moveRight();
        }
        cat.moveUp();
        for(int i = 0; i<4; i++){
            cat.moveLeft();
        }
        cat.moveDown();
        cat.moveDown();
        for(int i = 0; i<4; i++){
            cat.moveRight();
        }
    }

    /**
     * To complete this island, your cat should collect every yarn ball on the
     * island.
     * It does not have to return to its starting position
     * 
     * @param cat
     * @throws CatInWaterException
     */
    public static void solveIsland3(Cat cat) throws CatInWaterException {
        cat.moveRight(); cat.moveRight();
        cat.moveUp();
        cat.moveDown();cat.moveDown();
        cat.moveUp();
        for(int i = 0; i<3; i++){
            cat.moveLeft();
        }
        cat.moveUp();
        cat.moveDown();cat.moveDown();
        cat.moveLeft();
    }

    /**
     * To complete this island, you will need to modify the island
     * instead of moving a cat object.
     * 
     * You can get the 2D array of tiles from the island with .getTiles().
     * 
     * You should bridge the gap between the islands, by setting
     * the type attribute of the tiles at (1,2), (2,2), and (3,2)
     * equal to Tile.LAND.
     * 
     * You should also yarn to (1,2) and (3,2), by setting the "hasYarn"
     * attribute of those tiles to true.
     * 
     * @param cat
     * @throws CatInWaterException
     */
    public static void solveIsland4(Island islandFour) throws CatInWaterException {
        islandFour.getTiles()[1][2].type = Tile.LAND;
       
        islandFour.getTiles()[2][2].type = Tile.LAND;
       
        islandFour.getTiles()[3][2].type = Tile.LAND;
        islandFour.getTiles()[1][2].hasYarn = true;
       
       
        islandFour.getTiles()[3][2].hasYarn = true;
        Cat cat = new Cat("cat", islandFour, 2, 2, Color.BLACK);
    }

    /**
     * To complete this island, you will need to write a simple path following
     * algorithm, which will walk the cat along the single wide path.
     * 
     * This method is the caller method, which will call your recursive solution.
     * Do not modify this method
     * 
     * @param cat The path walker cat
     * @throws CatInWaterException
     */
    public static void solveIsland5(Cat cat, Island islandFive) throws CatInWaterException {
        // DO NOT EDIT
        CatIsland.solveIsland5Recursive(cat, islandFive.getTiles(), islandFive.getTiles()[cat.getRow()][cat.getCol()]);
    }

    /**
     * This recursive method walks a cat on a single wide path from beginning to end
     * 
     * You should check all four directions from the cats current position:
     * 1) if it is in bounds (>= 0 and <= num rows or num cols)
     * 2) if the type of the tile in that direction is land
     * 3) if the tile in that direction != the prev tile
     * If all 3, then move the cat in that direction, recursively call this method
     * 
     * @param c
     * @param tiles
     * @param prev
     * @throws CatInWaterException
     */
    public static void solveIsland5Recursive(Cat cat, Tile[][] tiles, Tile prev) throws CatInWaterException {
        if(cat.getRow() ==0 && cat.getCol() == 8){
            return;
        }
        else{
            if(cat.getRow() > 0 && tiles[cat.getRow()-1][cat.getCol()].type == Tile.LAND && tiles[cat.getRow()-1][cat.getCol()] != prev){
                cat.moveUp();
                
                solveIsland5Recursive(cat, tiles, tiles[cat.getRow()+1][cat.getCol()]);
            }
            else if(cat.getRow() < 8 && tiles[cat.getRow()+1][cat.getCol()].type == Tile.LAND && tiles[cat.getRow()+1][cat.getCol()] != prev){
               
               
                cat.moveDown();
                solveIsland5Recursive(cat, tiles, tiles[cat.getRow()-1][cat.getCol()]);
            }
            else if(cat.getCol() > 0 && tiles[cat.getRow()][cat.getCol()-1].type == Tile.LAND && tiles[cat.getRow()][cat.getCol()-1] != prev){
                
                
                cat.moveLeft();
                solveIsland5Recursive(cat, tiles, tiles[cat.getRow()][cat.getCol()+1]);
            }
            else if(cat.getCol() < 8 && tiles[cat.getRow()][cat.getCol()+1].type == Tile.LAND && tiles[cat.getRow()][cat.getCol()+1] != prev){
                
                cat.moveRight();
                
                
                solveIsland5Recursive(cat, tiles, tiles[cat.getRow()][cat.getCol()-1]);
            }
        }
    }

}
