package ldts.hungergames.viewer;

import ldts.hungergames.gui.GUI;
import ldts.hungergames.model.character.CharacterFactory;
import ldts.hungergames.model.character.Position;
import ldts.hungergames.model.selection.CharacterSelectionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CharacterSelectionViewerTest {
    private GUI gui;
    private CharacterSelectionModel model;
    private final CharacterSelectionViewer viewer = new CharacterSelectionViewer();

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        when(gui.getHeight()).thenReturn(24);
        model = new CharacterSelectionModel(new CharacterFactory().createAll(new Position(0, 0)));
    }

    @Test
    void clearsScreenAndDrawsTitle() {
        viewer.draw(gui, model);
        verify(gui).clear();
        verify(gui).drawText(eq(2), eq(1), contains("ESCOLHE"), any());
    }

    @Test
    void marksTheSelectedCharacter() {
        viewer.draw(gui, model);
        verify(gui).drawText(eq(2), eq(3), contains("> Finnick"), any());
    }

    @Test
    void marksTheNewSelectionAfterMoving() {
        model.next();
        viewer.draw(gui, model);
        verify(gui).drawText(eq(2), eq(4), contains("> Haymitch"), any());
    }

    @Test
    void showsTraitOfSelectedCharacter() {
        viewer.draw(gui, model);
        verify(gui).drawText(eq(2), eq(12), contains("Forte"), any());
    }
}