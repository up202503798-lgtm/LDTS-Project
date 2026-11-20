package ldts.hungergames.controller.decision;

import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.end.EndScreen;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import ldts.hungergames.state.GameOverState;
import ldts.hungergames.state.State;
import ldts.hungergames.state.StateMachine;

import java.util.Random;

public class FirstDecisionHandler implements DecisionHandler {
    private final State arena;
    private final Character player;
    private final Random random;

    public FirstDecisionHandler(State arena, Character player, Random random) {
        this.arena = arena;
        this.player = player;
        this.random = random;
    }

    @Override
    public void onResult(Option option, boolean survived, StateMachine machine) {
        if (!survived) {
            machine.changeState(new GameOverState(EndScreen.gameOver(option.message())));
        } else if (option.risk() == Risk.SAFE) {
            machine.changeState(ForestArenaFactory.create(player, random, System::currentTimeMillis));
        } else {
            machine.changeState(arena);
        }
    }
}