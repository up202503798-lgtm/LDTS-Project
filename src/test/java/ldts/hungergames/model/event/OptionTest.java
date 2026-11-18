package ldts.hungergames.model.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionTest {
    @Test
    void storesItsValues() {
        Option option = new Option("Fugir", Risk.SAFE, "Escapaste");

        assertEquals("Fugir", option.text());
        assertEquals(Risk.SAFE, option.risk());
        assertEquals("Escapaste", option.message());
    }

    @Test
    void rejectsNullText() {
        assertThrows(NullPointerException.class, () -> new Option(null, Risk.SAFE, "m"));
    }

    @Test
    void rejectsNullRisk() {
        assertThrows(NullPointerException.class, () -> new Option("t", null, "m"));
    }

    @Test
    void rejectsNullMessage() {
        assertThrows(NullPointerException.class, () -> new Option("t", Risk.SAFE, null));
    }
}
