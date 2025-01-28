import com.teamfourtyone.*;
import com.teamfourtyone.TowerEntity.AOETower;
import com.teamfourtyone.TowerEntity.DamageBoostTower;
import com.teamfourtyone.TowerEntity.DamageTower;
import com.teamfourtyone.TowerEntity.SingleShotTower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GridTests {

    @Test
    void setPathTest() {
        TileController.clearTiles();
        int[][] pathCoords = {{0, 0}, {0, 1}, {1, 1}, {1, 2}, {1, 3}, {1, 4}};
        TileController.setPathCoords(pathCoords);
        String message = "TileController mis-set the pathCoords.";
        assertEquals(TileController.getPath(), pathCoords, message);

        message = "TileController incorrectly placed the path.";
        assertEquals(TileController.getTile(1, 3) instanceof Path, true, message);
    }

    @Test
    void placeTowerTest() {
        TileController.clearTiles();
        TileController.placeTile(1, 1, new SingleShotTower());
        TileController.placeTile(2, 2, new DamageBoostTower());
        TileController.placeTile(3, 3, new AOETower());

        String message = "TileController incorrectly placed the tower.";
        assertEquals(TileController.getTile(1, 1) instanceof DamageTower,
                true, message);
        assertEquals(TileController.getTile(2, 2) instanceof DamageBoostTower,
                true, message);
        assertEquals(TileController.getTile(3, 3) instanceof AOETower,
                true, message);
    }

    @Test
    void removeTowerTest() {
        TileController.clearTiles();
        TileController.placeTile(1, 1, new SingleShotTower());
        TileController.placeTile(2, 2, new DamageBoostTower());
        String message = "TileController failed to remove the tower.";
        assertEquals(TileController.removeTile(1, 1), true, message);
        assertEquals(TileController.removeTile(2, 2), true, message);
        assertEquals(TileController.removeTile(3, 3), true, message);
    }

    @Test
    void boundaryTest() {
        TileController.clearTiles();
        String message = "TileController failed to add tower.";
        assertEquals(TileController.placeTile(100, 100, new SingleShotTower()), false, message);
    }

    @Test
    void checkClearingTest() {
        TileController.clearTiles();
        Tileable[][] grid = TileController.getTileGrid();
        String message = "There is a non-null element in the empty grid";
        for (Tileable[] row : grid) {
            for (Tileable t : row) {
                assertEquals(t, null, message);
            }
        }
    }

    @Test
    void setInvalidPathTest() {
        TileController.clearTiles();
        int[][] cleared = {{}};
        String message = "The path is set invalidly";
        assertEquals(TileController.setPathCoords(cleared), false, message);
    }
}
