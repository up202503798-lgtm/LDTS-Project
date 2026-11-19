package ldts.hungergames.controller;

import ldts.hungergames.gui.Action;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ArenaBuilder;
import ldts.hungergames.model.arena.ItemType;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.character.Trait;
import ldts.hungergames.state.StateMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ArenaControllerTest {
    private Arena arena;
    private Character player;
    private ArenaController controller;
    private StateMachine machine;

    @BeforeEach
    void setUp() {
        arena = new ArenaBuilder(10, 8, new Random(1))
                .withBorderWalls()
                .withItemAt(new Position(6, 4), ItemType.SUPPLY)
                .withItemAt(new Position(4, 4), ItemType.MEDKIT)
                .build();
        player = new CharacterFactory().create(Trait.HUNTER, arena.getStartPosition());
        controller = new ArenaController(arena, player);
        machine = mock(StateMachine.class);
    }

    @Test
    void movesInAllDirections() {
        assertTrue(controller.move(player, Action.UP));
        assertEquals(new Position(5, 3), player.getPosition());
        assertTrue(controller.move(player, Action.DOWN));
        assertEquals(new Position(5, 4), player.getPosition());
        assertTrue(controller.move(player, Action.LEFT));
        assertEquals(new Position(4, 4), player.getPosition());
        assertTrue(controller.move(player, Action.RIGHT));
        assertEquals(new Position(5, 4), player.getPosition());
    }

    @Test
    void wallsBlockMovement() {
        player.setPosition(new Position(1, 1));
        assertFalse(controller.move(player, Action.UP));
        assertFalse(controller.move(player, Action.LEFT));
        assertEquals(new Position(1, 1), player.getPosition());

        player.setPosition(new Position(8, 6));
        assertFalse(controller.move(player, Action.DOWN));
        assertFalse(controller.move(player, Action.RIGHT));
        assertEquals(new Position(8, 6), player.getPosition());
    }

    @Test
    void otherActionsDoNotMove() {
        for (Action action : new Action[]{Action.SELECT, Action.ATTACK, Action.QUIT, Action.NONE, null}) {
            assertFalse(controller.move(player, action));
        }
        assertEquals(arena.getStartPosition(), player.getPosition());
    }

    @Test
    void updateMovesThePlayer() {
        controller.update(Action.UP, machine);
        assertEquals(new Position(5, 3), player.getPosition());
    }

    @Test
    void walkingOverASupplyCollectsIt() {
        int before = player.getSupplies();
        controller.update(Action.RIGHT, machine);
        assertEquals(new Position(6, 4), player.getPosition());
        assertEquals(before + 1, player.getSupplies());
        assertTrue(arena.getItemAt(new Position(6, 4)).isEmpty());
    }

    @Test
    void walkingOverAMedkitHeals() {
        player.takeDamage(50);
        int before = player.getHealth();
        controller.update(Action.LEFT, machine);
        assertEquals(before + 20, player.getHealth());
        assertTrue(arena.getItemAt(new Position(4, 4)).isEmpty());
    }

    @Test
    void anyCharacterCanBeMovedByCode() {
        Character other = new CharacterFactory().create(Trait.FAST, new Position(2, 2));
        assertTrue(controller.move(other, Action.RIGHT));
        assertEquals(new Position(3, 2), other.getPosition());
    }
    @Test
    void charactersCannotWalkThroughEachOther() {
        Character other = new CharacterFactory().create(Trait.FAST, new Position(5, 3));
        ArenaController crowded = new ArenaController(arena, player, List.of(other));

        assertFalse(crowded.move(player, Action.UP));
        assertFalse(crowded.move(other, Action.DOWN));
        assertEquals(new Position(5, 4), player.getPosition());
        assertEquals(new Position(5, 3), other.getPosition());
    }
}