package ldts.hungergames.model.event;

import java.util.Random;

public class DeadlyOutcome implements OutcomeStrategy {
    @Override
    public boolean survives(Random random) {
        return false;
    }
}