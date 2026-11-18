package ldts.hungergames.model.decision;

import ldts.hungergames.model.event.GameEvent;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecisionScreenTest {
    private final Option safe = new Option("a", Risk.SAFE, "m1");
    private final Option risky = new Option("b", Risk.RISKY, "m2");
    private final Option deadly = new Option("c", Risk.DEADLY, "m3");
    private DecisionScreen screen;

    @BeforeEach
    void setUp() {
        screen = new DecisionScreen(new GameEvent("t", "x", List.of(safe, risky, deadly)));
    }

    @Test
    void startsOnTheFirstOption() {
        assertTrue(screen.isSelected(0));
        assertEquals(safe, screen.getSelectedOption());
    }

    @Test
    void nextOptionMovesDownAndStopsAtTheLast() {
        screen.nextOption();
        assertEquals(risky, screen.getSelectedOption());
        screen.nextOption();
        screen.nextOption();
        assertEquals(deadly, screen.getSelectedOption());
        assertTrue(screen.isSelected(2));
        assertFalse(screen.isSelected(0));
    }

    @Test
    void prevOptionStopsAtTheFirst() {
        screen.prevOption();
        assertEquals(safe, screen.getSelectedOption());
        screen.nextOption();
        screen.prevOption();
        assertEquals(safe, screen.getSelectedOption());
    }
}