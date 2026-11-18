package ldts.hungergames.model.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskTest {
    @Test
    void percentagesMatchTheDesign() {
        assertEquals(100, Risk.SAFE.getPercent());
        assertEquals(50, Risk.RISKY.getPercent());
        assertEquals(0, Risk.DEADLY.getPercent());
    }
}