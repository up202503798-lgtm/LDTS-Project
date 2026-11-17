package ldts.hungergames.controller;

import ldts.hungergames.gui.Action;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.selection.CharacterSelectionModel;
import ldts.hungergames.state.ArenaState;
import ldts.hungergames.state.StateMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class CharacterSelectionControllerTest {
    private CharacterSelectionModel model;
    private CharacterSelectionController controller;
    private StateMachine machine;

    @BeforeEach
    void setUp() {
        model = new CharacterSelectionModel(new CharacterFactory().createAll(new Position(0, 0)));
        controller = new CharacterSelectionController(model);
        machine = mock(StateMachine.class);
    }

    @Test
    void rightSelectsNext() {
        controller.update(Action.RIGHT, machine);
        assertEquals(1, model.getSelectedIndex());
    }

    @Test
    void leftSelectsPreviousAndWraps() {
        controller.update(Action.LEFT, machine);
        assertEquals(5, model.getSelectedIndex());
    }

    @Test
    void selectGoesToArena() {
        controller.update(Action.SELECT, machine);
        verify(machine).changeState(any(ArenaState.class));
    }

    @Test
    void otherActionsDoNothing() {
        controller.update(Action.UP, machine);
        assertEquals(0, model.getSelectedIndex());
        verify(machine, never()).changeState(any());
    }

    @Test
    void noneActionDoesNothing() {
        controller.update(Action.NONE, machine);
        assertEquals(0, model.getSelectedIndex());
        verify(machine, never()).changeState(any());
    }

    @Test
    void nullActionIsIgnored() {
        controller.update(null, machine);
        assertEquals(0, model.getSelectedIndex());
        verify(machine, never()).changeState(any());
    }
}
