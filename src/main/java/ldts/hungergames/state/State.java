package ldts.hungergames.state;

import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;

public interface State {
    void update(Action action, StateMachine machine);
    void draw(GUI gui);
}
