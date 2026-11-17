package ldts.hungergames.state;

import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;

public class StateMachine {
    private State currentState;
    private boolean running=true;
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
    public boolean isRunning() {
        return running;
    }
    public void stop(){
        this.running=false;
    }
}
