package ldts.hungergames.model.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character("Katniss", 100, Set.of(Trait.HUNTER), 3, new Position(0, 0));
    }

    @Test
    void exposesInitialValues() {
        assertEquals("Katniss", character.getName());
        assertEquals(100, character.getHealth());
        assertEquals(100, character.getMaxHealth());
        assertEquals(3, character.getSupplies());
        assertEquals(new Position(0, 0), character.getPosition());
        assertTrue(character.isAlive());
    }

    @Test
    void takesDamage() {
        character.takeDamage(30);
        assertEquals(70, character.getHealth());
        assertTrue(character.isAlive());
    }

    @Test
    void diesWhenHealthReachesZero() {
        character.takeDamage(100);
        assertEquals(0, character.getHealth());
        assertFalse(character.isAlive());
    }

    @Test
    void healthNeverGoesBelowZero() {
        character.takeDamage(500);
        assertEquals(0, character.getHealth());
    }

    @Test
    void healRestoresHealth() {
        character.takeDamage(40);
        character.heal(10);
        assertEquals(70, character.getHealth());
    }

    @Test
    void healthNeverGoesAboveMax() {
        character.takeDamage(10);
        character.heal(50);
        assertEquals(100, character.getHealth());
    }

    @Test
    void consumesAndAddsSupplies() {
        character.consumeSupplies(2);
        assertEquals(1, character.getSupplies());
        character.addSupplies(4);
        assertEquals(5, character.getSupplies());
    }

    @Test
    void suppliesNeverGoNegative() {
        character.consumeSupplies(10);
        assertEquals(0, character.getSupplies());
    }

    @Test
    void changesPosition() {
        character.setPosition(new Position(4, 7));
        assertEquals(new Position(4, 7), character.getPosition());
    }

    @Test
    void knowsItsTraits() {
        assertTrue(character.hasTrait(Trait.HUNTER));
        assertFalse(character.hasTrait(Trait.FAST));
    }

    @Test
    void traitsCannotBeModifiedFromOutside() {
        assertThrows(UnsupportedOperationException.class, () -> character.getTraits().add(Trait.FAST));
    }

    @Test
    void acceptsCharacterWithoutTraits() {
        Character plain = new Character("Plain", 50, Set.of(), 0, new Position(1, 1));
        assertTrue(plain.getTraits().isEmpty());
        assertFalse(plain.hasTrait(Trait.STRONG));
    }
}