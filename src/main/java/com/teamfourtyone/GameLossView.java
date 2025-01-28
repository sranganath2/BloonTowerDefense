package com.teamfourtyone;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameLossView {
    private VBox mainBox;
    private Scene scene;
    private Button restartButton;
    private Button exitButton;

    public GameLossView() {
        this.scene = initScene();
    }

    private Scene initScene() {
        Text losingText = new Text("Game Over " + App.getPlayerName()
                + "\nYou Died\n" + Statistics.getStats());
        losingText.setWrappingWidth(500);
        losingText.setFill(Color.rgb(0xB3, 0xA3, 0x69));
        losingText.setStyle("-fx-font-size: 300%;");
        losingText.setTextAlignment(TextAlignment.CENTER);
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
        mainBox.getChildren().addAll(losingText, restartButton, exitButton);
        return new Scene(mainBox, App.SCREENWIDTH, App.SCREENHEIGHT);
    }

    public Scene getScene() {
        return scene;
    }
}

