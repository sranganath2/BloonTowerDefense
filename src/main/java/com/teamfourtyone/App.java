package com.teamfourtyone;

import com.teamfourtyone.EnemyEntity.Enemy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static final Integer SCREENHEIGHT = 600;
    public static final Integer SCREENWIDTH = 800;
    public static final Integer TILESIZE = 40;
    public static final Integer TILEHEIGHT = 560 / TILESIZE;
    public static final Integer TILEWIDTH = 600 / TILESIZE;
    private static Integer difficulty;
    private static String playerName;
    public enum TowerType { AOE, SINGLESHOT, DAMAGEBOOST };
    public enum EnemyType { GLASSBOMB, DRAGON, GOBLIN };

    private static Stage stage;
    private static GameController gameController;
    private static ConfigController configController;
    private static Scene welcomeScene;
    private static Scene configScene;
    private static Scene gameScene;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        restartGame();
        switchSceneWelcome();
        stage.show();
    }

    public static void restartGame() {
        welcomeScene = new WelcomeView().getScene();
        configScene = loadFXML("config");
        configController = new ConfigController();
        gameController = new GameController("level1");
        gameScene = gameController.getScene();
        Statistics.resetStats();
        Enemy.resetCounter();
    }

    public static void switchSceneWelcome() {
        stage.setTitle("Welcome - Team 41");
        stage.setScene(welcomeScene);
    }

    public static void switchSceneConfig() {
        stage.setTitle("Tower Defense Config");
        stage.setScene(configScene);
    }

    public static void switchSceneGame() {
        stage.setTitle("Tower Defense - Team 41");
        stage.setScene(gameScene);
    }

    public static void switchSceneGameLoss() {
        Scene gameLossScene = new GameLossView().getScene();
        stage.setTitle("Tower Defense - End Game");
        stage.setScene(gameLossScene);
    }

    public static void switchSceneGameWin() {
        Scene gameWinScene = new GameWinView().getScene();
        stage.setTitle("Tower Defense - Congratulations");
        stage.setScene(gameWinScene);
    }

    public static void updateDifficulty() {
        gameController.updateDifficulty();
    }

    public static Integer getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Integer difficulty) {
        App.difficulty = difficulty;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        App.playerName = playerName;
    }

    private static Scene loadFXML(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            return new Scene(fxmlLoader.load(), SCREENWIDTH, SCREENHEIGHT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return null;
    }


    public static void main(String[] args) {
        launch();
    }
}
