package ldts.hungergames.state;

import ldts.hungergames.controller.ArenaController;
import ldts.hungergames.controller.CompetitorController;
import ldts.hungergames.controller.decision.FirstDecisionHandler;
import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ArenaBuilder;
import ldts.hungergames.model.arena.CompetitorSpawner;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.event.GameEvents;
import ldts.hungergames.viewer.ArenaViewer;

import java.util.List;
import java.util.Random;
import java.util.function.LongSupplier;

public class ArenaState implements State {
    private static final long COMPETITOR_INTERVAL_MILLIS = 500;
    static final int FRAMES_BEFORE_DECISION = 100;

    private final Character player;
    private final Random random;
    private final LongSupplier clock;
    private final ArenaViewer viewer = new ArenaViewer();
    private Arena arena;
    private List<Character> competitors = List.of();
    private ArenaController controller;
    private CompetitorController competitorController;
    private int frames = 0;
    private boolean decisionShown = false;

    public ArenaState(Character player) {
        this(player, new Random());
    }

    public ArenaState(Character player, Random random) {
        this(player, random, System::currentTimeMillis);
    }

    public ArenaState(Character player, Random random, LongSupplier clock) {
        this.player = player;
        this.random = random;
        this.clock = clock;
    }

    @Override
    public void update(Action action, StateMachine machine) {
        if (controller == null) {
            return;
        }
        controller.update(action, machine);
        competitorController.update();
        if (!decisionShown && ++frames >= FRAMES_BEFORE_DECISION) {
            showDecision(machine);
        }
    }

    private void showDecision(StateMachine machine) {
        decisionShown = true;
        machine.changeState(new DecisionState(
                GameEvents.firstDecision(), random, new FirstDecisionHandler(this)));
    }

    @Override
    public void draw(GUI gui) {
        if (arena == null) {
            build(gui);
        }
        viewer.draw(gui, arena, player, competitors);
    }

    private void build(GUI gui) {
        arena = ArenaBuilder.initialArena(gui.getWidth(), gui.getHeight(), random);
        player.setPosition(arena.getStartPosition());
        competitors = new CompetitorSpawner(new CharacterFactory(), random).spawn(arena, player);
        controller = new ArenaController(arena, player, competitors);
        competitorController = new CompetitorController(controller, competitors, random, clock,
                COMPETITOR_INTERVAL_MILLIS);
    }
}