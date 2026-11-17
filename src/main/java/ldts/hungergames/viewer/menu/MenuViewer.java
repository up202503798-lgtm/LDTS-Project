package ldts.hungergames.viewer.menu;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.menu.Menu;

import java.util.List;

public class MenuViewer {
    private static final int TITLE_Y = 5;
    private static final int FIRST_OPTION_Y = 12;
    private static final int OPTION_SPACING = 2;

    public void draw(Menu menu, GUI gui) {
        drawTitle(gui, menu.getTitle(), TITLE_Y, GuiColor.WHITE);
        drawOptions(menu, gui);
    }

    private void drawOptions(Menu menu, GUI gui) {
        List<String> options = menu.getOptions();
        for (int i = 0; i < options.size(); i++) {
            GuiColor color = menu.isSelected(i) ? GuiColor.RED : GuiColor.WHITE;
            drawTitle(gui, options.get(i), FIRST_OPTION_Y + i * OPTION_SPACING, color);
        }
    }

    private void drawTitle(GUI gui, String text, int y, GuiColor color) {
        int x = (gui.getWidth() - text.length()) / 2;
        gui.drawText(x, y, text, color);
    }
}
