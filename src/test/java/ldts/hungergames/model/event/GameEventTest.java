package ldts.hungergames.model.event;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameEventTest {
    private final Option safe = new Option("a", Risk.SAFE, "m1");
    private final Option risky = new Option("b", Risk.RISKY, "m2");
    private final Option deadly = new Option("c", Risk.DEADLY, "m3");

    @Test
    void storesItsValues() {
        GameEvent event = new GameEvent("A aliança", "Chega alguém", List.of(safe, risky, deadly));

        assertEquals("A aliança", event.title());
        assertEquals("Chega alguém", event.text());
        assertEquals(List.of(safe, risky, deadly), event.options());
    }

    @Test
    void requiresExactlyThreeOptions() {
        assertThrows(IllegalArgumentException.class,
                () -> new GameEvent("t", "x", List.of(safe, risky)));
        assertThrows(IllegalArgumentException.class,
                () -> new GameEvent("t", "x", List.of(safe, risky, deadly, safe)));
    }

    @Test
    void rejectsNullOptions() {
        assertThrows(IllegalArgumentException.class, () -> new GameEvent("t", "x", null));
    }

    @Test
    void rejectsNullTitleAndText() {
        assertThrows(NullPointerException.class,
                () -> new GameEvent(null, "x", List.of(safe, risky, deadly)));
        assertThrows(NullPointerException.class,
                () -> new GameEvent("t", null, List.of(safe, risky, deadly)));
    }

    @Test
    void optionsAreADefensiveCopy() {
        List<Option> original = new ArrayList<>(List.of(safe, risky, deadly));
        GameEvent event = new GameEvent("t", "x", original);

        original.set(0, deadly);

        assertEquals(safe, event.options().get(0));
        assertThrows(UnsupportedOperationException.class, () -> event.options().add(safe));
    }
}