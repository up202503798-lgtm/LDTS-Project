package ldts.hungergames.model.arena;

import ldts.hungergames.model.character.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new ArenaBuilder(10, 8, new Random(1))
                .withBorderWalls()
                .withItems(ItemType.SUPPLY, 1)
                .build();
    }

    @Test
    void hasDimensionsAndCenterStart() {
        assertEquals(10, arena.getWidth());
        assertEquals(8, arena.getHeight());
        assertEquals(new Position(5, 4), arena.getStartPosition());
    }

    @Test
    void knowsWhatIsInside() {
        assertTrue(arena.isInside(new Position(0, 0)));
        assertTrue(arena.isInside(new Position(9, 7)));
        assertFalse(arena.isInside(new Position(-1, 3)));
        assertFalse(arena.isInside(new Position(10, 3)));
        assertFalse(arena.isInside(new Position(3, -1)));
        assertFalse(arena.isInside(new Position(3, 8)));
    }

    @Test
    void wallsBlockMovement() {
        assertTrue(arena.isWall(new Position(0, 3)));
        assertFalse(arena.isWalkable(new Position(0, 3)));
        assertTrue(arena.isWalkable(arena.getStartPosition()));
        assertFalse(arena.isWalkable(new Position(20, 20)));
    }

    @Test
    void itemCanBeFoundAndRemoved() {
        Position itemPosition = arena.getItems().keySet().iterator().next();
        assertEquals(Optional.of(ItemType.SUPPLY), arena.getItemAt(itemPosition));
        assertEquals(Optional.of(ItemType.SUPPLY), arena.removeItem(itemPosition));
        assertTrue(arena.getItemAt(itemPosition).isEmpty());
        assertTrue(arena.removeItem(itemPosition).isEmpty());
    }

    @Test
    void emptyPositionHasNoItem() {
        assertTrue(arena.getItemAt(arena.getStartPosition()).isEmpty());
    }

    @Test
    void exposedCollectionsCannotBeModified() {
        assertThrows(UnsupportedOperationException.class, () -> arena.getWalls().add(new Position(3, 3)));
        assertThrows(UnsupportedOperationException.class, () -> arena.getItems().clear());
    }
}