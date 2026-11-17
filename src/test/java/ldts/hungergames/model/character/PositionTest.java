package ldts.hungergames.model.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PositionTest {
    @Test
    void movesInAllDirections() {
        Position p = new Position(5, 5);
        assertEquals(new Position(5, 4), p.up());
        assertEquals(new Position(5, 6), p.down());
        assertEquals(new Position(4, 5), p.left());
        assertEquals(new Position(6, 5), p.right());
    }

    @Test
    void isImmutable() {
        Position p = new Position(1, 1);
        p.up();
        p.left();
        assertEquals(new Position(1, 1), p);
    }

    @Test
    void equalityDependsOnBothCoordinates() {
        assertEquals(new Position(2, 3), new Position(2, 3));
        assertEquals(new Position(2, 3).hashCode(), new Position(2, 3).hashCode());
        assertNotEquals(new Position(2, 3), new Position(3, 2));
    }
}