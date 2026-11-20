package ldts.hungergames.controller.decision;

import ldts.hungergames.model.event.Option;
import ldts.hungergames.model.event.Risk;
import ldts.hungergames.model.forest.ForestProgress;
import ldts.hungergames.state.GameOverState;
import ldts.hungergames.state.StateMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ForestResourcesHandlerTest {
    private final StateMachine machine = mock(StateMachine.class);
    private final ForestProgress progress = new ForestProgress();
    private final ForestResourcesHandler handler = new ForestResourcesHandler(progress);
    private final Option nearby = new Option("a", Risk.SAFE, "m1");
    private final Option far = new Option("b", Risk.RISKY, "m2");
    private final Option hide = new Option("c", Risk.DEADLY, "m3");

    @Test
    void searchingNearbyFindsAFewSuppliesAndWins() {
        handler.onResult(nearby, true, machine);

        assertEquals(ForestResourcesHandler.NEARBY_SUPPLIES, progress.getSupplies());
        verify(machine).changeState(any(GameOverState.class));
    }

    @Test
    void searchingFarFindsMoreSuppliesAndWins() {
        handler.onResult(far, true, machine);

        assertEquals(ForestResourcesHandler.FAR_SUPPLIES, progress.getSupplies());
        verify(machine).changeState(any(GameOverState.class));
    }

    @Test
    void dyingFindsNothing() {
        handler.onResult(hide, false, machine);
        handler.onResult(far, false, machine);

        assertEquals(0, progress.getSupplies());
    }
}