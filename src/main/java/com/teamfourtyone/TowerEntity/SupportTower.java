package com.teamfourtyone.TowerEntity;

import java.util.ArrayList;

public abstract class SupportTower extends Tower {
    private int numUpgrades;
    private int range;
    private double boost = 1.1;

    public abstract ArrayList<DamageTower> findTargets();

    public abstract void grantSupport();

    public abstract void removeSupport(); //Since there is no selling, removal doesn't work

    SupportTower(String imageID) {
        super(imageID);
    }

    protected int getDistance(int row, int col) {
        return Math.max(Math.abs(getPosition()[0] - row), Math.abs(getPosition()[1] - col));
    }

    public double getBoost() {
        return boost;
    }

    public void setBoost(double boost) {
        this.boost = boost;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean upgradeAbility() {
        if (getNumUpgrades() < MAX_UPGRADE) {
            boost *= 1.03;
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
