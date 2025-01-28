package com.teamfourtyone.TowerEntity;

import com.teamfourtyone.App;
import com.teamfourtyone.Tileable;

public abstract class Tower extends Tileable {
    public static final int MAX_UPGRADE = 10;
    private int cost;
    protected int numUpgrades;
    private String description;
    private int[] position = {-1, -1};

    Tower(String imageID) {
        super("towericons/" + imageID);
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getCost() {
        return (int) (cost * (0.75 + (App.getDifficulty() * 0.25)));
    }

    public int getUpgradeCost() {
        return (int) (cost * (0.15 + ((App.getDifficulty() + 1) * numUpgrades * 0.05)));
    }

    public boolean setCost(int cost) {
        if (cost < 0) {
            return false;
        } else {
            this.cost = cost;
            return true;
        }
    }

    public String getDescription() {
        return description;
    }

    public boolean setDescription(String description) {
        if (description == null) {
            return false;
        } else {
            this.description = description;
            return true;
        }
    }

    public void setPosition(int row, int col) {
        position[0] = row;
        position[1] = col;
    }

    public int getNumUpgrades() {
        return numUpgrades;
    }

    public abstract boolean upgradeAbility();
    public abstract boolean upgradeRange();
}
