package ldts.hungergames.model.end;

import java.util.Objects;

public record EndScreen(String title, String message, String footer) {
    public EndScreen {
        Objects.requireNonNull(title);
        Objects.requireNonNull(message);
        Objects.requireNonNull(footer);
    }

    public static EndScreen gameOver(String message) {
        return new EndScreen("GAME OVER", message,
                "A tua jornada chegou ao fim. Boa sorte para a próxima!");
    }

    public static EndScreen victory(String message) {
        return new EndScreen("VITÓRIA", message, "Prima Enter para voltar ao menu.");
    }

    public static EndScreen comingSoon(String message) {
        return new EndScreen("EM BREVE", message,
                "Esta parte do jogo ainda está em desenvolvimento.");
    }
}