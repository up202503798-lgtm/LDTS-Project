package ldts.hungergames.model.event;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OutcomePropertiesTest {
    @Property
    void safeOptionNeverKills(@ForAll long seed) {
        assertTrue(OutcomeStrategy.forRisk(Risk.SAFE).survives(new Random(seed)));
    }

    @Property
    void deadlyOptionAlwaysKills(@ForAll long seed) {
        assertFalse(OutcomeStrategy.forRisk(Risk.DEADLY).survives(new Random(seed)));
    }
}