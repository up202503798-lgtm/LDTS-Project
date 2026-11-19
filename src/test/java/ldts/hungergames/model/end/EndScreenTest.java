package ldts.hungergames.model.end;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EndScreenTest {
    @Test
    void gameOverKeepsTheMessageAndUsesTheClosingPhrase() {
        EndScreen screen = EndScreen.gameOver("Não hesites.");

        assertEquals("GAME OVER", screen.title());
        assertEquals("Não hesites.", screen.message());
        assertEquals("A tua jornada chegou ao fim. Boa sorte para a próxima!", screen.footer());
    }

    @Test
    void victoryHasItsOwnTitle() {
        EndScreen screen = EndScreen.victory("Venceste.");

        assertEquals("VITÓRIA", screen.title());
        assertEquals("Venceste.", screen.message());
    }

    @Test
    void comingSoonHasItsOwnTitle() {
        EndScreen screen = EndScreen.comingSoon("Floresta.");

        assertEquals("EM BREVE", screen.title());
        assertEquals("Floresta.", screen.message());
    }

    @Test
    void rejectsNullValues() {
        assertThrows(NullPointerException.class, () -> new EndScreen(null, "m", "f"));
        assertThrows(NullPointerException.class, () -> new EndScreen("t", null, "f"));
        assertThrows(NullPointerException.class, () -> new EndScreen("t", "m", null));
    }
}