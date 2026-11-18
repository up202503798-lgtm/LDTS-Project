package ldts.hungergames.model.event;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GameEventsTest {
    @Test
    void firstDecisionHasThreeOptionsFromSafestToDeadliest() {
        List<Option> options = GameEvents.firstDecision().options();

        assertEquals(3, options.size());
        assertEquals(Risk.SAFE, options.get(0).risk());
        assertEquals(Risk.RISKY, options.get(1).risk());
        assertEquals(Risk.DEADLY, options.get(2).risk());
    }

    @Test
    void firstDecisionHasTextForEverything() {
        GameEvent event = GameEvents.firstDecision();

        assertFalse(event.title().isBlank());
        assertFalse(event.text().isBlank());
        for (Option option : event.options()) {
            assertFalse(option.text().isBlank());
            assertFalse(option.message().isBlank());
        }
    }
}