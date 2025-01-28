package com.teamfourtyone.EnemyEntity;

public class GlassBombEnemy extends Enemy {
    private static String imageFile = "glassbomb.png";

    public GlassBombEnemy() {
        super(imageFile);
        setHealth(5);
        setDamage(300);
    }

    @Override
    public void takeDamage(int damageTaken) {
        super.takeDamage(1);
    }
}
