import com.teamfourtyone.*;
import com.teamfourtyone.TowerEntity.MonumentTower;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class GameViewTest {
    private GameView game;

    @Start
    void onStart(Stage stage) throws IOException {
        game = new GameView();
        stage.setScene(game.getScene());
        stage.show();
    }

    @Test
    void updateHealthTest() {
        game.setHealth(100);
        verifyThat("#healthText", hasText("Health: 100"));
    }

    @Test
    void updateMoneyText() {
        game.setMoney(7500);
        verifyThat("#moneyText", hasText("Money: $7500"));
    }

    @Test
    void updateMoneyValue() {
        this.game.setMoney(7500);
        assertEquals(game.getMoney(), 7500);
    }

    @Test
    void updateTowerDescription() {
        this.game.setTowerDescription("Test");
        verifyThat("#towerDescription", hasText("Test"));
    }

    @Test
    void checkPathExists() {
        new GameController("level1");
        String message = "There is no initial path set";
        assertEquals(TileController.getPath() == null, false, message);
    }

    @Test
    void checkTowerExists() {
        TileController.clearTiles();
        TileController.placeTile(5, 8, new MonumentTower());
        String message = "There is not an intially set tower";
        assertEquals(true, TileController.getTile(5, 8) != null, message);
    }

    @Test
    void checkTowerDoesntExist() {
        TileController.clearTiles();
        String message = "There is an intially set tower where there shouldn't be";
        assertEquals(TileController.getTile(5, 6) == null, true, message);
    }

    @Test
    void checkNumberOfGridElements() {
        new GameController("level1");
        String message = "There is an incorrect number of elements initially set";
        int count = 0;
        for (Tileable[] row : TileController.getTileGrid()) {
            for (Tileable t : row) {
                if (t != null) {
                    count += 1;
                }
            }
        }
        assertEquals(0 != count, true, message);
    }
}
