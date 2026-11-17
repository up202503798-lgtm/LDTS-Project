package ldts.hungergames.model.selection;

import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharacterSelectionModelTest {
    private List<Character> characters;
    private CharacterSelectionModel model;

    @BeforeEach
    void setUp() {
        characters = new CharacterFactory().createAll(new Position(0, 0));
        model = new CharacterSelectionModel(characters);
    }

    @Test
    void startsOnFirstCharacter() {
        assertEquals(0, model.getSelectedIndex());
        assertSame(characters.get(0), model.getSelected());
    }

    @Test
    void nextMovesForward() {
        model.next();
        assertEquals(1, model.getSelectedIndex());
        assertSame(characters.get(1), model.getSelected());
    }

    @Test
    void nextWrapsAroundToFirst() {
        for (int i = 0; i < characters.size(); i++) {
            model.next();
        }
        assertEquals(0, model.getSelectedIndex());
    }

    @Test
    void previousWrapsAroundToLast() {
        model.previous();
        assertEquals(characters.size() - 1, model.getSelectedIndex());
    }

    @Test
    void previousMovesBack() {
        model.next();
        model.next();
        model.previous();
        assertEquals(1, model.getSelectedIndex());
    }

    @Test
    void rejectsEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> new CharacterSelectionModel(List.of()));
    }
}