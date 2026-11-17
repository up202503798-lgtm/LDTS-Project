package model.event;

import java.util.Random;

public class SafeOutcome implements OutcomeStrategy {
    @Override
    public boolean survives(Random random) {
        return true;
    }
}