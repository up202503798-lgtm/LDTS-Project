package ldts.hungergames.controller.decision;

import ldts.hungergames.gui.Action;
import ldts.hungergames.model.decision.DecisionScreen;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.OutcomeStrategy;
import ldts.hungergames.state.StateMachine;

import java.util.Random;

public class DecisionController {
    private final DecisionScreen screen;
    private final Random random;
    private final DecisionHandler handler;

    public DecisionController(DecisionScreen screen, Random random, DecisionHandler handler) {
        this.screen = screen;
        this.random = random;
        this.handler = handler;
    }

    public void step(Action action, StateMachine machine) {
        switch (action) {
            case UP -> screen.prevOption();
            case DOWN -> screen.nextOption();
            case SELECT -> resolve(machine);
        }
    }

    private void resolve(StateMachine machine) {
        Option option = screen.getSelectedOption();
        boolean survived = OutcomeStrategy.forRisk(option.risk()).survives(random);
        handler.onResult(option, survived, machine);
    }
}