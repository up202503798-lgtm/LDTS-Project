package ldts.hungergames.viewer.end;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.end.EndScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EndScreenViewerTest {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 24;
    private GUI gui;

    private static int centered(String text) {
        return (WIDTH - text.length()) / 2;
    }

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        when(gui.getWidth()).thenReturn(WIDTH);
        when(gui.getHeight()).thenReturn(HEIGHT);
    }

    @Test
    void drawsTheTitleInRed() {
        new EndScreenViewer().draw(new EndScreen("FIM", "msg", "rodape"), gui);

        verify(gui).drawText(centered("FIM"), 4, "FIM", GuiColor.RED);
    }

    @Test
    void drawsAShortMessageOnOneLineAndTheFooterBelow() {
        new EndScreenViewer().draw(new EndScreen("FIM", "msg", "rodape"), gui);

        verify(gui).drawText(centered("msg"), 8, "msg", GuiColor.WHITE);
        verify(gui).drawText(centered("rodape"), 10, "rodape", GuiColor.WHITE);
    }

    @Test
    void wrapsALongMessageAcrossLines() {
        String word = "aaaaaaaaa";
        String message = String.join(" ", Collections.nCopies(10, word));
        String firstLine = String.join(" ", Collections.nCopies(6, word));
        String secondLine = String.join(" ", Collections.nCopies(4, word));

        new EndScreenViewer().draw(new EndScreen("FIM", message, "f"), gui);

        verify(gui).drawText(centered(firstLine), 8, firstLine, GuiColor.WHITE);
        verify(gui).drawText(centered(secondLine), 9, secondLine, GuiColor.WHITE);
        verify(gui).drawText(centered("f"), 11, "f", GuiColor.WHITE);
    }

    @Test
    void drawsTheHintNearTheBottom() {
        new EndScreenViewer().draw(new EndScreen("FIM", "msg", "rodape"), gui);

        String hint = "Prima Enter para continuar";
        verify(gui).drawText(centered(hint), HEIGHT - 2, hint, GuiColor.WHITE);
    }
}