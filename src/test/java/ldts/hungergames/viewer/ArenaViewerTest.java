package ldts.hungergames.viewer;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.arena.Arena;
import ldts.hungergames.model.arena.ArenaBuilder;
import ldts.hungergames.model.arena.ItemType;
import ldts.hungergames.model.character.Character;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.character.Trait;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ArenaViewerTest {
    private GUI gui;
    private Arena arena;
    private Character player;
    private final ArenaViewer viewer = new ArenaViewer();

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        arena = new ArenaBuilder(10, 8, new Random(1))
                .withBorderWalls()
                .withItemAt(new Position(6, 4), ItemType.SUPPLY)
                .withItemAt(new Position(4, 4), ItemType.MEDKIT)
                .build();
        player = new CharacterFactory().create(Trait.HUNTER, arena.getStartPosition());
    }

    @Test
    void clearsTheScreen() {
        viewer.draw(gui, arena, player);
        verify(gui).clear();
    }

    @Test
    void drawsTheWalls() {
        viewer.draw(gui, arena, player);
        verify(gui).drawChar(0, 0, '#', GuiColor.WHITE);
        verify(gui).drawChar(9, 7, '#', GuiColor.WHITE);
    }

    @Test
    void drawsTheItems() {
        viewer.draw(gui, arena, player);
        verify(gui).drawChar(6, 4, '*', GuiColor.YELLOW);
        verify(gui).drawChar(4, 4, '+', GuiColor.GREEN);
    }

    @Test
    void drawsThePlayer() {
        viewer.draw(gui, arena, player);
        verify(gui).drawChar(5, 4, '@', GuiColor.CYAN);
    }

    @Test
    void drawsTheStatusLine() {
        viewer.draw(gui, arena, player);
        verify(gui).drawText(eq(2), eq(0), contains("Vida: 100/100"), any());
    }
}