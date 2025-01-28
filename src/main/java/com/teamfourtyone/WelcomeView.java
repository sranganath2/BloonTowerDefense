package com.teamfourtyone;

import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class WelcomeView {
    private VBox mainBox;
    private Scene scene;
    private Button startButton;

    public WelcomeView() {
        this.scene = initScene();
    }

    private Scene initScene() {
        Text welcomeText = new Text("WELCOME TO TEAM 41 TOWER DEFENSE GAME");
        welcomeText.setWrappingWidth(500);
        welcomeText.setFill(Color.rgb(0xB3, 0xA3, 0x69));
        welcomeText.setStyle("-fx-font-size: 300%;");
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        startButton = new Button("Start");
        startButton.setOnAction(e -> {
            App.switchSceneConfig();
        });
        startButton.setStyle("-fx-text-fill:white; -fx-font-size: 150%; "
                + "-fx-background-color:#003057;");
        mainBox = new VBox();
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(20);
        mainBox.setMinWidth(App.SCREENWIDTH);
        mainBox.setMinHeight(App.SCREENHEIGHT);
        mainBox.getChildren().addAll(welcomeText, startButton);
        return new Scene(mainBox, App.SCREENWIDTH, App.SCREENHEIGHT);
    }

    public Scene getScene() {
        return scene;
    }
}

