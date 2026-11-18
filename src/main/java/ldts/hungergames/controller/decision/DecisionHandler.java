package ldts.hungergames.controller.decision;

import ldts.hungergames.model.event.Option;
import ldts.hungergames.state.StateMachine;

public interface DecisionHandler {
    void onResult(Option option, boolean survived, StateMachine machine);
}