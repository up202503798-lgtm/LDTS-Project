package ldts.hungergames.state;

import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class ArenaStateDecisionTest {
    private final StateMachine machine = mock(StateMachine.class);
    private ArenaState state;

    @BeforeEach
    void setUp() {
        Character player = new CharacterFactory().createAll(new Position(0, 0)).get(0);
        state = new ArenaState(player, new Random(1));
        GUI gui = mock(GUI.class);
        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(24);
        state.draw(gui);
    }

    @Test
    void noDecisionBeforeTheFrameLimit() {
        for (int i = 0; i < ArenaState.FRAMES_BEFORE_DECISION - 1; i++) {
            state.update(Action.NONE, machine);
        }

        verifyNoInteractions(machine);
    }

    @Test
    void decisionAppearsAtTheFrameLimit() {
        for (int i = 0; i < ArenaState.FRAMES_BEFORE_DECISION; i++) {
            state.update(Action.NONE, machine);
        }

        verify(machine).changeState(any(DecisionState.class));
    }

    @Test
    void decisionAppearsOnlyOnce() {
        for (int i = 0; i < ArenaState.FRAMES_BEFORE_DECISION * 3; i++) {
            state.update(Action.NONE, machine);
        }

        verify(machine, times(1)).changeState(any(DecisionState.class));
    }
}