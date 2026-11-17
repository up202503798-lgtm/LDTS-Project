package ldts.hungergames.model.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CharacterFactory {
    private static final int DEFAULT_HEALTH = 100;
    private static final int STRONG_HEALTH = 130;
    private static final int DEFAULT_SUPPLIES = 3;
    private static final int STRATEGIST_SUPPLIES = 5;

    public Character create(Trait archetype, Position start) {
        return switch (archetype) {
            case STRONG -> build("Atlas", STRONG_HEALTH, DEFAULT_SUPPLIES, archetype, start);
            case FAST -> build("Zéfiro", DEFAULT_HEALTH, DEFAULT_SUPPLIES, archetype, start);
            case STEALTHY -> build("Sombra", DEFAULT_HEALTH, DEFAULT_SUPPLIES, archetype, start);
            case STRATEGIST -> build("Sábio", DEFAULT_HEALTH, STRATEGIST_SUPPLIES, archetype, start);
            case HUNTER -> build("Artemisa", DEFAULT_HEALTH, DEFAULT_SUPPLIES, archetype, start);
            case HEALER -> build("Hera", DEFAULT_HEALTH, DEFAULT_SUPPLIES, archetype, start);
        };
    }

    public List<Character> createAll(Position start) {
        List<Character> characters = new ArrayList<>();
        for (Trait archetype : Trait.values()) {
            characters.add(create(archetype, start));
        }
        return characters;
    }

    private Character build(String name, int health, int supplies, Trait trait, Position start) {
        return new Character(name, health, Set.of(trait), supplies, start);
    }
}