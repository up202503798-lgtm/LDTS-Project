package ldts.hungergames.controller.decision;

import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import ldts.hungergames.state.ArenaState;
import ldts.hungergames.state.GameOverState;
import ldts.hungergames.state.State;
import ldts.hungergames.state.StateMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class FirstDecisionHandlerTest {
    private final State arena = mock(State.class);
    private final StateMachine machine = mock(StateMachine.class);
    private final Option safe = new Option("a", Risk.SAFE, "m1");
    private final Option risky = new Option("b", Risk.RISKY, "m2");
    private final Option deadly = new Option("c", Risk.DEADLY, "m3");
    private FirstDecisionHandler handler;

    @BeforeEach
    void setUp() {
        Character player = new CharacterFactory().createAll(new Position(0, 0)).get(0);
        handler = new FirstDecisionHandler(arena, player, new Random(1));
    }

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
    void theSafeOptionGoesToTheForest() {
        handler.onResult(safe, true, machine);

        verify(machine).changeState(any(ArenaState.class));
        verify(machine, never()).changeState(arena);
    }

    @Test
    void survivingTheRiskyOptionResumesTheSameArena() {
        handler.onResult(risky, true, machine);

        verify(machine).changeState(arena);
    }
}