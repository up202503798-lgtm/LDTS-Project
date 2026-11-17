package ldts.hungergames.state;

import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;

public class StateMachine {
    private State currentState;
    public StateMachine(State firstState) {
        this.currentState = firstState;
    }
    public void changeState(State next){
        this.currentState = next;
    }
    public void update(Action action){
        this.currentState.update(action, this);
    }
    public void draw(GUI gui){
        this.currentState.draw(gui);
    }
}
