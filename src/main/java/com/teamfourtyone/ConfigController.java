package com.teamfourtyone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ConfigController {
    private String playerName = "Player 1";
    private int difficulty = 0;

    @FXML
    private ToggleGroup group;
    @FXML
    private Button submitButton;
    @FXML
    private Button returnButton;
    @FXML
    private TextField nameField;
    @FXML
    private Label warningText;

    public ConfigController() {
    }

    public String setPlayerName(String newName) {
        if (newName == null || newName.isBlank()) {
            return "Player Name cannot be empty.\nPlease enter a name.";
        } else {
            playerName = newName;
            return playerName;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int setDifficulty(int newDifficulty) {
        if (newDifficulty < 3 && newDifficulty >= 0) {
            difficulty = newDifficulty;
            return difficulty;
        } else {
            return -1;
        }
    }

    public int getDifficulty() {
        return difficulty;
    }

    @FXML
    public void returnHandler(ActionEvent event) {
        App.switchSceneGame();
    }

    @FXML
    public void submitHandler(ActionEvent event) {
        event.consume();
        if (nameField.getText() != null && !nameField.getText().isBlank()
                && group.getSelectedToggle() != null) {
            setPlayerName(nameField.getText());
            difficulty = Integer.parseInt(group.getSelectedToggle().getUserData().toString());
            warningText.setVisible(true);
            returnButton.setVisible(true);
            submitButton.setText("Restart");
            App.setDifficulty(difficulty);
            App.setPlayerName(playerName);
            App.switchSceneGame();
            App.updateDifficulty();
        } else {
            submitButton.setStyle("-fx-background-color:red");
        }
    }
}
