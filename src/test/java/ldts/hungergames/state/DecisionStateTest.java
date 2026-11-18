package ldts.hungergames.state;

import ldts.hungergames.controller.decision.DecisionHandler;
import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.event.GameEvent;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DecisionStateTest {
    private final Option safe = new Option("a", Risk.SAFE, "m1");
    private final Option risky = new Option("b", Risk.RISKY, "m2");
    private final Option deadly = new Option("c", Risk.DEADLY, "m3");
    private DecisionHandler handler;
    private StateMachine machine;
    private DecisionState state;

    @BeforeEach
    void setUp() {
        handler = mock(DecisionHandler.class);
        machine = mock(StateMachine.class);
        GameEvent event = new GameEvent("Titulo", "Texto", List.of(safe, risky, deadly));
        state = new DecisionState(event, new Random(1), handler);
    }

    @Test
    void updateForwardsTheActionToTheController() {
        state.update(Action.SELECT, machine);

        verify(handler).onResult(safe, true, machine);
    }

    @Test
    void drawShowsTheEventTitle() {
        GUI gui = mock(GUI.class);
        when(gui.getWidth()).thenReturn(80);

        state.draw(gui);

        verify(gui).drawText(anyInt(), anyInt(), org.mockito.ArgumentMatchers.eq("Titulo"),
                org.mockito.ArgumentMatchers.eq(GuiColor.WHITE));
    }
}