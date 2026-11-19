package ldts.hungergames.controller;

import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ArenaBuilder;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.character.Trait;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CompetitorControllerTest {
    private static final long INTERVAL = 500;

    private final long[] time = {0};
    private Arena arena;
    private Character player;
    private Character first;
    private Character second;
    private CompetitorController controller;

    private static int distance(Position a, Position b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    @BeforeEach
    void setUp() {
        CharacterFactory factory = new CharacterFactory();
        arena = new ArenaBuilder(10, 8, new Random(1)).withBorderWalls().build();
        player = factory.create(Trait.HUNTER, arena.getStartPosition());
        first = factory.create(Trait.FAST, new Position(2, 2));
        second = factory.create(Trait.HEALER, new Position(7, 2));
        List<Character> competitors = List.of(first, second);
        ArenaController movement = new ArenaController(arena, player, competitors);
        controller = new CompetitorController(movement, competitors, new Random(1), () -> time[0], INTERVAL);
    }

    @Test
    void doesNotMoveBeforeTheInterval() {
        time[0] = INTERVAL - 1;
        controller.update();
        assertEquals(new Position(2, 2), first.getPosition());
        assertEquals(new Position(7, 2), second.getPosition());
    }

    @Test
    void movesEveryCompetitorOneStepAfterTheInterval() {
        time[0] = INTERVAL;
        controller.update();
        assertEquals(1, distance(new Position(2, 2), first.getPosition()));
        assertEquals(1, distance(new Position(7, 2), second.getPosition()));
    }

    @Test
    void neverLeavesTheWalkableArea() {
        for (int i = 0; i < 200; i++) {
            time[0] += INTERVAL;
            controller.update();
            assertTrue(arena.isWalkable(first.getPosition()));
            assertTrue(arena.isWalkable(second.getPosition()));
        }
    }

    @Test
    void neverWalksThroughThePlayer() {
        for (int i = 0; i < 200; i++) {
            time[0] += INTERVAL;
            controller.update();
            assertNotEquals(player.getPosition(), first.getPosition());
            assertNotEquals(player.getPosition(), second.getPosition());
        }
    }
}