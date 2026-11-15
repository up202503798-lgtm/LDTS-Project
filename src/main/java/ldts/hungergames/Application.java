package ldts.hungergames;

import ldts.hungergames.gui.LanternaGUI;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        new Game(new LanternaGUI(80, 30)).run();
    }
}
