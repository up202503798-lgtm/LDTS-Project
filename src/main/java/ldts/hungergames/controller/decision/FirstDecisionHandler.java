package ldts.hungergames.controller.decision;

import ldts.hungergames.model.end.EndScreen;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import ldts.hungergames.state.GameOverState;
import ldts.hungergames.state.State;
import ldts.hungergames.state.StateMachine;

public class FirstDecisionHandler implements DecisionHandler {
    private final State arena;

    public FirstDecisionHandler(State arena) {
        this.arena = arena;
    }

    @Override
    public void onResult(Option option, boolean survived, StateMachine machine) {
        if (!survived) {
            machine.changeState(new GameOverState(EndScreen.gameOver(option.message())));
        } else if (option.risk() == Risk.SAFE) {
            machine.changeState(new GameOverState(EndScreen.comingSoon(option.message())));
        } else {
            machine.changeState(arena);
        }
    }
}