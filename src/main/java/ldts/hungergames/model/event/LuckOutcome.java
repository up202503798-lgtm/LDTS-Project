package ldts.hungergames.model.event;

import java.util.Random;

public class LuckOutcome implements OutcomeStrategy {
    @Override
    public boolean survives(Random random) {
        return random.nextBoolean();
    }
}