import com.teamfourtyone.*;

import com.teamfourtyone.EnemyEntity.DragonEnemy;
import com.teamfourtyone.EnemyEntity.Enemy;
import com.teamfourtyone.EnemyEntity.GoblinEnemy;
import com.teamfourtyone.TowerEntity.AOETower;
import com.teamfourtyone.TowerEntity.DamageTower;
import com.teamfourtyone.TowerEntity.SingleShotTower;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AttackTest {
    private static int[][] path = new int[][]{new int[]{0, 0}, new int[]{1, 0},
        new int[]{1, 1}, new int[]{2, 1}, new int[]{2, 2}, new int[]{3, 2}};
    private static ArrayList<Enemy> enemyArray;
    private static ArrayList<DamageTower> damageTowerArray;

    @BeforeAll
    static void setUp() {
        TileController.clearTiles();
        TileController.setPathCoords(path);
        enemyArray = new ArrayList<>();
        damageTowerArray = new ArrayList<>();

        GoblinEnemy g1 = new GoblinEnemy();
        g1.setPosition(0);
        TileController.setTile(0, 0, g1);
        enemyArray.add(g1);

        GoblinEnemy g2 = new GoblinEnemy();
        g2.setPosition(3);
        TileController.setTile(2, 1, g2);
        enemyArray.add(g2);

        GoblinEnemy g3 = new GoblinEnemy();
        g3.setPosition(5);
        TileController.setTile(3, 2, g3);
        enemyArray.add(g3);

        SingleShotTower s1 = new SingleShotTower();
        s1.setPosition(0, 1);
        TileController.setTile(0, 1, s1);
        damageTowerArray.add(s1);

        SingleShotTower s2 = new SingleShotTower();
        s2.setPosition(10, 11);
        TileController.setTile(10, 11, s2);
        damageTowerArray.add(s2);
    }

    @Test
    void calculateRange() {
        assertEquals(1, damageTowerArray.get(0)
                .getDistance(0, 0), "Distance is incorrectly calculated");
    }

    @Test
    void getRangeTest() {
        assertEquals(4, damageTowerArray.get(0)
                .getRange(), "Range value is incorrect");
    }

    @Test
    void getCloseTargets() {
        ArrayList<Enemy> targets = (ArrayList<Enemy>) damageTowerArray.get(0)
                .findTargets(enemyArray);
        assertEquals(1, targets.size(), "Incorrect enemies detected");
    }

    @Test
    void getFarTargets() {
        ArrayList<Enemy> targets = (ArrayList<Enemy>) damageTowerArray.get(1)
                .findTargets(enemyArray);
        assertEquals(0, targets.size(), "Incorrect enemies detected");
    }

    @Test
    void enemyTakeDamage() {
        GoblinEnemy goblin = new GoblinEnemy();
        goblin.takeDamage(10);
        assertEquals(goblin.getHealth(), goblin.getMaxHealth()
                - 10, "Health incorrectly calculated");
    }

    @Test
    void testAttack() {
        ArrayList<Enemy> targets = new ArrayList<>();
        GoblinEnemy goblin = new GoblinEnemy();
        goblin.setPosition(0);
        TileController.setTile(0, 0, goblin);
        targets.add(goblin);

        damageTowerArray.get(0).attackEnemy(targets);
        assertTrue(goblin.getHealth() < goblin.getMaxHealth(), "Damage not inflicted");
    }

    @Test
    void getEnemyHealths() {
        Enemy e1 = new GoblinEnemy();
        Enemy e2 = new DragonEnemy();
        assertNotEquals(e1.getHealth(), e2.getHealth());
    }

    @Test
    void getEnemyDamages() {
        Enemy e1 = new GoblinEnemy();
        Enemy e2 = new DragonEnemy();
        assertNotEquals(e1.getDamage(), e2.getDamage());
    }

    @Test
    void getTowerRanges() {
        DamageTower t1 = new SingleShotTower();
        DamageTower t2 = new AOETower();
        assertNotEquals(t1.getRange(), t2.getRange());
    }

    @Test
    void getTowerDamages() {
        DamageTower t1 = new SingleShotTower();
        DamageTower t2 = new AOETower();
        assertNotEquals(t1.getBaseDamage(), t2.getBaseDamage());
    }
}



