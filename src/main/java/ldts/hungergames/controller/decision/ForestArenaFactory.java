package ldts.hungergames.controller.decision;

import ldts.hungergames.model.arena.ArenaType;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.event.GameEvents;
import ldts.hungergames.model.forest.ForestProgress;
import ldts.hungergames.state.ArenaState;
import ldts.hungergames.state.DecisionState;

import java.util.List;
import java.util.Random;
import java.util.function.LongSupplier;

public final class ForestArenaFactory {
    private ForestArenaFactory() {
    }

    public static ArenaState create(Character player, Random random, LongSupplier clock) {
        ForestProgress progress = new ForestProgress();
        return new ArenaState(player, random, clock, ArenaType.FOREST, List.of(
                arena -> new DecisionState(GameEvents.forestAlliance(), random,
                        new ForestAllianceHandler(arena, progress)),
                arena -> new DecisionState(GameEvents.forestResources(), random,
                        new ForestResourcesHandler(progress))));
    }
}