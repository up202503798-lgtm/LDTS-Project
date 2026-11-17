package ldts.hungergames.controller.menu;

import ldts.hungergames.gui.Action;
import ldts.hungergames.model.menu.Menu;
import ldts.hungergames.state.CharacterSelectState;
import ldts.hungergames.state.StateMachine;

public class MenuController {
    private final Menu menu;
    public MenuController(Menu menu) {
        this.menu = menu;
    }
    public void step(Action action, StateMachine machine){
        switch (action) {
            case UP -> menu.prevOption();
            case DOWN -> menu.nextOption();
            case SELECT -> {
                if(menu.isSelectedStart()){
                    machine.changeState(new CharacterSelectState());
                }
                else{
                    machine.stop();
                }
            }

        }
    }
}
