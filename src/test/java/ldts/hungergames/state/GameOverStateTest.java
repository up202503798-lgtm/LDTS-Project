package ldts.hungergames.state;

import ldts.hungergames.gui.Action;
import ldts.hungergames.gui.GUI;
import ldts.hungergames.gui.GuiColor;
import ldts.hungergames.model.end.EndScreen;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameOverStateTest {
    @Test
    void drawShowsTheTitleOfTheScreen() {
        GUI gui = mock(GUI.class);
        when(gui.getWidth()).thenReturn(80);
        when(gui.getHeight()).thenReturn(24);
        GameOverState state = new GameOverState(EndScreen.gameOver("Morreste."));

        state.draw(gui);

        verify(gui).drawText(anyInt(), anyInt(), eq("GAME OVER"), eq(GuiColor.RED));
    }

    @Test
    void selectGoesBackToTheMenu() {
        StateMachine machine = mock(StateMachine.class);
        GameOverState state = new GameOverState(EndScreen.gameOver("Morreste."));

        state.update(Action.SELECT, machine);

        verify(machine).changeState(any(MenuState.class));
    }
}