package com.teamfourtyone.EnemyEntity;

import com.teamfourtyone.*;

public abstract class Enemy extends Tileable {
    private int health;
    private int maxHealth = 0;
    private int position = 0;
    private int damage;
    private static int counter = 0;
    private static final double SCALING_FACTOR = 7;

    public Enemy(String imageID) {
        super("enemyicons/" + imageID);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
        if (newHealth > maxHealth) {
            maxHealth = newHealth;
        }
    }

    public void takeDamage(int damageTaken) {
        health -= damageTaken;
        Statistics.updateDamageDealt(damageTaken);
        if (health <= 0) {
            killEnemy();
        }
    }

    public int getPosition() {
        return position;
    }

    public void incPosition() {
        position += 1;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void killEnemy() {
        int[] enemyPosition;
        if (position < TileController.getPath().length) {
            enemyPosition = TileController.getPath()[position];
            Path newPath = new Path();
            TileController.placeTile(enemyPosition[0], enemyPosition[1], newPath);
        }

        GameController.getView().setMoney(GameController.getView().getMoney()
                + (int) (damageFunction() * (SCALING_FACTOR - App.getDifficulty() * 2)));
        GameController.getEnemyArray().remove(this);
        GameController.changeHealthModifier(damageFunction());
        Statistics.updateEnemiesKilled();
        counter++;
    }

    private double damageFunction() {
        return 10 * Math.pow(Math.E, 0.2331 * counter - 5)
                / (0.19196 + Math.pow(Math.E, 0.2331 * counter - 5));
    }

    public static int getCounter() {
        return counter;
    }

    public static void resetCounter() {
        counter = 0;
    }
}
