package ldts.hungergames.model.arena;

import ldts.hungergames.model.character.Position;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArenaBuilderTest {
    @Test
    void borderWallsCoverTheEdges() {
        Arena arena = new ArenaBuilder(10, 8, new Random(1)).withBorderWalls().build();
        assertEquals(2 * 10 + 2 * (8 - 2), arena.getWalls().size());
        assertTrue(arena.isWall(new Position(0, 0)));
        assertTrue(arena.isWall(new Position(9, 7)));
        assertFalse(arena.isWall(new Position(1, 1)));
    }

    @Test
    void addsTheRequestedNumberOfRandomWalls() {
        Arena arena = new ArenaBuilder(10, 8, new Random(1)).withBorderWalls().withRandomWalls(5).build();
        assertEquals(2 * 10 + 2 * (8 - 2) + 5, arena.getWalls().size());
        assertFalse(arena.isWall(arena.getStartPosition()));
    }

    @Test
    void itemsNeverLandOnWallsOrStart() {
        Arena arena = new ArenaBuilder(10, 8, new Random(1))
                .withBorderWalls()
                .withRandomWalls(10)
                .withItems(ItemType.SUPPLY, 6)
                .build();
        assertEquals(6, arena.getItems().size());
        for (Position position : arena.getItems().keySet()) {
            assertFalse(arena.isWall(position));
            assertNotEquals(arena.getStartPosition(), position);
        }
    }

    @Test
    void sameSeedBuildsTheSameArena() {
        Arena first = ArenaBuilder.initialArena(40, 20, new Random(7));
        Arena second = ArenaBuilder.initialArena(40, 20, new Random(7));
        assertEquals(first.getWalls(), second.getWalls());
        assertEquals(first.getItems(), second.getItems());
    }

    @Test
    void cannotPlaceMoreItemsThanFreeCells() {
        Arena arena = new ArenaBuilder(6, 6, new Random(1)).withBorderWalls().withItems(ItemType.SUPPLY, 1000).build();
        assertEquals(15, arena.getItems().size());
    }

    @Test
    void rejectsTinyArenas() {
        assertThrows(IllegalArgumentException.class, () -> new ArenaBuilder(4, 10, new Random(1)));
        assertThrows(IllegalArgumentException.class, () -> new ArenaBuilder(10, 4, new Random(1)));
    }

    @Test
    void initialArenaScalesWithTheScreenSize() {
        Arena arena = ArenaBuilder.initialArena(80, 24, new Random(1));
        assertEquals(80, arena.getWidth());
        assertEquals(24, arena.getHeight());
        assertEquals(2 * 80 + 2 * (24 - 2) + 19, arena.getWalls().size());
        assertEquals(12 + 4, arena.getItems().size());
    }
    @Test
    void placesAnItemAtAGivenPosition() {
        Arena arena = new ArenaBuilder(10, 8, new Random(1))
                .withItemAt(new Position(3, 3), ItemType.MEDKIT)
                .build();
        assertEquals(ItemType.MEDKIT, arena.getItems().get(new Position(3, 3)));
    }
}