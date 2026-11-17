package ldts.hungergames.model.character;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CharacterFactoryTest {
    private final CharacterFactory factory = new CharacterFactory();
    private final Position start = new Position(3, 4);

    @ParameterizedTest
    @EnumSource(Trait.class)
    void createdCharacterHasItsArchetypeTrait(Trait trait) {
        Character character = factory.create(trait, start);
        assertEquals(Set.of(trait), character.getTraits());
        assertEquals(start, character.getPosition());
        assertTrue(character.isAlive());
        assertFalse(character.getName().isBlank());
    }

    @Test
    void strongHasMoreHealth() {
        Character strong = factory.create(Trait.STRONG, start);
        assertEquals(130, strong.getMaxHealth());
        assertEquals(130, strong.getHealth());
    }

    @Test
    void strategistHasMoreSupplies() {
        assertEquals(5, factory.create(Trait.STRATEGIST, start).getSupplies());
    }

    @ParameterizedTest
    @EnumSource(value = Trait.class, names = {"FAST", "STEALTHY", "HUNTER", "HEALER"})
    void otherArchetypesHaveDefaultStats(Trait trait) {
        Character character = factory.create(trait, start);
        assertEquals(100, character.getMaxHealth());
        assertEquals(3, character.getSupplies());
    }

    @Test
    void createAllReturnsOneCharacterPerTrait() {
        List<Character> all = factory.createAll(start);
        assertEquals(6, all.size());

        Set<Trait> traits = new HashSet<>();
        Set<String> names = new HashSet<>();
        for (Character character : all) {
            traits.addAll(character.getTraits());
            names.add(character.getName());
        }
        assertEquals(6, traits.size());
        assertEquals(6, names.size());
    }
}