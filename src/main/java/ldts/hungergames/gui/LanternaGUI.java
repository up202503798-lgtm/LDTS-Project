package ldts.hungergames.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LanternaGUI implements GUI{
    private final Screen screen;
    private final TextGraphics graphics;

    public LanternaGUI(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height))
                .setTerminalEmulatorTitle("Hunger Games")
                .createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        graphics = screen.newTextGraphics();
    }

    @Override
    public int getWidth() {
        return screen.getTerminalSize().getColumns();
    }

    @Override
    public int getHeight() {
        return screen.getTerminalSize().getRows();
    }

    @Override
    public Action getNextAction() throws IOException {
        KeyStroke key = screen.pollInput();
        return key == null ? Action.NONE : toAction(key);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void drawChar(int x, int y, char character, GuiColor color) {
        graphics.setForegroundColor(toLanternaColor(color));
        graphics.setCharacter(x,y, character);
    }

    @Override
    public void drawText(int x, int y, String text, GuiColor color) {
        graphics.setForegroundColor(toLanternaColor(color));
        graphics.putString(x, y, text);
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    static Action toAction(KeyStroke key) {
        return switch(key.getKeyType()){
            case ArrowUp -> Action.UP;
            case ArrowDown -> Action.DOWN;
            case ArrowLeft -> Action.LEFT;
            case ArrowRight -> Action.RIGHT;
            case Enter -> Action.SELECT;
            case Escape, EOF -> Action.QUIT;
            case Character -> fromCharacter(key.getCharacter());
            default -> Action.NONE;
        };
    }

    private static Action fromCharacter(char typed) {
        return switch (Character.toLowerCase(typed)) {
            case 'w' -> Action.UP;
            case 's' -> Action.DOWN;
            case 'a' -> Action.LEFT;
            case 'd' -> Action.RIGHT;
            case 'q' -> Action.ATTACK;
            default -> Action.NONE;
        };
    }

    private static TextColor toLanternaColor(GuiColor color) {
        return switch (color){
            case WHITE ->   TextColor.ANSI.WHITE;
            case RED ->    TextColor.ANSI.RED;
            case GREEN ->    TextColor.ANSI.GREEN;
            case YELLOW ->    TextColor.ANSI.YELLOW;
            case BLUE ->    TextColor.ANSI.BLUE;
            case CYAN ->     TextColor.ANSI.CYAN;
        };
    }
}
