package ldts.hungergames.event;

import java.util.Objects;

public record Option(String text, Risk risk, String message) {
    public Option {
        Objects.requireNonNull(text);
        Objects.requireNonNull(risk);
        Objects.requireNonNull(message);
    }
}