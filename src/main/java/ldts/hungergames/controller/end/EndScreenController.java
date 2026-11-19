package ldts.hungergames.controller.end;

import ldts.hungergames.gui.Action;
import ldts.hungergames.state.MenuState;
import ldts.hungergames.state.StateMachine;

public class EndScreenController {
    public void step(Action action, StateMachine machine) {
        if (action == Action.SELECT) {
            machine.changeState(new MenuState());
        }
    }
}