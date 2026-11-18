package ldts.hungergames.model.event;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OutcomeStrategyTest {
    private static Random fixed(boolean value) {
        return new Random() {
            @Override
            public boolean nextBoolean() {
                return value;
            }
        };
    }

    @Test
    void factoryPicksTheRightStrategy() {
        assertInstanceOf(SafeOutcome.class, OutcomeStrategy.forRisk(Risk.SAFE));
        assertInstanceOf(LuckOutcome.class, OutcomeStrategy.forRisk(Risk.RISKY));
        assertInstanceOf(DeadlyOutcome.class, OutcomeStrategy.forRisk(Risk.DEADLY));
    }

    @Test
    void safeOutcomeAlwaysSurvives() {
        assertTrue(new SafeOutcome().survives(fixed(false)));
        assertTrue(new SafeOutcome().survives(fixed(true)));
    }

    @Test
    void deadlyOutcomeNeverSurvives() {
        assertFalse(new DeadlyOutcome().survives(fixed(false)));
        assertFalse(new DeadlyOutcome().survives(fixed(true)));
    }

    @Test
    void luckOutcomeFollowsTheRandomGenerator() {
        assertTrue(new LuckOutcome().survives(fixed(true)));
        assertFalse(new LuckOutcome().survives(fixed(false)));
    }
}