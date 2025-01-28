package com.teamfourtyone;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameWinView {
    private VBox mainBox;
    private Scene scene;
    private Button restartButton;
    private Button exitButton;

    public GameWinView() {
        this.scene = initScene();
    }

    private Scene initScene() {
        Text winningText = new Text("Congratulations " + App.getPlayerName()
                + "\nYou Beat The Game\n" + Statistics.getStats());
        winningText.setWrappingWidth(500);
        winningText.setFill(Color.rgb(0xB3, 0xA3, 0x69));
        winningText.setStyle("-fx-font-size: 300%;");
        winningText.setTextAlignment(TextAlignment.CENTER);
        restartButton = new Button("Restart Game");
        restartButton.setOnAction(e -> {
            App.restartGame();
            App.switchSceneWelcome();
        });
        restartButton.setStyle("-fx-text-fill:white; -fx-font-size: 150%; "
                + "-fx-background-color:#003057;");
        exitButton = new Button("Exit Game");
        exitButton.setOnAction(e -> {
            Platform.exit();
        });
        exitButton.setStyle("-fx-text-fill:white; -fx-font-size: 150%; "
                + "-fx-background-color:#003057;");
        mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(20);
        mainBox.setMinWidth(App.SCREENWIDTH);
        mainBox.setMinHeight(App.SCREENHEIGHT);
        mainBox.getChildren().addAll(winningText, restartButton, exitButton);
        return new Scene(mainBox, App.SCREENWIDTH, App.SCREENHEIGHT);
    }

    public Scene getScene() {
        return scene;
    }
}

