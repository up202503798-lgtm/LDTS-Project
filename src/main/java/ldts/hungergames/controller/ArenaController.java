package ldts.hungergames.controller;

import ldts.hungergames.gui.Action;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ItemType;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.state.StateMachine;

public class ArenaController {
    private static final int SUPPLY_AMOUNT = 1;
    private static final int MEDKIT_HEAL = 20;

    private final Arena arena;
    private final Character player;

    public ArenaController(Arena arena, Character player) {
        this.arena = arena;
        this.player = player;
    }

    public void update(Action action, StateMachine machine) {
        if (move(player, action)) {
            collectItem();
        }
    }

    // Também serve para mover personagens por código (concorrentes, "tempo do evento" do José).
    public boolean move(Character character, Action direction) {
        Position from = character.getPosition();
        Position target = destination(from, direction);
        if (target.equals(from) || !arena.isWalkable(target)) {
            return false;
        }
        character.setPosition(target);
        return true;
    }

    private Position destination(Position from, Action direction) {
        if (direction == null) {
            return from;
        }
        return switch (direction) {
            case UP -> from.up();
            case DOWN -> from.down();
            case LEFT -> from.left();
            case RIGHT -> from.right();
            default -> from;
        };
    }

    private void collectItem() {
        arena.removeItem(player.getPosition()).ifPresent(this::applyItem);
    }

    private void applyItem(ItemType type) {
        switch (type) {
            case SUPPLY -> player.addSupplies(SUPPLY_AMOUNT);
            case MEDKIT -> player.heal(MEDKIT_HEAL);
        }
    }
}