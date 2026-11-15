package ldts.hungergames;

import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;

import java.io.IOException;

public class Game {
    private final GUI gui;

    public Game(GUI gui) {
        this.gui = gui;
    }

    public void run() throws IOException {
        Action action = gui.getNextAction();
        while (action != Action.QUIT) {
            gui.clear();
            gui.drawText(1, 2, "HUNGER GAMES", GuiColor.RED);
            gui.refresh();
            waitForNextFrame();
            action = gui.getNextAction();
        }
        gui.close();
    }
    private void waitForNextFrame() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
