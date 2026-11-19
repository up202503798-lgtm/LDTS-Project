package ldts.hungergames.controller.decision;

import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import ldts.hungergames.state.GameOverState;
import ldts.hungergames.state.State;
import ldts.hungergames.state.StateMachine;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class FirstDecisionHandlerTest {
    private final State arena = mock(State.class);
    private final StateMachine machine = mock(StateMachine.class);
    private final FirstDecisionHandler handler = new FirstDecisionHandler(arena);
    private final Option safe = new Option("a", Risk.SAFE, "m1");
    private final Option risky = new Option("b", Risk.RISKY, "m2");
    private final Option deadly = new Option("c", Risk.DEADLY, "m3");

    @Test
    void dyingGoesToTheEndScreen() {
        handler.onResult(deadly, false, machine);

        verify(machine).changeState(any(GameOverState.class));
        verify(machine, never()).changeState(arena);
    }

    @Test
    void failingTheRiskyOptionGoesToTheEndScreen() {
        handler.onResult(risky, false, machine);

        verify(machine).changeState(any(GameOverState.class));
        verify(machine, never()).changeState(arena);
    }

    @Test
    void theSafeOptionGoesToTheComingSoonScreen() {
        handler.onResult(safe, true, machine);

        verify(machine).changeState(any(GameOverState.class));
        verify(machine, never()).changeState(arena);
    }

    @Test
    void survivingTheRiskyOptionResumesTheSameArena() {
        handler.onResult(risky, true, machine);

        verify(machine).changeState(arena);
    }
}