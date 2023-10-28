package edu.project1;

import static org.junit.jupiter.api.Assertions.*;
import edu.project1.Game.GameIsOverException;
import edu.project1.Game.GameSession;
import edu.project1.Game.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

class TestProject1 {

    @Test
    @DisplayName("Sample test (to loose)")
    void sampleTest() {
        String[] simulatedInput = {"u\n", "v\n", "c\n", "j\n", "d\n"};
        ByteArrayInputStream originalSystemIn = new ByteArrayInputStream("".getBytes());
        System.setIn(originalSystemIn);

        GameSession game = null;
        try {
            game = new GameSession();
        } catch(GameIsOverException e) {
            fail();
        }
        game.hangmanWord.createNewWord();
        Player player = new Player();

        for (int i = 0; i < 5; ++i) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput[i].getBytes());
            System.setIn(inputStream);

            game.playTurn(player);
        }

        assertEquals(player.getAttemptsUsed(), 5);
        assertFalse(game.isWin());

        System.setIn(originalSystemIn);
    }

    @Test
    @DisplayName("Sample test (to win)")
    void testForWin() {
        String[] simulatedInput = {"m\n", "a\n", "n\n", "g\n", "e\n", "r\n"};
        ByteArrayInputStream originalSystemIn = new ByteArrayInputStream("".getBytes());
        System.setIn(originalSystemIn);

        GameSession game = null;
        try {
            game = new GameSession("manager");
        } catch (GameIsOverException e) {
            fail("Invalid word");
        }
        Player player = new Player();

        ByteArrayInputStream inputStream;

        for (int i = 0; i < 6; ++i) {
            inputStream = new ByteArrayInputStream(simulatedInput[i].getBytes());
            System.setIn(inputStream);

            game.playTurn(player);
        }

        game.gameOver(player);
        assertEquals(player.getAttemptsUsed(), 0);
        assertTrue(game.isWin());

        System.setIn(originalSystemIn);
    }


    @Test
    @DisplayName("Test with null as word")
    void testNullWord() {
        try {
            new GameSession(null);
            fail();
        } catch (GameIsOverException e) {
            return;
        }
    }

    @Test
    @DisplayName("Test with invalid word")
    void testInvalidWord() {
        try {
            new GameSession("");
            fail();
        } catch (GameIsOverException e) {
            return;
        }
    }

    @Test
    @DisplayName("Test for correct behavior after the wrong answer")
    void testWrongAnswer() {
        String[] simulatedInput = {"p\n", "i\n", "n\n", "k\n", "o\n", "f\n"};
        ByteArrayInputStream originalSystemIn = new ByteArrayInputStream("".getBytes());
        System.setIn(originalSystemIn);

        GameSession game = null;
        try {
            game = new GameSession("tinkoff");
        } catch (GameIsOverException e) {
            fail("Invalid word");
        }
        Player player = new Player();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput[0].getBytes());
        System.setIn(inputStream);
        game.playTurn(player);

        game.gameOver(player);
        assertEquals(player.getAttemptsUsed(), 1);
        assertFalse(game.isWin());

        System.setIn(originalSystemIn);
    }

    static Stream<String[]> provideData() {
        return Stream.of(
            new String[]{"t\n"}, // Корректное поведение после угаданной буквы
            new String[]{"ti\nt"} // После опечатки
        );
    }

    @ParameterizedTest
    @DisplayName("Test with no attempts changing")
    @MethodSource("provideData")
    void testNoAttemptsChanging(String simulatedInput) {
        ByteArrayInputStream originalSystemIn = new ByteArrayInputStream("".getBytes());
        System.setIn(originalSystemIn);

        GameSession game = null;
        try {
            game = new GameSession("tinkoff");
        } catch (GameIsOverException e) {
            fail("Invalid word");
        }
        Player player = new Player();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        game.playTurn(player);

        game.gameOver(player);
        assertEquals(player.getAttemptsUsed(), 0);
        assertFalse(game.isWin());

        System.setIn(originalSystemIn);
    }
}

