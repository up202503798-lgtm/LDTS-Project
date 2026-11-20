package ldts.hungergames.viewer;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ArenaBuilder;
import ldts.hungergames.model.arena.ArenaType;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class ArenaViewerForestTest {
    private final GUI gui = mock(GUI.class);
    private Arena arena;
    private Character player;

    @BeforeEach
    void setUp() {
        arena = new ArenaBuilder(10, 10, new Random(1)).withBorderWalls().build();
        player = new CharacterFactory().createAll(new Position(5, 5)).get(0);
        player.setPosition(new Position(5, 5));
    }

    @Test
    void forestDrawsTreesInGreen() {
        new ArenaViewer(ArenaType.FOREST).draw(gui, arena, player);

        verify(gui).drawChar(0, 0, 'T', GuiColor.GREEN);
        verify(gui, never()).drawChar(0, 0, '#', GuiColor.WHITE);
    }

    @Test
    void theInitialArenaKeepsItsWhiteWalls() {
        new ArenaViewer().draw(gui, arena, player);

        verify(gui).drawChar(0, 0, '#', GuiColor.WHITE);
        verify(gui, never()).drawChar(0, 0, 'T', GuiColor.GREEN);
    }
}