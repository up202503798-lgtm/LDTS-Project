package ldts.hungergames.model.forest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForestProgressTest {
    @Test
    void startsWithoutAllianceOrSupplies() {
        ForestProgress progress = new ForestProgress();

        assertFalse(progress.isAllied());
        assertEquals(0, progress.getSupplies());
    }

    @Test
    void makingAnAllianceIsRemembered() {
        ForestProgress progress = new ForestProgress();

        progress.makeAlliance();

        assertTrue(progress.isAllied());
    }

    @Test
    void suppliesAccumulate() {
        ForestProgress progress = new ForestProgress();

        progress.addSupplies(1);
        progress.addSupplies(3);

        assertEquals(4, progress.getSupplies());
    }

    @Test
    void negativeSuppliesAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> new ForestProgress().addSupplies(-1));
    }
}