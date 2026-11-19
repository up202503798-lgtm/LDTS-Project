package ldts.hungergames.controller;

import ldts.hungergames.gui.Action;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ItemType;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.state.StateMachine;

import java.util.List;

public class ArenaController {
    private static final int SUPPLY_AMOUNT = 1;
    private static final int MEDKIT_HEAL = 20;

    private final Arena arena;
    private final Character player;
    private final List<Character> competitors;

    public ArenaController(Arena arena, Character player) {
        this(arena, player, List.of());
    }

    public ArenaController(Arena arena, Character player, List<Character> competitors) {
        this.arena = arena;
        this.player = player;
        this.competitors = List.copyOf(competitors);
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
        if (target.equals(from) || !arena.isWalkable(target) || isOccupied(target, character)) {
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

    private boolean isOccupied(Position target, Character mover) {
        if (player != mover && player.getPosition().equals(target)) {
            return true;
        }
        for (Character competitor : competitors) {
            if (competitor != mover && competitor.getPosition().equals(target)) {
                return true;
            }
        }
        return false;
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