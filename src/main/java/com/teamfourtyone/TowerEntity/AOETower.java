package com.teamfourtyone.TowerEntity;

import com.teamfourtyone.*;
import com.teamfourtyone.EnemyEntity.Enemy;

import java.util.ArrayList;
import java.util.List;

public class AOETower extends DamageTower {
    public static final String TOWER_DESCRIPTION = "Tower that hits many at low damage.";
    private static String imageFile = "cannon_tower.png";

    public AOETower() {
        super(imageFile);
        setCost(20); // Sets the basic cost for normal difficulty
        setDescription("Tower that hits many at low damage.");
        setBaseDamage(20);
        setRange(3);
    }

    @Override
    public List<Enemy> findTargets(List<Enemy> enemyArray) {
        List<Enemy> targetList = new ArrayList<>();
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
            int[] targetPos = TileController.getPath()[target.getPosition()];
            for (int r = targetPos[0] - 1; r <= targetPos[0] + 1; r++) {
                for (int c = targetPos[1] - 1; c <= targetPos[1] + 1; c++) {
                    if (TileController.getTile(r, c) instanceof Enemy) {
                        targetList.add((Enemy) TileController.getTile(r, c));
                    }
                }
            }
        }

        return targetList;
    }
}
