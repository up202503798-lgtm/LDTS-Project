package ldts.hungergames.state;

import ldts.hungergames.controller.end.EndScreenController;
import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.end.EndScreen;
import ldts.hungergames.viewer.end.EndScreenViewer;

public class GameOverState implements State {
    private final EndScreen screen;
    private final EndScreenController controller;
    private final EndScreenViewer viewer;

    public GameOverState() {
        this(EndScreen.gameOver(""));
    }

    public GameOverState(EndScreen screen) {
        this.screen = screen;
        this.controller = new EndScreenController();
        this.viewer = new EndScreenViewer();
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