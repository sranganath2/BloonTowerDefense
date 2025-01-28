package com.teamfourtyone;

import com.teamfourtyone.EnemyEntity.Enemy;
import com.teamfourtyone.EnemyEntity.BossEnemy;
import com.teamfourtyone.EnemyEntity.DragonEnemy;
import com.teamfourtyone.EnemyEntity.GlassBombEnemy;
import com.teamfourtyone.EnemyEntity.GoblinEnemy;
import com.teamfourtyone.TowerEntity.AOETower;
import com.teamfourtyone.TowerEntity.DamageBoostTower;
import com.teamfourtyone.TowerEntity.DamageTower;
import com.teamfourtyone.TowerEntity.MonumentTower;
import com.teamfourtyone.TowerEntity.SingleShotTower;
import com.teamfourtyone.TowerEntity.SupportTower;
import com.teamfourtyone.TowerEntity.Tower;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameController {
    private static GameView gameView;
    private boolean isPurchase;
    private boolean isUpgradeRange;
    private boolean isUpgradeAbility;
    private static int[][] path;
    private static LinkedList<Enemy> enemyArray;
    private static ArrayList<DamageTower> damageTowerArray;
    private static ArrayList<SupportTower> supportTowerArray;
    private static ArrayList<Enemy> killArray;
    private static double healthModifier = 0.0;
    private App.TowerType selectedType;
    private Scene scene;
    private int nextSpawn;
    private Random rand;
    private static boolean gameWin;

    public GameController(String levelID) {
        gameView = new GameView();
        scene = gameView.getScene();
        enemyArray = new LinkedList<>();
        damageTowerArray = new ArrayList<>();
        supportTowerArray = new ArrayList<>();
        killArray = new ArrayList<>();
        loadLevel(levelID);

        gameView.aoeTowerButton.setOnMouseClicked(e ->
                purchaseEventHandler(e, App.TowerType.AOE));
        gameView.damageBoostTowerButton.setOnMouseClicked(e ->
                purchaseEventHandler(e, App.TowerType.DAMAGEBOOST));
        gameView.singleShotTowerButton.setOnMouseClicked(e ->
                purchaseEventHandler(e, App.TowerType.SINGLESHOT));
        gameView.startButton.setOnAction(e -> startButtonHandler());
        gameView.stepButton.setOnAction(e -> stepButtonHandler());
        gameView.autostepButton.setOnAction(e -> autostepButtonHandler());
        gameView.upgradeRangeButton.setOnAction(e -> isUpgradeRange = true);
        gameView.upgradeAbilityButton.setOnAction(e -> isUpgradeAbility = true);
    }

    public void step() {
        for (SupportTower spptTower : supportTowerArray) {
            spptTower.grantSupport();
        }
        for (DamageTower dmgTower : damageTowerArray) {
            dmgTower.attackEnemy(enemyArray);
        }
        for (Enemy enemy : enemyArray) {
            moveEnemy(enemy);
        }
        for (Enemy enemy : killArray) {
            enemy.killEnemy();
        }
        killArray.clear();

        nextSpawn--;
        if (nextSpawn <= 0) {
            spawnEnemy();
        }
        gameView.display(TileController.getTileGrid(), TileController.getPath());
        gameView.setGridPaneEventHandler(this::gridPaneHandler);

        if (gameView.getHealth() <= 0) {
            App.switchSceneGameLoss();
        } else if (gameWin) {
            App.switchSceneGameWin();
        }
    }

    private void loadLevel(String levelID) {
        TileController.clearTiles();
        path = LevelCreator.loadPath(levelID);
        TileController.setPathCoords(path);
        Path.setColor(LevelCreator.loadPathColor(levelID));
        MonumentTower monumentTower = new MonumentTower();
        int[] monumentLocation = LevelCreator.loadMonument(levelID);
        TileController.placeTile(monumentLocation[0], monumentLocation[1], monumentTower);
    }

    public void startButtonHandler() {
        gameView.startButton.setVisible(false);
        gameView.stepButton.setVisible(true);
        gameView.autostepButton.setVisible(true);
        step();
    }

    public void stepButtonHandler() {
        step();
    }

    public void autostepButtonHandler() {
        for (int i = 0; i < 5; i++) {
            step();
        }
    }

    public void gridPaneHandler(MouseEvent event) {
        if (isPurchase) {
            towerPurchaseHandler(event);
            isPurchase = false;
        }
        if (isUpgradeRange) {
            upgradeRangeButtonHandler(event);
            isUpgradeRange = false;
        }
        if (isUpgradeAbility) {
            upgradeAbilityButtonHandler(event);
            isUpgradeAbility = false;
        }
    }

    public void towerPurchaseHandler(MouseEvent event) {
        Tower newTower;
        if (selectedType == App.TowerType.AOE) {
            newTower = new AOETower();
        } else if (selectedType == App.TowerType.DAMAGEBOOST) {
            newTower = new DamageBoostTower();
        } else if (selectedType == App.TowerType.SINGLESHOT) {
            newTower = new SingleShotTower();
        } else {
            newTower = null;
        }
        if (newTower != null && gameView.getMoney() >= newTower.getCost()) {
            int x = (int) event.getX() / App.TILESIZE;
            int y = (int) event.getY() / App.TILESIZE;
            if (TileController.isEmpty(x, y)) {
                TileController.placeTile(x, y, newTower);
                newTower.setPosition(x, y);
                gameView.setMoney(gameView.getMoney() - newTower.getCost());
                if (newTower instanceof DamageTower) {
                    damageTowerArray.add((DamageTower) newTower);
                } else if (newTower instanceof SupportTower) {
                    supportTowerArray.add((SupportTower) newTower);
                }
                gameView.addTile(newTower, x, y);
            } else {
                gameView.setTowerDescription("Sorry, cannot place tower in occupied spot");
            }
            isPurchase = false;
        } else if (newTower != null) {
            gameView.setTowerDescription("Sorry, you don't have enough money for that tower");
        }
    }

    public void upgradeRangeButtonHandler(MouseEvent event) {
        int x = (int) event.getX() / App.TILESIZE;
        int y = (int) event.getY() / App.TILESIZE;
        Tower newTower;
        if (TileController.getTile(x, y) instanceof Tower) {
            newTower = (Tower) TileController.getTile(x, y);
            if (gameView.getMoney() >= newTower.getUpgradeCost()) {
                if (newTower.upgradeRange()) {
                    gameView.setMoney(gameView.getMoney() - newTower.getUpgradeCost());
                } else {
                    gameView.setTowerDescription("Sorry, you don't have reached the "
                            + "maximum amount of upgrades(ten upgrades)");
                }
            } else {
                gameView.setTowerDescription("Sorry, you don't have enough money for that tower");
            }
        }
    }

    public void upgradeAbilityButtonHandler(MouseEvent event) {
        int x = (int) event.getX() / App.TILESIZE;
        int y = (int) event.getY() / App.TILESIZE;
        Tower newTower;
        if (TileController.getTile(x, y) instanceof Tower) {
            newTower = (Tower) TileController.getTile(x, y);
            if (gameView.getMoney() >= newTower.getUpgradeCost()) {
                if (newTower.upgradeAbility()) {
                    gameView.setMoney(gameView.getMoney() - newTower.getUpgradeCost());
                } else {
                    gameView.setTowerDescription("Sorry, you don't have reached "
                            + "the maximum amount of upgrades(ten upgrades)");
                }
            } else {
                gameView.setTowerDescription("Sorry, you don't have enough money for that tower");
            }
        }
    }

    private void spawnEnemy() {
        if (Enemy.getCounter() < 50) {
            rand = new Random();
            int enemyChooser = rand.nextInt(100);
            Enemy newEnemy;
            if (enemyChooser < 70) {
                newEnemy = new GoblinEnemy();
                newEnemy.setHealth(newEnemy.getHealth() + (int) healthModifier);
            } else if (enemyChooser < 90) {
                newEnemy = new GlassBombEnemy();
                newEnemy.setHealth(newEnemy.getHealth() + (int) (healthModifier / 150));
            } else {
                newEnemy = new DragonEnemy();
                newEnemy.setHealth(newEnemy.getHealth() + (int) (3 * healthModifier));
            }
            int[] spawnPoint = TileController.getPath()[0];

            enemyArray.addLast(newEnemy);
            TileController.placeTile(spawnPoint[0], spawnPoint[1], newEnemy);
            nextSpawn = 5 - 2 * App.getDifficulty();
        } else {
            Enemy bossEnemy = new BossEnemy();
            enemyArray.addLast(bossEnemy);
            int[] spawnPoint = TileController.getPath()[0];
            TileController.placeTile(spawnPoint[0], spawnPoint[1], bossEnemy);
            nextSpawn = 99;
        }
    }

    private void moveEnemy(Enemy enemy) {
        int[] position = TileController.getPath()[enemy.getPosition()];
        Path newPath = new Path();
        TileController.placeTile(position[0], position[1], newPath);
        enemy.incPosition();
        if (enemy.getPosition() < path.length) {
            int[] nextPosition = TileController.getPath()[enemy.getPosition()];
            TileController.placeTile(nextPosition[0], nextPosition[1], enemy);
        } else {
            GameController.getKillArray().add(enemy);
            gameView.setHealth(gameView.getHealth() - enemy.getDamage());
        }
    }

    public void purchaseEventHandler(MouseEvent event, App.TowerType type) {
        isPurchase = true;
        Tower newTower;
        if (type == App.TowerType.AOE) {
            newTower = new AOETower();
        } else if (type == App.TowerType.DAMAGEBOOST) {
            newTower = new DamageBoostTower();
        } else if (type == App.TowerType.SINGLESHOT) {
            newTower = new SingleShotTower();
        } else {
            return;
        }
        gameView.setCost(newTower.getCost());
        gameView.updateTowerDescription("Tower Description: " + newTower.getDescription());
        selectedType = type;
    }

    public void placeTowerEventHandler(MouseEvent event) {
        if (!isPurchase) {
            return;
        }

        Tower newTower;
        if (selectedType == App.TowerType.AOE) {
            newTower = new AOETower();
        } else if (selectedType == App.TowerType.DAMAGEBOOST) {
            newTower = new DamageBoostTower();
        } else if (selectedType == App.TowerType.SINGLESHOT) {
            newTower = new SingleShotTower();
        } else {
            newTower = null;
        }
        if (newTower != null && gameView.getMoney() >= newTower.getCost()) {
            int x = (int) event.getX() / App.TILESIZE;
            int y = (int) event.getY() / App.TILESIZE;
            if (TileController.isEmpty(x, y)) {
                TileController.placeTile(x, y, newTower);
                newTower.setPosition(x, y);
                gameView.setMoney(gameView.getMoney() - newTower.getCost());
                if (newTower instanceof DamageTower) {
                    damageTowerArray.add((DamageTower) newTower);
                } else if (newTower instanceof SupportTower) {
                    supportTowerArray.add((SupportTower) newTower);
                }
                gameView.addTile(newTower, x, y);
            } else {
                gameView.setTowerDescription("Sorry, cannot place tower in occupied spot");
            }
            isPurchase = false;
        } else if (newTower != null) {
            gameView.setTowerDescription("Sorry, you don't have enough money for that tower");
        }

    }

    public void setSelectedType(App.TowerType selectedType) {
        this.selectedType = selectedType;
    }

    public void updateDifficulty() {
        int difficulty = App.getDifficulty();
        int[] moneyList = {100, 150, 200};
        int[] healthList = {300, 200, 100};
        if (difficulty >= 0 && difficulty <= 2) {
            difficulty = 0;
        }
        gameView.setMoney(moneyList[difficulty]);
        gameView.setHealth(healthList[difficulty]);
    }

    public static GameView getView() {
        return gameView;
    }

    public Scene getScene() {
        return scene;
    }

    public static List<Enemy> getEnemyArray() {
        return enemyArray;
    }

    public static ArrayList<Enemy> getKillArray() {
        return killArray;
    }

    public static ArrayList<DamageTower> getDamageTowerArray() {
        return damageTowerArray;
    }

    public static void changeHealthModifier(double increase) {
        healthModifier += increase;
    }

    public static void setGameWin(boolean gameWin) {
        GameController.gameWin = gameWin;
    }
}
