package com.teamfourtyone;

import com.teamfourtyone.EnemyEntity.Enemy;
import com.teamfourtyone.TowerEntity.Tower;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameView {
    private Scene gameScene;
    private HBox mainPane;
    private VBox shopPane;
    private VBox gamePane;
    private HBox topPane;
    private GridPane gridPane;
    private Text healthText;
    private Text moneyText;
    private int money;
    private int health;
    private Text towerDescription;
    private Text costText;
    protected Button aoeTowerButton;
    protected Button damageBoostTowerButton;
    protected Button singleShotTowerButton;
    protected Button startButton;
    protected Button stepButton;
    protected Button autostepButton;
    protected Button upgradeRangeButton;
    protected Button upgradeAbilityButton;

    public GameView() {
        this.gameScene = initScene();
        gameScene.getStylesheets().add(App.class.getResource("gamestyle.css").toExternalForm());
    }

    private Scene initScene() {
        // Initialize Panes
        mainPane = new HBox();
        gamePane = new VBox();
        shopPane = new VBox();
        topPane = new HBox();
        gridPane = new GridPane();
        shopPane.setMinSize(200, 600);
        topPane.setMinSize(600, 40);
        gridPane.setMinSize(600, 560);
        mainPane.getChildren().addAll(shopPane, gamePane);
        gamePane.getChildren().addAll(topPane, gridPane);

        // Set top pane items
        topPane.setAlignment(Pos.CENTER_LEFT);
        topPane.setPadding(new Insets(15));
        topPane.setSpacing(15);
        healthText = new Text("Health: x");
        healthText.setId("healthText");
        moneyText = new Text("Money: x");
        moneyText.setId("moneyText");

        Button settingsButton = new Button("Settings");
        settingsButton.setId("Settings");
        settingsButton.setOnAction(e -> App.switchSceneConfig());

        Pane paddingPane = new Pane();
        HBox.setHgrow(paddingPane, Priority.ALWAYS);
        topPane.getChildren().addAll(healthText, moneyText, paddingPane, settingsButton);

        // Set shop pane
        shopPane.setId("shopPane");
        topPane.setId("topPane");
        Text shopTitle = new Text("TOWER SHOP");
        towerDescription = new Text("Tower Description: ");
        towerDescription.setId("towerDescription");
        towerDescription.setWrappingWidth(170);
        costText = new Text("Cost: $X");
        shopPane.setAlignment(Pos.TOP_CENTER);
        shopPane.setPadding(new Insets(20, 0, 0, 0));
        shopPane.setSpacing(10);

        aoeTowerButton = new Button("AOE Tower");
        damageBoostTowerButton = new Button("Damage Boost Tower");
        singleShotTowerButton = new Button("Single Shot Tower");
        startButton = new Button("Start");
        stepButton = new Button("Step Next");
        autostepButton = new Button("Auto Step");
        upgradeRangeButton = new Button("Upgrade Range");
        upgradeAbilityButton = new Button("Upgrade Ability");
        stepButton.setVisible(false);
        autostepButton.setVisible(false);

        shopPane.getChildren().addAll(shopTitle, towerDescription, costText,
                aoeTowerButton, damageBoostTowerButton, singleShotTowerButton,
                startButton, stepButton, autostepButton, upgradeRangeButton, upgradeAbilityButton);
        return new Scene(mainPane, App.SCREENWIDTH, App.SCREENHEIGHT);
    }

    public void setGridPaneEventHandler(EventHandler<MouseEvent> eventHandler) {
        gridPane.setOnMouseClicked(eventHandler);
    }

    public Scene getScene() {
        return gameScene;
    }

    public void addTile(Tileable tile, int i, int j) {
        gridPane.add(tile.getTileImage(), i, j);
    }

    public void clearTile() {
        gridPane.getChildren().clear();
    }

    public void setTile(Tileable tile, int i, int j) {
        gridPane.setColumnIndex(tile.getTileImage(), i);
        gridPane.setRowIndex(tile.getTileImage(), j);
    }

    public void displayTile(Tileable tile, int i, int j) {
        gridPane.add(tile.getTileImage(), i, j);
    }

    public void display(Tileable[][] grid, int[][] path) {
        gamePane.getChildren().remove(gridPane);
        gridPane = new GridPane();
        for (int i = 0; i < path.length; i++) {
            int[] p = path[i];
            Rectangle pathImage = new Rectangle();
            pathImage.setWidth(App.TILESIZE);
            pathImage.setHeight(App.TILESIZE);
            pathImage.setFill(Color.valueOf(Path.getColor()));
            gridPane.add(pathImage, p[0], p[1]);
        }
        for (int i = 0; i < App.TILEWIDTH; i++) {
            for (int j = 0; j < App.TILEHEIGHT; j++) {
                Tileable tile = grid[i][j];
                if (tile == null) {
                    Rectangle emptyTile = new Rectangle();
                    emptyTile.setWidth(App.TILESIZE);
                    emptyTile.setHeight(App.TILESIZE);
                    emptyTile.setFill(Color.WHITESMOKE);
                    gridPane.add(emptyTile, i, j);
                } else if (tile.getTileImage() != null) {
                    if (tile instanceof Enemy) {
                        gridPane.add(enemyBackground((Enemy) tile), i, j);
                    } else if (tile instanceof Tower) {
                        gridPane.add(towerBackground((Tower) tile), i, j);
                    }
                    gridPane.add(tile.getTileImage(), i, j);
                }
            }
        }
        gamePane.getChildren().add(gridPane);
    }

    public Rectangle enemyBackground(Enemy e) {
        Rectangle rect = new Rectangle();
        rect.setWidth(App.TILESIZE);
        rect.setHeight(App.TILESIZE);
        rect.setFill(Color.color(1 - ((double) e.getHealth() / e.getMaxHealth()),
                ((double) e.getHealth() / e.getMaxHealth()), 0.0));
        return rect;
    }

    public Rectangle towerBackground(Tower t) {
        Rectangle rect = new Rectangle();
        rect.setWidth(App.TILESIZE);
        rect.setHeight(App.TILESIZE);
        rect.setFill(Color.color(((double) t.getNumUpgrades() / Tower.MAX_UPGRADE),
                0.0,  1 - ((double) t.getNumUpgrades() / Tower.MAX_UPGRADE)));
        return rect;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
        updateHealthText();
    }

    private void updateHealthText() {
        healthText.setText("Health: " + Integer.toString(health));
    }

    public void setMoney(int m) {
        moneyText.setText("Money: $" + Integer.toString(m));
        money = m;
    }

    public void setCost(int c) {
        costText.setText("Cost: $" + Integer.toString(c));
    }

    public int getMoney() {
        return money;
    }

    public void updateTowerDescription(String s) {
        towerDescription.setText(s);
    }

    public void setTowerDescription(String s) {
        towerDescription.setText(s);
    }
}
