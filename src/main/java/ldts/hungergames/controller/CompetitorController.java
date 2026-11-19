package ldts.hungergames.controller;

import ldts.hungergames.gui.Action;
import ldts.hungergames.model.character.Character;

import java.util.List;
import java.util.Random;
import java.util.function.LongSupplier;

public class CompetitorController {
    private static final Action[] DIRECTIONS = {Action.UP, Action.DOWN, Action.LEFT, Action.RIGHT};

    private final ArenaController movement;
    private final List<Character> competitors;
    private final Random random;
    private final LongSupplier clock;
    private final long intervalMillis;
    private long lastMove;

    public CompetitorController(ArenaController movement, List<Character> competitors, Random random,
                                LongSupplier clock, long intervalMillis) {
        this.movement = movement;
        this.competitors = List.copyOf(competitors);
        this.random = random;
        this.clock = clock;
        this.intervalMillis = intervalMillis;
        this.lastMove = clock.getAsLong();
    }

    public void update() {
        long now = clock.getAsLong();
        if (now - lastMove < intervalMillis) {
            return;
        }
        lastMove = now;
        for (Character competitor : competitors) {
            movement.move(competitor, DIRECTIONS[random.nextInt(DIRECTIONS.length)]);
        }
    }
}