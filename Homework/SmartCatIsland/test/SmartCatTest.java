package test;

import static org.junit.Assert.*;
import org.junit.*;

import island.*;
import island.constants.Color;

/**
 * This is a JUnit test class, used to test code
 * 
 * You should test the SmartCat class by designing Island test cases
 * and filling in the JUnit tests according to the assignment
 * description.
 * 
 * @author Colin Sullivan
 */

public class SmartCatTest {

    // Define pathIsland for testWalkPath
    public static Island pathIsland = new Island(new String[][] {
            {"L", "L", "L", "L", "L", "L", "L"},
            {"W", "W", "L", "W", "L", "W", "L"},
            {"L", "L", "L", "L", "L", "L", "L"},
            {"L", "L", "W", "L", "W", "W", "L"},
            {"L", "L", "L", "L", "L", "L", "L"},
            {"W", "W", "L", "W", "L", "L", "W"},
            {"L", "L", "L", "L", "L", "L", "L"}
    });

    // Define yarnIsland for testCollectAllYarn
    public static Island yarnIsland = new Island(new String[][] {
            {"L", "Y", "L", "L", "L", "W", "Y"},
            {"W", "L", "W", "L", "W", "L", "Y"},
            {"L", "Y", "L", "L", "L", "L", "Y"},
            {"W", "W", "L", "Y", "L", "L", "L"},
            {"L", "L", "L", "W", "Y", "Y", "L"},
            {"L", "W", "L", "L", "L", "L", "W"},
            {"Y", "L", "L", "L", "L", "L", "Y"}
    });

    // Define mazeIsland for testSolveMaze
    public static Island mazeIsland = new Island(new String[][] {
            {"L", "L", "L", "L", "L", "W", "L"},
            {"W", "W", "L", "W", "L", "W", "L"},
            {"W", "L", "L", "L", "L", "L", "L"},
            {"W", "W", "W", "L", "W", "W", "L"},
            {"L", "L", "L", "L", "L", "L", "L"},
            {"W", "W", "W", "W", "L", "L", "W"},
            {"L", "L", "L", "L", "L", "L", "L"}
    });
    
    

    
    @Test
    public void testWalkPath() {
        // Set up a SmartCat on the path island (assuming pathIsland is initialized correctly)
        SmartCat smartCat = new SmartCat("TestCat", pathIsland, 0, 0, Color.BLACK);

        // Call walkPath to make the cat walk along the path
        smartCat.walkPath();


        assertNotEquals(0, smartCat.getRow()); // Example check: Assert that the cat has moved
        assertNotEquals(0, smartCat.getCol()); // Example check: Assert that the cat has moved
}


    @Test
    public void testCollectAllYarn() {
        // Set up a SmartCat on the yarn island (assuming yarnIsland is initialized correctly)
        SmartCat smartCat = new SmartCat("TestCat", yarnIsland, 0, 0, Color.BLACK);

        // Call collectAllYarn to make the cat collect all reachable yarn
        smartCat.collectAllYarn();

        // Assert that all yarn has been collected (for example, you could check if any yarn is still left on the island)
        Tile[][] tiles = yarnIsland.getTiles();
        boolean yarnLeft = false;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].hasYarn) {
                    yarnLeft = true;
                    break;
                }
            }
            if (yarnLeft) {
                break;
            }
        }
        assertFalse("There should be no yarn left to collect.", yarnLeft); // Assert no yarn left
    }


    @Test
public void testSolveMaze() {
    Tile[][] tiles = mazeIsland.getTiles();
    
    SmartCat testCat = new SmartCat("TestCat", mazeIsland, 0, 0, Color.BLACK);

    // Call solveMaze to make the cat attempt to solve the maze
    testCat.solveMaze();

    int expectedRow = 0; // This assumes that 6 is the correct goal row based on your maze layout.
    int expectedCol = tiles[0].length - 1; // This is the top-right corner column

    assertEquals("The cat should reach the expected row", expectedRow, testCat.getRow());
    assertEquals("The cat should reach the exit column", expectedCol, testCat.getCol());
}



    

}
