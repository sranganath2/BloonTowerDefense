import com.teamfourtyone.TowerEntity.SingleShotTower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TowerTests {
    @Test
    void setCost() {
        SingleShotTower t = new SingleShotTower();
        assertEquals(t.setCost(100), true, "Failed to set cost");
    }

    @Test
    void setCostInvalid() {
        SingleShotTower t = new SingleShotTower();
        assertEquals(t.setCost(-100), false, "Set invalid cost");
    }

    @Test
    void setDescription() {
        SingleShotTower t = new SingleShotTower();
        assertEquals(t.setDescription("Desc"), true, "Failed to set description");
    }

    @Test
    void setUpgradeDamage() {
        SingleShotTower t = new SingleShotTower();
	double a = t.getBaseDamage();
	t.upgradeDamage();
	assertEquals(t.getBaseDamage()>a, true, "Upgrade damage invalid");
    }

    @Test
    void setUpgradeRange() {
        SingleShotTower t = new SingleShotTower();
	double a = t.getRange();
	t.upgradeRange();
	assertEquals(t.getRange()>a, true, "Upgrade range invalid");
    }

    @Test
    void setDamageUpgrades() {
        SingleShotTower t = new SingleShotTower();
	t.upgradeRange();
	assertEquals(t.getNumUpgrades(), 1, "Upgrade damage uncounted");
    }

    @Test
    void setRangeUpgrades() {
        SingleShotTower t = new SingleShotTower();
	t.upgradeRange();
	assertEquals(t.getNumUpgrades(), 1, "Upgrade range uncounted");
    }

    @Test
    void getNumUpgrades() {
        SingleShotTower t = new SingleShotTower();
	assertEquals(t.getNumUpgrades(), 0, "Num Upgrades Set too Early");
    }

    @Test
    void resetCost() {
        SingleShotTower t = new SingleShotTower();
	t.setCost(20);
	assertEquals(t.setCost(12),true, "Set cost invalid");
    }

}
