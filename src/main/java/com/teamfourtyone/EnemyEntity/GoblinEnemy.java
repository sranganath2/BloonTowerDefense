package com.teamfourtyone.EnemyEntity;

public class GoblinEnemy extends Enemy {
    private static String imageFile = "goblin.png";

    public GoblinEnemy() {
        super(imageFile);
        setHealth(50);
        setDamage(30);
    }
}
