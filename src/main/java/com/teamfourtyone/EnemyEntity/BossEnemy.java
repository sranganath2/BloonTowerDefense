package com.teamfourtyone.EnemyEntity;

import com.teamfourtyone.GameController;

public class BossEnemy extends Enemy {
    private static String imageFile = "tree.png";

    public BossEnemy() {
        super(imageFile);
        setHealth(4000);
        setDamage(300);
    }

    @Override
    public void killEnemy() {
        super.killEnemy();
        GameController.setGameWin(true);
    }
}
