package com.teamfourtyone.EnemyEntity;

public class DragonEnemy extends Enemy {
    private static String imageFile = "dragon.png";

    public DragonEnemy() {
        super(imageFile);
        setHealth(500);
        setDamage(100);
    }
}
