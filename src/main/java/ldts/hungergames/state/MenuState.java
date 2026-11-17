package ldts.hungergames.state;

import ldts.hungergames.controller.menu.MenuController;
import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.menu.Menu;
import ldts.hungergames.viewer.menu.MenuViewer;

public class MenuState implements State{
    private final Menu menu;
    private final MenuController controller;
    private final MenuViewer viewer;
    public MenuState() {
        this.menu = new Menu("HungerGames");
        this.controller = new MenuController(menu);
        this.viewer=new MenuViewer();
    }
    @Override
    public void update(Action action, StateMachine machine) {
        controller.step(action, machine);
    }

    @Override
    public void draw(GUI gui) {
        viewer.draw(menu, gui);
    }
}
