package ldts.hungergames.viewer.end;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.end.EndScreen;

import java.util.ArrayList;
import java.util.List;

public class EndScreenViewer {
    private static final int TITLE_Y = 4;
    private static final int MESSAGE_Y = 8;
    private static final int SIDE_MARGIN = 10;
    private static final int HINT_OFFSET = 2;
    private static final String HINT = "Prima Enter para continuar";

    public void draw(EndScreen screen, GUI gui) {
        int maxWidth = gui.getWidth() - 2 * SIDE_MARGIN;
        drawCentered(gui, screen.title(), TITLE_Y, GuiColor.RED);
        int y = drawParagraph(gui, screen.message(), MESSAGE_Y, maxWidth);
        drawParagraph(gui, screen.footer(), y + 1, maxWidth);
        drawCentered(gui, HINT, gui.getHeight() - HINT_OFFSET, GuiColor.WHITE);
    }

    private int drawParagraph(GUI gui, String text, int startY, int maxWidth) {
        int y = startY;
        for (String line : wrap(text, maxWidth)) {
            drawCentered(gui, line, y, GuiColor.WHITE);
            y++;
        }
        return y;
    }

    private List<String> wrap(String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        for (String word : text.split(" ")) {
            if (current.length() > 0 && current.length() + 1 + word.length() > maxWidth) {
                lines.add(current.toString());
                current = new StringBuilder();
            }
            if (current.length() > 0) {
                current.append(' ');
            }
            current.append(word);
        }
        if (current.length() > 0) {
            lines.add(current.toString());
        }
        return lines;
    }

    private void drawCentered(GUI gui, String text, int y, GuiColor color) {
        int x = (gui.getWidth() - text.length()) / 2;
        gui.drawText(x, y, text, color);
    }
}