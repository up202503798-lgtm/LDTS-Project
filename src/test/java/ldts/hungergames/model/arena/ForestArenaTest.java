package ldts.hungergames.model.arena;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForestArenaTest {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 24;

    @Test
    void forestHasMoreTreesThanTheInitialArena() {
        Arena initial = ArenaBuilder.initialArena(WIDTH, HEIGHT, new Random(1));
        Arena forest = ArenaBuilder.forestArena(WIDTH, HEIGHT, new Random(1));

        assertTrue(forest.getWalls().size() > initial.getWalls().size());
    }

    @Test
    void forestHasSuppliesButNoMedkits() {
        Arena forest = ArenaBuilder.forestArena(WIDTH, HEIGHT, new Random(1));

        assertTrue(forest.getItems().containsValue(ItemType.SUPPLY));
        assertFalse(forest.getItems().containsValue(ItemType.MEDKIT));
    }

    @Test
    void theStartPositionIsFree() {
        Arena forest = ArenaBuilder.forestArena(WIDTH, HEIGHT, new Random(1));

        assertTrue(forest.isWalkable(forest.getStartPosition()));
    }

    @Test
    void forTypePicksTheMatchingArena() {
        Arena forest = ArenaBuilder.forType(ArenaType.FOREST, WIDTH, HEIGHT, new Random(1));
        Arena initial = ArenaBuilder.forType(ArenaType.INITIAL, WIDTH, HEIGHT, new Random(1));

        assertEquals(ArenaBuilder.forestArena(WIDTH, HEIGHT, new Random(1)).getWalls(), forest.getWalls());
        assertEquals(ArenaBuilder.initialArena(WIDTH, HEIGHT, new Random(1)).getWalls(), initial.getWalls());
    }
}