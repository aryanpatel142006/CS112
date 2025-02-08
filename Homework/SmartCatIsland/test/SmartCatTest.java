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


    public static Island pathIsland = new Island(new String[][] {
        { "L", "L", "W", "L", "L", "L", "L", "W", "L" },
        { "W", "L", "W", "L", "W", "W", "L", "W", "L" },
        { "L", "L", "W", "L", "W", "L", "L", "W", "L" },
        { "L", "W", "L", "L", "W", "L", "W", "L", "L" },
        { "L", "W", "L", "W", "W", "L", "W", "L", "W" },
        { "L", "W", "L", "L", "W", "L", "W", "L", "L" },
        { "L", "L", "W", "L", "W", "L", "W", "W", "L" },
        { "W", "L", "W", "L", "W", "L", "W", "L", "L" },
        { "W", "L", "L", "L", "W", "L", "L", "L", "W" }
    });


    public static Island yarnIsland = new Island(new String[][] {
        { "L", "L", "W", "L", "Y", "L", "L", "W", "Y" },
        { "W", "L", "W", "L", "W", "W", "L", "W", "L" },
        { "L", "L", "W", "Y", "W", "L", "L", "W", "L" },
        { "L", "W", "L", "L", "Y", "Y", "W", "L", "L" },
        { "Y", "W", "L", "W", "W", "L", "W", "Y", "W" },
        { "L", "W", "L", "L", "W", "L", "W", "L", "L" },
        { "L", "L", "W", "Y", "W", "L", "W", "W", "L" },
        { "W", "L", "W", "L", "W", "L", "W", "L", "Y" },
        { "W", "Y", "L", "L", "W", "L", "L", "L", "W" }
        
    });


    public static Island mazeIsland = new Island(new String[][] {
        { "L", "L", "W", "W", "W", "W", "W", "W", "W", "L" },
        { "W", "L", "W", "L", "L", "L", "L", "W", "W", "L" },
        { "W", "L", "W", "L", "W", "W", "L", "W", "L", "L" },
        { "W", "L", "W", "L", "W", "L", "L", "W", "L", "L" },
        { "W", "L", "W", "L", "W", "L", "L", "W", "L", "L" },
        { "W", "L", "W", "L", "W", "W", "L", "W", "W", "L" },
        { "W", "L", "W", "L", "W", "L", "L", "L", "W", "L" },
        { "L", "L", "W", "L", "W", "W", "W", "L", "W", "L" },
        { "L", "L", "W", "L", "W", "L", "L", "L", "L", "L" },
        { "L", "L", "L", "L", "W", "L", "W", "L", "L", "L" }
        
    });

    @Test
    public void testWalkPath() {

        SmartCat smartCat = new SmartCat("TestCat", pathIsland, 0, 0, Color.BLACK);


        smartCat.walkPath();


        assertEquals(0, smartCat.getRow()); 
        assertEquals(8, smartCat.getCol()); 

    }

    @Test
    public void testCollectAllYarn() {
        // Set up a SmartCat on the yarn island
        SmartCat smartCat = new SmartCat("TestCat", yarnIsland, 0, 0, Color.BLACK);


        smartCat.collectAllYarn();


        Tile[][] tiles = yarnIsland.getTiles();
        boolean yarnLeft = false;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].hasYarn) {
                    yarnLeft = true;
                    break;
                }
            }
            if (yarnLeft) {
                break;
            }
        }
        assertFalse("There should be no yarn left to collect.", yarnLeft);
    }

    @Test
    public void testSolveMaze() {
        Tile[][] tiles = mazeIsland.getTiles();
        
        SmartCat testCat = new SmartCat("TestCat", mazeIsland, 0, 0, Color.BLACK);


        testCat.solveMaze();

        int expectedRow = 0;
        int expectedCol = tiles[0].length - 1; 

        assertEquals(expectedRow, testCat.getRow());
        assertEquals(expectedCol, testCat.getCol());
        assertTrue(testCat.numStepsTaken() >= 30);
}
    }

