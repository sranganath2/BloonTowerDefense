package com.teamfourtyone.TowerEntity;

import com.teamfourtyone.EnemyEntity.Enemy;
import com.teamfourtyone.TileController;

import java.util.ArrayList;
import java.util.List;

public class SingleShotTower extends DamageTower {
    public static final String TOWER_DESCRIPTION = "Basic Tower that shoots once at high damage.";
    private static String imageFile = "sniper_tower.png";

    public SingleShotTower() {
        super(imageFile);
        setCost(16); // Sets the basic cost for normal difficulty
        setDescription("Basic Tower that shoots once at low damage.");
        setBaseDamage(25);
        setRange(4);
    }

    @Override
    public List<Enemy> findTargets(List<Enemy> enemyArray) {
        ArrayList<Enemy> targetList = new ArrayList<>();
        Enemy target = null;
        double targetDist = 0;

        for (Enemy enemy : enemyArray) {
            int[] enemyPos = TileController.getPath()[enemy.getPosition()];
            double enemyDist = getDistance(enemyPos[0], enemyPos[1]);
            if (enemyDist <= getRange() && (target == null || enemyDist < targetDist
                    || (enemyDist == targetDist && enemy.getPosition() > target.getPosition()))) {
                target = enemy;
                targetDist = enemyDist;
            }
        }
        if (target != null) {
            targetList.add(target);
        }
        return targetList;
    }
}
