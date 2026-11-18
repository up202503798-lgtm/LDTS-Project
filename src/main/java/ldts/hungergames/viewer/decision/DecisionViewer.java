package ldts.hungergames.viewer.decision;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.decision.DecisionScreen;
import ldts.hungergames.model.event.GameEvent;
import ldts.hungergames.model.event.Option;

import java.util.List;

public class DecisionViewer {
    private static final int TITLE_Y = 3;
    private static final int TEXT_Y = 6;
    private static final int FIRST_OPTION_Y = 12;
    private static final int OPTION_SPACING = 2;

    public void draw(DecisionScreen screen, GUI gui) {
        GameEvent event = screen.getEvent();
        drawCentered(gui, event.title(), TITLE_Y, GuiColor.WHITE);
        drawCentered(gui, event.text(), TEXT_Y, GuiColor.WHITE);
        drawOptions(screen, gui);
    }

    private void drawOptions(DecisionScreen screen, GUI gui) {
        List<Option> options = screen.getEvent().options();
        for (int i = 0; i < options.size(); i++) {
            GuiColor color = screen.isSelected(i) ? GuiColor.RED : GuiColor.WHITE;
            String line = (i + 1) + ". " + options.get(i).text();
            drawCentered(gui, line, FIRST_OPTION_Y + i * OPTION_SPACING, color);
        }
    }

    private void drawCentered(GUI gui, String text, int y, GuiColor color) {
        int x = (gui.getWidth() - text.length()) / 2;
        gui.drawText(x, y, text, color);
    }
}