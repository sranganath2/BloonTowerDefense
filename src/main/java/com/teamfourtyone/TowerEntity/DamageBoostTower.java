package com.teamfourtyone.TowerEntity;

import com.teamfourtyone.GameController;

import java.util.ArrayList;

public class DamageBoostTower extends SupportTower {
    public static final String TOWER_DESCRIPTION =
            "Tower that increases the damage of nearby towers.";
    private static String imageFile = "damage_supporttower.png";

    public DamageBoostTower() {
        super(imageFile);
        setCost(40); // Sets the basic cost for normal difficulty
        setDescription("Tower that increases the damage of nearby towers.");

        setBoost(1.1);
        setRange(5);
    }

    public ArrayList<DamageTower> findTargets() {
        ArrayList<DamageTower> targetList = new ArrayList<DamageTower>();
        ArrayList<DamageTower> towerList = GameController.getDamageTowerArray();

        for (DamageTower dmgTower : towerList) {
            if (getDistance(dmgTower.getPosition()[0], dmgTower.getPosition()[1]) <= getRange()) {
                targetList.add(dmgTower);
            }
        }

        return targetList;
    }

    public void grantSupport() {
        ArrayList<DamageTower> targetTowers = findTargets();
        for (DamageTower dmgTower : targetTowers) {
            if (dmgTower != null) {
                dmgTower.setDamage(dmgTower.getDamage() * getBoost());
            }
        }
    }

    public void removeSupport() {
        ArrayList<DamageTower> targetTowers = findTargets();
        for (DamageTower dmgTower : targetTowers) {
            dmgTower.setBaseDamage((int) (dmgTower.getBaseDamage() / getBoost()));
        }
    }
}
