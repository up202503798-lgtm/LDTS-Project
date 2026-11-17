package ldts.hungergames;

import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.state.StateMachine;

import java.io.IOException;

public class Game {
    private final GUI gui;
    private final StateMachine machine;
    public Game(GUI gui,StateMachine machine) {
        this.gui = gui;
        this.machine = machine;
    }

    public void run() throws IOException {
        try{
            loop();
        }finally {
            gui.close();
        }
    }

    private void loop() throws IOException {
        Action action = gui.getNextAction();
        while (machine.isRunning() && action != Action.QUIT) {
            machine.update(action);
            draw();
            waitForNextFrame();
            action = gui.getNextAction();
        }
    }

    private void draw() throws IOException {
        gui.clear();
        machine.draw(gui);
        gui.refresh();
    }

    private void waitForNextFrame() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
