package ldts.hungergames.controller.decision;

import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import ldts.hungergames.model.forest.ForestProgress;
import ldts.hungergames.state.GameOverState;
import ldts.hungergames.state.State;
import ldts.hungergames.state.StateMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class ForestAllianceHandlerTest {
    private final State arena = mock(State.class);
    private final StateMachine machine = mock(StateMachine.class);
    private final ForestProgress progress = new ForestProgress();
    private final ForestAllianceHandler handler = new ForestAllianceHandler(arena, progress);
    private final Option ally = new Option("a", Risk.RISKY, "m1");
    private final Option refuse = new Option("b", Risk.SAFE, "m2");
    private final Option threaten = new Option("c", Risk.DEADLY, "m3");

    @Test
    void survivingTheAllianceRemembersItAndResumesTheArena() {
        handler.onResult(ally, true, machine);

        assertTrue(progress.isAllied());
        verify(machine).changeState(arena);
    }

    @Test
    void refusingResumesTheArenaWithoutAnAlliance() {
        handler.onResult(refuse, true, machine);

        assertFalse(progress.isAllied());
        verify(machine).changeState(arena);
    }

    @Test
    void dyingGoesToTheEndScreen() {
        handler.onResult(threaten, false, machine);

        assertFalse(progress.isAllied());
        verify(machine).changeState(any(GameOverState.class));
        verify(machine, never()).changeState(arena);
    }

    @Test
    void aBetrayedAllianceIsNotRemembered() {
        handler.onResult(ally, false, machine);

        assertFalse(progress.isAllied());
        verify(machine).changeState(any(GameOverState.class));
    }
}