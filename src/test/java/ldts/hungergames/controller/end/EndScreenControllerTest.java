package ldts.hungergames.controller.end;

import ldts.hungergames.gui.Action;
import ldts.hungergames.state.MenuState;
import ldts.hungergames.state.StateMachine;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class EndScreenControllerTest {
    private final StateMachine machine = mock(StateMachine.class);
    private final EndScreenController controller = new EndScreenController();

    @Test
    void selectGoesBackToTheMenu() {
        controller.step(Action.SELECT, machine);

        verify(machine).changeState(any(MenuState.class));
    }

    @Test
    void otherActionsDoNothing() {
        controller.step(Action.NONE, machine);
        controller.step(Action.UP, machine);
        controller.step(Action.ATTACK, machine);

        verifyNoInteractions(machine);
    }
}