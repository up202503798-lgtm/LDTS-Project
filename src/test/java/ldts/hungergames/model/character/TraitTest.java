package ldts.hungergames.model.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TraitTest {
    @Test
    void thereAreSixTraits() {
        assertEquals(6, Trait.values().length);
    }

    @Test
    void everyTraitHasLabelAndDescription() {
        for (Trait trait : Trait.values()) {
            assertFalse(trait.getLabel().isBlank());
            assertFalse(trait.getDescription().isBlank());
        }
    }
}