package ldts.hungergames.model.arena;

import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.character.Trait;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CompetitorSpawnerTest {
    private final CharacterFactory factory = new CharacterFactory();

    private Character playerAt(Arena arena) {
        return factory.create(Trait.HUNTER, arena.getStartPosition());
    }

    @Test
    void spawnsTheFiveOtherArchetypes() {
        Arena arena = new ArenaBuilder(20, 10, new Random(1)).withBorderWalls().build();
        Character player = playerAt(arena);

        List<Character> competitors = new CompetitorSpawner(factory, new Random(1)).spawn(arena, player);

        assertEquals(5, competitors.size());
        for (Character competitor : competitors) {
            assertFalse(competitor.hasTrait(Trait.HUNTER));
        }
    }

    @Test
    void competitorsStandOnDistinctFreeCells() {
        Arena arena = new ArenaBuilder(20, 10, new Random(1)).withBorderWalls().withRandomWalls(20).build();
        Character player = playerAt(arena);

        List<Character> competitors = new CompetitorSpawner(factory, new Random(2)).spawn(arena, player);

        Set<Position> positions = new HashSet<>();
        for (Character competitor : competitors) {
            assertTrue(arena.isWalkable(competitor.getPosition()));
            assertNotEquals(player.getPosition(), competitor.getPosition());
            positions.add(competitor.getPosition());
        }
        assertEquals(competitors.size(), positions.size());
    }

    @Test
    void spawnsFewerCompetitorsWhenThereIsNoRoom() {
        Arena arena = new ArenaBuilder(5, 5, new Random(1)).withBorderWalls().withRandomWalls(7).build();
        Character player = playerAt(arena);

        List<Character> competitors = new CompetitorSpawner(factory, new Random(1)).spawn(arena, player);

        assertEquals(1, competitors.size());
    }

    @Test
    void sameSeedPlacesCompetitorsInTheSameCells() {
        Arena arena = new ArenaBuilder(20, 10, new Random(1)).withBorderWalls().build();
        Character player = playerAt(arena);

        List<Character> first = new CompetitorSpawner(factory, new Random(3)).spawn(arena, player);
        List<Character> second = new CompetitorSpawner(factory, new Random(3)).spawn(arena, player);

        for (int i = 0; i < first.size(); i++) {
            assertEquals(first.get(i).getPosition(), second.get(i).getPosition());
        }
    }
}