package ldts.hungergames.state;

import ldts.hungergames.controller.ArenaController;
import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ArenaBuilder;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.viewer.ArenaViewer;

import java.util.Random;

public class ArenaState implements State {
    private final Character player;
    private final Random random;
    private final ArenaViewer viewer = new ArenaViewer();
    private Arena arena;
    private ArenaController controller;

    public ArenaState(Character player) {
        this(player, new Random());
    }

    public ArenaState(Character player, Random random) {
        this.player = player;
        this.random = random;
    }

    @Override
    public void update(Action action, StateMachine machine) {
        if (controller != null) {
            controller.update(action, machine);
        }
    }

    @Override
    public void draw(GUI gui) {
        if (arena == null) {
            arena = ArenaBuilder.initialArena(gui.getWidth(), gui.getHeight(), random);
            player.setPosition(arena.getStartPosition());
            controller = new ArenaController(arena, player);
        }
        viewer.draw(gui, arena, player);
    }
}