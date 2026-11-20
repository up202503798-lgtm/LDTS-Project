package ldts.hungergames.state;

import ldts.hungergames.controller.ArenaController;
import ldts.hungergames.controller.CompetitorController;
import ldts.hungergames.controller.decision.FirstDecisionHandler;
import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ArenaBuilder;
import ldts.hungergames.model.arena.ArenaType;
import ldts.hungergames.model.arena.CompetitorSpawner;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.event.GameEvents;
import ldts.hungergames.viewer.ArenaViewer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.LongSupplier;

public class ArenaState implements State {
    private static final long COMPETITOR_INTERVAL_MILLIS = 500;
    public static final int FRAMES_BEFORE_DECISION = 100;

    private final Character player;
    private final Random random;
    private final LongSupplier clock;
    private final ArenaType type;
    private final ArenaViewer viewer;
    private final Deque<Function<State, State>> decisions;
    private Arena arena;
    private List<Character> competitors = List.of();
    private ArenaController controller;
    private CompetitorController competitorController;
    private int frames = 0;

    public ArenaState(Character player) {
        this(player, new Random());
    }

    public ArenaState(Character player, Random random) {
        this(player, random, System::currentTimeMillis);
    }

    public ArenaState(Character player, Random random, LongSupplier clock) {
        this(player, random, clock, ArenaType.INITIAL, List.of(
                arena -> new DecisionState(GameEvents.firstDecision(), random,
                        new FirstDecisionHandler(arena, player, random))));
    }

    public ArenaState(Character player, Random random, LongSupplier clock, ArenaType type,
                      List<Function<State, State>> decisions) {
        this.player = player;
        this.random = random;
        this.clock = clock;
        this.type = type;
        this.viewer = new ArenaViewer(type);
        this.decisions = new ArrayDeque<>(decisions);
    }

    @Override
    public void update(Action action, StateMachine machine) {
        if (controller == null) {
            return;
        }
        controller.update(action, machine);
        competitorController.update();
        if (!decisions.isEmpty() && ++frames >= FRAMES_BEFORE_DECISION) {
            showDecision(machine);
        }
    }

    private void showDecision(StateMachine machine) {
        frames = 0;
        machine.changeState(decisions.poll().apply(this));
    }

    @Override
    public void draw(GUI gui) {
        if (arena == null) {
            build(gui);
        }
        viewer.draw(gui, arena, player, competitors);
    }

    private void build(GUI gui) {
        arena = ArenaBuilder.forType(type, gui.getWidth(), gui.getHeight(), random);
        player.setPosition(arena.getStartPosition());
        competitors = new CompetitorSpawner(new CharacterFactory(), random).spawn(arena, player);
        controller = new ArenaController(arena, player, competitors);
        competitorController = new CompetitorController(controller, competitors, random, clock,
                COMPETITOR_INTERVAL_MILLIS);
    }
}