package com.teamfourtyone.TowerEntity;

import com.teamfourtyone.EnemyEntity.Enemy;

import java.util.List;

public abstract class DamageTower extends Tower {
    private double baseDamage;
    private double damage;
    private int range;
    private double critChance = 0.03;
    private double critModifier = 2.5;

    public abstract List<Enemy> findTargets(List<Enemy> enemyArray);

    DamageTower(String imageID) {
        super(imageID);
    }

    public void attackEnemy(List<Enemy> enemyArrayList) {
        List<Enemy> targetedEnemies = findTargets(enemyArrayList);
        for (Enemy enemy : targetedEnemies) {
            if (enemy != null) {
                enemy.takeDamage(calculateDamage());
            }
        }
        damage = baseDamage;
    }


    public int getDistance(int row, int col) {
        return Math.max(Math.abs(getPosition()[0] - row), Math.abs(getPosition()[1] - col));
    }

    private int calculateDamage() {
        return (int) (Math.random() <= critChance ? (damage * critModifier) : damage);
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public int getRange() {
        return range;
    }

    public double getDamage() {
        return damage;
    }

    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
        this.damage = baseDamage;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }


    public boolean upgradeAbility() {
        if (getNumUpgrades() < MAX_UPGRADE) {
            baseDamage *= 1.25;
            numUpgrades++;
            return true;
        } else {
            return false;
        }
    }

    public boolean upgradeRange() {
        if (getNumUpgrades() < MAX_UPGRADE) {
            range++;
            numUpgrades++;
            return true;
        } else {
            return false;
        }
    }
}
