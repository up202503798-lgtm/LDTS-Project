package ldts.hungergames.controller.decision;

import ldts.hungergames.gui.Action;
import ldts.hungergames.model.decision.DecisionScreen;
import ldts.hungergames.model.event.GameEvent;
import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import ldts.hungergames.state.StateMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class DecisionControllerTest {
    private final Option safe = new Option("a", Risk.SAFE, "m1");
    private final Option risky = new Option("b", Risk.RISKY, "m2");
    private final Option deadly = new Option("c", Risk.DEADLY, "m3");
    private DecisionHandler handler;
    private StateMachine machine;
    private DecisionController controller;

    @BeforeEach
    void setUp() {
        handler = mock(DecisionHandler.class);
        machine = mock(StateMachine.class);
        DecisionScreen screen = new DecisionScreen(new GameEvent("t", "x", List.of(safe, risky, deadly)));
        controller = new DecisionController(screen, new Random(1), handler);
    }

    @Test
    void selectingTheSafeOptionSurvives() {
        controller.step(Action.SELECT, machine);

        verify(handler).onResult(safe, true, machine);
    }

    @Test
    void selectingTheDeadlyOptionDies() {
        controller.step(Action.DOWN, machine);
        controller.step(Action.DOWN, machine);
        controller.step(Action.SELECT, machine);

        verify(handler).onResult(deadly, false, machine);
    }

    @Test
    void movingUpAndDownChangesWhatIsSelected() {
        controller.step(Action.DOWN, machine);
        controller.step(Action.UP, machine);
        controller.step(Action.SELECT, machine);

        verify(handler).onResult(safe, true, machine);
    }

    @Test
    void otherActionsDoNothing() {
        controller.step(Action.NONE, machine);
        controller.step(Action.LEFT, machine);
        controller.step(Action.RIGHT, machine);
        controller.step(Action.ATTACK, machine);

        verifyNoInteractions(handler);
    }
}