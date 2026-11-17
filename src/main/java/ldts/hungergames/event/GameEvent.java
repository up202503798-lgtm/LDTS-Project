package ldts.hungergames.event;

import java.util.List;
import java.util.Objects;

public record GameEvent(String title, String text, List<Option> options) {
    public GameEvent {
        Objects.requireNonNull(title);
        Objects.requireNonNull(text);
        if (options == null || options.size() != 3) {
            throw new IllegalArgumentException("Um evento tem de ter exatamente 3 opções");
        }
        options = List.copyOf(options);
    }
}