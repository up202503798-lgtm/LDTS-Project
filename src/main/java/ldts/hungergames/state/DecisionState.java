package ldts.hungergames.state;

import ldts.hungergames.controller.decision.DecisionController;
import ldts.hungergames.controller.decision.DecisionHandler;
import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.decision.DecisionScreen;
import ldts.hungergames.model.event.GameEvent;
import ldts.hungergames.viewer.decision.DecisionViewer;

import java.util.Random;

public class DecisionState implements State {
    private final DecisionScreen screen;
    private final DecisionController controller;
    private final DecisionViewer viewer;

    public DecisionState(GameEvent event, Random random, DecisionHandler handler) {
        this.screen = new DecisionScreen(event);
        this.controller = new DecisionController(screen, random, handler);
        this.viewer = new DecisionViewer();
    }

    @Override
    public void update(Action action, StateMachine machine) {
        controller.step(action, machine);
    }

    @Override
    public void draw(GUI gui) {
        viewer.draw(screen, gui);
    }
}

