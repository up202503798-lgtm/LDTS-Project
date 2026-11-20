package ldts.hungergames.controller.decision;

import ldts.hungergames.model.end.EndScreen;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import ldts.hungergames.model.forest.ForestProgress;
import ldts.hungergames.state.GameOverState;
import ldts.hungergames.state.State;
import ldts.hungergames.state.StateMachine;

public class ForestAllianceHandler implements DecisionHandler {
    private final State arena;
    private final ForestProgress progress;

    public ForestAllianceHandler(State arena, ForestProgress progress) {
        this.arena = arena;
        this.progress = progress;
    }

    @Override
    public void onResult(Option option, boolean survived, StateMachine machine) {
        if (!survived) {
            machine.changeState(new GameOverState(EndScreen.gameOver(option.message())));
            return;
        }
        if (option.risk() == Risk.RISKY) {
            progress.makeAlliance();
        }
        machine.changeState(arena);
    }
}