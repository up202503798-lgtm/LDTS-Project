package ldts.hungergames.controller.decision;

import ldts.hungergames.model.end.EndScreen;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import ldts.hungergames.model.forest.ForestProgress;
import ldts.hungergames.state.GameOverState;
import ldts.hungergames.state.StateMachine;

public class ForestResourcesHandler implements DecisionHandler {
    static final int NEARBY_SUPPLIES = 1;
    static final int FAR_SUPPLIES = 3;
    static final String VICTORY_MESSAGE = "A paciência e a estratégia foram as tuas maiores armas. "
            + "Conseguiste sobreviver aos jogos e provar que nem sempre a força é a solução. "
            + "Parabéns, venceste os Hunger Games!";

    private final ForestProgress progress;

    public ForestResourcesHandler(ForestProgress progress) {
        this.progress = progress;
    }

    @Override
    public void onResult(Option option, boolean survived, StateMachine machine) {
        if (!survived) {
            machine.changeState(new GameOverState(EndScreen.gameOver(option.message())));
            return;
        }
        progress.addSupplies(option.risk() == Risk.SAFE ? NEARBY_SUPPLIES : FAR_SUPPLIES);
        machine.changeState(new GameOverState(EndScreen.victory(VICTORY_MESSAGE)));
    }
}