package ldts.hungergames.state;

import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.character.Trait;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ArenaStateTest {
    private GUI gui;
    private Character player;
    private ArenaState state;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        when(gui.getWidth()).thenReturn(20);
        when(gui.getHeight()).thenReturn(10);
        player = new CharacterFactory().create(Trait.HUNTER, new Position(0, 0));
        state = new ArenaState(player, new Random(1));
    }

    @Test
    void buildsTheArenaWithTheScreenSizeAndPlacesThePlayerInTheCenter() {
        state.draw(gui);
        assertEquals(new Position(10, 5), player.getPosition());
        verify(gui).drawChar(10, 5, '@', GuiColor.CYAN);
        verify(gui).drawChar(19, 9, '#', GuiColor.WHITE);
    }

    @Test
    void ignoresInputBeforeTheFirstDraw() {
        state.update(Action.RIGHT, mock(StateMachine.class));
        assertEquals(new Position(0, 0), player.getPosition());
    }
}