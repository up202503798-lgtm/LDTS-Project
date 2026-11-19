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

    @Test
    void forestAllianceHasThreeOptionsFromRiskyToDeadly() {
        List<Option> options = GameEvents.forestAlliance().options();

        assertEquals(3, options.size());
        assertEquals(Risk.RISKY, options.get(0).risk());
        assertEquals(Risk.SAFE, options.get(1).risk());
        assertEquals(Risk.DEADLY, options.get(2).risk());
    }

    @Test
    void forestResourcesHasThreeOptionsAndAllTheText() {
        GameEvent event = GameEvents.forestResources();

        assertEquals(Risk.SAFE, event.options().get(0).risk());
        assertEquals(Risk.RISKY, event.options().get(1).risk());
        assertEquals(Risk.DEADLY, event.options().get(2).risk());
        assertFalse(event.title().isBlank());
        assertFalse(event.text().isBlank());
        for (Option option : event.options()) {
            assertFalse(option.text().isBlank());
            assertFalse(option.message().isBlank());
        }
    }
}