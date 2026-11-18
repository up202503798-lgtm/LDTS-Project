package ldts.hungergames.model.event;

import java.util.Random;

public interface OutcomeStrategy {
    boolean survives(Random random);

    static OutcomeStrategy forRisk(Risk risk) {
        return switch (risk) {
            case SAFE -> new SafeOutcome();
            case RISKY -> new LuckOutcome();
            case DEADLY -> new DeadlyOutcome();
        };
    }
}