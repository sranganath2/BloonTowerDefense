import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.teamfourtyone.ConfigController;

public class ConfigTest {

    @Test
    void nullNameTest() {
        ConfigController cc = new ConfigController();
        String newName = null;
        String actual = "Player Name cannot be empty.\nPlease enter a name.";
        String message = "ConfigController incorrectly accepted a null name.";
        assertEquals(cc.setPlayerName(newName), actual, message);
    }

    @Test
    void emptyNameTest() {
        ConfigController cc = new ConfigController();
        String newName = "";
        String actual = "Player Name cannot be empty.\nPlease enter a name.";
        String message = "ConfigController incorrectly accepted an empty name.";
        assertEquals(cc.setPlayerName(newName), actual, message);
    }

    @Test
    void blankNameTest() {
        ConfigController cc = new ConfigController();
        String newName = "          ";
        String actual = "Player Name cannot be empty.\nPlease enter a name.";
        String message = "ConfigController incorrectly accepted a blank name.";
        assertEquals(cc.setPlayerName(newName), actual, message);
    }

    @Test
    void validNameTest() {
        ConfigController cc = new ConfigController();
        String newName = "Turtle";
        String actual = "Turtle";
        String message = "ConfigController rejected a valid name.";
        assertEquals(cc.setPlayerName(newName), actual, message);
        newName = "Phonzi";
        actual = "Phonzi";
        assertEquals(cc.setPlayerName(newName), actual, message);
    }

    @Test
    void invalidDifficultyTest() {
        ConfigController cc = new ConfigController();
        String message = "ConfigController accepted an invalid difficulty.";
        assertEquals(cc.setDifficulty(3), -1, message);
        assertEquals(cc.setDifficulty(500), -1, message);
        assertEquals(cc.setDifficulty(-10), -1, message);
    }

    @Test
    void validDifficultyTest() {
        ConfigController cc = new ConfigController();
        String message = "ConfigController rejected a valid difficulty.";
        assertEquals(cc.setDifficulty(0), 0, message);
        assertEquals(cc.setDifficulty(1), 1, message);
        assertEquals(cc.setDifficulty(2), 2, message);
    }

    @Test
    void retrieveDifficultyValid() {
        ConfigController cc = new ConfigController();
        String message = "ConfigController did not set its value to a valid difficulty";
        cc.setDifficulty(2);
        assertEquals(cc.getDifficulty(), 2, message);
    }
    @Test
    void retrieveDifficultyInvalid() {
        ConfigController cc = new ConfigController();
        String message = "ConfigController set its value to an invalid difficulty";
        cc.setDifficulty(200);
        assertEquals(cc.getDifficulty(), 0, message);
    }
}
