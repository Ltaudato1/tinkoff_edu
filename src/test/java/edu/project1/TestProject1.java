package edu.project1;

import static org.junit.jupiter.api.Assertions.*;
import edu.project1.Game.GameSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

class TestProject1 {

    @org.junit.jupiter.api.Test
    @DisplayName("Sample test (to loose)")
    void sampleTest() {
        String[] simulatedInput = {"u\n", "v\n", "c\n", "j\n", "d\n"};
        ByteArrayInputStream originalSystemIn = new ByteArrayInputStream("".getBytes());
        System.setIn(originalSystemIn);

        GameSession.Game game = null;
        try {
            game = new GameSession.Game();
        } catch(GameSession.GameIsOverException e) {
            fail();
        }
        game.hangmanWord.createNewWord();
        GameSession.Player player = new GameSession.Player();

        for (int i = 0; i < 5; ++i) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput[i].getBytes());
            System.setIn(inputStream);

            game.playTurn(player);
        }

        assertEquals(player.getAttemptsUsed(), 5);
        assertFalse(game.isWin());

        System.setIn(originalSystemIn);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Sample test (to win)")
    void testForWin() {
        String[] simulatedInput = {"t\n", "i\n", "n\n", "k\n", "o\n", "f\n"};
        ByteArrayInputStream originalSystemIn = new ByteArrayInputStream("".getBytes());
        System.setIn(originalSystemIn);

        GameSession.Game game = null;
        try {
            game = new GameSession.Game("tinkoff");
        } catch (GameSession.GameIsOverException e) {
            fail("Invalid word");
        }
        GameSession.Player player = new GameSession.Player();

        for (int i = 0; i < 6; ++i) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput[i].getBytes());
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
            new GameSession.Game(null);
            fail();
        } catch (GameSession.GameIsOverException e) {
            return;
        }
    }

    @Test
    @DisplayName("Test with invalid word")
    void testInvalidWord() {
        try {
            new GameSession.Game("");
            fail();
        } catch (GameSession.GameIsOverException e) {
            return;
        }
    }

    @Test
    @DisplayName("Test for correct behavior after the wrong answer")
    void testWrongAnswer() {
        String[] simulatedInput = {"p\n", "i\n", "n\n", "k\n", "o\n", "f\n"};
        ByteArrayInputStream originalSystemIn = new ByteArrayInputStream("".getBytes());
        System.setIn(originalSystemIn);

        GameSession.Game game = null;
        try {
            game = new GameSession.Game("tinkoff");
        } catch (GameSession.GameIsOverException e) {
            fail("Invalid word");
        }
        GameSession.Player player = new GameSession.Player();

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

        GameSession.Game game = null;
        try {
            game = new GameSession.Game("tinkoff");
        } catch (GameSession.GameIsOverException e) {
            fail("Invalid word");
        }
        GameSession.Player player = new GameSession.Player();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        game.playTurn(player);

        game.gameOver(player);
        assertEquals(player.getAttemptsUsed(), 0);
        assertFalse(game.isWin());

        System.setIn(originalSystemIn);
    }
}

