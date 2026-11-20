package ldts.hungergames.controller.decision;

import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.state.ArenaState;
import ldts.hungergames.state.DecisionState;
import ldts.hungergames.state.StateMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ForestArenaFactoryTest {
    private final StateMachine machine = mock(StateMachine.class);
    private ArenaState forest;

    @BeforeEach
    void setUp() {
        Character player = new CharacterFactory().createAll(new Position(0, 0)).get(0);
        forest = ForestArenaFactory.create(player, new Random(1), () -> 0L);
        GUI gui = mock(GUI.class);
        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(24);
        forest.draw(gui);
    }

    private void play(int frames) {
        for (int i = 0; i < frames; i++) {
            forest.update(Action.NONE, machine);
        }
    }

    @Test
    void theAllianceAppearsFirst() {
        play(ArenaState.FRAMES_BEFORE_DECISION);

        verify(machine, times(1)).changeState(any(DecisionState.class));
    }

    @Test
    void theResourcesAppearAfterTheAlliance() {
        play(ArenaState.FRAMES_BEFORE_DECISION * 2);

        verify(machine, times(2)).changeState(any(DecisionState.class));
    }

    @Test
    void nothingMoreAppearsAfterTheTwoEvents() {
        play(ArenaState.FRAMES_BEFORE_DECISION * 5);

        verify(machine, times(2)).changeState(any(DecisionState.class));
    }
}