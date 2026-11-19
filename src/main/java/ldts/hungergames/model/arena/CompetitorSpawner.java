package ldts.hungergames.model.arena;

import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.character.Trait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CompetitorSpawner {
    private final CharacterFactory factory;
    private final Random random;

    public CompetitorSpawner(CharacterFactory factory, Random random) {
        this.factory = factory;
        this.random = random;
    }

    public List<Character> spawn(Arena arena, Character player) {
        List<Position> free = freePositions(arena, player);
        Collections.shuffle(free, random);

        List<Character> competitors = new ArrayList<>();
        for (Trait trait : Trait.values()) {
            if (player.hasTrait(trait) || competitors.size() >= free.size()) {
                continue;
            }
            competitors.add(factory.create(trait, free.get(competitors.size())));
        }
        return competitors;
    }

    private List<Position> freePositions(Arena arena, Character player) {
        List<Position> free = new ArrayList<>();
        for (int x = 0; x < arena.getWidth(); x++) {
            for (int y = 0; y < arena.getHeight(); y++) {
                Position position = new Position(x, y);
                if (arena.isWalkable(position) && !position.equals(player.getPosition())) {
                    free.add(position);
                }
            }
        }
        return free;
    }
}