import com.teamfourtyone.*;

import com.teamfourtyone.EnemyEntity.Enemy;
import com.teamfourtyone.EnemyEntity.GoblinEnemy;
import com.teamfourtyone.EnemyEntity.DragonEnemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EnemyTests {
    @Test
    void testAddEnemy() {
        TileController.clearTiles();
        TileController.placeTile(0, 0, new GoblinEnemy());
        assertEquals(TileController.isEmpty(0, 0), false, "Enemy Not Properly Added");
    }

    @Test
    void testRemoveEnemy() {
        TileController.clearTiles();
        TileController.placeTile(0, 0, new GoblinEnemy());
        assertEquals(TileController.removeTile(0, 0), true, "Enemy Not Properly Removed");
    }

    @Test
    void testGetEnemy() {
        TileController.clearTiles();
        Enemy goblin = new GoblinEnemy();
        TileController.placeTile(0, 0, goblin);
        assertEquals(TileController.getTile(0, 0), goblin, "Enemy Not Properly Removed");
    }

    @Test
    void swapOutEnemy() {
        TileController.clearTiles();
        GoblinEnemy g1 = new GoblinEnemy();
        GoblinEnemy g2 = new GoblinEnemy();
        Path p = new Path();
        TileController.placeTile(0, 0, g1);
        TileController.placeTile(0, 0, g2);
        assertEquals(TileController.getTile(0, 0), g2, "Enemy Not Properly Swapped");
    }

    @Test
    void setEnemyHealth() {
        GoblinEnemy g = new GoblinEnemy();
        g.setHealth(101);
        assertEquals(g.getHealth(), 101, "Enemy Has Incorrecty Set Health Function");
    }

    @Test
    void placeEnemyGrid() {
        TileController.clearTiles();
        GoblinEnemy g = new GoblinEnemy();
        TileController.placeTile(1, 1, g);
        assertEquals(TileController.getTile(1, 1), g);
    }

    @Test
    void moveEnemy() {
        TileController.clearTiles();
        GoblinEnemy g = new GoblinEnemy();
        TileController.placeTile(1, 1, g);
        Tileable t = TileController.getTile(1, 1);
        TileController.placeTile(2, 2, t);

        assertEquals(TileController.getTile(2, 2), g);
    }

    @Test
    void removeEnemyGrid() {
        TileController.clearTiles();
        GoblinEnemy g = new GoblinEnemy();
        TileController.placeTile(2, 2, g);
        assertEquals(TileController.getTile(2, 2), g);
        TileController.removeTile(2, 2);
        assertNull(TileController.getTile(2, 2));
    }

    @Test
    void checkMaxHealth() {
   	DragonEnemy d = new DragonEnemy();	 
	assertEquals(d.getMaxHealth(),500,"Dragon Health not correct");
    }
    
    @Test
    void checkHealth() {
   	DragonEnemy d = new DragonEnemy();	 
	assertEquals(d.getMaxHealth(),d.getHealth(),"Dragon Health Not Set to Full");
    }

    @Test
    void checkDamage() {
   	DragonEnemy d = new DragonEnemy();	 
	assertEquals(d.getDamage(),100,"Dragon Damage not correct");
    }

    @Test 
    void checkImage() {
   	DragonEnemy d = new DragonEnemy();	 
	assertEquals(d.getTileImage()==null,false,"Dragon Image not present");
    }
}


