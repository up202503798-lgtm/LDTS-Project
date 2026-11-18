package ldts.hungergames.viewer.decision;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.decision.DecisionScreen;
import ldts.hungergames.model.event.GameEvent;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DecisionViewerTest {
    private static final int WIDTH = 80;
    private GUI gui;
    private DecisionScreen screen;

    private static int centered(String text) {
        return (WIDTH - text.length()) / 2;
    }

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        when(gui.getWidth()).thenReturn(WIDTH);
        GameEvent event = new GameEvent("A aliança", "Chega alguém", List.of(
                new Option("Aceitar", Risk.RISKY, "m1"),
                new Option("Recusar", Risk.SAFE, "m2"),
                new Option("Ameaçar", Risk.DEADLY, "m3")));
        screen = new DecisionScreen(event);
    }

    @Test
    void drawsTitleAndText() {
        new DecisionViewer().draw(screen, gui);

        verify(gui).drawText(centered("A aliança"), 3, "A aliança", GuiColor.WHITE);
        verify(gui).drawText(centered("Chega alguém"), 6, "Chega alguém", GuiColor.WHITE);
    }

    @Test
    void drawsTheSelectedOptionInRed() {
        new DecisionViewer().draw(screen, gui);

        verify(gui).drawText(centered("1. Aceitar"), 12, "1. Aceitar", GuiColor.RED);
        verify(gui).drawText(centered("2. Recusar"), 14, "2. Recusar", GuiColor.WHITE);
        verify(gui).drawText(centered("3. Ameaçar"), 16, "3. Ameaçar", GuiColor.WHITE);
    }

    @Test
    void highlightFollowsTheSelection() {
        screen.nextOption();

        new DecisionViewer().draw(screen, gui);

        verify(gui).drawText(centered("1. Aceitar"), 12, "1. Aceitar", GuiColor.WHITE);
        verify(gui).drawText(centered("2. Recusar"), 14, "2. Recusar", GuiColor.RED);
    }
}