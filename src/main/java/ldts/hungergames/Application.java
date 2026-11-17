package ldts.hungergames;

import ldts.hungergames.gui.LanternaGUI;
import ldts.hungergames.model.menu.Menu;
import ldts.hungergames.state.MenuState;
import ldts.hungergames.state.StateMachine;

import java.io.IOException;

public class Application {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    public static void main(String[] args) throws IOException {
        new Game(new LanternaGUI(WIDTH, HEIGHT),new StateMachine(new MenuState())).run();
    }
}
